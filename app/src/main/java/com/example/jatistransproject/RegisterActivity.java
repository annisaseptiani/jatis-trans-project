package com.example.jatistransproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private EditText etemail, etnope, etusername, etpassword;
    private Button btnregister;
    private TextView tvlogin;
    private PreferenceHelper preferenceHelper;
    String email, nope, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        preferenceHelper = new PreferenceHelper(this);

        if (preferenceHelper.getIsRegis()) {
            Intent intent = new Intent(RegisterActivity.this, BerandaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        etusername = (EditText) findViewById(R.id.txt_nama);
        etnope = (EditText) findViewById(R.id.txt_nope);
        etemail = (EditText) findViewById(R.id.txt_user);
        etpassword = (EditText) findViewById(R.id.txt_password);

        //adding validation to edittexts

        btnregister = (Button) findViewById(R.id.btn_regis);
        tvlogin = (TextView) findViewById(R.id.tvlogin);

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
//                RegisterActivity.this.finish();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        intialize();
        if (!validate()) {
            Toast.makeText(this,"Signup Failed", Toast.LENGTH_SHORT).show();
        } else {
            registerMe();
        }
    }

    public boolean validate(){
        boolean valid = true;
        if (username.isEmpty()||username.length()>20){
            etusername.setError("Username tidak boleh kosong dan kurang dari 5 dan lebih dari 20 karakter.");
            valid=false;
        }
        if (password.isEmpty()||password.length()<6) {
            etpassword.setError("Password tidak boleh kosong dan kurang dari 6 karakter");
            valid=false;
        }
        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("Email tidak valid!");
            valid=false;
        }
        if (nope.isEmpty()||nope.length()>13) {
            etnope.setError("No HP tidak valid!");
            valid=false;
        }
        return valid;
    }

    public void intialize(){

        email = etemail.getText().toString();
        nope = etnope.getText().toString();
        username = etusername.getText().toString();
        password = etpassword.getText().toString();
    }

    private void registerMe() {

        intialize();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RegisterInterface api = retrofit.create(RegisterInterface.class);

        Call<String> call = api.getUserRegi(username, nope, email, password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        try {
                            parseRegData(jsonresponse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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

    private void parseRegData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")) {

            saveInfo(response);

            Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, BerandaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        } else {

            Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInfo(String response) {

        preferenceHelper.putIsRegis(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    preferenceHelper.putEmail(dataobj.getString("email"));
                    preferenceHelper.putUsername(dataobj.getString("user_name"));
                    preferenceHelper.putNumber(dataobj.getString("phone_no"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
