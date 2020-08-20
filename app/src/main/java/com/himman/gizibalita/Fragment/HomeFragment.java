package com.himman.gizibalita.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.himman.gizibalita.Activity.AddBalitaActivity;
import com.himman.gizibalita.Activity.AddBbtbActivity;
import com.himman.gizibalita.Activity.AddBbuActivity;
import com.himman.gizibalita.Activity.AddTbuActivity;
import com.himman.gizibalita.Activity.CekGovActivity;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.HomeViewModel;

public class HomeFragment extends Fragment {

    private CardView CardBBu, CardTBu, CardBBtb, CardGov;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton addData = root.findViewById(R.id.addData);
//        CardBBu = (CardView) root.findViewById(R.id.sh_bbu);
//        CardBBu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddBbuActivity.class);
//                startActivity(i);
//            }
//        });
//        CardTBu = root.findViewById(R.id.sh_tbu);
//        CardTBu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddTbuActivity.class);
//                startActivity(i);
//            }
//        });
//        CardBBtb = root.findViewById(R.id.sh_bbtb);
//        CardBBtb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddBbtbActivity.class);
//                startActivity(i);
//            }
//        });
//        CardGov = root.findViewById(R.id.sh_gov);
//        CardGov.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), CekGovActivity.class);
//                startActivity(i);
//            }
//        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddBalitaActivity.class);
                startActivity(i);
            }
        });

        return root;
    }
}
