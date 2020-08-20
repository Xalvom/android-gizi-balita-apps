package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.Model.BbtbResponse;
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

public class AddBbtbActivity extends AppCompatActivity {
    private static final String TAG = AddBbtbActivity.class.getSimpleName();
    private EditText mBeratBadan;
    private EditText mKelamin;
    private EditText mTinggiBadan;
    private Button mSubmit;
    private ApiInterface service;
    private Spinner spinnerBalita;
    private String idBalita;
    private Context mContext;
    private List<String> listId = new ArrayList<String>();
    private List<String> listKelamin = new ArrayList<String>();
    private List<String> listUmur = new ArrayList<String>();
    private List<String> listSpinner = new ArrayList<String>();
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bbtb);
        getSupportActionBar().setTitle("Tambah Data Gizi BB / TB");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        spinnerBalita = findViewById(R.id.spinnerBalita);
        mKelamin = findViewById(R.id.tbtKelaminBalita);
        mTinggiBadan = findViewById(R.id.tbtTinggiBalita);
        mBeratBadan = findViewById(R.id.tbtBeratBalita);
        mSubmit = findViewById(R.id.btnsubmit);
        sharedPrefManager = new SharedPrefManager(this);
        String idUser = sharedPrefManager.getSpIduser();
        initBalita(idUser);
        spinnerBalita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedId=listId.get(position);
                String selectedKelamin=listKelamin.get(position);
                String selectedUmur=listUmur.get(position);
                //Checked IdBalita
                idBalita = selectedId;
                //Checked Kelamin
                if(selectedKelamin.equals("L")){
                    mKelamin.setText("Laki - Laki");
                }else{
                    mKelamin.setText("Perempuan");
                }
                //Checked Umur
                String umur = yearsFormat(selectedUmur);
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
                mSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idBalitas = idBalita;
                        String kelaminBalita = selectedKelamin;
                        String tinggiBalita = mTinggiBadan.getText().toString();
                        String beratBalita = mBeratBadan.getText().toString();
                        String kat_umur = umurString;
                        String update = formattedDate;
                        if(tinggiBalita.isEmpty() | beratBalita.isEmpty()){
                            mBeratBadan.setError("Berat Badan Tidak Boleh Kosong");
                            mTinggiBadan.setError("Tinggi Badan tidak boleh kosong");
                            mBeratBadan.requestFocus();
                            mTinggiBadan.requestFocus();
                            return;
                        }else{
                            addBbtb(idBalitas,kelaminBalita,tinggiBalita,beratBalita,kat_umur,update);
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addBbtb(String idBalita, String kelamin, String tinggiBadan, String beratBadan, String katUmur, String updateBbtb){
        retrofit2.Call<BbtbResponse> Call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            Call = service.apiInsertBbtb(idBalita, kelamin, tinggiBadan, beratBadan, katUmur, updateBbtb);
            Call.enqueue(new Callback<BbtbResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BbtbResponse> call, Response<BbtbResponse> response) {
                    if(response.isSuccessful()){
                        mBeratBadan.setText(null);
                        mTinggiBadan.setText(null);
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initBalita(String idUser){
        retrofit2.Call<BalitaResponse> call;

        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getListBalita(idUser);
            call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if (response.isSuccessful()) {
                        List<Balita> balita = response.body().getMData();
                        for (int i = 0; i < balita.size(); i++) {
                            String idBalita = balita.get(i).getIdBalita();
                            String namaBalita = balita.get(i).getNamaBalita();
                            String kelaminBalita = balita.get(i).getKelaminBalita();
                            String tglLahir = balita.get(i).getTgllahirBalita();
                            listSpinner.add(namaBalita);
                            listId.add(idBalita);
                            listKelamin.add(kelaminBalita);
                            listUmur.add(tglLahir);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerBalita.setAdapter(adapter);
                    }else{
                        Toast.makeText(mContext, "Gagal mengambil data balita", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<BalitaResponse> call, Throwable t) {
                    Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
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
}
