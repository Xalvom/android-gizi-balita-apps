package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.himman.gizibalita.Model.Artikel;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailArtikelActivity extends AppCompatActivity {
    private final static String KIRIM = "KIRIM";

    private TextView jArtikel;
    private TextView iArtikel;
    private TextView tArtikel;
    private TextView aArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        initListiner();
        Artikel artikel = getIntent().getParcelableExtra(KIRIM);
        showDetail(artikel);
    }
    private void initListiner(){
        jArtikel = findViewById(R.id.dta_title);
        iArtikel = findViewById(R.id.dta_detail);
        tArtikel = findViewById(R.id.dta_release);
        aArtikel = findViewById(R.id.dta_author);
    }
    private void showDetail(Artikel artikel){
        getSupportActionBar().setTitle(artikel.getJudulArtikel());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jArtikel.setText(artikel.getJudulArtikel());
        iArtikel.setText(Html.fromHtml(artikel.getIsiArtikel()));
        tArtikel.setText(yearsFormat(artikel.getCreatedAt()));
        aArtikel.setText(artikel.getNamaUser());
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
