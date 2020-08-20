package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.himman.gizibalita.Adapter.ArtikelAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Artikel;
import com.himman.gizibalita.Model.ArtikelResponse;
import com.himman.gizibalita.R;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelActivity extends AppCompatActivity {
    private RecyclerView rvArtikel;
    private ArtikelAdapter artikelAdapter;
    private ProgressBar progressBar;
    private Context mContext;
    private ApiInterface service;
    private final static String KIRIM_DATA = "KIRIM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        getSupportActionBar().setTitle("Artikel Pengetahuan");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this;
        initListiner();
        devider(rvArtikel);
        initArtikel();
    }
    public void initListiner(){
        //Inisialiasi Variabel
        rvArtikel = findViewById(R.id.rv_artikel);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvArtikel.setLayoutManager(mLayoutManager);
        progressBar = findViewById(R.id.progressBar);

    }
    public void initArtikel(){
        retrofit2.Call<ArtikelResponse> call;
        try{
            service = Api.getApi().create(ApiInterface.class);
            call = service.getArtikelData();
            call.enqueue(new Callback<ArtikelResponse>() {
                @Override
                public void onResponse(Call<ArtikelResponse> call, Response<ArtikelResponse> response) {
                    if(response.isSuccessful()) {
                        List<Artikel> artikels = response.body().getMData();
                        artikelAdapter = new ArtikelAdapter();
                        artikelAdapter.setListArtikel(artikels);
                        rvArtikel.setAdapter(artikelAdapter);
                        artikelAdapter.setOnItemClickCallback(new ArtikelAdapter.OnItemClickCallback() {
                            @Override
                            public void onItemClicked(Artikel data) {
                                Intent detail = new Intent(mContext, DetailArtikelActivity.class);
                                detail.putExtra(KIRIM_DATA, data);
                                startActivity(detail);
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<ArtikelResponse> call, Throwable t) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void devider(RecyclerView rvArtikel){
        DividerItemDecoration divider = new DividerItemDecoration(Objects.requireNonNull(getApplication()), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        rvArtikel.addItemDecoration(divider);
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
