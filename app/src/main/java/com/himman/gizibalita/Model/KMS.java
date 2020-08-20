package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KMS implements Parcelable {
    @SerializedName("id_bbu")
    @Expose
    private String idBbu;
    @SerializedName("kelamin")
    @Expose
    private String kelamin;
    @SerializedName("umur")
    @Expose
    private String umur;
    @SerializedName("min3SD")
    @Expose
    private String min3SD;
    @SerializedName("min2SD")
    @Expose
    private String min2SD;
    @SerializedName("min1SD")
    @Expose
    private String min1SD;
    @SerializedName("median")
    @Expose
    private String median;
    @SerializedName("plus1SD")
    @Expose
    private String plus1SD;
    @SerializedName("plus2SD")
    @Expose
    private String plus2SD;
    @SerializedName("plus3SD")
    @Expose
    private String plus3SD;

    public KMS(){}

    public String getIdBbu() {
        return idBbu;
    }

    public void setIdBbu(String idBbu) {
        this.idBbu = idBbu;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getMin3SD() {
        return min3SD;
    }

    public void setMin3SD(String min3SD) {
        this.min3SD = min3SD;
    }

    public String getMin2SD() {
        return min2SD;
    }

    public void setMin2SD(String min2SD) {
        this.min2SD = min2SD;
    }

    public String getMin1SD() {
        return min1SD;
    }

    public void setMin1SD(String min1SD) {
        this.min1SD = min1SD;
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median;
    }

    public String getPlus1SD() {
        return plus1SD;
    }

    public void setPlus1SD(String plus1SD) {
        this.plus1SD = plus1SD;
    }

    public String getPlus2SD() {
        return plus2SD;
    }

    public void setPlus2SD(String plus2SD) {
        this.plus2SD = plus2SD;
    }

    public String getPlus3SD() {
        return plus3SD;
    }

    public void setPlus3SD(String plus3SD) {
        this.plus3SD = plus3SD;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idBbu);
        parcel.writeString(kelamin);
        parcel.writeString(umur);
        parcel.writeString(min3SD);
        parcel.writeString(min2SD);
        parcel.writeString(min1SD);
        parcel.writeString(median);
        parcel.writeString(plus1SD);
        parcel.writeString(plus2SD);
        parcel.writeString(plus3SD);
    }

    protected KMS(Parcel in) {
        idBbu = in.readString();
        kelamin = in.readString();
        umur = in.readString();
        min3SD = in.readString();
        min2SD = in.readString();
        min1SD = in.readString();
        median = in.readString();
        plus1SD = in.readString();
        plus2SD = in.readString();
        plus3SD = in.readString();
    }

    public static final Creator<KMS> CREATOR = new Creator<KMS>() {
        @Override
        public KMS createFromParcel(Parcel in) {
            return new KMS(in);
        }

        @Override
        public KMS[] newArray(int size) {
            return new KMS[size];
        }
    };

}
