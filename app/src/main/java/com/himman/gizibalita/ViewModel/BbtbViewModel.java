package com.himman.gizibalita.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Bbtb;
import com.himman.gizibalita.Model.BbtbResponse;
import com.himman.gizibalita.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BbtbViewModel extends ViewModel {
    private MutableLiveData<List<Bbtb>> listBbtb = new MutableLiveData<List<Bbtb>>();
    private Context context;

    public MutableLiveData<List<Bbtb>> getListBbtb(){return listBbtb;}

    public void setListBbtb(String idBalita, Context context) {
        ApiInterface Service;
        Call<BbtbResponse> call;

        try{
            Service = Api.getApi().create(ApiInterface.class);
            call = Service.getListBbtb(idBalita);
            call.enqueue(new Callback<BbtbResponse>() {
                @Override
                public void onResponse(Call<BbtbResponse> call, Response<BbtbResponse> response) {
                    Log.d("Response",""+" "+ response.body());
                    if(response.isSuccessful()){
                        List<Bbtb> bbtbList;
                        bbtbList = response.body().getMData();
                        listBbtb.postValue(bbtbList);
                    }else{
                        Toast.makeText(context, R.string.bbtb_not_found, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BbtbResponse> call, Throwable t) {
                    Log.d("Message", t.getMessage());
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
