package com.himman.gizibalita.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Model.Bbu;
import com.himman.gizibalita.R;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BbuAdapter extends RecyclerView.Adapter<BbuAdapter.BbuViewHolder> {
    @SerializedName("data")
    @Expose
    private Context context;
    private List<Bbu> listBbu;

    private BalitaAdapter.OnItemClickCallback onItemClickCallback;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public BbuAdapter(){}
    public List<Bbu> getListBbu(){return listBbu;}
    public void setListBbu(List<Bbu> listBbu){this.listBbu = listBbu;}

    public void setOnItemClickCallback(BalitaAdapter.OnItemClickCallback onItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack;
    }
    @NonNull
    @Override
    public BbuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbu_items, parent, false);
        return new BbuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BbuViewHolder holder, int position) {
//        Resources res = holder.itemView.getContext().getResources();
        holder.bind(listBbu.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listBbu.size();
    }

    public class BbuViewHolder extends RecyclerView.ViewHolder{
        TextView updateBbu;
        TextView umurBbu;
        TextView kelaminBbu;
        TextView beratBbu;
        TextView statusGiziBbu;

        public BbuViewHolder(@NonNull View itemView) {
            super(itemView);
            updateBbu = (TextView) itemView.findViewById(R.id.buUpdate);
            umurBbu = (TextView) itemView.findViewById(R.id.buUmur);
            kelaminBbu = (TextView) itemView.findViewById(R.id.buKelamin);
            beratBbu = (TextView) itemView.findViewById(R.id.buBerat);
            statusGiziBbu =  (TextView) itemView.findViewById(R.id.buStatusGizi);
        }
        void bind (final Bbu bbu){
            updateBbu.setText(yearsFormat(bbu.getUpdateBbu()));
            umurBbu.setText(bbu.getUmur() + " Bulan");
            kelaminBbu.setText(kelamin(bbu.getKelamin()));
            beratBbu.setText(bbu.getBeratBadan() + " Kg");
//            statusGiziBbu.setText(bbu.getZScore());
            statusGizi(bbu.getZScore());
        }
        @SuppressLint("ResourceAsColor")
        private void statusGizi(String zscore){
            if (zscore.equals("Gizi Baik")){
                statusGiziBbu.setBackgroundResource(R.color.colorGreen);
            }
            else if (zscore.equals("Gizi Lebih")){
                statusGiziBbu.setBackgroundResource(R.color.colorBlue);
            }else if(zscore.equals("Gizi Kurang")){
                statusGiziBbu.setTextColor(R.color.colorBlack);
                statusGiziBbu.setBackgroundResource(R.color.colorYellow);
            }else{
                statusGiziBbu.setBackgroundResource(R.color.colorRed);
            }
            String usrScor = zscore;
            statusGiziBbu.setText(usrScor);
        }
    }

    private String yearsFormat(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
            dateInString = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }

    private String kelamin(String changeKelamin) {
        if (changeKelamin.equals("L")) {
            changeKelamin = "Laki-Laki";
        } else {
            changeKelamin = "Perempuan";
        }
        return changeKelamin;
    }
}
