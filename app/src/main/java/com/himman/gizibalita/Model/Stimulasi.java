package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stimulasi implements Parcelable {
    @SerializedName("id_stimulasi")
    @Expose
    private String idStimulasi;
    @SerializedName("kat_umur")
    @Expose
    private String katUmur;
    @SerializedName("isi_stimulasi")
    @Expose
    private String isiStimulasi;

    public String getIdStimulasi() {
        return idStimulasi;
    }

    public void setIdStimulasi(String idStimulasi) {
        this.idStimulasi = idStimulasi;
    }

    public String getKatUmur() {
        return katUmur;
    }

    public void setKatUmur(String katUmur) {
        this.katUmur = katUmur;
    }

    public String getIsiStimulasi() {
        return isiStimulasi;
    }

    public void setIsiStimulasi(String isiStimulasi) {
        this.isiStimulasi = isiStimulasi;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idStimulasi);
        parcel.writeString(katUmur);
        parcel.writeString(isiStimulasi);
    }

    protected Stimulasi(Parcel in) {
        idStimulasi = in.readString();
        katUmur = in.readString();
        isiStimulasi = in.readString();
    }

    public static final Creator<Stimulasi> CREATOR = new Creator<Stimulasi>() {
        @Override
        public Stimulasi createFromParcel(Parcel in) {
            return new Stimulasi(in);
        }

        @Override
        public Stimulasi[] newArray(int size) {
            return new Stimulasi[size];
        }
    };
}
