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

import com.himman.gizibalita.Adapter.TbuAdapter;
import com.himman.gizibalita.Model.Tbu;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.TbuViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TbuFragment extends Fragment {
    private RecyclerView rvTbu;
    private ProgressBar progressBar;
    private TbuAdapter tbuAdapter;
    private TbuViewModel tbuViewModel;

    public static final String EXTRA_TBU= "extra_tbu";

    public TbuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tbu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTbu = view.findViewById(R.id.rvTbu);
        rvTbu.setLayoutManager(new LinearLayoutManager(getContext()));
        String idbalita = getArguments().getString(EXTRA_TBU);
        devider(rvTbu);
        tbuViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TbuViewModel.class);
        tbuViewModel.setListTbu(idbalita, getContext());

        tbuViewModel.getListTbu().observe(getViewLifecycleOwner(), new Observer<List<Tbu>>() {
            @Override
            public void onChanged(List<Tbu> tbus) {
                tbuAdapter = new TbuAdapter();
                tbuAdapter.notifyDataSetChanged();
                tbuAdapter.setListTbu(tbus);
                rvTbu.setAdapter(tbuAdapter);
            }
        });
    }

    private void devider(RecyclerView rvMovie){
        DividerItemDecoration divider = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.divider));
        rvMovie.addItemDecoration(divider);
    }
}
