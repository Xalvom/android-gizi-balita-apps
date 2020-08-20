package com.himman.gizibalita.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Model.Bbtb;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BbtbAdapter extends RecyclerView.Adapter<BbtbAdapter.BbtbViewHolder> {
    @SerializedName("data")
    @Expose
    private Context context;
    private List<Bbtb> listBbtb;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public BbtbAdapter(){}
    public List<Bbtb> getListBbtb(){return listBbtb;}

    public void setListBbtb(List<Bbtb> listBbtb) {
        this.listBbtb = listBbtb;
    }

    @NonNull
    @Override
    public BbtbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbtb_items, parent, false);
        return new BbtbViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BbtbViewHolder holder, int position) {
        holder.bind(listBbtb.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listBbtb.size();
    }

    public class BbtbViewHolder extends RecyclerView.ViewHolder{
        TextView updateBbtb;
        TextView kelaminBbtb;
        TextView beratBbtb;
        TextView tinggiBbtb;
        TextView statusGiziBbtb;
        public BbtbViewHolder(@NonNull View itemView) {
            super(itemView);
            updateBbtb = (TextView) itemView.findViewById(R.id.bbtbUpdate);
            beratBbtb = (TextView) itemView.findViewById(R.id.bbtbBerat);
            kelaminBbtb = (TextView) itemView.findViewById(R.id.bbtbKelamin);
            tinggiBbtb = (TextView) itemView.findViewById(R.id.bbtbTinggi);
            statusGiziBbtb =  (TextView) itemView.findViewById(R.id.bbtbStatusGizi);
        }
        void bind (final Bbtb bbtb){
            updateBbtb.setText(yearsFormat(bbtb.getUpdateBbtb()));
            beratBbtb.setText(bbtb.getBeratBadan());
            kelaminBbtb.setText(kelamin(bbtb.getKelamin()));
            tinggiBbtb.setText(bbtb.getTinggiBadan());
            statusGizi(bbtb.getZScore());
        }
        @SuppressLint("ResourceAsColor")
        private void statusGizi(String zscore){
            if (zscore.equals("Normal")){
                statusGiziBbtb.setBackgroundResource(R.color.colorGreen);
            }
            else if (zscore.equals("Gemuk")){
                statusGiziBbtb.setBackgroundResource(R.color.colorBlue);
            }else if(zscore.equals("Kurus")){
                statusGiziBbtb.setTextColor(R.color.colorBlack);
                statusGiziBbtb.setBackgroundResource(R.color.colorYellow);
            }else{
                statusGiziBbtb.setBackgroundResource(R.color.colorRed);
            }
            String usrScor = zscore;
            statusGiziBbtb.setText(usrScor);
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
