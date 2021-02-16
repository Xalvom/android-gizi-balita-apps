package com.himman.gizibalita.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.himman.gizibalita.Activity.AddBbuActivity;
import com.himman.gizibalita.Activity.BalitaActivity;
import com.himman.gizibalita.Activity.EditAkunActivity;
import com.himman.gizibalita.Activity.ListPesanActivity;
import com.himman.gizibalita.Activity.LoginActivity;
import com.himman.gizibalita.Api.SharedPrefManager;
import com.himman.gizibalita.R;
import com.himman.gizibalita.ViewModel.AkunViewModel;

public class AkunFragment extends Fragment {

    private CardView CardAkun, CardKonsultasi,  CardDB, CardPasswd, CardLogout;
    private TextView tvUser, tvEmail;
    private SharedPrefManager sharedPrefManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_akun, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPrefManager = new SharedPrefManager(getContext());
        CardAkun = (CardView) view.findViewById(R.id.sh_akunU);
        CardKonsultasi = (CardView) view.findViewById(R.id.sh_akunK);
        CardLogout = (CardView) view.findViewById(R.id.ak_logout);
        tvUser = view.findViewById(R.id.ak_user);
        tvEmail = view.findViewById(R.id.ak_email);
        CardAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditAkunActivity.class);
                startActivity(i);
            }
        });
        CardKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ListPesanActivity.class);
                startActivity(i);
            }
        });
        CardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Apakah anda ingin keluar akun?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                        startActivity(new Intent(getActivity(), LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        tvUser.setText(sharedPrefManager.getSPNama());
        tvEmail.setText(sharedPrefManager.getSPEmail());
    }
}
