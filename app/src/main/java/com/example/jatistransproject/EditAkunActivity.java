package com.example.jatistransproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jatistransproject.api.ApiClient;
import com.example.jatistransproject.api.ApiInterface;
import com.example.jatistransproject.model.ResponseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditAkunActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_email;
    private EditText et_phone;
    private String MY_PREFS_NAME = "MySharedPreferences";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("debug", "onCreate");

        setContentView(R.layout.activity_edit_akun);

        et_name = findViewById(R.id.edit_user_name);
        et_email = findViewById(R.id.edit_user_email);
        et_phone = findViewById(R.id.edit_user_phone);
        //adding validation to edittexts

        sharedPreferences = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Saving all UI fields values in the sharedPreferences XML file.
                editor.putString("user_name", et_name.getText().toString());
                editor.putString("user_email", et_email.getText().toString());
                editor.putString("user_phone", et_phone.getText().toString());
                editor.apply();
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO debugging
        Log.d("debug", "onResume");
        // Updating the sharedPreferences data structure containing user info

        sharedPreferences=getApplicationContext().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        // Restoring all UI values
        et_name.setText(sharedPreferences.getString("user_name", null));
        et_email.setText(sharedPreferences.getString("user_email", null));
        et_phone.setText(sharedPreferences.getString("user_phone", null));

    }
}


/*
        et_name=findViewById(R.id.edit_user_name);
        //et_password=findViewById(R.id.edit_user_password);
        et_email=findViewById(R.id.edit_user_email);
        et_phone=findViewById(R.id.edit_user_phone);



        sharedPreferences=getApplicationContext().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Saving all UI fields values in the sharedPreferences XML file.
                editor.putString("user_name", et_name.getText().toString());
                editor.putString("user_email", et_email.getText().toString());
                editor.putString("user_phone", et_phone.getText().toString());
                editor.putString("user_photo",sharedPreferences.getString("user_photo_temp",null));

                editor.apply();

                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO debugging
        Log.d("debug", "onResume");
        // Updating the sharedPreferences data structure containing user info

        sharedPreferences=getApplicationContext().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

        // Restoring all UI values
        et_name.setText(sharedPreferences.getString("user_name", null));
        et_email.setText(sharedPreferences.getString("user_email", null));
        et_phone.setText(sharedPreferences.getString("user_phone", null));

        //tvName.setText("Nama : " + preferenceHelper.getUsername());
        //tvEmail.setText("Email   : " + preferenceHelper.getEmail());
        //tvNope.setText("No Hp : " + preferenceHelper.getNumber());

    }



}

 */
