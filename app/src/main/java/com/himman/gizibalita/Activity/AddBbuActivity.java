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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.Model.BbuResponse;
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

public class AddBbuActivity extends AppCompatActivity {
    private static final String TAG = AddBbuActivity.class.getSimpleName();
    private EditText mUmur;
    private EditText mKelamin;
    private EditText mBeratBadan;
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
        setContentView(R.layout.activity_add_bbu);
        getSupportActionBar().setTitle("Tambah Data Gizi BB / U");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = this;
        spinnerBalita = findViewById(R.id.spinnerBalita);
        mKelamin = findViewById(R.id.ibKelaminBalita);
        mUmur = findViewById(R.id.ibUmurBalita);
        mBeratBadan = findViewById(R.id.ibBeratBalita);
        mSubmit = findViewById(R.id.btnsubmit);
        sharedPrefManager = new SharedPrefManager(this);
        String idUser = sharedPrefManager.getSpIduser();
        initBalita(idUser);
        spinnerBalita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                String selectedName = adapterView.getItemAtPosition(position).toString();
                String selectedId=listId.get(position);
                String selectedKelamin=listKelamin.get(position);
                String selectedUmur=listUmur.get(position);
                String balita = adapterView.getItemAtPosition(position).toString();
                //Checked IdBalita
                idBalita = selectedId;
                //Checked Kelamin
                if(selectedKelamin.equals("L")){
                    mKelamin.setText("Laki - Laki");
                }else{
                    mKelamin.setText("Perempuan");
                }
                //Checked Umur
                mUmur.setText(yearsFormat(selectedUmur));
                //Chechked Update
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);
                mSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idBalitas = idBalita;
                        String kelaminBalita = selectedKelamin;
                        String umurBalita = mUmur.getText().toString();
                        String beratBalita = mBeratBadan.getText().toString();
                        String update = formattedDate;

                        if(beratBalita.isEmpty()){
                            mBeratBadan.setError("Berat Badan Tidak Boleh Kosong");
                            mBeratBadan.requestFocus();
                            return;
                        } else {
                            addDataBbu(idBalitas, kelaminBalita, umurBalita, beratBalita, update);
                        }
//                        Toast.makeText(AddBbuActivity.this, "Id Balita : " + idBalitas + "\n Kelamin : " +kelaminBalita + "\n Umur : "+umurBalita+ "\n Berat Badan :" +beratBalita +"\n Current Date: "+now, Toast.LENGTH_SHORT).show();


                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                        mBeratBadan.setText(null);
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

    public void displayBalita(Balita balita){
        String namaBalita = balita.getNamaBalita();
        String kelaminBalita = balita.getKelaminBalita();
        String tglLahir = balita.getTgllahirBalita();

        String bltData = "Nama: " + namaBalita + "\nKelamin: "+ kelaminBalita+ "\nTgl: " +tglLahir;
        Toast.makeText(mContext, bltData, Toast.LENGTH_SHORT).show();
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

    private String dateFormat(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            dateInString = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }
}
