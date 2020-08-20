package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBalitaActivity extends AppCompatActivity {
    private static final String TAG = EditBalitaActivity.class.getSimpleName();
    private EditText edtNama;
    private EditText edtOrtu;
    private EditText edtAlamat;
    private EditText edtRt;
    private EditText edtRw;
    private RadioButton mKelaminL;
    private RadioButton mKelaminP;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private Button mUpdate;
    private ApiInterface service;
    private SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_balita);
        edtNama = findViewById(R.id.ebNamaBalita);
        edtOrtu = findViewById(R.id.ebOrtuBalita);
        edtAlamat = findViewById(R.id.ebAlamatBalita);
        edtRt = findViewById(R.id.ebRtBalita);
        edtRw = findViewById(R.id.ebRwBalita);
        mKelaminL = findViewById(R.id.ebLaki);
        mKelaminP = findViewById(R.id.ebPerempuan);
        mDisplayDate = findViewById(R.id.ebDate);
        mUpdate = findViewById(R.id.btn_update);
        sharedPrefManager = new SharedPrefManager(this);
        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");
        initBalita(idBalita);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditBalitaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy/mm/dd: " + year + "-" + month + "-" + day);
                String date = year + "-" + month + "-" + day;
                mDisplayDate.setText(date);
            }
        };
    }
    private void updateData(String idBalita, String nama, String namaOrtu, String rtBalita, String rwBalita, String Alamat, String Jenis, String Tgl, String idUser){
        retrofit2.Call<BalitaResponse> Call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            Call = service.apiUpdateBalita(idBalita, nama, namaOrtu, rtBalita, rwBalita, Alamat, Tgl, Jenis, idUser);
            Call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if(response.isSuccessful()){
                        startActivity(new Intent(EditBalitaActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(EditBalitaActivity.this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditBalitaActivity.this, "Update Data Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BalitaResponse> call, Throwable t) {
                    Toast.makeText(EditBalitaActivity.this, "Tidak Bisa Terhubung ke Web Service", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initListener(String idBalita){
        String namaBalita = edtNama.getText().toString();
        String namaOrtu = edtOrtu.getText().toString();
        String rtBalita = edtRt.getText().toString();
        String rwBalita = edtRw.getText().toString();
        String alamatBalita = edtAlamat.getText().toString();
        String tglBalita = mDisplayDate.getText().toString();
        int tglCheck = Integer.parseInt(yearsFormat(tglBalita));
        String idUser = sharedPrefManager.getSpIduser();
        String jenisKelamin = "";
        if (mKelaminL.isChecked()) {
            jenisKelamin = "L";
        } else if (mKelaminP.isChecked()) {
            jenisKelamin = "P";
        }
        if (namaBalita.isEmpty() | alamatBalita.isEmpty() | namaOrtu.isEmpty() | rtBalita.isEmpty() | rwBalita.isEmpty()) {
            edtNama.setError("Nama Tidak Boleh Kosong");
            edtOrtu.setError("Nama Ortu Tidak Boleh Kosong");
            edtAlamat.setError("Alamat Tidak Boleh Kosong");
            edtRt.setError("RT Tidak Boleh Kosong");
            edtRw.setError("RW Tidak Boleh Kosong");
        } else if(tglCheck > 60){
            Toast.makeText(EditBalitaActivity.this, "Umur Minimal di Bawah 5 Tahun", Toast.LENGTH_SHORT).show();
        } else {
            updateData(idBalita, namaBalita, namaOrtu, rtBalita, rwBalita, alamatBalita, jenisKelamin, tglBalita, idUser);
        }
    }
    private void initBalita(String idBalita){
        retrofit2.Call<BalitaResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getBalitaId(idBalita);
            call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if(response.isSuccessful()){
                        List<Balita> list = response.body().getMData();
                        for (int i = 0; i < list.size(); i++) {
                            getSupportActionBar().setTitle("Edit Balita: "+list.get(i).getNamaBalita());
                            getSupportActionBar().setDisplayShowHomeEnabled(true);
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                            String idBalita = list.get(i).getIdBalita();
                            String kelaminBalita = list.get(i).getKelaminBalita();
                            edtNama.setText(list.get(i).getNamaBalita());
                            edtOrtu.setText(list.get(i).getNamaOrangtua());
                            edtAlamat.setText(list.get(i).getAlamatBalita());
                            edtRt.setText(list.get(i).getRtBalita());
                            edtRw.setText(list.get(i).getRwBalita());
                            mDisplayDate.setText(list.get(i).getTgllahirBalita());
                            //Kelamin
                            if(kelaminBalita.equals("L")){
                                mKelaminL.setChecked(true);
                            }else{
                                mKelaminP.setChecked(true);
                            }
                            mUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    initListener(idBalita);
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<BalitaResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private String yearsFormat(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Calendar now =  Calendar.getInstance();
            Date date = format.parse(dateInString);
            CharSequence mo = DateFormat.format("MM", date);
            CharSequence ye = DateFormat.format("yyyy", date);
            CharSequence da = DateFormat.format("dd", date);
            int year = Integer.parseInt(ye.toString());
            int mMonth = Integer.parseInt(mo.toString());
            int mDay = Integer.parseInt(da.toString());
            int years = now.get(Calendar.YEAR) - year;
            int months = now.get(Calendar.DAY_OF_MONTH) - mMonth;
            int days = now.get(Calendar.DAY_OF_MONTH) - mDay;
            if(days < 0){
                months --;
                days+=now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if(months < 0){
                years--;
                months+=12;
            }
            int umur = (years * 12) + (months+1);
            int umurs = 0;
            if(umur < 0){
                umurs = 0;
            }else{
                umurs = umur;
            }
            dateInString = String.valueOf(umurs);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }

}
