package com.himman.gizibalita.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Bbu;
import com.himman.gizibalita.Model.BbuResponse;
import com.himman.gizibalita.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BbuViewModel extends ViewModel {
    private MutableLiveData<List<Bbu>> listBbu = new MutableLiveData<List<Bbu>>();
    private Context context;

    public MutableLiveData<List<Bbu>> getListBbu(){return listBbu;}

    public void setListBbu(String idBalita, Context context){
        ApiInterface Service;
        Call<BbuResponse> Call;

        try{
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getListBbu(idBalita);
            Call.enqueue(new Callback<BbuResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BbuResponse> call, Response<BbuResponse> response) {
                    Log.d("Response",""+" "+ response.body());
                    if(response.isSuccessful()){
                    List<Bbu> listBbus;
                    listBbus = response.body().getMData();
                    listBbu.postValue(listBbus);
                    }else{
                        Toast.makeText(context, R.string.bbu_not_found, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BbuResponse> call, Throwable t) {
                    Log.d("Message", t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
