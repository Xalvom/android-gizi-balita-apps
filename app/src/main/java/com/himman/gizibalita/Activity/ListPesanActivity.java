package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.himman.gizibalita.Adapter.PesanAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Pesan;
import com.himman.gizibalita.Model.PesanResponse;
import com.himman.gizibalita.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPesanActivity extends AppCompatActivity {
    private RecyclerView rvPesan;
    private TextView emptyPesan;
    private PesanAdapter pesanAdapter;
    private SharedPrefManager sharedPrefManager;
    private Context mContext;
    private ApiInterface Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesan);
        getSupportActionBar().setTitle("Daftar Konsultasi ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this;
        rvPesan = findViewById(R.id.rv_pesan);
        emptyPesan = findViewById(R.id.emptyPesan);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvPesan.setLayoutManager(mLayoutManager);
        FloatingActionButton addPesan = findViewById(R.id.addPesan);
        sharedPrefManager = new SharedPrefManager(this);
        String idUser = sharedPrefManager.getSpIduser();
        initPesan(idUser);
        addPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, AddPesanActivity.class);
                startActivity(i);
            }
        });
    }
    public void initPesan(String idUser){
        retrofit2.Call<PesanResponse> Call;

        try {
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getListPesan(idUser);
            Call.enqueue(new Callback<PesanResponse>() {
                @Override
                public void onResponse(retrofit2.Call<PesanResponse> call, Response<PesanResponse> response) {
                    if(response.isSuccessful()){
                        List<Pesan> pesans  = response.body().getMData();
                        pesanAdapter = new PesanAdapter();
                        pesanAdapter.setListPesan(pesans);
                        rvPesan.setAdapter(pesanAdapter);
                        emptyPesan.setVisibility(View.GONE);
                        rvPesan.setVisibility(View.VISIBLE);
                    }else{
                        emptyPesan.setVisibility(View.VISIBLE);
                        rvPesan.setVisibility(View.GONE);
                        Toast.makeText(mContext, "Data Pesan Tidak Ada", Toast.LENGTH_LONG).show();
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
