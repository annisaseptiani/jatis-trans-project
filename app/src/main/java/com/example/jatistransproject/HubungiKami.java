package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class HubungiKami extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubungi_kami);

    getSupportActionBar().setTitle("Hubungi Kami");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick_mail (View view) {
        String url = "https://indivara.com" ;
        Intent bukabrowser = new Intent(Intent. ACTION_VIEW);
        bukabrowser.setData(Uri. parse(url));
        startActivity(bukabrowser);
    }

    public void onClick_call (View view) {
        String nomor = "150400";
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.fromParts("tel", nomor, null));
        startActivity(call);
    }
}
