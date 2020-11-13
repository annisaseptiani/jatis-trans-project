package com.example.jatistransproject.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface ApiBank {


    String JS_URL = "http://10.0.2.2/Eudeka%20x%20Jatis/FIX/db_jatistrans%20new/jatistrans1/";
/*
    @GET("tiket.php")
    Call<String> getTiket(

            @Field("ticket_id") String user_name,
            @Field("user_id") String phone_no,
            @Field("total_seat") String email,
            @Field("total_price") String password,
            @Field("date_time_now") String date_time_now,
            @Field("set_id") String set_id,
            @Field("workday_id") String workday_id,
            @Field("class_name") String class_name,
            @Field("nama_bank") String nama_bank,
            @Field("check_bank") String check_bank
    );

 */

    @GET("nama_bank")
    Call<String>getBank();
}
