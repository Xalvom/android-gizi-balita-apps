package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bbtb implements Parcelable {
    @SerializedName("id_gizibbtb")
    @Expose
    private String idGizibbtb;
    @SerializedName("kelamin")
    @Expose
    private String kelamin;
    @SerializedName("berat_badan")
    @Expose
    private String beratBadan;
    @SerializedName("tinggi_badan")
    @Expose
    private String tinggiBadan;
    @SerializedName("z-score")
    @Expose
    private String zScore;
    @SerializedName("update_bbtb")
    @Expose
    private String updateBbtb;
    @SerializedName("id_balita")
    @Expose
    private String idBalita;
    @SerializedName("nama_balita")
    @Expose
    private String namaBalita;

    public Bbtb(){}

    public String getIdGizibbtb() {
        return idGizibbtb;
    }

    public void setIdGizibbtb(String idGizibbtb) {
        this.idGizibbtb = idGizibbtb;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(String beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getTinggiBadan() {
        return tinggiBadan;
    }

    public void setTinggiBadan(String tinggiBadan) {
        this.tinggiBadan = tinggiBadan;
    }

    public String getZScore() {
        return zScore;
    }

    public void setZScore(String zScore) {
        this.zScore = zScore;
    }

    public String getUpdateBbtb() {
        return updateBbtb;
    }

    public void setUpdateBbtb(String updateBbtb) {
        this.updateBbtb = updateBbtb;
    }

    public String getIdBalita() {
        return idBalita;
    }

    public void setIdBalita(String idBalita) {
        this.idBalita = idBalita;
    }

    public String getNamaBalita() {
        return namaBalita;
    }

    public void setNamaBalita(String namaBalita) {
        this.namaBalita = namaBalita;
    }

    protected Bbtb(Parcel in){
        kelamin = in.readString();
        namaBalita = in.readString();
        idBalita = in.readString();
        idGizibbtb = in.readString();
        zScore = in.readString();
        updateBbtb = in.readString();
        beratBadan = in.readString();
        tinggiBadan = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(namaBalita);
        parcel.writeString(idBalita);
        parcel.writeString(kelamin);
        parcel.writeString(idGizibbtb);
        parcel.writeString(tinggiBadan);
        parcel.writeString(beratBadan);
        parcel.writeString(zScore);
        parcel.writeString(updateBbtb);
    }

    public static final Creator<Bbtb> CREATOR = new Creator<Bbtb>() {
        @Override
        public Bbtb createFromParcel(Parcel in) {
            return new Bbtb(in);
        }

        @Override
        public Bbtb[] newArray(int size) {
            return new Bbtb[size];
        }
    };
}
