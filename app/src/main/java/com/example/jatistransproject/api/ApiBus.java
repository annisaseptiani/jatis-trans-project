package com.example.jatistransproject.api;

import com.example.jatistransproject.model.Bus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiBus {

    @GET("viewbus.php")
    Call<List<Bus>> getBus();
}
