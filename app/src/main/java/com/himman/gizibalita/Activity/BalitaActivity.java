package com.himman.gizibalita.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.himman.gizibalita.Adapter.BalitaAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.BalitaViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalitaActivity extends AppCompatActivity {
//    private ProgressBar progressBar;
//    private RecyclerView rvBalita;
//    private BalitaViewModel balitaViewModel;
//    private BalitaAdapter adapter;
//    private final static String SEND_DATA = "SEND";
//    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_balita);
//        balitaViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(BalitaViewModel.class);
//        rvBalita = findViewById(R.id.rv_balita);
//        rvBalita.setLayoutManager(new LinearLayoutManager(this));
//        rvBalita.setHasFixedSize(true);
//        devider(rvBalita);
//
//        progressBar = findViewById(R.id.progressBar);
//        sharedPrefManager = new SharedPrefManager(getApplicationContext());
//        String idUser = sharedPrefManager.getSpIduser();
//
//        progressBar.setVisibility(View.INVISIBLE);
//        balitaViewModel.setListBalita(idUser, getApplicationContext());
//
//        getBalita(idUser);
//        balitaViewModel.getListBalita().observe((LifecycleOwner) getViewModelStore(), list ->{
//            adapter = new BalitaAdapter();
//            adapter.setListBalita(list);
//            rvBalita.setAdapter(adapter);
//            progressBar.setVisibility(View.INVISIBLE);

//            adapter.setOnItemClickCallback((Balita data) -> {
////                showLoading(true);
//                Intent i = new Intent(getApplicationContext(), DetailBalitaActivity.class);
//                i.putExtra("namaBalita", data.getNamaBalita());
//                i.putExtra("idBalita", data.getIdBalita());
//                i.putExtra("alamatBalita", data.getAlamatBalita());
//                i.putExtra("kelaminBalita", data.getKelaminBalita());
//                i.putExtra("tglLahir", data.getTgllahirBalita());
//                startActivity(i);
//            });
//        });
    }

//    public void getBalita(String idUser){
//        ApiInterface Service;
//        retrofit2.Call<BalitaResponse> Call;
//
//        try{
//            Service = Api.getApi().create(ApiInterface.class);
//            Call = Service.getListBalita(idUser);
//            Call.enqueue(new Callback<BalitaResponse>() {
//                @Override
//                public void onResponse(retrofit2.Call<BalitaResponse> call, Response<BalitaResponse> response) {
//                    Log.d("Response", ""+""+response.body());
//                    if(response.isSuccessful()){
//                        List<Balita> list = response.body().getMData();
//                        BalitaAdapter adapters = new BalitaAdapter(list, BalitaActivity.this);
//
//                        adapters.setOnItemClickCallback((Balita data) -> {
//                            Intent i = new Intent(getApplicationContext(), DetailBalitaActivity.class);
//                            i.putExtra("namaBalita", data.getNamaBalita());
//                            i.putExtra("idBalita", data.getIdBalita());
//                            i.putExtra("alamatBalita", data.getAlamatBalita());
//                            i.putExtra("kelaminBalita", data.getKelaminBalita());
//                            i.putExtra("tglLahir", data.getTgllahirBalita());
//                            startActivity(i);
//                        });
//                    }
//                }
//
//                @Override
//                public void onFailure(retrofit2.Call<BalitaResponse> call, Throwable t) {
//
//                }
//            });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    private void devider(RecyclerView rvMovie){
//        DividerItemDecoration divider = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
//        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
//        rvMovie.addItemDecoration(divider);
//    }
}
