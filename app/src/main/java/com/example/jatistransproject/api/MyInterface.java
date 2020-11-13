package com.example.jatistransproject.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyInterface {

    String JSONURL = "http://10.0.2.2/Eudeka%20x%20Jatis/FIX/db_jatistrans%20new/jatistrans1/";

    @GET("viewbus.php?time_dari=2020-01-12 17:10:01&dari_id=1&ke_id=4")
    Call<String> getString();
}
