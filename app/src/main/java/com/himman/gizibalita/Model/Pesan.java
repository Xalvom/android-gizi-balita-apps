package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pesan implements Parcelable {
    @SerializedName("id_pesan")
    @Expose
    private String idPesan;
    @SerializedName("judul_pesan")
    @Expose
    private String judulPesan;
    @SerializedName("pertanyaan")
    @Expose
    private String pertanyaan;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("tanggal_pesan")
    @Expose
    private String tanggalPesan;
    @SerializedName("id_pengirim")
    @Expose
    private String idPengirim;
    @SerializedName("id_penerima")
    @Expose
    private String idPenerima;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("penjawab")
    @Expose
    private String penjawab;

    public String getIdPesan() {
        return idPesan;
    }

    public void setIdPesan(String idPesan) {
        this.idPesan = idPesan;
    }

    public String getJudulPesan() {
        return judulPesan;
    }

    public void setJudulPesan(String judulPesan) {
        this.judulPesan = judulPesan;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String isiPesan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(String tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public String getIdPengirim() {
        return idPengirim;
    }

    public void setIdPengirim(String idPengirim) {
        this.idPengirim = idPengirim;
    }

    public String getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(String idPenerima) {
        this.idPenerima = idPenerima;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
    public String getPenjawab() {
        return penjawab;
    }

    public void setPenjawab(String penjawab) {
        this.penjawab = penjawab;
    }

    public Pesan(String idPesan, String judulPesan, String pertanyaan, String idPenerima, String idPengirim, String tanggalPesan, String namaUser, String jawaban, String penjawab){
        this.idPesan = idPesan;
        this.judulPesan = judulPesan;
        this.pertanyaan = pertanyaan;
        this.idPenerima = idPenerima;
        this.idPengirim = idPengirim;
        this.tanggalPesan = tanggalPesan;
        this.namaUser = namaUser;
        this.jawaban = jawaban;
        this.penjawab = penjawab;
    }
    protected Pesan(Parcel in){
        idPesan = in.readString();
        judulPesan = in.readString();
        pertanyaan = in.readString();
        idPenerima = in.readString();
        idPengirim = in.readString();
        tanggalPesan = in.readString();
        namaUser = in.readString();
        jawaban = in.readString();
        penjawab = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idPesan);
        parcel.writeString(judulPesan);
        parcel.writeString(pertanyaan);
        parcel.writeString(idPenerima);
        parcel.writeString(idPengirim);
        parcel.writeString(tanggalPesan);
        parcel.writeString(namaUser);
        parcel.writeString(jawaban);
    }

    public static final Creator<Pesan> CREATOR = new Creator<Pesan>() {
        @Override
        public Pesan createFromParcel(Parcel in) {
            return new Pesan(in);
        }

        @Override
        public Pesan[] newArray(int size) {
            return new Pesan[size];
        }
    };

}
