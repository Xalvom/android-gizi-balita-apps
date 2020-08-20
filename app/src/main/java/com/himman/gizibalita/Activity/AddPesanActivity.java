package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.PesanResponse;
import com.himman.gizibalita.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPesanActivity extends AppCompatActivity {
    private EditText mJudulPesan;
    private EditText mIsiPesan;
    private Button mSubmit;
    private ApiInterface Service;
    private SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pesan);
        getSupportActionBar().setTitle("Kirim Konsultasi ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mJudulPesan = findViewById(R.id.ipPsnJudul);
        mIsiPesan = findViewById(R.id.ipPsnIsi);
        mSubmit = findViewById(R.id.btn_submit_psn);
        sharedPrefManager = new SharedPrefManager(this);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jPesan = mJudulPesan.getText().toString();
                String iPesan = mIsiPesan.getText().toString();
                String penjawab = "-";
                String idPengirim = sharedPrefManager.getSpIduser();

                //Chechked Update
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c);

                String update = formattedDate;

                if(jPesan.isEmpty() | iPesan.isEmpty()){
                    mJudulPesan.setError("Judul Tidak Boleh Kosong");
                    mIsiPesan.setError("Isi Tidak Boleh Kosong");
                }else{
                    addPesan(penjawab, idPengirim, jPesan, iPesan, update);
                }
            }
        });
    }

    public void addPesan(String penjawab, String idPengirim, String judulPesan, String isiPesan, String tanggalPesan){
        String jawaban = "-";
        retrofit2.Call<PesanResponse> Call;
        try {
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.apiPesan(idPengirim, penjawab, judulPesan, isiPesan, jawaban, tanggalPesan );
            Call.enqueue(new Callback<PesanResponse>() {
                @Override
                public void onResponse(retrofit2.Call<PesanResponse> call, Response<PesanResponse> response) {
                    if (response.isSuccessful()) {
                        mJudulPesan.setText(null);
                        mIsiPesan.setText(null);
                        Toast.makeText(AddPesanActivity.this, "Pesan Berhasil dikirim, \nSilahkan tunggu balasan pesan anda", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddPesanActivity.this, "Pesan gagal dikirim", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<PesanResponse> call, Throwable t) {

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
}
