package com.himman.gizibalita.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("email_user")
    @Expose
    private String emailUser;
    @SerializedName("password_user")
    @Expose
    private String passwordUser;
    @SerializedName("level_user")
    @Expose
    private String levelUser;
    @SerializedName("is_aktif")
    @Expose
    private String isAktif;
    @SerializedName("no_hp_user")
    @Expose
    private String noHpUser;

    public User(){}

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(String levelUser) {
        this.levelUser = levelUser;
    }

    public String getIsAktif() {
        return isAktif;
    }

    public void setIsAktif(String isAktif) {
        this.isAktif = isAktif;
    }

    public String getNoHpUser() {
        return noHpUser;
    }

    public void setNoHpUser(String noHpUser) {
        this.noHpUser = noHpUser;
    }
    protected User(Parcel in){
        emailUser = in.readString();
        passwordUser = in.readString();
        idUser = in.readString();
        namaUser = in.readString();
        levelUser = in.readString();
        isAktif = in.readString();
        noHpUser = in.readString();
    }
    public User(String emailUser, String passwordUser, String idUser, String isAktif, String levelUser, String namaUser, String noHpUser){
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.idUser = idUser;
        this.isAktif = isAktif;
        this.levelUser = levelUser;
        this.namaUser = namaUser;
        this.noHpUser = noHpUser;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idUser);
        parcel.writeString(emailUser);
        parcel.writeString(passwordUser);
        parcel.writeString(isAktif);
        parcel.writeString(namaUser);
        parcel.writeString(levelUser);
        parcel.writeString(noHpUser);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
