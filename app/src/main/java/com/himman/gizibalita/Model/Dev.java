package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dev implements Parcelable {
    @SerializedName("id_perkembangan")
    @Expose
    private String idPerkembangan;
    @SerializedName("kat_umur")
    @Expose
    private String katUmur;
    @SerializedName("isi_perkembangan")
    @Expose
    private String isiPerkembangan;

    public Dev(String idPerkembangan, String katUmur, String isiPerkembangan){
        this.isiPerkembangan = isiPerkembangan;
        this.idPerkembangan = idPerkembangan;
        this.katUmur = katUmur;
    }
    public Dev(){}

    public String getIdPerkembangan() {
        return idPerkembangan;
    }

    public void setIdPerkembangan(String idPerkembangan) {
        this.idPerkembangan = idPerkembangan;
    }

    public String getKatUmur() {
        return katUmur;
    }

    public void setKatUmur(String katUmur) {
        this.katUmur = katUmur;
    }

    public String getIsiPerkembangan() {
        return isiPerkembangan;
    }

    public void setIsiPerkembangan(String isiPerkembangan) {
        this.isiPerkembangan = isiPerkembangan;
    }


    protected Dev(Parcel in){
        idPerkembangan = in.readString();
        isiPerkembangan = in.readString();
        katUmur = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idPerkembangan);
        parcel.writeString(isiPerkembangan);
        parcel.writeString(katUmur);
    }

    public static Creator<Dev> CREATOR = new Creator<Dev>() {
        @Override
        public Dev createFromParcel(Parcel parcel) {
            return new Dev(parcel);
        }

        @Override
        public Dev[] newArray(int size) {
            return new Dev[size];
        }
    };
}
