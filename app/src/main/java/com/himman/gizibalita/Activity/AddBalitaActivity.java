package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBalitaActivity extends AppCompatActivity {
    private static final String TAG = AddBalitaActivity.class.getSimpleName();
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private EditText mNamaBalita;
    private EditText mRtBalita;
    private EditText mRwBalita;
    private EditText nAlamatBalita;
    private EditText mNamaOrangTua;
    private EditText mBeratBadan;
    private EditText mTinggiBadan;
    private RadioButton mKelaminL;
    private RadioButton mKelaminP;
    private Button mSubmit;
    private ApiInterface Service;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balita);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mNamaBalita = (EditText) findViewById(R.id.isNamaBalita);
        mRtBalita = (EditText) findViewById(R.id.isRtBalita);
        mRwBalita = (EditText) findViewById(R.id.isRwBalita);
        nAlamatBalita = (EditText) findViewById(R.id.isAlamatBalita);
        mNamaOrangTua = (EditText) findViewById(R.id.isNamaOrtuBalita);
        mBeratBadan = (EditText) findViewById(R.id.isBeratBadanBalita);
        mTinggiBadan = (EditText) findViewById(R.id.isTinggiBadanBalita);
        mKelaminL = (RadioButton) findViewById(R.id.isLaki);
        mKelaminP = (RadioButton) findViewById(R.id.isPerempuan);
        mSubmit = (Button) findViewById(R.id.btn_submit);
        sharedPrefManager = new SharedPrefManager(this);
        getSupportActionBar().setTitle("Tambah Data Balita");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initListener();

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                long now = calendar.getTimeInMillis();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddBalitaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: yyyy/mm/dd: " + year + "-" + month + "-" + day);
                String date = year + "-" + month + "-" + day;
                mDisplayDate.setText(date);
            }
        };
        //Disable Future

    }

    private void initListener() {
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaBalita = mNamaBalita.getText().toString();
                String namaOrangtua = mNamaOrangTua.getText().toString();
                String rtBalita = mRtBalita.getText().toString();
                String rwBalita = mRwBalita.getText().toString();
                String alamatBalita = nAlamatBalita.getText().toString();
                String beratBadan = mBeratBadan.getText().toString();
                String tinggiBadan = mTinggiBadan.getText().toString();
                String tglBalita = mDisplayDate.getText().toString();
                //Chechked Update
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String updateNow = sf.format(c);
//                String formattedDate = df.format(c);
                int tglCheck = Integer.parseInt(yearsFormat(tglBalita));
                String idUser = sharedPrefManager.getSpIduser();
                String jenisKelamin = "";
                if (mKelaminL.isChecked()) {
                    jenisKelamin = "L";
                } else if (mKelaminP.isChecked()) {
                    jenisKelamin = "P";
                }
                if (namaBalita.isEmpty() | alamatBalita.isEmpty() | rtBalita.isEmpty() | rwBalita.isEmpty()) {
                    mNamaBalita.setError("Nama Tidak Boleh Kosong");
                    nAlamatBalita.setError("Dusun Tidak Boleh Kosong");
                    mRtBalita.setError("RT Tidak Boleh Kosong");
                    mRwBalita.setError("RW Tidak Boleh Kosong");
                } else if(tglCheck > 60){
                    Toast.makeText(AddBalitaActivity.this, "Umur Minimal di Bawah 5 Tahun", Toast.LENGTH_SHORT).show();
                } else {
                    addData(namaBalita, rtBalita, rwBalita, alamatBalita, jenisKelamin, tglBalita, idUser, namaOrangtua, beratBadan, tinggiBadan, updateNow, updateNow);
                }
            }
        });
    }

    private void addData(String nama, String rtBalita, String rwBalita, String Alamat, String Jenis, String Tgl, String idUser, String namaOrtu, String beratBadan, String tinggiBadan, String update, String createdAt){
        retrofit2.Call<BalitaResponse> Call;
        try{
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.apiInsertBalita(nama, rtBalita, rwBalita, Alamat, Tgl, Jenis, idUser, namaOrtu, beratBadan, tinggiBadan, update, createdAt);
            Call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    if(response.isSuccessful()){
                        mNamaBalita.setText(null);
                        nAlamatBalita.setText(null);
                        mRtBalita.setText(null);
                        mRwBalita.setText(null);
                        mBeratBadan.setText(null);
                        mTinggiBadan.setText(null);
                        mKelaminP.setChecked(false);
                        mKelaminL.setChecked(false);
                        startActivity(new Intent(AddBalitaActivity.this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                        Toast.makeText(AddBalitaActivity.this, "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddBalitaActivity.this, "Data Gagal Di Tambahakan", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BalitaResponse> call, Throwable t) {
                    Log.e(TAG + ".error", t.toString());
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
            int months = now.get(Calendar.DAY_OF_MONTH) - mMonth;
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
