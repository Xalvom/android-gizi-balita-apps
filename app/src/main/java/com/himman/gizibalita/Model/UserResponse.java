package com.himman.gizibalita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("error")
    private boolean error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    @Expose
    private List<User> mData;

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

    public List<User> getMData(){return mData;}

    public void setMData(List<User> mData) {this.mData = mData;}
}
