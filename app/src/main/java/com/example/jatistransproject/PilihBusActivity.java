package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jatistransproject.adapter.BusAdapter;
import com.example.jatistransproject.adapter.RetroAdapter;
import com.example.jatistransproject.api.ApiBus;
import com.example.jatistransproject.api.ApiClient;
import com.example.jatistransproject.api.MyInterface;
import com.example.jatistransproject.model.Bus;
import com.example.jatistransproject.model.ModelListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.widget.AdapterView.OnItemClickListener;

public class PilihBusActivity extends AppCompatActivity {

    private BusAdapter busAdapter;
    public static String KEY_DARI = "dari";
    public static String KEY_TUJUAN = "tujuan";
    public static String KEY_DATE = "date";
    private String sDari, sTujuan, sDate, sPrice;
    private ImageView image;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bus);

        getSupportActionBar().setTitle("Pilih Bus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.lv);

        sDari = getIntent().getStringExtra(KEY_DARI);
        sTujuan = getIntent().getStringExtra(KEY_TUJUAN);
        sDate = getIntent().getStringExtra(KEY_DATE);
        getJSONResponse();
    }

    private void getJSONResponse() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MyInterface api = retrofit.create(MyInterface.class);

        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    private void writeRecycler(String response) {

        try {
            //getting the whole json object from the response
            JSONObject obj = new JSONObject(response);
            if (obj.optString("status").equals("true")) {

                ArrayList<Bus> modelRecyclerArrayList = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    Bus modelRecycler = new Bus();
                    JSONObject dataobj = dataArray.getJSONObject(i);


                    modelRecycler.setClass_name(dataobj.getString("class_name"));
                    modelRecycler.setPrice(dataobj.getString("price"));
                    modelRecycler.setTime_dari(dataobj.getString("time_dari"));

                    modelRecyclerArrayList.add(modelRecycler);

                }

                busAdapter = new BusAdapter(this, modelRecyclerArrayList);
                recyclerView.setAdapter(busAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            } else {
                Toast.makeText(PilihBusActivity.this, obj.optString("message") + "", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
