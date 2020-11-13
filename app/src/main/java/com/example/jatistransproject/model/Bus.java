package com.example.jatistransproject.model;

import com.google.gson.annotations.SerializedName;

public class Bus {
    @SerializedName("class_name")
    private String class_name;
    @SerializedName("price")
    private String price;
    @SerializedName("time_dari")
    private String time_dari;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime_dari() {
        return time_dari;
    }

    public void setTime_dari(String time_dari) {
        this.time_dari = time_dari;
    }
}
