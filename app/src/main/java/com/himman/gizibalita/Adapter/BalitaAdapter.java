package com.himman.gizibalita.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Activity.BalitaActivity;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BalitaAdapter extends RecyclerView.Adapter<BalitaAdapter.BalitaViewHolder> {
    @SerializedName("data")
    @Expose
    private List<Balita> listBalita;
    private Context context;

    private OnItemClickCallback onItemClickCallback;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public BalitaAdapter(){}

    public List<Balita> getListBalita(){return listBalita;}

    public void setListBalita(List<Balita> listBalita){this.listBalita = listBalita;}

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack;
    }

    @NonNull
    @Override
    public BalitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.balita_items, parent, false);
        return new BalitaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BalitaViewHolder holder, int position) {
        holder.bind(listBalita.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listBalita.size();
    }


    public class BalitaViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaBalita;
        TextView tvAlamatBalita;
        TextView tvTglBalita;
        TextView tvKelaminBalita;

        public BalitaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaBalita = (TextView) itemView.findViewById(R.id.tvNamaBalita);
            tvAlamatBalita = (TextView) itemView.findViewById(R.id.tvAlamatBalita);
            tvTglBalita = (TextView) itemView.findViewById(R.id.tvTglBalita);
            tvKelaminBalita =  (TextView) itemView.findViewById(R.id.tvKelaminBalita);
        }
        void bind(final Balita balita){
            tvNamaBalita.setText(balita.getNamaBalita());
            tvAlamatBalita.setText("RT: " + balita.getRtBalita() + ", RW: " + balita.getRwBalita() + ", Dusun: " + balita.getAlamatBalita());
            tvTglBalita.setText(yearsFormat(balita.getTgllahirBalita()));
            tvKelaminBalita.setText(kelamin(balita.getKelaminBalita()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickCallback.onItemClicked(listBalita.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Balita data);
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
