package com.example.hyperlink.Util;

import com.example.hyperlink.Model.AllImages;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Client {

    @GET("api/v1/images/latest")
    Call<AllImages> getAllimages();

}
