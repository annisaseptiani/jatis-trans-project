package com.example.jatistransproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jatistransproject.api.ApiBank;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MetodePembayaran extends AppCompatActivity {

    String ticket_id, user_id, total_seat, total_price, date_time_now, set_id, workday_id,
            class_name, nama_bank, check_bank;

    String sPrice, sDate, sClass;
    TextView txtPrice, txtDate, txtClass;

    public static String KEY_PRICE2 = "harga";
    public static String KEY_DATE2 = "date";
    public static String KEY_CLASS2 = "kelas";

    private Button btnBca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);

        txtDate = (TextView) findViewById(R.id.txt_date2);
        txtClass = (TextView) findViewById(R.id.txt_class2);
        txtPrice = (TextView) findViewById(R.id.txt_price2);

        sPrice = getIntent().getStringExtra(KEY_PRICE2);
        sDate = getIntent().getStringExtra(KEY_DATE2);
        sClass = getIntent().getStringExtra(KEY_CLASS2);

        txtDate.setText(sDate);
        txtClass.setText(sClass);
        txtPrice.setText(sPrice);


        btnBca = (Button) findViewById(R.id.btn_bca);
        btnBca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetodePembayaran.this, FormPembayaran.class);
                intent.putExtra(FormPembayaran.KEY_DATE3, txtDate.getText().toString());
                intent.putExtra(FormPembayaran.KEY_PRICE3, txtPrice.getText().toString());
                intent.putExtra(FormPembayaran.KEY_CLASS3, txtClass.getText().toString());
//                Bayar();
                startActivity(intent);
            }
        });


    }

//    private void Bayar() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(RegisterInterface.REGIURL)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        ApiBank api = retrofit.create(ApiBank.class);
//
////        Call<String> call = api.getBank(ticket_id, user_id, total_seat, total_price, date_time_now
////                , set_id, workday_id, class_name, nama_bank, check_bank);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
//                //Toast.makeText()
//                if (response.isSuccessful()) {
//                    if (response.body() != null) {
//                        Log.i("onSuccess", response.body().toString());
//
//                        String jsonresponse = response.body().toString();
//                        try {
//                            parseRegData(jsonresponse);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    } else {
//                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void parseRegData(String response) throws JSONException {
//
//        JSONObject jsonObject = new JSONObject(response);
//        if (jsonObject.optString("status").equals("true")) {
//
//
//            Toast.makeText(MetodePembayaran.this, "Successfully!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(MetodePembayaran.this, FormPembayaran.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            this.finish();
//        } else {
//
//            Toast.makeText(MetodePembayaran.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//        }
//
//
//    }


}

