package com.himman.gizibalita.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.himman.gizibalita.Adapter.BalitaAdapter;
import com.himman.gizibalita.Adapter.BbuAdapter;
import com.himman.gizibalita.Model.Bbu;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.BbuViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BbuFragment extends Fragment {
    private RecyclerView rvBbu;
    private ProgressBar progressBar;
    private BbuAdapter bbuAdapter;
    private BbuViewModel bbuViewModel;

    public static final String EXTRA_BBU= "extra_bbu";

    public BbuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bbu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBbu = view.findViewById(R.id.rvBbu);
        rvBbu.setLayoutManager(new LinearLayoutManager(getContext()));
        String idbalita = getArguments().getString(EXTRA_BBU);
        devider(rvBbu);
        bbuViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BbuViewModel.class);
        bbuViewModel.setListBbu(idbalita, getContext());

        bbuViewModel.getListBbu().observe(getViewLifecycleOwner(), new Observer<List<Bbu>>() {
            @Override
            public void onChanged(List<Bbu> bbus) {
                bbuAdapter = new BbuAdapter();
                bbuAdapter.notifyDataSetChanged();
                bbuAdapter.setListBbu(bbus);
                rvBbu.setAdapter(bbuAdapter);
            }
        });
    }

    private void devider(RecyclerView rvMovie){
        DividerItemDecoration divider = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        rvMovie.addItemDecoration(divider);
    }
}
