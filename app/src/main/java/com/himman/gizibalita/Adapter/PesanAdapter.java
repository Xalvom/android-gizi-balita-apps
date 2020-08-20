package com.himman.gizibalita.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Model.Pesan;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.PesanViewHolder> {
    @SerializedName("data")
    @Expose
    private List<Pesan> listPesan;
    private Context context;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public PesanAdapter(){}

    public List<Pesan> getListPesan(){return listPesan;}

    public void setListPesan(List<Pesan> listPesan){this.listPesan = listPesan;}

    @NonNull
    @Override
    public PesanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesan_items, parent, false);
        return new PesanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PesanViewHolder holder, final int position) {
        holder.bind(listPesan.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listPesan.size();
    }
    public class PesanViewHolder extends RecyclerView.ViewHolder{
        TextView psTgl;
        TextView psPengirim;
        TextView psJudul;
        TextView psPer;
        TextView psIsi;
        TextView psTextPengirim;
        public PesanViewHolder(@NonNull View itemView) {
            super(itemView);
            psTgl = (TextView) itemView.findViewById(R.id.psTanggal);
            psPengirim = (TextView) itemView.findViewById(R.id.psPengirim);
            psTextPengirim = (TextView) itemView.findViewById(R.id.psTextPengirim);
            psJudul = (TextView) itemView.findViewById(R.id.psJudul);
            psPer = (TextView) itemView.findViewById(R.id.psPer);
            psIsi = (TextView) itemView.findViewById(R.id.psIsi);
        }
        void bind(final Pesan pesan){
            psTgl.setText(yearsFormat(pesan.getTanggalPesan()));
            if(pesan.getPenjawab().equals("-")){
                psPengirim.setVisibility(View.INVISIBLE);
                psTextPengirim.setVisibility(View.INVISIBLE);
            }else {
                psPengirim.setText(pesan.getPenjawab());
            }
            psJudul.setText(pesan.getJudulPesan());
            psPer.setText(pesan.getPertanyaan());
            if(pesan.getJawaban().equals("-")){
                psIsi.setText(Html.fromHtml("<b>Pesan Belum Di Jawab</b>"));
            }else{
                psIsi.setText(pesan.getJawaban());
            }
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
}
