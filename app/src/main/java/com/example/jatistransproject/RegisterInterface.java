package com.example.jatistransproject;

import com.example.jatistransproject.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {
    String REGIURL = "http://10.0.2.2/Eudeka%20x%20Jatis/FIX/db_jatistrans%20new/jatistrans1/";
    @FormUrlEncoded
    @POST("simpleregistration.php")
    Call<String> getUserRegi(
            @Field("user_name") String user_name,
            @Field("phone_no") String phone_no,
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("update.php")
    Call<String> updateData(@Field("user_name") String user_name,
                            @Field("email") String email,
                            @Field("phone_no") String phone_no);


}
