package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Tbu implements Parcelable {
    @SerializedName("kelamin")
    private String kelamin;

    @SerializedName("id_gizitbu")
    private String idGizitbu;

    @SerializedName("umur")
    private String umur;

    @SerializedName("nama_balita")
    private String namaBalita;

    @SerializedName("tinggi_badan")
    private String tinggiBadan;

    @SerializedName("id_balita")
    private String idBalita;

    @SerializedName("z-score")
    private String zScore;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("update_tbu")
    private String updatetbu;

    public Tbu(){}

    public void setKelamin(String kelamin){
        this.kelamin = kelamin;
    }

    public String getKelamin(){
        return kelamin;
    }

    public void setIdGizitbu(String idGizitbu){
        this.idGizitbu = idGizitbu;
    }

    public String getIdGizitbu(){
        return idGizitbu;
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

    public void setTinggiBadan(String tinggiBadan){
        this.tinggiBadan = tinggiBadan;
    }

    public String getTinggiBadan(){
        return tinggiBadan;
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

    public void setUpdateTbu(String updateTbu){
        this.updatetbu = updateTbu;
    }

    public String getUpdateTbu(){
        return updatetbu;
    }

    protected Tbu(Parcel in){
        kelamin = in.readString();
        namaBalita = in.readString();
        idUser = in.readString();
        idBalita = in.readString();
        idGizitbu = in.readString();
        zScore = in.readString();
        updatetbu = in.readString();
        tinggiBadan = in.readString();
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
        parcel.writeString(idGizitbu);
        parcel.writeString(umur);
        parcel.writeString(tinggiBadan);
        parcel.writeString(zScore);
        parcel.writeString(updatetbu);
    }

    public static final Parcelable.Creator<Tbu> CREATOR = new Creator<Tbu>() {
        @Override
        public Tbu createFromParcel(Parcel parcel) {
            return new Tbu(parcel);
        }

        @Override
        public Tbu[] newArray(int size) {
            return new Tbu[size];
        }
    };
}
