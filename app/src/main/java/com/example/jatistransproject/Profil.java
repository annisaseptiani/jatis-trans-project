package com.example.jatistransproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profil extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_email;
    private TextView tv_location;
    private TextView tv_bio;

    private String MY_PREFS_NAME = "MySharedPreferences";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //preferenceHelper = new PreferenceHelper(this);

        tv_name=findViewById(R.id.user_name);
        tv_email=findViewById(R.id.user_email);
        tv_bio=findViewById(R.id.user_bio);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.edit:
                Intent i = new Intent(getApplicationContext(), EditAkunActivity.class);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.apply();
                startActivity(i);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        tv_name.setText(sharedPreferences.getString("user_name",null));
        tv_email.setText(sharedPreferences.getString("user_email",null));
        tv_bio.setText(sharedPreferences.getString("user_bio",null));

    }
}

        //sharedPreferences=getApplicationContext().getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);

//        nama.setText("Nama : " + preferenceHelper.getUsername());
//        email_user.setText("Email   : " + preferenceHelper.getEmail());
//        notlp.setText("No Hp : " + preferenceHelper.getNumber());
    /*
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Saving all UI fields values in the sharedPreferences XML file.
                editor.putString("user_name", et_name.getText().toString());
                editor.putString("user_password", et_password.getText().toString());
                editor.putString("user_email", et_email.getText().toString());
                editor.putString("user_phone", et_phone.getText().toString());
                editor.putString("user_location", et_location.getText().toString());
                editor.putString("user_bio", et_bio.getText().toString());
                editor.putString("user_photo",sharedPreferences.getString("user_photo_temp",null));
                editor.apply();

                finish();
            }
        });
    }

//   private void editUser(){
//           final String username = nama.getText().toString().trim();
//           final String email = email_user.getText().toString().trim();
//           final String nohp = notlp.getText().toString().trim();
//
//           Retrofit retrofit = new Retrofit.Builder()
//                   .baseUrl(LoginInterface.LOGINURL)
//                   .addConverterFactory(ScalarsConverterFactory.create())
//                   .build();
//
//           LoginInterface api = retrofit.create(LoginInterface.class);
//
//           Call<String> call = api.updateData(username, email, nohp);
//
//           call.enqueue(new Callback<String>() {
//               @Override
//               public void onResponse(Call<String> call, Response<String> response) {
//                   Log.i("Responsestring", response.body().toString());
//                   //Toast.makeText()
//                   if (response.isSuccessful()) {
//                       if (response.body() != null) {
//                           Log.i("onSuccess", response.body().toString());
//
//                           String jsonresponse = response.body().toString();
//                           parseLoginData(jsonresponse);
//
//                       } else {
//                           Log.i("onEmptyResponse", "Returned empty response");
//                           //Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
//                       }
//                   }
//               }
//
//               @Override
//               public void onFailure(Call<String> call, Throwable t) {
//
//               }
//           });
//
//       }


//    private void parseLoginData(String response){
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            if (jsonObject.getString("status").equals("true")) {
//                Intent intent = new Intent(Profil.this, AkunActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                this.finish();
//
//                saveInfo(response);
//
//                Toast.makeText(Profil.this, "Update Successfully!", Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

//    private void saveInfo(String response){
//
//        preferenceHelper.putIsLogin(true);
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            if (jsonObject.getString("status").equals("true")) {
//                JSONArray dataArray = jsonObject.getJSONArray("data");
//                for (int i = 0; i < dataArray.length(); i++) {
//
//                    JSONObject dataobj = dataArray.getJSONObject(i);
//                    preferenceHelper.putEmail(dataobj.getString("email"));
//                    preferenceHelper.putUsername(dataobj.getString("user_name"));
//                    preferenceHelper.putNumber(dataobj.getString("phone_no"));
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

     */





