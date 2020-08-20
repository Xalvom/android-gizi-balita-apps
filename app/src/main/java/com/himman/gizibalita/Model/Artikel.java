package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artikel implements Parcelable {
    @SerializedName("id_artikel")
    @Expose
    private String idArtikel;
    @SerializedName("judul_artikel")
    @Expose
    private String judulArtikel;
    @SerializedName("isi_artikel")
    @Expose
    private String isiArtikel;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id_author")
    @Expose
    private String idAuthor;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;

    public String getIdArtikel() {
        return idArtikel;
    }

    public void setIdArtikel(String idArtikel) {
        this.idArtikel = idArtikel;
    }

    public String getJudulArtikel() {
        return judulArtikel;
    }

    public void setJudulArtikel(String judulArtikel) {
        this.judulArtikel = judulArtikel;
    }

    public String getIsiArtikel() {
        return isiArtikel;
    }

    public void setIsiArtikel(String isiArtikel) {
        this.isiArtikel = isiArtikel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(String idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public Artikel(){}

    public Artikel(String idArtikel, String judulArtikel, String isiArtikel, String createdAt, String idAuthor, String namaUser){
        this.idArtikel = idArtikel;
        this.judulArtikel = judulArtikel;
        this.isiArtikel = isiArtikel;
        this.createdAt = createdAt;
        this.idAuthor = idAuthor;
        this.namaUser = namaUser;
    }

    protected Artikel(Parcel in){
        idArtikel = in.readString();
        judulArtikel = in.readString();
        isiArtikel = in.readString();
        createdAt = in.readString();
        idAuthor = in.readString();
        namaUser = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idArtikel);
        parcel.writeString(judulArtikel);
        parcel.writeString(isiArtikel);
        parcel.writeString(createdAt);
        parcel.writeString(idAuthor);
        parcel.writeString(namaUser);

    }

    public static final Creator<Artikel> CREATOR = new Creator<Artikel>() {
        @Override
        public Artikel createFromParcel(Parcel in) {
            return new Artikel(in);
        }

        @Override
        public Artikel[] newArray(int size) {
            return new Artikel[size];
        }
    };
}
