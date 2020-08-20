package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Imun implements Parcelable {
    @SerializedName("id_imunisasi")
    @Expose
    private String idImunisasi;
    @SerializedName("hb0")
    @Expose
    private String hb0;
    @SerializedName("bcg")
    @Expose
    private String bcg;
    @SerializedName("polio1")
    @Expose
    private String polio1;
    @SerializedName("dpt1")
    @Expose
    private String dpt1;
    @SerializedName("polio2")
    @Expose
    private String polio2;
    @SerializedName("dpt2")
    @Expose
    private String dpt2;
    @SerializedName("polio3")
    @Expose
    private String polio3;
    @SerializedName("dpt3")
    @Expose
    private String dpt3;
    @SerializedName("polio4")
    @Expose
    private String polio4;
    @SerializedName("ipv")
    @Expose
    private String ipv;
    @SerializedName("campak")
    @Expose
    private String campak;
    @SerializedName("dpt_lanjut")
    @Expose
    private String dptLanjut;
    @SerializedName("campak_lanjut")
    @Expose
    private String campakLanjut;
    @SerializedName("update_imunisasi")
    @Expose
    private String updateImunisasi;
    @SerializedName("id_balita")
    @Expose
    private String idBalita;

    public String getIdImunisasi() {
        return idImunisasi;
    }

    public void setIdImunisasi(String idImunisasi) {
        this.idImunisasi = idImunisasi;
    }

    public String getHb0() {
        return hb0;
    }

    public void setHb0(String hb0) {
        this.hb0 = hb0;
    }

    public String getBcg() {
        return bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public String getPolio1() {
        return polio1;
    }

    public void setPolio1(String polio1) {
        this.polio1 = polio1;
    }

    public String getDpt1() {
        return dpt1;
    }

    public void setDpt1(String dpt1) {
        this.dpt1 = dpt1;
    }

    public String getPolio2() {
        return polio2;
    }

    public void setPolio2(String polio2) {
        this.polio2 = polio2;
    }

    public String getDpt2() {
        return dpt2;
    }

    public void setDpt2(String dpt2) {
        this.dpt2 = dpt2;
    }

    public String getPolio3() {
        return polio3;
    }

    public void setPolio3(String polio3) {
        this.polio3 = polio3;
    }

    public String getDpt3() {
        return dpt3;
    }

    public void setDpt3(String dpt3) {
        this.dpt3 = dpt3;
    }

    public String getPolio4() {
        return polio4;
    }

    public void setPolio4(String polio4) {
        this.polio4 = polio4;
    }

    public String getIpv() {
        return ipv;
    }

    public void setIpv(String ipv) {
        this.ipv = ipv;
    }

    public String getCampak() {
        return campak;
    }

    public void setCampak(String campak) {
        this.campak = campak;
    }

    public String getDptLanjut() {
        return dptLanjut;
    }

    public void setDptLanjut(String dptLanjut) {
        this.dptLanjut = dptLanjut;
    }

    public String getCampakLanjut() {
        return campakLanjut;
    }

    public void setCampakLanjut(String campakLanjut) {
        this.campakLanjut = campakLanjut;
    }

    public String getUpdateImunisasi() {
        return updateImunisasi;
    }

    public void setUpdateImunisasi(String updateImunisasi) {
        this.updateImunisasi = updateImunisasi;
    }

    public String getIdBalita() {
        return idBalita;
    }

    public void setIdBalita(String idBalita) {
        this.idBalita = idBalita;
    }

    public Imun(){}

    public Imun(String hb0, String bcg, String polio1, String dpt1, String polio2, String dpt2, String polio3, String dpt3, String polio4, String ipv, String campak, String dptLanjut, String campakLanjut, String updateImun, String idBalita, String idImun){
        this.idImunisasi = idImun;
        this.hb0 = hb0;
        this.bcg = bcg;
        this.polio1 = polio1;
        this.dpt1 = dpt1;
        this.polio2 = polio2;
        this.dpt2 = dpt2;
        this.polio3 = polio3;
        this.dpt3 = dpt3;
        this.polio4 = polio4;
        this.ipv = ipv;
        this.campak = campak;
        this.dptLanjut = dptLanjut;
        this.campakLanjut = campakLanjut;
        this.updateImunisasi = updateImun;
        this.idBalita = idBalita;
    }

    protected Imun(Parcel in){
        idImunisasi = in.readString();
        hb0 = in.readString();
        bcg = in.readString();
        polio1 = in.readString();
        dpt1 = in.readString();
        polio2 = in.readString();
        dpt2 = in.readString();
        polio3 = in.readString();
        dpt3 = in.readString();
        polio4 = in.readString();
        ipv = in.readString();
        campak = in.readString();
        dptLanjut = in.readString();
        campakLanjut = in.readString();
        updateImunisasi = in.readString();
        idBalita = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idImunisasi);
        parcel.writeString(hb0);
        parcel.writeString(bcg);
        parcel.writeString(polio1);
        parcel.writeString(dpt1);
        parcel.writeString(polio2);
        parcel.writeString(dpt2);
        parcel.writeString(polio3);
        parcel.writeString(dpt3);
        parcel.writeString(polio4);
        parcel.writeString(ipv);
        parcel.writeString(campak);
        parcel.writeString(dptLanjut);
        parcel.writeString(campakLanjut);
        parcel.writeString(updateImunisasi);
        parcel.writeString(idBalita);
    }

    public static final Creator<Imun> CREATOR = new Creator<Imun>() {
        @Override
        public Imun createFromParcel(Parcel in) {
            return new Imun(in);
        }

        @Override
        public Imun[] newArray(int size) {
            return new Imun[size];
        }
    };
}
