package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.himman.gizibalita.Adapter.SectionPageAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBalitaActivity extends AppCompatActivity {

    TextView tvNamaBalita, tvAlamatBalita, tvKT;
    ImageButton mEdit;
    public static final String SEND_DATA = "SEND_DATA";
    private Balita balita;
    private ApiInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_balita);

        tvNamaBalita = findViewById(R.id.dtNamaBalita);
        tvAlamatBalita = findViewById(R.id.dtAlamatBalita);
        tvKT = findViewById(R.id.dtKT);
        mEdit = findViewById(R.id.editButton);
        Intent mIntent = getIntent();
        String namaBalita = mIntent.getStringExtra("namaBalita");
        tvNamaBalita.setText(namaBalita);
        String idBalita = mIntent.getStringExtra("idBalita");
        String alamatBalita = mIntent.getStringExtra("alamatBalita");
        String kelaminBalita = mIntent.getStringExtra("kelaminBalita");
        String tglLahir = mIntent.getStringExtra("tglLahir");
        tvAlamatBalita.setText(alamatBalita);
        tvKT.setText(kelamin(kelaminBalita) + " | " + yearsFormat(tglLahir));

        getSupportActionBar().setTitle(namaBalita);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditBalitaActivity.class);
                i.putExtra("namaBalita", namaBalita);
                i.putExtra("alamatBalita", alamatBalita);
                i.putExtra("kelaminBalita", kelaminBalita);
                i.putExtra("tglBalita", tglLahir);
                i.putExtra("idBalita", idBalita);
                startActivity(i);
            }
        });
//        getDetailBalita(idBalita);
//        getDetailBalita("1");
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(this,getSupportFragmentManager(), idBalita);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionPageAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
    private void doDelete(String idBalita){
        retrofit2.Call<BalitaResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiDeleteBalita(idBalita);
            call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    Intent i = new Intent(DetailBalitaActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(DetailBalitaActivity.this, "Data Balita Sudah Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<BalitaResponse> call, Throwable t) {

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
    private String yearsFormat(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            dateInString = df.format(date);
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
