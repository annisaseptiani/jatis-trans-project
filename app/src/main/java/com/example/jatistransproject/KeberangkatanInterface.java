package com.example.jatistransproject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KeberangkatanInterface {
    String JSONURL = "http://10.0.2.2/Eudeka%20x%20Jatis/FIX/db_jatistrans%20new/jatistrans1/";

    @GET("view.php")
    Call<String> getJSONString();
}
