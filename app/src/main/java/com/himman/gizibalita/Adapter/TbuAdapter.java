package com.himman.gizibalita.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Model.Bbu;
import com.himman.gizibalita.Model.Tbu;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TbuAdapter extends RecyclerView.Adapter<TbuAdapter.TbuViewHolder> {
    @SerializedName("data")
    @Expose
    private Context context;
    private List<Tbu> listTbu;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public TbuAdapter(){}
    public List<Tbu> getListTbu(){return listTbu;}
    public void setListTbu(List<Tbu> listTbu){this.listTbu = listTbu;}


    @NonNull
    @Override
    public TbuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tbu_items, parent, false);
        return new TbuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TbuViewHolder holder, int position) {
        holder.bind(listTbu.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listTbu.size();
    }
    public class TbuViewHolder extends RecyclerView.ViewHolder {
        TextView updateTbu;
        TextView umurTbu;
        TextView kelaminTbu;
        TextView tinggiTbu;
        TextView statusGiziTbu;
        TextView tvTitleTb;
        public TbuViewHolder(@NonNull View itemView) {
            super(itemView);
            updateTbu = (TextView) itemView.findViewById(R.id.tbuUpdate);
            umurTbu = (TextView) itemView.findViewById(R.id.tbuUmur);
            kelaminTbu = (TextView) itemView.findViewById(R.id.tbuKelamin);
            tinggiTbu = (TextView) itemView.findViewById(R.id.tbuTinggi);
            statusGiziTbu =  (TextView) itemView.findViewById(R.id.tbuStatusGizi);
            tvTitleTb = (TextView) itemView.findViewById(R.id.tvTitleTb);
        }
        void bind (final Tbu tbu){
            updateTbu.setText(yearsFormat(tbu.getUpdateTbu()));
            umurTbu.setText(tbu.getUmur() + " Bulan");
            kelaminTbu.setText(kelamin(tbu.getKelamin()));
            tinggiTbu.setText(tbu.getTinggiBadan() + " cm");
//            statusGiziBbu.setText(bbu.getZScore());
            statusGizi(tbu.getZScore());
            int TitleTb = Integer.parseInt(tbu.getUmur());
            tvTitleTb.setText(umur(TitleTb));
        }
        @SuppressLint("ResourceAsColor")
        private void statusGizi(String zscore){
            if (zscore.equals("Normal")){
                statusGiziTbu.setBackgroundResource(R.color.colorGreen);
            }
            else if (zscore.equals("Tinggi")){
                statusGiziTbu.setBackgroundResource(R.color.colorBlue);
            }else if(zscore.equals("Pendek")){
                statusGiziTbu.setTextColor(R.color.colorBlack);
                statusGiziTbu.setBackgroundResource(R.color.colorYellow);
            }else{
                statusGiziTbu.setBackgroundResource(R.color.colorRed);
            }
            String usrScor = zscore;
            statusGiziTbu.setText(usrScor);
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
    private String umur(int tvUmur) {
        String changeTvUmur;
        if (tvUmur > 24) {
            changeTvUmur = "Tinggi Badan";
        } else {
            changeTvUmur = "Panjang Badan";
        }
        return changeTvUmur;
    }
}
