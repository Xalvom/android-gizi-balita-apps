package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Imun;
import com.himman.gizibalita.Model.ImunResponse;
import com.himman.gizibalita.R;

import java.net.IDN;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImunisasiActivity extends AppCompatActivity {
    private TextView jHB0, dHB0, tHB0;
    private TextView jBCG, dBCG, tBCG;
    private TextView jPolio1, dPolio1, tPolio1;
    private TextView jDPT1, dDPT1, tDPT1;
    private TextView jPolio2, dPolio2, tPolio2;
    private TextView jDPT2, dDPT2, tDPT2;
    private TextView jPolio3, dPolio3, tPolio3;
    private TextView jDPT3, dDPT3, tDPT3;
    private TextView jPolio4, dPolio4, tPolio4;
    private TextView jIPV, dIPV, tIPV;
    private TextView jCampak, dCampak, tCampak;
    private TextView jDPTLanjut, dDPTLanjut, tDPTLanjut;
    private TextView jCampakLanjut, dCampakLanjut, tCampakLanjut;
    private CheckBox i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13;
    private CardView HB0, BCG, Polio1, DPT1, Polio2, DPT2, Polio3, DPT3, Polio4, IPV, Campak, DPTLanjut, CampakLanjut;
    private ApiInterface service;
    private ProgressBar progressBar;
    private Button iSubmit;
    private TextView lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imunisasi);
        //Title NavBar
        getSupportActionBar().setTitle("Jadwal Imunisasi Balita");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Init Variabel
        initVariabel();

        //Intent getExtra
        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");
//        String namaBalita = mIntent.getStringExtra("namaBalita");
//        String tglLahir = mIntent.getStringExtra("tglLahir");
//
//        //Umur as Days
//        String umurAsDays = daysFormat(tglLahir);
//        int umurAsDay = Integer.parseInt(umurAsDays);
//
//        hasilImun(umurAsDay, namaBalita, tglLahir);
        showLoading(false);
        viewHasil(idBalita);

    }
    public void initVariabel(){
        //HB0
        HB0 = findViewById(R.id.iHB0);
        jHB0 = findViewById(R.id.iJHBO);
        dHB0 = findViewById(R.id.iDHB0);
        tHB0 = findViewById(R.id.iTHB0);
        i1 = findViewById(R.id.cbHB0);

        //BCG
        BCG = findViewById(R.id.iBCG);
        jBCG = findViewById(R.id.iJBCG);
        dBCG = findViewById(R.id.iDBCG);
        tBCG = findViewById(R.id.iTBCG);
        i2 = findViewById(R.id.cbBCG);

        //Polio1
        Polio1 = findViewById(R.id.iPolio1);
        jPolio1 = findViewById(R.id.iJPolio1);
        dPolio1 = findViewById(R.id.iDPolio1);
        tPolio1 = findViewById(R.id.iTPolio1);
        i3 = findViewById(R.id.cbPolio1);

        //DPT1
        DPT1 = findViewById(R.id.iDPT1);
        jDPT1 = findViewById(R.id.iJDPT1);
        dDPT1 = findViewById(R.id.iDDPT1);
        tDPT1 = findViewById(R.id.iTDPT1);
        i4 = findViewById(R.id.cbDPT1);

        //Polio2
        Polio2 = findViewById(R.id.iPolio2);
        jPolio2 = findViewById(R.id.iJPolio2);
        dPolio2 = findViewById(R.id.iDPolio2);
        tPolio2 = findViewById(R.id.iTPolio2);
        i5 = findViewById(R.id.cbPolio2);

        //DPT2
        DPT2 = findViewById(R.id.iDPT2);
        jDPT2 = findViewById(R.id.iJDPT2);
        dDPT2 = findViewById(R.id.iDDPT2);
        tDPT2 = findViewById(R.id.iTDPT2);
        i6 = findViewById(R.id.cbDPT2);

        //Polio3
        Polio3 = findViewById(R.id.iPolio3);
        jPolio3 = findViewById(R.id.iJPolio3);
        dPolio3 = findViewById(R.id.iDPolio3);
        tPolio3 = findViewById(R.id.iTPolio3);
        i7 = findViewById(R.id.cbPolio3);

        //DPT3
        DPT3 = findViewById(R.id.iDPT3);
        jDPT3 = findViewById(R.id.iJDPT3);
        dDPT3 = findViewById(R.id.iDDPT3);
        tDPT3 = findViewById(R.id.iTDPT3);
        i8 = findViewById(R.id.cbDPT3);

        //Polio4
        Polio4 = findViewById(R.id.iPolio4);
        jPolio4 = findViewById(R.id.iJPolio4);
        dPolio4 = findViewById(R.id.iDPolio4);
        tPolio4 = findViewById(R.id.iTPolio4);
        i9 = findViewById(R.id.cbPolio4);

        //IPV
        IPV = findViewById(R.id.iIPV);
        jIPV = findViewById(R.id.iJIPV);
        dIPV = findViewById(R.id.iDIPV);
        tIPV = findViewById(R.id.iTIPV);
        i10 = findViewById(R.id.cbIPV);

        //Campak
        Campak = findViewById(R.id.iCampak);
        jCampak = findViewById(R.id.iJCampak);
        dCampak = findViewById(R.id.iDCampak);
        tCampak = findViewById(R.id.iTCampak);
        i11 = findViewById(R.id.cbCampak);

        //DPTLanjut
        DPTLanjut = findViewById(R.id.iDPTLanjut);
        jDPTLanjut = findViewById(R.id.iJDPTLanjut);
        dDPTLanjut = findViewById(R.id.iDDPTLanjut);
        tDPTLanjut = findViewById(R.id.iTDPTLanjut);
        i12 = findViewById(R.id.cbDPTLanjut);

        //CampakLanjut
        CampakLanjut = findViewById(R.id.iCampakLanjut);
        jCampakLanjut = findViewById(R.id.iJCampakLanjut);
        dCampakLanjut = findViewById(R.id.iDCampakLanjut);
        tCampakLanjut = findViewById(R.id.iTCampakLanjut);
        i13 = findViewById(R.id.cbCampakLanjut);

        //Other
        progressBar = findViewById(R.id.progressBar);
        iSubmit = findViewById(R.id.imunSubmit);
        lastUpdate = findViewById(R.id.lastUpdate);

    }
    public void initCheckedUpdate(int value, String idImun){
        String hb0, bgc, polio1, dpt1, polio2, dpt2, polio3, dpt3, polio4, ipv, campak, dptLanjut, campakLanjut;
        //Intent getExtra
        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");
        //Chechked Update
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        if(value == 0){
            if(i1.isChecked()){
                hb0 = "1";
            }else{
                hb0 = "0";
            }
            if(i2.isChecked()){
                bgc = "1";
            }else{
                bgc = "0";
            }
            if(i3.isChecked()){
                polio1 = "1";
            }else{
                polio1 = "0";
            }
            if(i4.isChecked()){
                dpt1 = "1";
            }else{
                dpt1 = "0";
            }
            if(i5.isChecked()){
                polio2 = "1";
            }else{
                polio2 = "0";
            }
            if(i6.isChecked()){
                dpt2 = "1";
            }else{
                dpt2 = "0";
            }
            if(i7.isChecked()){
                polio3 = "1";
            }else{
                polio3 = "0";
            }
            if(i8.isChecked()){
                dpt3 = "1";
            }else{
                dpt3 = "0";
            }
            if(i9.isChecked()){
                polio4 = "1";
            }else{
                polio4 = "0";
            }
            if(i10.isChecked()){
                ipv = "1";
            }else{
                ipv = "0";
            }
            if(i11.isChecked()){
                campak = "1";
            }else{
                campak = "0";
            }
            if(i12.isChecked()){
                dptLanjut = "1";
            }else{
                dptLanjut = "0";
            }
            if(i13.isChecked()){
                campakLanjut = "1";
            }else{
                campakLanjut = "0";
            }
            insertImun(hb0, bgc, polio1, dpt1,polio2, dpt2, polio3, dpt3, polio4, ipv, campak, dptLanjut, campakLanjut, formattedDate, idBalita);
        }else if(value == 1){
            if(i1.isChecked()){
                hb0 = "1";
            }else{
                hb0 = "0";
            }
            if(i2.isChecked()){
                bgc = "1";
            }else{
                bgc = "0";
            }
            if(i3.isChecked()){
                polio1 = "1";
            }else{
                polio1 = "0";
            }
            if(i4.isChecked()){
                dpt1 = "1";
            }else{
                dpt1 = "0";
            }
            if(i5.isChecked()){
                polio2 = "1";
            }else{
                polio2 = "0";
            }
            if(i6.isChecked()){
                dpt2 = "1";
            }else{
                dpt2 = "0";
            }
            if(i7.isChecked()){
                polio3 = "1";
            }else{
                polio3 = "0";
            }
            if(i8.isChecked()){
                dpt3 = "1";
            }else{
                dpt3 = "0";
            }
            if(i9.isChecked()){
                polio4 = "1";
            }else{
                polio4 = "0";
            }
            if(i10.isChecked()){
                ipv = "1";
            }else{
                ipv = "0";
            }
            if(i11.isChecked()){
                campak = "1";
            }else{
                campak = "0";
            }
            if(i12.isChecked()){
                dptLanjut = "1";
            }else{
                dptLanjut = "0";
            }
            if(i13.isChecked()){
                campakLanjut = "1";
            }else{
                campakLanjut = "0";
            }
            updateImun(hb0, bgc, polio1, dpt1,polio2, dpt2, polio3, dpt3, polio4, ipv, campak, dptLanjut, campakLanjut, formattedDate, idBalita, idImun);
        }
    }
    public void hasilImun(int umurAsDay, String namaBalita, String tglLahir){
        showLoading(false);
        if(umurAsDay < 15){
            String value = "";
            jHB0.setText("Imunisasi HB0");
            if(!i1.isChecked()){
                value = noValue(0, namaBalita, "HB0");
            }else{
                value = noValue(1, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(value));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //Gone
            BCG.setVisibility(View.GONE);
            Polio1.setVisibility(View.GONE);
            DPT1.setVisibility(View.GONE);
            Polio2.setVisibility(View.GONE);
            DPT2.setVisibility(View.GONE);
            Polio3.setVisibility(View.GONE);
            DPT3.setVisibility(View.GONE);
            Polio4.setVisibility(View.GONE);
            IPV.setVisibility(View.GONE);
            Campak.setVisibility(View.GONE);
            DPTLanjut.setVisibility(View.GONE);
            CampakLanjut.setVisibility(View.GONE);
        }else if(umurAsDay >  15 && umurAsDay < 59){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Gone
            DPT1.setVisibility(View.GONE);
            Polio2.setVisibility(View.GONE);
            DPT2.setVisibility(View.GONE);
            Polio3.setVisibility(View.GONE);
            DPT3.setVisibility(View.GONE);
            Polio4.setVisibility(View.GONE);
            IPV.setVisibility(View.GONE);
            Campak.setVisibility(View.GONE);
            DPTLanjut.setVisibility(View.GONE);
            CampakLanjut.setVisibility(View.GONE);
        }else if(umurAsDay > 45 && umurAsDay < 89){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //DPT1
            String vDPT1 = "";
            jDPT1.setText("Imunisasi DPT-HB-Hib 1");
            if(i4.isChecked()){
                vDPT1 = noValue(1, namaBalita, "DPT-HB-Hib 1");
            }else{
                vDPT1 = noValue(0, namaBalita, "DPT-HB-Hib 1");
            }
            dDPT1.setText(Html.fromHtml(vDPT1));
            tDPT1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Polio2
            String vPolio2 = "";
            jPolio2.setText("Imunisasi Polio 2");
            if(i5.isChecked()){
                vPolio2 = noValue(1, namaBalita, "Polio 2");
            }else{
                vPolio2 = noValue(0, namaBalita, "Polio 2");
            }
            dPolio2.setText(Html.fromHtml(vPolio2));
            tPolio2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Gone
            DPT2.setVisibility(View.GONE);
            Polio3.setVisibility(View.GONE);
            DPT3.setVisibility(View.GONE);
            Polio4.setVisibility(View.GONE);
            IPV.setVisibility(View.GONE);
            Campak.setVisibility(View.GONE);
            DPTLanjut.setVisibility(View.GONE);
            CampakLanjut.setVisibility(View.GONE);
        }else if(umurAsDay > 75 && umurAsDay < 119){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //DPT1
            String vDPT1 = "";
            jDPT1.setText("Imunisasi DPT-HB-Hib 1");
            if(i4.isChecked()){
                vDPT1 = noValue(1, namaBalita, "DPT-HB-Hib 1");
            }else{
                vDPT1 = noValue(0, namaBalita, "DPT-HB-Hib 1");
            }
            dDPT1.setText(Html.fromHtml(vDPT1));
            tDPT1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Polio2
            String vPolio2 = "";
            jPolio2.setText("Imunisasi Polio 2");
            if(i5.isChecked()){
                vPolio2 = noValue(1, namaBalita, "Polio 2");
            }else{
                vPolio2 = noValue(0, namaBalita, "Polio 2");
            }
            dPolio2.setText(Html.fromHtml(vPolio2));
            tPolio2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //DPT2
            String vDPT2 = "";
            jDPT2.setText("Imunisasi DPT-HB-Hib 2");
            if(i6.isChecked()){
                vDPT2 = noValue(1, namaBalita, "DPT-HB-Hib 2");
            }else{
                vDPT2 = noValue(0, namaBalita, "DPT-HB-Hib 2");
            }
            dDPT2.setText(Html.fromHtml(vDPT2));
            tDPT2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //Polio3
            String vPolio3 = "";
            jPolio3.setText("Imunisasi Polio 3");
            if(i7.isChecked()){
                vPolio3 = noValue(1, namaBalita, "Polio 3");
            }else{
                vPolio3 = noValue(0, namaBalita, "Polio 3");
            }
            dPolio3.setText(Html.fromHtml(vPolio3));
            tPolio3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));


            //Gone
            DPT3.setVisibility(View.GONE);
            Polio4.setVisibility(View.GONE);
            IPV.setVisibility(View.GONE);
            Campak.setVisibility(View.GONE);
            DPTLanjut.setVisibility(View.GONE);
            CampakLanjut.setVisibility(View.GONE);
        }else if(umurAsDay > 105 && umurAsDay < 269){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //DPT1
            String vDPT1 = "";
            jDPT1.setText("Imunisasi DPT-HB-Hib 1");
            if(i4.isChecked()){
                vDPT1 = noValue(1, namaBalita, "DPT-HB-Hib 1");
            }else{
                vDPT1 = noValue(0, namaBalita, "DPT-HB-Hib 1");
            }
            dDPT1.setText(Html.fromHtml(vDPT1));
            tDPT1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Polio2
            String vPolio2 = "";
            jPolio2.setText("Imunisasi Polio 2");
            if(i5.isChecked()){
                vPolio2 = noValue(1, namaBalita, "Polio 2");
            }else{
                vPolio2 = noValue(0, namaBalita, "Polio 2");
            }
            dPolio2.setText(Html.fromHtml(vPolio2));
            tPolio2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //DPT2
            String vDPT2 = "";
            jDPT2.setText("Imunisasi DPT-HB-Hib 2");
            if(i6.isChecked()){
                vDPT2 = noValue(1, namaBalita, "DPT-HB-Hib 2");
            }else{
                vDPT2 = noValue(0, namaBalita, "DPT-HB-Hib 2");
            }
            dDPT2.setText(Html.fromHtml(vDPT2));
            tDPT2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //Polio3
            String vPolio3 = "";
            jPolio3.setText("Imunisasi Polio 3");
            if(i7.isChecked()){
                vPolio3 = noValue(1, namaBalita, "Polio 3");
            }else{
                vPolio3 = noValue(0, namaBalita, "Polio 3");
            }
            dPolio3.setText(Html.fromHtml(vPolio3));
            tPolio3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //DPT3
            String vDPT3 = "";
            jDPT3.setText("Imunisasi DPT-HB-Hib 3");
            if(i8.isChecked()){
                vDPT3 = noValue(1, namaBalita, "DPT-HB-Hib 3");
            }else{
                vDPT3 = noValue(0, namaBalita, "DPT-HB-Hib 3");
            }
            dDPT3.setText(Html.fromHtml(vDPT3));
            tDPT3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Polio4
            String vPolio4 = "";
            jPolio4.setText("Imunisasi Polio 4");
            if(i9.isChecked()){
                vPolio4 = noValue(1, namaBalita, "Polio 4");
            }else{
                vPolio4 = noValue(0, namaBalita, "Polio 4");
            }
            dPolio4.setText(Html.fromHtml(vPolio4));
            tPolio4.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //IPV
            String vIPV = "";
            jIPV.setText("Imunisasi IPV");
            if(i10.isChecked()){
                vIPV = noValue(1, namaBalita, "IPV");
            }else{
                vIPV = noValue(0, namaBalita, "IPV");
            }
            dIPV.setText(Html.fromHtml(vIPV));
            tIPV.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Gone
            Campak.setVisibility(View.GONE);
            DPTLanjut.setVisibility(View.GONE);
            CampakLanjut.setVisibility(View.GONE);
        }else if(umurAsDay > 255 && umurAsDay < 539){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //DPT1
            String vDPT1 = "";
            jDPT1.setText("Imunisasi DPT-HB-Hib 1");
            if(i4.isChecked()){
                vDPT1 = noValue(1, namaBalita, "DPT-HB-Hib 1");
            }else{
                vDPT1 = noValue(0, namaBalita, "DPT-HB-Hib 1");
            }
            dDPT1.setText(Html.fromHtml(vDPT1));
            tDPT1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Polio2
            String vPolio2 = "";
            jPolio2.setText("Imunisasi Polio 2");
            if(i5.isChecked()){
                vPolio2 = noValue(1, namaBalita, "Polio 2");
            }else{
                vPolio2 = noValue(0, namaBalita, "Polio 2");
            }
            dPolio2.setText(Html.fromHtml(vPolio2));
            tPolio2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //DPT2
            String vDPT2 = "";
            jDPT2.setText("Imunisasi DPT-HB-Hib 2");
            if(i6.isChecked()){
                vDPT2 = noValue(1, namaBalita, "DPT-HB-Hib 2");
            }else{
                vDPT2 = noValue(0, namaBalita, "DPT-HB-Hib 2");
            }
            dDPT2.setText(Html.fromHtml(vDPT2));
            tDPT2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //Polio3
            String vPolio3 = "";
            jPolio3.setText("Imunisasi Polio 3");
            if(i7.isChecked()){
                vPolio3 = noValue(1, namaBalita, "Polio 3");
            }else{
                vPolio3 = noValue(0, namaBalita, "Polio 3");
            }
            dPolio3.setText(Html.fromHtml(vPolio3));
            tPolio3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //DPT3
            String vDPT3 = "";
            jDPT3.setText("Imunisasi DPT-HB-Hib 3");
            if(i8.isChecked()){
                vDPT3 = noValue(1, namaBalita, "DPT-HB-Hib 3");
            }else{
                vDPT3 = noValue(0, namaBalita, "DPT-HB-Hib 3");
            }
            dDPT3.setText(Html.fromHtml(vDPT3));
            tDPT3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Polio4
            String vPolio4 = "";
            jPolio4.setText("Imunisasi Polio 4");
            if(i9.isChecked()){
                vPolio4 = noValue(1, namaBalita, "Polio 4");
            }else{
                vPolio4 = noValue(0, namaBalita, "Polio 4");
            }
            dPolio4.setText(Html.fromHtml(vPolio4));
            tPolio4.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //IPV
            String vIPV = "";
            jIPV.setText("Imunisasi IPV");
            if(i10.isChecked()){
                vIPV = noValue(1, namaBalita, "IPV");
            }else{
                vIPV = noValue(0, namaBalita, "IPV");
            }
            dIPV.setText(Html.fromHtml(vIPV));
            tIPV.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Campak
            String vCampak = "";
            jCampak.setText("Imunisasi Campak");
            if(i11.isChecked()){
                vCampak = noValue(1, namaBalita, "Campak");
            }else{
                vCampak = noValue(0, namaBalita, "Campak");
            }
            dCampak.setText(Html.fromHtml(vCampak));
            tCampak.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,270));

            //Gone
            DPTLanjut.setVisibility(View.GONE);
            CampakLanjut.setVisibility(View.GONE);
        }else if(umurAsDay >  515 && umurAsDay < 729){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //DPT1
            String vDPT1 = "";
            jDPT1.setText("Imunisasi DPT-HB-Hib 1");
            if(i4.isChecked()){
                vDPT1 = noValue(1, namaBalita, "DPT-HB-Hib 1");
            }else{
                vDPT1 = noValue(0, namaBalita, "DPT-HB-Hib 1");
            }
            dDPT1.setText(Html.fromHtml(vDPT1));
            tDPT1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Polio2
            String vPolio2 = "";
            jPolio2.setText("Imunisasi Polio 2");
            if(i5.isChecked()){
                vPolio2 = noValue(1, namaBalita, "Polio 2");
            }else{
                vPolio2 = noValue(0, namaBalita, "Polio 2");
            }
            dPolio2.setText(Html.fromHtml(vPolio2));
            tPolio2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //DPT2
            String vDPT2 = "";
            jDPT2.setText("Imunisasi DPT-HB-Hib 2");
            if(i6.isChecked()){
                vDPT2 = noValue(1, namaBalita, "DPT-HB-Hib 2");
            }else{
                vDPT2 = noValue(0, namaBalita, "DPT-HB-Hib 2");
            }
            dDPT2.setText(Html.fromHtml(vDPT2));
            tDPT2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //Polio3
            String vPolio3 = "";
            jPolio3.setText("Imunisasi Polio 3");
            if(i7.isChecked()){
                vPolio3 = noValue(1, namaBalita, "Polio 3");
            }else{
                vPolio3 = noValue(0, namaBalita, "Polio 3");
            }
            dPolio3.setText(Html.fromHtml(vPolio3));
            tPolio3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //DPT3
            String vDPT3 = "";
            jDPT3.setText("Imunisasi DPT-HB-Hib 3");
            if(i8.isChecked()){
                vDPT3 = noValue(1, namaBalita, "DPT-HB-Hib 3");
            }else{
                vDPT3 = noValue(0, namaBalita, "DPT-HB-Hib 3");
            }
            dDPT3.setText(Html.fromHtml(vDPT3));
            tDPT3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Polio4
            String vPolio4 = "";
            jPolio4.setText("Imunisasi Polio 4");
            if(i9.isChecked()){
                vPolio4 = noValue(1, namaBalita, "Polio 4");
            }else{
                vPolio4 = noValue(0, namaBalita, "Polio 4");
            }
            dPolio4.setText(Html.fromHtml(vPolio4));
            tPolio4.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //IPV
            String vIPV = "";
            jIPV.setText("Imunisasi IPV");
            if(i10.isChecked()){
                vIPV = noValue(1, namaBalita, "IPV");
            }else{
                vIPV = noValue(0, namaBalita, "IPV");
            }
            dIPV.setText(Html.fromHtml(vIPV));
            tIPV.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Campak
            String vCampak = "";
            jCampak.setText("Imunisasi Campak");
            if(i11.isChecked()){
                vCampak = noValue(1, namaBalita, "Campak");
            }else{
                vCampak = noValue(0, namaBalita, "Campak");
            }
            dCampak.setText(Html.fromHtml(vCampak));
            tCampak.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,270));

            //DPTLanjut
            String vDPTLanjut = "";
            jDPTLanjut.setText("Imunisasi DPT-HB-Hib Lanjut");
            if(i12.isChecked()){
                vDPTLanjut = noValue(1, namaBalita, "DPT-HB-Hib Lanjut");
            }else{
                vDPTLanjut = noValue(0, namaBalita, "DPT-HB-Hib Lanjut");
            }
            dDPTLanjut.setText(Html.fromHtml(vDPTLanjut));
            tDPTLanjut.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,540));

            //Gone
            CampakLanjut.setVisibility(View.GONE);

        }else if(umurAsDay > 705){
            //HB0
            String vHB0 = "";
            jHB0.setText("Imunisasi HB0");
            if(i1.isChecked()){
                vHB0 = noValue(1, namaBalita, "HB0");
            }else{
                vHB0 = noValue(0, namaBalita, "HB0");
            }
            dHB0.setText(Html.fromHtml(vHB0));
            tHB0.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,0));

            //BCG
            String vBCG = "";
            jBCG.setText("Imunisasi BCG");
            if(i2.isChecked()){
                vBCG = noValue(1, namaBalita, "BCG");
            }else{
                vBCG = noValue(0, namaBalita, "BCG");
            }
            dBCG.setText(Html.fromHtml(vBCG));
            tBCG.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //Polio1
            String vPolio1 = "";
            jPolio1.setText("Imunisasi Polio 1");
            if(i3.isChecked()){
                vPolio1 = noValue(1, namaBalita, "Polio 1");
            }else{
                vPolio1 = noValue(0, namaBalita, "Polio 1");
            }
            dPolio1.setText(Html.fromHtml(vPolio1));
            tPolio1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,30));

            //DPT1
            String vDPT1 = "";
            jDPT1.setText("Imunisasi DPT-HB-Hib 1");
            if(i4.isChecked()){
                vDPT1 = noValue(1, namaBalita, "DPT-HB-Hib 1");
            }else{
                vDPT1 = noValue(0, namaBalita, "DPT-HB-Hib 1");
            }
            dDPT1.setText(Html.fromHtml(vDPT1));
            tDPT1.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //Polio2
            String vPolio2 = "";
            jPolio2.setText("Imunisasi Polio 2");
            if(i5.isChecked()){
                vPolio2 = noValue(1, namaBalita, "Polio 2");
            }else{
                vPolio2 = noValue(0, namaBalita, "Polio 2");
            }
            dPolio2.setText(Html.fromHtml(vPolio2));
            tPolio2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,60));

            //DPT2
            String vDPT2 = "";
            jDPT2.setText("Imunisasi DPT-HB-Hib 2");
            if(i6.isChecked()){
                vDPT2 = noValue(1, namaBalita, "DPT-HB-Hib 2");
            }else{
                vDPT2 = noValue(0, namaBalita, "DPT-HB-Hib 2");
            }
            dDPT2.setText(Html.fromHtml(vDPT2));
            tDPT2.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //Polio3
            String vPolio3 = "";
            jPolio3.setText("Imunisasi Polio 3");
            if(i7.isChecked()){
                vPolio3 = noValue(1, namaBalita, "Polio 3");
            }else{
                vPolio3 = noValue(0, namaBalita, "Polio 3");
            }
            dPolio3.setText(Html.fromHtml(vPolio3));
            tPolio3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,90));

            //DPT3
            String vDPT3 = "";
            jDPT3.setText("Imunisasi DPT-HB-Hib 3");
            if(i8.isChecked()){
                vDPT3 = noValue(1, namaBalita, "DPT-HB-Hib 3");
            }else{
                vDPT3 = noValue(0, namaBalita, "DPT-HB-Hib 3");
            }
            dDPT3.setText(Html.fromHtml(vDPT3));
            tDPT3.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Polio4
            String vPolio4 = "";
            jPolio4.setText("Imunisasi Polio 4");
            if(i9.isChecked()){
                vPolio4 = noValue(1, namaBalita, "Polio 4");
            }else{
                vPolio4 = noValue(0, namaBalita, "Polio 4");
            }
            dPolio4.setText(Html.fromHtml(vPolio4));
            tPolio4.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //IPV
            String vIPV = "";
            jIPV.setText("Imunisasi IPV");
            if(i10.isChecked()){
                vIPV = noValue(1, namaBalita, "IPV");
            }else{
                vIPV = noValue(0, namaBalita, "IPV");
            }
            dIPV.setText(Html.fromHtml(vIPV));
            tIPV.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,120));

            //Campak
            String vCampak = "";
            jCampak.setText("Imunisasi Campak");
            if(i11.isChecked()){
                vCampak = noValue(1, namaBalita, "Campak");
            }else{
                vCampak = noValue(0, namaBalita, "Campak");
            }
            dCampak.setText(Html.fromHtml(vCampak));
            tCampak.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,270));

            //DPTLanjut
            String vDPTLanjut = "";
            jDPTLanjut.setText("Imunisasi DPT-HB-Hib Lanjut");
            if(i12.isChecked()){
                vDPTLanjut = noValue(1, namaBalita, "DPT-HB-Hib Lanjut");
            }else{
                vDPTLanjut = noValue(0, namaBalita, "DPT-HB-Hib Lanjut");
            }
            dDPTLanjut.setText(Html.fromHtml(vDPTLanjut));
            tDPTLanjut.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,540));

            //CampakLanjut
            String vCampakLanjut = "";
            jCampakLanjut.setText("Imunisasi Campak Lanjut");
            if(i13.isChecked()){
                vCampakLanjut = noValue(1, namaBalita, "Campak Lanjut");
            }else{
                vCampakLanjut = noValue(0, namaBalita, "Campak Lanjut");
            }
            dCampakLanjut.setText(Html.fromHtml(vCampakLanjut));
            tCampakLanjut.setText("Tanggal Imunisasi Rekomendasi: "+stringToDay (tglLahir,720));
        }
    }
    public void viewHasil(String idBalita){
        retrofit2.Call<ImunResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getImunData(idBalita);
            call.enqueue(new Callback<ImunResponse>() {
                @Override
                public void onResponse(Call<ImunResponse> call, Response<ImunResponse> response) {
                    Intent mIntent = getIntent();
                    String namaBalita = mIntent.getStringExtra("namaBalita");
                    String tglLahir = mIntent.getStringExtra("tglLahir");

                    //Umur as Days
                    String umurAsDays = daysFormat(tglLahir);
                    int umurAsDay = Integer.parseInt(umurAsDays);
                    if(response.isSuccessful()){
                        showLoading(true);
                        List<Imun> artikels = response.body().getMData();
                        for(int i = 0; i < artikels.size(); i++){
                            if(artikels.get(i).getHb0().equals("0")){
                                i1.setChecked(false);
                            }else{
                                i1.setChecked(true);
                            }
                            if(artikels.get(i).getBcg().equals("0")){
                                i2.setChecked(false);
                            }else{
                                i2.setChecked(true);
                            }
                            if(artikels.get(i).getPolio1().equals("0")){
                                i3.setChecked(false);
                            }else{
                                i3.setChecked(true);
                            }
                            if(artikels.get(i).getDpt1().equals("0")){
                                i4.setChecked(false);
                            }else{
                                i4.setChecked(true);
                            }
                            if(artikels.get(i).getPolio2().equals("0")){
                                i5.setChecked(false);
                            }else{
                                i5.setChecked(true);
                            }
                            if(artikels.get(i).getDpt2().equals("0")){
                                i6.setChecked(false);
                            }else{
                                i6.setChecked(true);
                            }
                            if(artikels.get(i).getPolio3().equals("0")){
                                i7.setChecked(false);
                            }else{
                                i7.setChecked(true);
                            }
                            if(artikels.get(i).getDpt3().equals("0")){
                                i8.setChecked(false);
                            }else{
                                i8.setChecked(true);
                            }
                            if(artikels.get(i).getPolio4().equals("0")){
                                i9.setChecked(false);
                            }else{
                                i9.setChecked(true);
                            }
                            if(artikels.get(i).getIpv().equals("0")){
                                i10.setChecked(false);
                            }else{
                                i10.setChecked(true);
                            }
                            if(artikels.get(i).getCampak().equals("0")){
                                i11.setChecked(false);
                            }else{
                                i11.setChecked(true);
                            }
                            if(artikels.get(i).getDptLanjut().equals("0")){
                                i12.setChecked(false);
                            }else{
                                i12.setChecked(true);
                            }
                            if(artikels.get(i).getCampakLanjut().equals("0")){
                                i13.setChecked(false);
                            }else{
                                i13.setChecked(true);
                            }
                            String idImun = artikels.get(i).getIdImunisasi();
                            hasilImun(umurAsDay, namaBalita, tglLahir);
                            lastUpdate.setText("Last Update: "+ yearsFormat(artikels.get(i).getUpdateImunisasi()));
                            iSubmit.setText("Update Data");
                            iSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    initCheckedUpdate(1, idImun);
                                }
                            });
                        }
                    }else{
                        showLoading(true);
                        hasilImun(umurAsDay, namaBalita, tglLahir);
                        lastUpdate.setText("Last Update: Silahkan Simpan Data Terlebih Dahulu");
                        iSubmit.setText("Simpan Data");
                        String idImun = "idImun";
                        iSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                initCheckedUpdate(0, idImun);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ImunResponse> call, Throwable t) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void updateImun(String hb0, String bgc, String polio1, String dpt1, String polio2, String dpt2, String polio3, String dpt3, String polio4, String ipv, String campak, String dptLanjut, String campakLanjut, String updateImun, String idBalita, String idImun){
        retrofit2.Call<ImunResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiUpdateImun(hb0, bgc, polio1, dpt1, polio2, dpt2, polio3, dpt3, polio4, ipv, campak, dptLanjut, campakLanjut, updateImun, idBalita, idImun);
            call.enqueue(new Callback<ImunResponse>() {
                @Override
                public void onResponse(Call<ImunResponse> call, Response<ImunResponse> response) {
                    if (response.isSuccessful()){
                        showLoading(true);
                        startActivity(new Intent(ImunisasiActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(ImunisasiActivity.this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ImunResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void insertImun(String hb0, String bgc, String polio1, String dpt1, String polio2, String dpt2, String polio3, String dpt3, String polio4, String ipv, String campak, String dptLanjut, String campakLanjut, String updateImun, String idBalita){
        retrofit2.Call<ImunResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.apiInsertImun(hb0, bgc, polio1, dpt1, polio2, dpt2, polio3, dpt3, polio4, ipv, campak, dptLanjut, campakLanjut, updateImun, idBalita);
            call.enqueue(new Callback<ImunResponse>() {
                @Override
                public void onResponse(Call<ImunResponse> call, Response<ImunResponse> response) {
                    if (response.isSuccessful()){
                        showLoading(true);
                        startActivity(new Intent(ImunisasiActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(ImunisasiActivity.this, "Data Berhasil di Simpan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ImunResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String noValue(int value, String namaBalita, String jenisImun){
        String detail = "0";
        if( value == 0){
            detail = namaBalita + " <b>BELUM</b> melakukan imunisasi <b>"+ jenisImun +"</b>, silahkan segera imunisasi anak anda ke tempat pelayanan kesehatan terdekat.";
        }else if (value == 1){
            detail = namaBalita + " <b>SUDAH</b> melakukan imunisasi jenis: <b>" + jenisImun +"</b>";
        }
        return detail;
    }
    private void showLoading(Boolean state){
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
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
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
            dateInString = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }
    private String stringToDay(String stringToDate, int dates){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        String dateInString = stringToDate;
        try {
            c.setTime(format.parse(dateInString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, dates);
        SimpleDateFormat formatS = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id"));
        String output = formatS.format(c.getTime());
        return output;
    }
}