package com.himman.gizibalita.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Tbu;
import com.himman.gizibalita.Model.TbuResponse;
import com.himman.gizibalita.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TbuViewModel extends ViewModel {
    private MutableLiveData<List<Tbu>> listTbu = new MutableLiveData<List<Tbu>>();
    private Context context;

    public MutableLiveData<List<Tbu>> getListTbu(){return listTbu;}

    public void setListTbu(String idBalita, Context context){
        ApiInterface Service;
        Call<TbuResponse> Call;

        try{
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getListTbu(idBalita);
            Call.enqueue(new Callback<TbuResponse>() {
                @Override
                public void onResponse(retrofit2.Call<TbuResponse> call, Response<TbuResponse> response) {
                    Log.d("Response",""+" "+ response.body());
                    if(response.isSuccessful()){
                        List<Tbu> listTbus;
                        listTbus = response.body().getMData();
                        listTbu.postValue(listTbus);
                    }else{
                        Toast.makeText(context, R.string.tbu_not_found, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<TbuResponse> call, Throwable t) {
                    Log.d("Message", t.getMessage());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
