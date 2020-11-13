package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Bantuan extends AppCompatActivity {

    Button btnContent1, btnContent2, btnContent3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        getSupportActionBar().setTitle("Bantuan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnContent1 = (Button) findViewById(R.id.btn_Content1);
        btnContent2 = (Button) findViewById(R.id.btn_Content2);
        btnContent3 = (Button) findViewById(R.id.btn_Content3);

        btnContent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bantuan.this, PembayaranMataUang.class));
            }
        });

        btnContent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bantuan.this, KesamaanNamaPenumpang.class));
            }
        });

        btnContent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bantuan.this, KodePromo.class));
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
