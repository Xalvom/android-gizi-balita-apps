package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balita implements Parcelable {
    @SerializedName("alamat_balita")
    @Expose
    private String alamatBalita;
    @SerializedName("nama_balita")
    @Expose
    private String namaBalita;
    @SerializedName("id_balita")
    @Expose
    private String idBalita;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("tgllahir_balita")
    @Expose
    private String tgllahirBalita;
    @SerializedName("kelamin_balita")
    @Expose
    private String kelaminBalita;
    @SerializedName("nama_orangtua")
    @Expose
    private String namaOrangtua;
    @SerializedName("beratbadan_balita")
    @Expose
    private String beratbadanBalita;
    @SerializedName("tinggibadan_balita")
    @Expose
    private String tinggibadanBalita;
    @SerializedName("update_balita")
    @Expose
    private String updateBalita;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("rt_balita")
    @Expose
    private String rtBalita;
    @SerializedName("rw_balita")
    @Expose
    private String rwBalita;

    public Balita(String alamatBalita, String namaBalita, String idBalita, String idUser, String tgllahirBalita, String kelaminBalita, String namaOrangtua, String beratbadanBalita, String tinggibadanBalita, String updateBalita, String createdAt, String rtBalita, String rwBalita){
        this.alamatBalita = alamatBalita;
        this.namaBalita = namaBalita;
        this.idBalita = idBalita;
        this.idUser = idUser;
        this.tgllahirBalita = tgllahirBalita;
        this.kelaminBalita = kelaminBalita;
        this.namaOrangtua = namaOrangtua;
        this.beratbadanBalita = beratbadanBalita;
        this.tinggibadanBalita = tinggibadanBalita;
        this.updateBalita = updateBalita;
        this.createdAt = createdAt;
        this.rtBalita = rtBalita;
        this.rwBalita = rwBalita;
    }
    public Balita(){

    }

    public void setAlamatBalita(String alamatBalita){
        this.alamatBalita = alamatBalita;
    }

    public String getAlamatBalita(){
        return alamatBalita;
    }

    public void setNamaBalita(String namaBalita){
        this.namaBalita = namaBalita;
    }

    public String getNamaBalita(){
        return namaBalita;
    }

    public void setIdBalita(String idBalita){
        this.idBalita = idBalita;
    }

    public String getIdBalita(){
        return idBalita;
    }

    public void setIdUser(String idUser){
        this.idUser = idUser;
    }

    public String getIdUser(){
        return idUser;
    }

    public void setTgllahirBalita(String tgllahirBalita){
        this.tgllahirBalita = tgllahirBalita;
    }

    public String getTgllahirBalita(){
        return tgllahirBalita;
    }

    public void setKelaminBalita(String kelaminBalita){
        this.kelaminBalita = kelaminBalita;
    }

    public String getKelaminBalita(){
        return kelaminBalita;
    }

    public void setNamaOrangtua(String namaOrangtua){
        this.namaOrangtua = namaOrangtua;
    }

    public String getNamaOrangtua(){
        return namaOrangtua;
    }

    public void setBeratbadanBalita(String beratbadanBalita){
        this.beratbadanBalita = beratbadanBalita;
    }

    public String getBeratbadanBalita(){
        return beratbadanBalita;
    }

    public void setTinggibadanBalita(String tinggibadanBalita){
        this.tinggibadanBalita = tinggibadanBalita;
    }

    public String getTinggibadanBalita(){
        return tinggibadanBalita;
    }

    public void setUpdateBalita(String updateBalita){
        this.updateBalita = updateBalita;
    }

    public String getUpdateBalita(){
        return updateBalita;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setRtBalita(String rtBalita){
        this.rtBalita = rtBalita;
    }

    public String getRtBalita(){
        return rtBalita;
    }

    public void setRwBalita(String rwBalita){
        this.rwBalita = rwBalita;
    }

    public String getRwBalita(){
        return rwBalita;
    }

    protected Balita(Parcel in){
        alamatBalita = in.readString();
        namaBalita = in.readString();
        idUser = in.readString();
        idBalita = in.readString();
        tgllahirBalita = in.readString();
        kelaminBalita = in.readString();
        namaOrangtua = in.readString();
        beratbadanBalita = in.readString();
        tinggibadanBalita = in.readString();
        updateBalita = in.readString();
        createdAt = in.readString();
        rtBalita = in.readString();
        rwBalita = in.readString();
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
        parcel.writeString(kelaminBalita);
        parcel.writeString(tgllahirBalita);
        parcel.writeString(alamatBalita);
        parcel.writeString(namaOrangtua);
        parcel.writeString(beratbadanBalita);
        parcel.writeString(tinggibadanBalita);
        parcel.writeString(updateBalita);
        parcel.writeString(createdAt);
        parcel.writeString(rtBalita);
        parcel.writeString(rwBalita);

    }

    public static Creator<Balita> CREATOR = new Creator<Balita>(){
        @Override
        public Balita createFromParcel(Parcel parcel) {
            return new Balita(parcel);
        }

        @Override
        public Balita[] newArray(int size) {
            return new Balita[size];
        }
    };
}
