package com.himman.gizibalita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BbuResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    @Expose
    private List<Bbu> mData;

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

    public List<Bbu> getMData(){return mData;}

    public void setMData(List<Bbu> mData) {this.mData = mData;}
}
