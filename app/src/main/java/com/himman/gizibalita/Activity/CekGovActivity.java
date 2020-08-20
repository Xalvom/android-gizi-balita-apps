package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Adapter.DevAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.Model.Dev;
import com.himman.gizibalita.Model.DevResponse;
import com.himman.gizibalita.Model.User;
import com.himman.gizibalita.Model.UserResponse;
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

public class CekGovActivity extends AppCompatActivity {
    private static final String TAG = CekGovActivity.class.getSimpleName();
    private RecyclerView rvCek;
    private DevAdapter devAdapter;
    private List<String> listSpinner = new ArrayList<String>();
    private List<String> listUmur = new ArrayList<String>();
    private List<Dev> devsItem = new ArrayList<>();
    private TextView infoCek;
    private Context mContext;
    private Spinner spinnerCek;
    private ApiInterface Service;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_gov);
        getSupportActionBar().setTitle("Cek Perkembangan Balita");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this;
        progressBar = findViewById(R.id.progressBar);
        rvCek = findViewById(R.id.rv_cek);
        spinnerCek = findViewById(R.id.spinnerCek);
        infoCek = findViewById(R.id.infoCek);
        Intent mIntent = getIntent();
        String idBalita = mIntent.getStringExtra("idBalita");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvCek.setLayoutManager(mLayoutManager);
        initCek(idBalita);
        showLoading(false);
        spinnerCek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedUmur = listUmur.get(position);
                //Checked Umur
                String umur = yearsFormat(selectedUmur);
                int um = Integer.parseInt(umur);
                int idxUm = 0;
                if(um >= 0 && um <= 1 ){
                    idxUm = 1;
                }else if(um >= 2 && um <= 3){
                    idxUm = 3;
                }else if(um >= 4 && um <= 6){
                    idxUm = 6;
                }else if(um >= 7 && um <= 9){
                    idxUm = 9;
                }else if(um >= 10 && um <= 12){
                    idxUm = 12;
                }else if(um >= 13 && um <= 24){
                    idxUm = 24;
                }else if(um >= 25 && um <= 36){
                    idxUm = 36;
                }else if(um >= 37 && um <= 48){
                    idxUm = 48;
                }else if(um >= 49 && um <= 60){
                    idxUm = 60;
                }
                String umurString = String.valueOf(idxUm);
                if(um >= 0 && um <= 24){
                    infoCek.setText(R.string.anak2tahun);
                }else if(um >= 25 && um <= 60){
                    infoCek.setText(R.string.anak6tahun);
                }
                showLoading(true);
                initSpin(umurString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void initCek(String idBalita){
        ApiInterface Service;
        retrofit2.Call<BalitaResponse> Call;

        try{
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getBalitaId(idBalita);
            Call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    List<Balita> balita = response.body().getMData();
                    if (response.isSuccessful()) {
                        for (int i = 0; i < balita.size(); i++) {
                            String tgllahirBalita = balita.get(i).getTgllahirBalita();
                            String namaBalita = balita.get(i).getNamaBalita();
                            listSpinner.add(namaBalita);
                            listUmur.add(tgllahirBalita);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerCek.setAdapter(adapter);
                    } else {
                        Toast.makeText(mContext, "Gagal mengambil data balita", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BalitaResponse> call, Throwable t) {

                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void initSpin(String katUmur){
        retrofit2.Call<DevResponse> Call;

        try{
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getListPerkembangan(katUmur);
            Call.enqueue(new Callback<DevResponse>() {
                @Override
                public void onResponse(retrofit2.Call<DevResponse> call, Response<DevResponse> response) {
                    if(response.isSuccessful()){
                        showLoading(false);
                        List<Dev> devs  = response.body().getMData();
                        devAdapter = new DevAdapter();
                        devAdapter.setListDev(devs);
                        rvCek.setAdapter(devAdapter);
                    }else{
                        Toast.makeText(mContext, "Gagal mengambil data perkembangan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<DevResponse> call, Throwable t) {

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
    private void showLoading(Boolean state){
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
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
}
