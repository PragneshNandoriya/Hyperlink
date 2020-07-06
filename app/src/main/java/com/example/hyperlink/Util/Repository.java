package com.example.hyperlink.Util;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hyperlink.Model.AllImages;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    Retrofit retrofit;
    API_Client api_client ;

    MutableLiveData<AllImages> mutableLiveData = new MutableLiveData();
    public Repository(Application application){
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .cache(cache).build();

        retrofit = new Retrofit.Builder().baseUrl(API_URL.BASEURL).addConverterFactory(GsonConverterFactory.create()).
                client(client).build();

    }
    public MutableLiveData<AllImages> getList(){
        return mutableLiveData;
    }
    public void call_request(){
        api_client = retrofit.create(API_Client.class);
        api_client.getAllimages().enqueue(new Callback<AllImages>() {
            @Override
            public void onResponse(Call<AllImages> call, Response<AllImages> response) {
                if(response.isSuccessful()){
                    AllImages allImages = response.body();
                    Log.e("Retofit",allImages.toString());

                    mutableLiveData.postValue(response.body());
                }else{
                    Log.e("Repository","Error:"+response.code()+" body"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AllImages> call, Throwable t) {

            }
        });
    }

}
