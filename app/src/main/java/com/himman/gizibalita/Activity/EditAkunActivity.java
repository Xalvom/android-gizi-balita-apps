package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.User;
import com.himman.gizibalita.Model.UserResponse;
import com.himman.gizibalita.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity {
    private static final String TAG = EditAkunActivity.class.getSimpleName();
    private TextView namaUser;
    private TextView emailUser;
    private TextView statusUser;
    private TextView katUser;
    private EditText newPassword;
    private EditText reNewPassword;
    private Button mSubmit;
    private Context mContext;
    private ApiInterface service;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);
        getSupportActionBar().setTitle("Detail Akun");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        namaUser = findViewById(R.id.txtNamaUser);
        emailUser = findViewById(R.id.txtEmailUser);
        statusUser = findViewById(R.id.txtStatusUser);
        katUser = findViewById(R.id.txtKatUser);
        newPassword = findViewById(R.id.txtNewPassword);
        reNewPassword = findViewById(R.id.txtReNewPassword);
        mSubmit = findViewById(R.id.txtPerbaharui);
        sharedPrefManager = new SharedPrefManager(this);
        String idUser = sharedPrefManager.getSpIduser();
        initUser(idUser);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPasswd = newPassword.getText().toString();
                String reNewPasswd = reNewPassword.getText().toString();

                if(newPasswd.equals(reNewPasswd) && newPasswd.length() >= 6 && reNewPasswd.length() >= 6){
                    passwdUser(idUser, newPasswd);
                }else if(!newPasswd.equals(reNewPasswd)){
                    newPassword.setError("Password Tidak Sama");
                    reNewPassword.setError("Re-Password Tidak Sama");
                    newPassword.requestFocus();
                    reNewPassword.requestFocus();
                }else if(!newPasswd.isEmpty() && !reNewPasswd.isEmpty() && newPasswd.length() <= 6 | reNewPasswd.length() <= 6){
                    newPassword.setError("Password Minimal 6 Karakter");
                    reNewPassword.setError("Re-Password Minimal 6 Karakter");
                    newPassword.requestFocus();
                    reNewPassword.requestFocus();
                }else if(newPasswd.isEmpty() | reNewPasswd.isEmpty()){
                    newPassword.setError("Password Tidak Boleh Kosong");
                    reNewPassword.setError("Re-Password Tidak Boleh Kosong");
                    newPassword.requestFocus();
                    reNewPassword.requestFocus();
                }
            }
        });


    }
    private void initUser(String idUser){
        retrofit2.Call<UserResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getListUser(idUser);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        List<User> list = response.body().getMData();
                        for (int i = 0; i < list.size(); i++) {
                            namaUser.setText(list.get(i).getNamaUser());
                            emailUser.setText(list.get(i).getEmailUser());
                            statusUser.setText(statusU(list.get(i).getIsAktif()));
                            katUser.setText(KatU(list.get(i).getLevelUser()));
                        }
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
    private void passwdUser(String idUser, String passwordBaru){
        retrofit2.Call<UserResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiUpdatePasswd(idUser, passwordBaru);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(response.isSuccessful()){
                        startActivity(new Intent(EditAkunActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(mContext, "Password Berhasil Di Perbaharui", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
    private String statusU(String U){
        if(U.equals("1")){
            U = "Aktif";
        }else{
            U = "Tidak Aktif";
        }
        return U;
    }
    private String KatU(String U){
        if(U.equals("2")){
            U = "Member";
        }else{
            U = "Administrator";
        }
        return U;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
