package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
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

public class ProfilBalitaActivity extends AppCompatActivity {

    private TextView namaBalita, namaOrtu, tglLahir, umurBalita, jenisKelamin, beratBadan, tinggiBadan, alamatBalita, updateData;
    private ApiInterface service;
    private Button bDelete, bUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_balita);

        initListiner();

        //Get Data idBalita
        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");

        initBalita(idBalita);


    }

    private void initListiner(){
        namaBalita = findViewById(R.id.pbNamaBalita);
        namaOrtu = findViewById(R.id.pbNamaOrtu);
        tglLahir = findViewById(R.id.pbTglLahir);
        umurBalita = findViewById(R.id.pbUmur);
        jenisKelamin = findViewById(R.id.pbJenisKelamin);
        beratBadan = findViewById(R.id.pbBeratBadan);
        tinggiBadan = findViewById(R.id.pbTinggiBadan);
        alamatBalita = findViewById(R.id.pbAlamat);
        updateData = findViewById(R.id.pbUpdate);
        bUpdate = findViewById(R.id.pbPerbaharui);
        bDelete = findViewById(R.id.pbDelete);
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
                            getSupportActionBar().setTitle("Profil Balita: "+list.get(i).getNamaBalita());
                            getSupportActionBar().setDisplayShowHomeEnabled(true);
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                            String idBalita = list.get(i).getIdBalita();
                            namaBalita.setText(list.get(i).getNamaBalita());
                            namaOrtu.setText(list.get(i).getNamaOrangtua());
                            tglLahir.setText(yearsFormat(list.get(i).getTgllahirBalita()));
                            umurBalita.setText(stringToMonth(list.get(i).getTgllahirBalita()) + " Bulan");
                            alamatBalita.setText("RT: " + list.get(i).getRtBalita() + " RW: " + list.get(i).getRwBalita() + " Dusun: " + list.get(i).getAlamatBalita());
                            beratBadan.setText(list.get(i).getBeratbadanBalita() + " /Kg");
                            tinggiBadan.setText(list.get(i).getTinggibadanBalita() + " /cm");
                            jenisKelamin.setText(kelamin(list.get(i).getKelaminBalita()));
                            updateData.setText(yearsFormat(list.get(i).getUpdateBalita()));

                            bUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(getApplicationContext(), EditBalitaActivity.class);
                                    i.putExtra("idBalita", idBalita);
                                    startActivity(i);
                                }
                            });
                            bDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProfilBalitaActivity.this);
                                    alertDialogBuilder.setMessage("Apakah anda yakin ingin hapus data?");
                                    alertDialogBuilder
                                            .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    doDelete(idBalita);
                                                }
                                            })
                                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            });
                                    alertDialogBuilder.create().show();
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
    private void doDelete(String idBalita){
        retrofit2.Call<BalitaResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiDeleteBalita(idBalita);
            call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if(response.isSuccessful()){
                        startActivity(new Intent(ProfilBalitaActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(ProfilBalitaActivity.this, "Data Balita Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    }else{
                        startActivity(new Intent(ProfilBalitaActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(ProfilBalitaActivity.this, "Data Balita Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BalitaResponse> call, Throwable t) {
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String yearsFormat(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id"));
            dateInString = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }
    private String stringToMonth(String dateToyears){
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
            int months = now.get(Calendar.MONTH) - mMonth;
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
    private String kelamin(String changeKelamin) {
        if (changeKelamin.equals("L")) {
            changeKelamin = "Laki-Laki";
        } else {
            changeKelamin = "Perempuan";
        }
        return changeKelamin;
    }
}
