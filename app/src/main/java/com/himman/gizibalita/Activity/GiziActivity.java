package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.himman.gizibalita.Adapter.SectionPageAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.Model.BbtbResponse;
import com.himman.gizibalita.Model.BbuResponse;
import com.himman.gizibalita.Model.TbuResponse;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiziActivity extends AppCompatActivity {
    private TextView gUpdate;
    private EditText edtUmur, edtBeratBadan, edtTinggiBadan;
    private Button gSubmit;
    private Spinner gSpinner;
    private Context mContext;
    public static final String SEND_DATA = "SEND_DATA";
    private Balita balita;
    private ApiInterface service;
    private List<String> listSpinner = new ArrayList<String>();
    private List<String> listKelamin = new ArrayList<String>();
    private List<String> listUmur = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gizi);

        getSupportActionBar().setTitle("Cek Gizi");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inisialisasi ID
        edtUmur = findViewById(R.id.gUmur);
        edtBeratBadan = findViewById(R.id.gBeratBadan);
        edtTinggiBadan = findViewById(R.id.gTinggiBadan);
        gUpdate = findViewById(R.id.gUpdate);
        gSubmit = findViewById(R.id.gSubmit);
        gSpinner = findViewById(R.id.gSpinner);
        mContext = this;

        //Inisialisasi Intent
        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");

        //Inisialisasi Simpan Data
        ViewGizi(idBalita);
        gSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectKelamin = listKelamin.get(i);
                String selectUmur = listUmur.get(i);
                //Checked Umur
                String umur = yearsFormat(selectUmur);
                int um = Integer.parseInt(umur);
                int idxUm = 0;
                if(um > 25 ){
                    idxUm = 2;
                }else{
                    idxUm = 1;
                }
                String umurString = String.valueOf(idxUm);
                //Chechked Update
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);
                gSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String umur = selectUmur;
                        String kelamin = selectKelamin;
                        String beratBadan = edtBeratBadan.getText().toString();
                        String tinggiBadan = edtTinggiBadan.getText().toString();
                        String katUmur = umurString;

                        if(beratBadan.isEmpty() | tinggiBadan.isEmpty()){
                            edtBeratBadan.setError("Berat Badan Tidak Boleh Kosong");
                            edtBeratBadan.requestFocus();
                            edtTinggiBadan.setError("Tinggi Badan Tidak Boleh Kosong");
                            edtTinggiBadan.requestFocus();
                            return;
                        }else{
                            addDataBbu(idBalita, kelamin, umur, beratBadan, formattedDate);
                            addDataTbu(idBalita, kelamin, umur, tinggiBadan, formattedDate);
                            addDataBbtb(idBalita, kelamin, tinggiBadan, beratBadan, katUmur, formattedDate);
                            updateData(idBalita, beratBadan, tinggiBadan, formattedDate);
                            startActivity(new Intent(GiziActivity.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                            Toast.makeText(mContext, "Data Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Inisialisasi ViewPager
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(this,getSupportFragmentManager(), idBalita);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionPageAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    public void ViewGizi(String idBalita){
        retrofit2.Call<BalitaResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getBalitaId(idBalita);
            call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if(response.isSuccessful()) {
                        List<Balita> balitas = response.body().getMData();
                        for(int i = 0; i < balitas.size(); i++){
                            String hari = daysFormat(balitas.get(i).getUpdateBalita());
                            int days = Integer.parseInt(hari);
                            String namaBalita = balitas.get(i).getNamaBalita();
                            String kelaminBalita = balitas.get(i).getKelaminBalita();
                            String umurBalita = yearsFormat(balitas.get(i).getTgllahirBalita());
                            String createdAt = balitas.get(i).getCreatedAt();
                            String updateData = balitas.get(i).getUpdateBalita();
                            listSpinner.add(namaBalita);
                            listKelamin.add(kelaminBalita);
                            listUmur.add(umurBalita);
                            if(days > 30){
                                edtUmur.setText(yearsFormat(balitas.get(i).getTgllahirBalita()));
                                edtBeratBadan.setText(null);
                                edtTinggiBadan.setText(null);
                                gUpdate.setText("Sudah lebih dari " + days + " Hari sejak terakhir update tanggal: " + dateString(balitas.get(i).getUpdateBalita()));

                                edtBeratBadan.setEnabled(true);
                                edtTinggiBadan.setEnabled(true);
                                gSubmit.setEnabled(true);
                            }else{
                                if(createdAt.equals(updateData)){
                                    edtUmur.setText(umurBalita);
                                    edtBeratBadan.setText(balitas.get(i).getBeratbadanBalita());
                                    edtTinggiBadan.setText(balitas.get(i).getTinggibadanBalita());
                                    gUpdate.setText(dateString(updateData));
                                    gSubmit.setEnabled(true);
                                }else{
                                    edtUmur.setText(umurBalita);
                                    edtBeratBadan.setText(balitas.get(i).getBeratbadanBalita());
                                    edtTinggiBadan.setText(balitas.get(i).getTinggibadanBalita());
                                    gUpdate.setText(dateString(updateData));
                                    gSubmit.setBackgroundResource(R.color.colorGrey);
                                }
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        gSpinner.setAdapter(adapter);
                    }else{
                        Toast.makeText(GiziActivity.this, "Gagal Ambil Data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BalitaResponse> call, Throwable t) {

                }
            });

        }catch (Exception e){

        }

    }
    private void addDataBbu(String id, String kelamin, String umur, String berat, String update){
        retrofit2.Call<BbuResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiInsertBbu(id, umur, berat, kelamin,update);
            call.enqueue(new Callback<BbuResponse>() {
                @Override
                public void onResponse(Call<BbuResponse> call, Response<BbuResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(mContext, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "Data Gagal Di Tambahakan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BbuResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void addDataTbu(String id, String kelamin, String umur, String tinggi, String update){
        retrofit2.Call<TbuResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiInsertTbu(id, umur, tinggi, kelamin,update);
            call.enqueue(new Callback<TbuResponse>() {
                @Override
                public void onResponse(Call<TbuResponse> call, Response<TbuResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(mContext, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "Data Gagal Di Tambahakan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TbuResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void addDataBbtb(String idBalita, String kelamin, String tinggiBadan, String beratBadan, String katUmur, String updateBbtb){
        retrofit2.Call<BbtbResponse> Call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            Call = service.apiInsertBbtb(idBalita, kelamin, tinggiBadan, beratBadan, katUmur, updateBbtb);
            Call.enqueue(new Callback<BbtbResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BbtbResponse> call, Response<BbtbResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(mContext, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "Data Gagal Di Tambahakan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BbtbResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }
    }
    private void updateData(String idBalita, String beratBadan, String tinggiBadan, String updateData){
        retrofit2.Call<BalitaResponse> Call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            Call = service.apiUpdateGiziBalita(idBalita, beratBadan, tinggiBadan, updateData);
            Call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(mContext, "Data Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "Update Data Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BalitaResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Tidak Bisa Terhubung ke Web Service", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
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

    private String daysFormat(String dateToDays){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToDays;
        try{
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
                days +=now.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if(months < 0){
                years--;
                months+=12;
            }
            int yearss = (years * 12) + (months+1);
            int umur = 0;
            if(yearss == 0){
                umur = days;
            }else{
                umur = (yearss*30) + days;
            }
            dateInString = String.valueOf(umur);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return  dateInString;
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
    private String dateString(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
            dateInString = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }
}
