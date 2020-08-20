package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.himman.gizibalita.Adapter.BbuAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Bbu;
import com.himman.gizibalita.Model.BbuResponse;
import com.himman.gizibalita.Model.KMS;
import com.himman.gizibalita.Model.KMSResponse;
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

public class KMSActivity extends AppCompatActivity {

    private List<Bbu> bbuList;
    private List<KMS> kmsList;
    private Bbu bbu;
    private KMS kms;
    private TextView idTipe, title;
    private RelativeLayout fullBg;
    private LineChart chart;
    private int start, end;
    private ApiInterface service;
    private RecyclerView rvKet;
    private BbuAdapter bbuAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_m_s);

        //Landscape
        chart = findViewById(R.id.cKms);
        idTipe = findViewById(R.id.idTitle);
        title = findViewById(R.id.titleKMS);
        fullBg = findViewById(R.id.fullBg);
        rvKet = findViewById(R.id.rvKet);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvKet.setLayoutManager(mLayoutManager);
        getSupportActionBar().setTitle("Kartu Menuju Sehat");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set leftAxis
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(28);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setAxisMinimum(0);
        rightAxis.setAxisMaximum(28);

        //Set XAxis
        XAxis botAxis = chart.getXAxis();
        botAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        Intent mIntent = getIntent();
        String kelaminBalita = mIntent.getStringExtra("kelaminBalita");
        String idBalita = mIntent.getStringExtra("idBalita");
        getKMS(kelaminBalita);
        getKeterangan(idBalita);
    }

    private void getKMS(String Kelamin){
        retrofit2.Call<KMSResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getKmsData(Kelamin);
            call.enqueue(new Callback<KMSResponse>() {
                @Override
                public void onResponse(Call<KMSResponse> call, Response<KMSResponse> response) {
                    if(response.isSuccessful()){
                        kmsList = response.body().getMData();
                        if(Kelamin.equals("L")){
                            fullBg.setBackgroundResource(R.color.colorBlue);
                        }else{
                            fullBg.setBackgroundResource(R.color.colorTbu);
                        }
                            ArrayList<Entry> min3SD = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getMin3SD());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                min3SD.add(new Entry(a22,a12));
                            }
                            ArrayList<Entry> min2SD = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getMin2SD());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                min2SD.add(new Entry(a22,a12));
                            }
                            ArrayList<Entry> min1SD = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getMin1SD());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                min1SD.add(new Entry(a22,a12));
                            }
                            ArrayList<Entry> median = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getMedian());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                median.add(new Entry(a22,a12));
                            }
                            ArrayList<Entry> plus3SD = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getPlus3SD());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                plus3SD.add(new Entry(a22,a12));
                            }
                            ArrayList<Entry> plus2SD = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getPlus2SD());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                plus2SD.add(new Entry(a22,a12));
                            }
                            ArrayList<Entry> plus1SD = new ArrayList<>();
                            for(int i = 0; i <= 60; i++){
                                float a12 = Float.parseFloat(kmsList.get(i).getPlus1SD());
                                float a22 = Float.parseFloat(kmsList.get(i).getUmur());
                                plus1SD.add(new Entry(a22,a12));
                            }
                            LineDataSet line1 = new LineDataSet(min3SD, "");
                            line1.setFillAlpha(110);
                            line1.setColor(Color.RED);
                            line1.setDrawValues(false);
                            line1.setLineWidth(1);
                            line1.setDrawCircles(false);

                            LineDataSet line2 = new LineDataSet(min2SD, "");
                            line2.setColor(Color.BLACK);
                            line2.setDrawValues(false);
                            line2.setLineWidth(1);
                            line2.setDrawCircles(false);
                            line2.setDrawFilled(true);
                            line2.setFillColor(getResources().getColor(R.color.colorYellow));
                            line2.setFillFormatter(new MyFillFormatter(line1));

                            LineDataSet line3 = new LineDataSet(min1SD, "");
                            line3.setColor(Color.BLACK);
                            line3.setDrawValues(false);
                            line3.setLineWidth(1);
                            line3.setDrawCircles(false);
                            line3.setDrawFilled(true);
                            line3.setFillColor(getResources().getColor(R.color.colorLightGreen));
                            line3.setFillFormatter(new MyFillFormatter(line2));

                            LineDataSet line4 = new LineDataSet(median, "");
                            line4.setColor(Color.BLACK);
                            line4.setDrawValues(false);
                            line4.setLineWidth(1);
                            line4.setDrawCircles(false);
                            line4.setDrawFilled(true);
                            line4.setFillColor(getResources().getColor(R.color.colorGreen));
                            line4.setFillFormatter(new MyFillFormatter(line3));

                            LineDataSet line5 = new LineDataSet(plus1SD, "");
                            line5.setColor(Color.BLACK);
                            line5.setDrawValues(false);
                            line5.setLineWidth(1);
                            line5.setDrawCircles(false);
                            line5.setDrawFilled(true);
                            line5.setFillColor(getResources().getColor(R.color.colorGreen));
                            line5.setFillFormatter(new MyFillFormatter(line4));

                            LineDataSet line6 = new LineDataSet(plus2SD, "");
                            line6.setColor(Color.BLACK);
                            line6.setDrawValues(false);
                            line6.setLineWidth(1);
                            line6.setDrawCircles(false);
                            line6.setDrawFilled(true);
                            line6.setFillColor(getResources().getColor(R.color.colorLightGreen));
                            line6.setFillFormatter(new MyFillFormatter(line5));

                            LineDataSet line7 = new LineDataSet(plus3SD, "");
                            line7.setColor(Color.BLACK);
                            line7.setDrawValues(false);
                            line7.setLineWidth(1);
                            line7.setDrawCircles(false);
                            line7.setDrawFilled(true);
                            line7.setFillColor(getResources().getColor(R.color.colorYellow));
                            line7.setFillFormatter(new MyFillFormatter(line6));

                            //Intent
                            Intent mIntent = getIntent();
                            String idBalita = mIntent.getStringExtra("idBalita");
                            getGiziBalita(idBalita, line1, line2, line3, line4, line5, line6, line7);

                            idTipe.setText("Kartu Menuju Sehat: untuk " + kelamin(Kelamin) + " usia 0 - 60 bulan");
                    }
                }

                @Override
                public void onFailure(Call<KMSResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }

    private void getGiziBalita(String idBalita,LineDataSet line1, LineDataSet line2, LineDataSet line3, LineDataSet line4, LineDataSet line5, LineDataSet line6, LineDataSet line7){
        retrofit2.Call<BbuResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getListBbu(idBalita);
            call.enqueue(new Callback<BbuResponse>() {
                @Override
                public void onResponse(Call<BbuResponse> call, Response<BbuResponse> response) {
                    if(response.isSuccessful()){
                        bbuList = response.body().getMData();
                            ArrayList<Entry> dataGizi = new ArrayList<>();
                            for(int i = 0; i < bbuList.size(); i++){
                                float a12 = Float.parseFloat(bbuList.get(i).getBeratBadan());
                                float a22 = Float.parseFloat(bbuList.get(i).getUmur());
                                dataGizi.add(new Entry(a22,a12));
                            }
                            LineDataSet line8 = new LineDataSet(dataGizi, "Data Gizi");
                            line8.setColor(Color.BLUE);
                            line8.setDrawValues(false);
                            line8.setLineWidth(3);
                            line8.setDrawCircles(true);
                            line8.setDrawCircleHole(true);
                            line8.setCircleHoleColor(Color.WHITE);

                            ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
                            iLineDataSets.add(line1);
                            iLineDataSets.add(line2);
                            iLineDataSets.add(line3);
                            iLineDataSets.add(line4);
                            iLineDataSets.add(line5);
                            iLineDataSets.add(line6);
                            iLineDataSets.add(line7);
                            iLineDataSets.add(line8);

                            LineData lineData = new LineData(iLineDataSets);
                            chart.setRenderer(new MyLineLegendRenderer(chart,chart.getAnimator(),chart.getViewPortHandler()));
                            chart.setData(lineData);
                            chart.getLegend().setEnabled(false);
                            chart.animateXY(2000, 2000);
                            chart.invalidate();
                    }
                }

                @Override
                public void onFailure(Call<BbuResponse> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }
    private void getKeterangan(String idBalita){
        retrofit2.Call<BbuResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getListBbu(idBalita);
            call.enqueue(new Callback<BbuResponse>() {
                @Override
                public void onResponse(Call<BbuResponse> call, Response<BbuResponse> response) {
                    if(response.isSuccessful()) {
                        List<Bbu> bbus = response.body().getMData();
                        bbuAdapter = new BbuAdapter();
                        bbuAdapter.setListBbu(bbus);
                        rvKet.setAdapter(bbuAdapter);
                    }
                }

                @Override
                public void onFailure(Call<BbuResponse> call, Throwable t) {

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
    private String kelamin(String changeKelamin) {
        if (changeKelamin.equals("L")) {
            changeKelamin = "Laki-Laki";
        } else {
            changeKelamin = "Perempuan";
        }
        return changeKelamin;
    }

    public class MyFillFormatter implements IFillFormatter {
        private ILineDataSet boundaryDataSet;

        public MyFillFormatter() {
            this(null);
        }
        //Pass the dataset of other line in the Constructor
        public MyFillFormatter(ILineDataSet boundaryDataSet) {
            this.boundaryDataSet = boundaryDataSet;
        }

        @Override
        public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
            return 0;
        }

        //Define a new method which is used in the LineChartRenderer
        public List<Entry> getFillLineBoundary() {
            if(boundaryDataSet != null) {
                return ((LineDataSet) boundaryDataSet).getValues();
            }
            return null;
        }

    }
    public class MyLineLegendRenderer extends LineChartRenderer {

        public MyLineLegendRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
            super(chart, animator, viewPortHandler);
        }

        //This method is same as it's parent implemntation
        @Override
        protected void drawLinearFill(Canvas c, ILineDataSet dataSet, Transformer trans, XBounds bounds) {
            final Path filled = mGenerateFilledPathBuffer;

            final int startingIndex = bounds.min;
            final int endingIndex = bounds.range + bounds.min;
            final int indexInterval = 128;

            int currentStartIndex = 0;
            int currentEndIndex = indexInterval;
            int iterations = 0;

            // Doing this iteratively in order to avoid OutOfMemory errors that can happen on large bounds sets.
            do {
                currentStartIndex = startingIndex + (iterations * indexInterval);
                currentEndIndex = currentStartIndex + indexInterval;
                currentEndIndex = currentEndIndex > endingIndex ? endingIndex : currentEndIndex;

                if (currentStartIndex <= currentEndIndex) {
                    generateFilledPath(dataSet, currentStartIndex, currentEndIndex, filled);

                    trans.pathValueToPixel(filled);

                    final Drawable drawable = dataSet.getFillDrawable();
                    if (drawable != null) {

                        drawFilledPath(c, filled, drawable);
                    } else {

                        drawFilledPath(c, filled, dataSet.getFillColor(), dataSet.getFillAlpha());
                    }
                }

                iterations++;

            } while (currentStartIndex <= currentEndIndex);
        }

        //This is where we define the area to be filled.
        private void generateFilledPath(final ILineDataSet dataSet, final int startIndex, final int endIndex, final Path outputPath) {

            //Call the custom method to retrieve the dataset for other line
            final List<Entry> boundaryEntry = ((MyFillFormatter)dataSet.getFillFormatter()).getFillLineBoundary();

            final float phaseY = mAnimator.getPhaseY();
            final Path filled = outputPath;
            filled.reset();

            final Entry entry = dataSet.getEntryForIndex(startIndex);

            filled.moveTo(entry.getX(), boundaryEntry.get(0).getY());
            filled.lineTo(entry.getX(), entry.getY() * phaseY);

            // create a new path
            Entry currentEntry = null;
            Entry previousEntry = null;
            for (int x = startIndex + 1; x <= endIndex; x++) {

                currentEntry = dataSet.getEntryForIndex(x);
                filled.lineTo(currentEntry.getX(), currentEntry.getY() * phaseY);

            }

            // close up
            if (currentEntry != null && previousEntry!= null) {
                filled.lineTo(currentEntry.getX(), previousEntry.getY());
            }

            //Draw the path towards the other line
            for (int x = endIndex ; x > startIndex; x--) {
                previousEntry = boundaryEntry.get(x);
                filled.lineTo(previousEntry.getX(), previousEntry.getY() * phaseY);
            }

            filled.close();
        }}
}
