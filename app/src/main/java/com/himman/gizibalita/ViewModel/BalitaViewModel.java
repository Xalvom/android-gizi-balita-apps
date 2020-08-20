package com.himman.gizibalita.ViewModel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.himman.gizibalita.Api.Api;
import com.himman.gizibalita.Api.ApiInterface;
import com.himman.gizibalita.Model.Balita;
import com.himman.gizibalita.Model.BalitaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalitaViewModel extends ViewModel {

    private MutableLiveData<List<Balita>> listBalitas = new MutableLiveData<>();
    private Context context;

    public Context getContext(){return context;}

    public void setContext(Context context){this.context = context;}

    public void setListBalita(MutableLiveData<List<Balita>> listBalitas){this.listBalitas = listBalitas;}

    public MutableLiveData<List<Balita>> getListBalita(){return listBalitas;}

    public void setListBalita(String idUser, Context context){
        ApiInterface Service;
        retrofit2.Call<BalitaResponse> Call;

        try{
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getListBalita(idUser);
            Call.enqueue(new Callback<BalitaResponse>() {
                @Override
                public void onResponse(retrofit2.Call<BalitaResponse> call, Response<BalitaResponse> response) {
                    Log.d("Response", ""+""+response.body());
                    if(response.isSuccessful()){
                        List<Balita> listBalita;
                        listBalita = response.body().getMData();
                        listBalitas.postValue(listBalita);
                    }else{
                        Toast.makeText(context, "Data Balita Belum Ada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<BalitaResponse> call, Throwable t) {
                    Log.d("Message", t.getMessage());
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
