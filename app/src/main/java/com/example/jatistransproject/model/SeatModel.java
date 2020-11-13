package com.example.jatistransproject.model;

import com.google.gson.annotations.SerializedName;

public class SeatModel {

    @SerializedName("seat_id")
    public int mSeat_id;
    @SerializedName("bus_id")
    public int mBus_id;
    @SerializedName("seat_no")
    public String mSeat_no;

    public SeatModel(String s) {
    }

    public int getmSeat_id() {
        return mSeat_id;
    }

    public void setmSeat_id(int mSeat_id) {
        this.mSeat_id = mSeat_id;
    }

    public int getmBus_id() {
        return mBus_id;
    }

    public void setmBus_id(int mBus_id) {
        this.mBus_id = mBus_id;
    }

    public String getmSeat_no() {
        return mSeat_no;
    }

    public void setmSeat_no(String mSeat_no) {
        this.mSeat_no = mSeat_no;
    }
}
