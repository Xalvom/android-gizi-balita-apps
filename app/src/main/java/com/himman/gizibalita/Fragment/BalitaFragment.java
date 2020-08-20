package com.himman.gizibalita.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.himman.gizibalita.Activity.AddBalitaActivity;
import com.himman.gizibalita.Activity.DetailBalitaActivity;
import com.himman.gizibalita.Activity.GiziBalitaActivity;
import com.himman.gizibalita.Adapter.BalitaAdapter;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.BalitaViewModel;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BalitaFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView rvBalita;
    private TextView emptyBalita;
    private BalitaViewModel balitaViewModel;
    private BalitaAdapter adapter;
    private final static String SEND_DATA = "SEND";
    private SharedPrefManager sharedPrefManager;


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

        progressBar = view.findViewById(R.id.progressBar);
        emptyBalita = view.findViewById(R.id.emptyBalita);
        FloatingActionButton addData = view.findViewById(R.id.addData);
        sharedPrefManager = new SharedPrefManager(getContext());
        String idUser = sharedPrefManager.getSpIduser();

        showLoading(false);
        showEmpty(true);
        balitaViewModel.setListBalita(idUser, getContext());
        balitaViewModel.getListBalita().observe(getViewLifecycleOwner(), list ->{
            adapter = new BalitaAdapter();
            adapter.setListBalita(list);
            rvBalita.setAdapter(adapter);
            showLoading(false);
            showEmpty(false);
            adapter.setOnItemClickCallback((Balita data) -> {
                showLoading(true);
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

    private void showLoading(Boolean state){
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void showEmpty(Boolean state){
        if(state){
            emptyBalita.setVisibility(View.VISIBLE);
        }else{
            emptyBalita.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoading(false);
    }
//
//    private void devider(RecyclerView rvMovie){
//        DividerItemDecoration divider = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
//        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
//        rvMovie.addItemDecoration(divider);
//    }
}
