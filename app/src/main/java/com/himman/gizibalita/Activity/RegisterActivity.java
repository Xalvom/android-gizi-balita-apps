package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.UserResponse;
import com.himman.gizibalita.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private TextView loginLink;
    private TextInputLayout mNama;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button btnSignup;
    private ApiInterface service;
    private SharedPrefManager sharedPrefManager;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Toast toast;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginLink = findViewById(R.id.login_caption);
        mNama = findViewById(R.id.rgNamaUser);
        mEmail = findViewById(R.id.rgEmail);
        mPassword = findViewById(R.id.rgPassword);
        btnSignup = findViewById(R.id.btnSignup);
        sharedPrefManager = new SharedPrefManager(this);
        progressBar = findViewById(R.id.progressBar);

        //Cek apakah user sudah pernah login apa belum
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        getSupportActionBar().hide();
        //OnClick btnSignup
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading(true);
                String namaUser = mNama.getEditText().getText().toString().trim();
                String emailUser = mEmail.getEditText().getText().toString().trim();
                String passwordUser = mPassword.getEditText().getText().toString().trim();
                String levelUser = "2";
                String isAktif = "1";
                String noHp = "-";

                if(emailUser.isEmpty() | passwordUser.isEmpty() | namaUser.isEmpty()){
                    mEmail.setError("Email tidak Boleh Kosong");
                    mPassword.setError("Password Tidak Boleh Kosong");
                    mNama.setError("Nama Lengkap Tidak Boleh Kosong");
                    mEmail.requestFocus();
                    mPassword.requestFocus();
                    mNama.requestFocus();
                    return;
                }else if(!emailUser.matches(emailPattern)){
                    mEmail.setError("Email Tidak Valid");
                    mEmail.requestFocus();
                    return;
                }else if(passwordUser.length() < 6) {
                    mPassword.setError("Password minimal 6 karakter");
                    mPassword.requestFocus();
                    return;
                }else{
                    userRegister(namaUser,noHp , emailUser, passwordUser, levelUser, isAktif);
                }
            }
        });

        //OnClick loginTextView
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading(true);
                Toast.makeText(RegisterActivity.this, "Login Akun Gizi Balita", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void userRegister(String nama, String noHp, String email, String passwd, String levelUser, String isAktif){
        retrofit2.Call<UserResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiRegister(nama, noHp, email, passwd, levelUser, isAktif);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    showLoading(false);
                    if(response.isSuccessful()){
                        mNama.getEditText().setText(null);
                        mEmail.getEditText().setText(null);
                        mPassword.getEditText().setText(null);
                        toast = Toast.makeText(getApplicationContext(), "Akun Gizi Balita berhasil di buat,\nSilahkan Cek Email Anda"+nama, Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 10000);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Daftar Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Koneksi Pada Api Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showLoading(Boolean state){
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
