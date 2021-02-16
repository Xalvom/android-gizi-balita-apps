package com.himman.gizibalita.Fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himman.gizibalita.Activity.AddBalitaActivity;
import com.himman.gizibalita.Activity.DetailArtikelActivity;
import com.himman.gizibalita.Activity.DetailBalitaActivity;
import com.himman.gizibalita.Activity.GiziBalitaActivity;
import com.himman.gizibalita.Adapter.ArtikelAdapter;
import com.himman.gizibalita.Adapter.BalitaAdapter;
import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Artikel;
import com.himman.gizibalita.Model.ArtikelResponse;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.BalitaViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalitaFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView rvBalita;
    private ImageView emptyBalita;
    private TextView textBalita;
    private BalitaViewModel balitaViewModel;
    private BalitaAdapter adapter;
    private final static String SEND_DATA = "SEND";
    private SharedPrefManager sharedPrefManager;
    private RecyclerView rvArtikel;
    private ArtikelAdapter artikelAdapter;
    private ApiInterface service;
    private final static String KIRIM_DATA = "KIRIM";
    private TextView tvName;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balita, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        balitaViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(BalitaViewModel.class);
        rvBalita = view.findViewById(R.id.rv_balita);
        rvBalita.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBalita.setHasFixedSize(true);


        tvName = view.findViewById(R.id.tvAkun);
        emptyBalita = view.findViewById(R.id.balita_not_found);
        textBalita = view.findViewById(R.id.tv_balita_kosong);
        FloatingActionButton addData = view.findViewById(R.id.addData);
        sharedPrefManager = new SharedPrefManager(getContext());
        tvName.setText(sharedPrefManager.getSPNama());
        String idUser = sharedPrefManager.getSpIduser();
        rvArtikel = view.findViewById(R.id.rv_artikelB);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvArtikel.setLayoutManager(mLayoutManager);
        initArtikel();

//        showLoading(false);
        showImage(true);
        showText(true);
        balitaViewModel.setListBalita(idUser, getContext());
        balitaViewModel.getListBalita().observe(getViewLifecycleOwner(), list ->{
            adapter = new BalitaAdapter();
            adapter.setListBalita(list);
            rvBalita.setAdapter(adapter);
//            showLoading(false);
            showImage(false);
            showText(false);
            adapter.setOnItemClickCallback((Balita data) -> {
//                showLoading(true);
                Intent i = new Intent(getContext(), GiziBalitaActivity.class);
                i.putExtra("namaBalita", data.getNamaBalita());
                i.putExtra("idBalita", data.getIdBalita());
                i.putExtra("alamatBalita", data.getAlamatBalita());
                i.putExtra("rtBalita", data.getRtBalita());
                i.putExtra("rwBalita", data.getRwBalita());
                i.putExtra("kelaminBalita", data.getKelaminBalita());
                i.putExtra("tglLahir", data.getTgllahirBalita());
                i.putExtra("namaOrtu", data.getNamaOrangtua());
                startActivity(i);
            });
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddBalitaActivity.class);
                startActivity(i);
            }
        });
    }

//    private void showLoading(Boolean state){
//        if (state) {
//            progressBar.setVisibility(View.VISIBLE);
//        } else {
//            progressBar.setVisibility(View.INVISIBLE);
//        }
//    }
//
    private void showImage(Boolean state){
        if(state){
            emptyBalita.setVisibility(View.VISIBLE);
        }else{
            emptyBalita.setVisibility(View.GONE);
        }
    }

    private void showText(Boolean state){
        if(state){
            textBalita.setVisibility(View.VISIBLE);
        }else{
            textBalita.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//        showLoading(false);
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//    }

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
                                Intent detail = new Intent(getContext(), DetailArtikelActivity.class);
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
//
//    private void devider(RecyclerView rvMovie){
//        DividerItemDecoration divider = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
//        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
//        rvMovie.addItemDecoration(divider);
//    }
}
