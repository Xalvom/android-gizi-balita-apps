package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.User;
import com.himman.gizibalita.Model.UserResponse;
import com.himman.gizibalita.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private TextView registerLink;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button btnLogin;
    private ApiInterface service;
    private SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPassword);
        registerLink = findViewById(R.id.register_caption);
        btnLogin = findViewById(R.id.btnLogin);
        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        getSupportActionBar().hide();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();

                if(email.isEmpty() | password.isEmpty()){
                    mEmail.setError("No Hp Tidak Boleh Kosong");
                    mPassword.setError("Password Tidak Boleh Kosong");
                    mEmail.requestFocus();
                    return;
                } else if(email.length() <= 12 && email.length() >= 13){
                    mEmail.setError("No Hp Tidak Valid");
                    mEmail.requestFocus();
                    return;
                } else{
                    userLogin(email, password);
                }
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Buat Akun", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void userLogin(String email, String password){
        retrofit2.Call<UserResponse> call;
        try {
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiLogin(email, password);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse userResponse = response.body();
                    if(response.isSuccessful()){
                        List<User> listUser;
                        listUser = response.body().getMData();
                        for (int i = 0; i < listUser.size(); i++) {
                            String idUser = listUser.get(i).getIdUser();
                            String noHpUser = listUser.get(i).getNoHpUser();
                            String namaUser = listUser.get(i).getNamaUser();
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, namaUser);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_IDUSER, idUser);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, noHpUser);
                        }
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(LoginActivity.this, userResponse.getMessage() + "\nSelamat Datang : " +listUser.get(0).getNamaUser(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
