package com.himman.gizibalita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtikelResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    @Expose
    private List<Artikel> mData;

    public boolean isError(){
        return error;
    }

    public void setError(boolean error){
        this.error = error;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public List<Artikel> getMData(){return mData;}

    public void setMData(List<Artikel> mData) {this.mData = mData;}
}
