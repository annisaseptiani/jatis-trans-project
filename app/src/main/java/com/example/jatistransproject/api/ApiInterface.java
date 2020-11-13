package com.example.jatistransproject.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("update.php")
    Call<String> updateData(       @Field("user_name") String user_name,
                                   @Field("email") String email,
                                   @Field("phone_no") String phone_no);

}
