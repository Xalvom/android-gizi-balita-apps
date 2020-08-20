package com.himman.gizibalita.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.Dev;
import com.himman.gizibalita.Model.DevResponse;
import com.himman.gizibalita.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevViewHolder> {
    @SerializedName("data")
    @Expose
    private List<Dev> listDev;
    private Context context;

    private OnItemClickCallback onItemClickCallback;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public DevAdapter(){}

    public List<Dev> getListDev(){return listDev;}

    public void setListDev(List<Dev> listDev){this.listDev = listDev;}

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack;
    }

    @NonNull
    @Override
    public DevViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gov_items, parent, false);
        return new DevViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DevViewHolder holder, int position) {
        holder.bind(listDev.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listDev.size();
    }

    public class DevViewHolder extends RecyclerView.ViewHolder{

        TextView tvIsiPerkembangan;
        CheckBox chbCek;

        public DevViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIsiPerkembangan = (TextView) itemView.findViewById(R.id.tvIsiPerkembangan);
            chbCek = (CheckBox) itemView.findViewById(R.id.chbIsi);
        }
        void bind(final Dev dev) {
            tvIsiPerkembangan.setText("Balita sudah bisa "+dev.getIsiPerkembangan());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(chbCek.isChecked()){
                        chbCek.setChecked(false);
                    }else{
                        chbCek.setChecked(true);
                    }
                }
            });
        }
    }
    public interface OnItemClickCallback{
        void onItemClicked(Balita data);
    }
}
