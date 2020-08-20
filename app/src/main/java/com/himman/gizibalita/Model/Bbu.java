package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Bbu implements Parcelable{

    @SerializedName("kelamin")
    private String kelamin;

    @SerializedName("id_gizibbu")
    private String idGizibbu;

    @SerializedName("umur")
    private String umur;

    @SerializedName("nama_balita")
    private String namaBalita;

    @SerializedName("berat_badan")
    private String beratBadan;

    @SerializedName("id_balita")
    private String idBalita;

    @SerializedName("z-score")
    private String zScore;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("update_bbu")
    private String updateBbu;

    public Bbu(){}

    public void setKelamin(String kelamin){
        this.kelamin = kelamin;
    }

    public String getKelamin(){
        return kelamin;
    }

    public void setIdGizibbu(String idGizibbu){
        this.idGizibbu = idGizibbu;
    }

    public String getIdGizibbu(){
        return idGizibbu;
    }

    public void setUmur(String umur){
        this.umur = umur;
    }

    public String getUmur(){
        return umur;
    }

    public void setNamaBalita(String namaBalita){
        this.namaBalita = namaBalita;
    }

    public String getNamaBalita(){
        return namaBalita;
    }

    public void setBeratBadan(String beratBadan){
        this.beratBadan = beratBadan;
    }

    public String getBeratBadan(){
        return beratBadan;
    }

    public void setIdBalita(String idBalita){
        this.idBalita = idBalita;
    }

    public String getIdBalita(){
        return idBalita;
    }

    public void setZScore(String zScore){
        this.zScore = zScore;
    }

    public String getZScore(){
        return zScore;
    }

    public void setIdUser(String idUser){
        this.idUser = idUser;
    }

    public String getIdUser(){
        return idUser;
    }

    public void setUpdateBbu(String updateBbu){
        this.updateBbu = updateBbu;
    }

    public String getUpdateBbu(){
        return updateBbu;
    }

    protected Bbu(Parcel in){
        kelamin = in.readString();
        namaBalita = in.readString();
        idUser = in.readString();
        idBalita = in.readString();
        idGizibbu = in.readString();
        zScore = in.readString();
        updateBbu = in.readString();
        beratBadan = in.readString();
        umur = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idUser);
        parcel.writeString(namaBalita);
        parcel.writeString(idBalita);
        parcel.writeString(kelamin);
        parcel.writeString(idGizibbu);
        parcel.writeString(umur);
        parcel.writeString(beratBadan);
        parcel.writeString(zScore);
        parcel.writeString(updateBbu);
    }

    public static final Parcelable.Creator<Bbu> CREATOR = new Parcelable.Creator<Bbu>() {
        @Override
        public Bbu createFromParcel(Parcel parcel) {
            return new Bbu(parcel);
        }

        @Override
        public Bbu[] newArray(int size) {
            return new Bbu[size];
        }
    };
}
