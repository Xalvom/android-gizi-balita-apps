package com.himman.gizibalita.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.himman.gizibalita.Model.Artikel;
import com.himman.gizibalita.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder> {
    @SerializedName("data")
    @Expose
    private List<Artikel> listArtikel;
    private Context context;
    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    private OnItemClickCallback onItemClickCallback;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public ArtikelAdapter(){}

    public List<Artikel> getListArtikel(){return listArtikel;}

    public void setListArtikel(List<Artikel> listArtikel){this.listArtikel = listArtikel;}

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack;
    }
    @NonNull
    @Override
    public ArtikelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.artikel_items, parent, false);
        return new ArtikelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelViewHolder holder, int position) {
        holder.bind(listArtikel.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listArtikel.size();
    }

    public class ArtikelViewHolder extends RecyclerView.ViewHolder{
        TextView jArtikel;
        TextView iArtikel;
        TextView tArtikel;
        TextView aArtikel;
        ImageView iPoster;

        public ArtikelViewHolder(@NonNull View itemView) {
            super(itemView);
            jArtikel = itemView.findViewById(R.id.it_title);
//            iArtikel = itemView.findViewById(R.id.it_overview);
            tArtikel = itemView.findViewById(R.id.it_years);
//            aArtikel = itemView.findViewById(R.id.it_author);
            iPoster = itemView.findViewById(R.id.it_poster);
        }
        @SuppressLint("ResourceAsColor")
        void bind(final Artikel artikel){
            iPoster.setBackgroundColor(getColor());
            jArtikel.setText(artikel.getJudulArtikel());
//            iArtikel.setText(Html.fromHtml(artikel.getIsiArtikel()));
            tArtikel.setText(yearsFormat(artikel.getCreatedAt()));
//            aArtikel.setText(artikel.getNamaUser());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickCallback.onItemClicked(artikel);
                }
            });
        }
    }
    public interface OnItemClickCallback{
        void onItemClicked(Artikel data);
    }
    private String yearsFormat(String dateToyears){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateInString = dateToyears;
        try {
            Date date = format.parse(dateInString);
            SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id"));
            dateInString = df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateInString;
    }
    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }
}
