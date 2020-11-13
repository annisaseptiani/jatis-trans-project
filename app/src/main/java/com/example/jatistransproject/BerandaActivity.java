package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.jatistransproject.model.SpinnerModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.layout.simple_spinner_item;

public class BerandaActivity extends AppCompatActivity {

    Button btnCari;
    EditText etDate;
    CarouselView carouselView;
    int[] sampelImages = {R.drawable.spesialprice, R.drawable.spesialprice, R.drawable.spesialprice, R.drawable.spesialprice};
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;

    private ArrayList<SpinnerModel> goodModelArrayList;
    private ArrayList<String> playerNames = new ArrayList<String>();
    private Spinner spinner, spinner2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        spinner = (Spinner) findViewById(R.id.sp_keberangkatan);
        fetchJSON();
        spinner2 = (Spinner) findViewById(R.id.sp_tujuan);
        getSupportActionBar().setTitle("Home");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_back:
                        break;
                    case R.id.ic_connect:
                        startActivity(new Intent(BerandaActivity.this, PesananActivity.class));
                        break;
                    case R.id.ic_logo:
                        startActivity(new Intent(BerandaActivity.this, AkunActivity.class));
                        break;
                }
                return false;
            }
        });

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampelImages.length);
        carouselView.setImageListener(imageListener);


        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        etDate = (EditText) findViewById(R.id.et_date);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnCari = (Button) findViewById(R.id.btn_cari);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, PilihBusActivity.class);
                intent.putExtra(PilihBusActivity.KEY_DARI, spinner.toString());
                intent.putExtra(PilihBusActivity.KEY_TUJUAN, spinner2.toString());
                intent.putExtra(PilihBusActivity.KEY_DATE, etDate.toString());
                startActivity(intent);
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampelImages[position]);
        }
    };

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(BerandaActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                etDate.setText(dateFormat.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void fetchJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KeberangkatanInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        KeberangkatanInterface api = retrofit.create(KeberangkatanInterface.class);

        Call<String> call = api.getJSONString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSON(jsonresponse);

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

    private void spinJSON(String response){

        try {

            JSONObject obj = new JSONObject(response);
            if(obj.optString("status").equals("true")){

                goodModelArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    SpinnerModel spinnerModel = new SpinnerModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    spinnerModel.setPool_id(dataobj.getInt("pool_id"));
                    spinnerModel.setPool_name(dataobj.getString("pool_name"));
                    spinnerModel.setCity_name(dataobj.getString("city_name"));
                    goodModelArrayList.add(spinnerModel);

                }

                for (int i = 0; i < goodModelArrayList.size(); i++){
                    playerNames.add(goodModelArrayList.get(i).getPool_name().toString());

                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(BerandaActivity.this, simple_spinner_item, playerNames);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spinner.setAdapter(spinnerArrayAdapter);
                spinner2.setAdapter(spinnerArrayAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}