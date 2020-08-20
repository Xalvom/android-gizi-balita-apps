package com.himman.gizibalita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TbuResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    @Expose
    private List<Tbu> mData;

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

    public List<Tbu> getMData(){return mData;}

    public void setMData(List<Tbu> mData) {this.mData = mData;}
}
