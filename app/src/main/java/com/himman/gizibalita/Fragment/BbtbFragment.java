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

import com.himman.gizibalita.Adapter.BbtbAdapter;
import com.himman.gizibalita.Model.Bbtb;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.BbtbViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BbtbFragment extends Fragment {
    private RecyclerView rvBbtb;
    private ProgressBar progressBar;
    private BbtbAdapter bbtbAdapter;
    private BbtbViewModel bbtbViewModel;

    public static final String EXTRA_BBTB= "extra_bbtb";

    public BbtbFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bbtb, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBbtb = view.findViewById(R.id.rvBbtb);
        rvBbtb.setLayoutManager(new LinearLayoutManager(getContext()));
        String idbalita = getArguments().getString(EXTRA_BBTB);
        devider(rvBbtb);
        bbtbViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(BbtbViewModel.class);
        bbtbViewModel.setListBbtb(idbalita, getContext());

        bbtbViewModel.getListBbtb().observe(getViewLifecycleOwner(), new Observer<List<Bbtb>>() {
            @Override
            public void onChanged(List<Bbtb> bbtbs) {
                bbtbAdapter = new BbtbAdapter();
                bbtbAdapter.notifyDataSetChanged();
                bbtbAdapter.setListBbtb(bbtbs);
                rvBbtb.setAdapter(bbtbAdapter);
            }
        });
    }
    private void devider(RecyclerView rvMovie){
        DividerItemDecoration divider = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        rvMovie.addItemDecoration(divider);
    }
}
