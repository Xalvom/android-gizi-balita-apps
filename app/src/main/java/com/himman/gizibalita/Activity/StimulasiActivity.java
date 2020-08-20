package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.Model.Stimulasi;
import com.himman.gizibalita.Model.StimulasiResponse;
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
import retrofit2.Retrofit;

public class StimulasiActivity extends AppCompatActivity {
    private TextView stKeterangan, stUmur, stIsi;
    private ApiInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stimulasi);
        getSupportActionBar().setTitle("Stimulasi Balita");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inisialisasi variabel
        stKeterangan = findViewById(R.id.st_Keterangan);
        stUmur = findViewById(R.id.st_Umur);
        stIsi = findViewById(R.id.st_detail);

        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");
        String tglBalita = mIntent.getStringExtra("tglLahir");
        int um = Integer.parseInt(yearsFormat(tglBalita));;
        int idxUm = 0;
        if(um >= 0 && um <= 3 ){
            idxUm = 1;
            stUmur.setText("Untuk Umur 0 -3 Bulan");
        }else if(um > 3 && um <= 6){
            idxUm = 2;
            stUmur.setText("Untuk Umur 3 -6 Bulan");
        }else if(um > 6 && um <= 12){
            idxUm = 3;
            stUmur.setText("Untuk Umur 6 -12 Bulan");
        }else if(um > 12 && um <= 24){
            idxUm = 4;
            stUmur.setText("Untuk Umur 12 -24 Bulan");
        }else if(um > 24 && um <= 36){
            idxUm = 5;
            stUmur.setText("Untuk Umur 24 -36 Bulan");
        }else if(um > 36 && um <= 48){
            idxUm = 6;
            stUmur.setText("Untuk Umur 36 -48 Bulan");
        }else if(um > 48 && um <= 60){
            idxUm = 7;
            stUmur.setText("Untuk Umur 48 -60 Bulan");
        }
        stKeterangan.setText("Balita anda saat ini sudah memasuki usia "+ um +" Bulan, lakukanlah beberapa stimulasi(ransangan) pada balita anda sebagai berikut:");
        String umurString = String.valueOf(idxUm);
        stimulasiView(umurString);



    }
    private void stimulasiView(String umur){
        retrofit2.Call<StimulasiResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getStData(umur);
            call.enqueue(new Callback<StimulasiResponse>() {
                @Override
                public void onResponse(Call<StimulasiResponse> call, Response<StimulasiResponse> response) {
                    if(response.isSuccessful()){
                        List<Stimulasi> stimulasis = response.body().getMData();
                        for(int i = 0 ; i < stimulasis.size(); i++){
                            stIsi.setText(Html.fromHtml(stimulasis.get(i).getIsiStimulasi()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<StimulasiResponse> call, Throwable t) {
                    Toast.makeText(StimulasiActivity.this, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
