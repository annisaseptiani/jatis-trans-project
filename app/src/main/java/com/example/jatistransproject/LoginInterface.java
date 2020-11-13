package com.example.jatistransproject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {
    String LOGINURL = "http://10.0.2.2/Eudeka%20x%20Jatis/FIX/db_jatistrans%20new/jatistrans1/";
    @FormUrlEncoded
    @POST("simplelogin.php")
    Call<String> getUserLogin(

            @Field("email") String email,
            @Field("password") String password
    );

}
