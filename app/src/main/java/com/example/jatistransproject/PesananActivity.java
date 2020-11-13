package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PesananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        getSupportActionBar().setTitle("History");

        TextView title = (TextView) findViewById(R.id.activityTitle1);
        title.setText("Activity One");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_logo:
                        startActivity(new Intent(PesananActivity.this, AkunActivity.class));
                        break;
                    case R.id.ic_back:
                        startActivity(new Intent(PesananActivity.this, BerandaActivity.class));
                        break;
                    case R.id.ic_connect:
                        break;
                }
                return false;
            }
        });
    }
}
