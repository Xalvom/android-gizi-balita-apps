package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GiziBalitaActivity extends AppCompatActivity {
    TextView tvNamaBalita, tvNamaOrtu, tvAlamatBalita, tvKelamin, tvTanggalLahir;
    CardView CardGov, CardGizi, CardImun, CardArtikel, CardDetail, CardKMS, CardStimulasi;
    ImageButton mEdit;
    private Balita balita;
    private ApiInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gizi_balita);

        //Inisialisasi
        tvNamaBalita = findViewById(R.id.gbNamaBalita);
        tvNamaOrtu = findViewById(R.id.gbNamaOrtu);
        tvAlamatBalita = findViewById(R.id.gbAlamatBalita);
        tvKelamin = findViewById(R.id.gbKelaminBalita);
        tvTanggalLahir = findViewById(R.id.gbTglBalita);
        CardGov = findViewById(R.id.sh_perkembangan);
        CardGizi = findViewById(R.id.sh_gizi);
        CardImun = findViewById(R.id.sh_imunisasi);
//        CardArtikel = findViewById(R.id.sh_artikel);
        CardDetail = findViewById(R.id.sh_detail);
        CardKMS = findViewById(R.id.sh_kms);
        CardStimulasi = findViewById(R.id.sh_stimulasi);

        //Intent getExtra
        Intent mIntent = getIntent();
        String namaBalita = mIntent.getStringExtra("namaBalita");
        String idBalita = mIntent.getStringExtra("idBalita");
        String alamatBalita = mIntent.getStringExtra("alamatBalita");
        String rtBalita = mIntent.getStringExtra("rtBalita");
        String rwBalita = mIntent.getStringExtra("rwBalita");
        String kelaminBalita = mIntent.getStringExtra("kelaminBalita");
        String tglLahir = mIntent.getStringExtra("tglLahir");
        String namaOrtu = mIntent.getStringExtra("namaOrtu");
        tvNamaBalita.setText(namaBalita);
        tvNamaOrtu.setText(namaOrtu);
        tvAlamatBalita.setText("RT: " + rtBalita + " RW: " + rwBalita +" Dusun: "+ alamatBalita);
        tvKelamin.setText(kelamin(kelaminBalita));
        tvTanggalLahir.setText(yearsFormat(tglLahir));
        getSupportActionBar().setTitle(namaBalita);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CardGov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CekGovActivity.class);
                i.putExtra("idBalita", idBalita);
                startActivity(i);
            }
        });
        CardGizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), GiziActivity.class);
                i.putExtra("idBalita", idBalita);
                startActivity(i);
            }
        });
        CardImun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ImunisasiActivity.class);
                i.putExtra("idBalita", idBalita);
                i.putExtra("namaBalita", namaBalita);
                i.putExtra("tglLahir", tglLahir);
                startActivity(i);
            }
        });
//        CardArtikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), ArtikelActivity.class);
//                startActivity(i);
//            }
//        });
        CardDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ProfilBalitaActivity.class);
                i.putExtra("idBalita", idBalita);
                startActivity(i);
            }
        });
        CardKMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), KMSActivity.class);
                i.putExtra("idBalita", idBalita);
                i.putExtra("kelaminBalita", kelaminBalita);
                startActivity(i);
            }
        });
        CardStimulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StimulasiActivity.class);
                i.putExtra("idBalita", idBalita);
                i.putExtra("tglLahir", tglLahir);
                startActivity(i);
            }
        });
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
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
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
    @Override
    public void onResume() {
        super.onResume();
    }
}
