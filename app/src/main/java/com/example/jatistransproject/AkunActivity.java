package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AkunActivity extends AppCompatActivity {

    Button btnLogout, btnPengaturan, btnTentang, btnHubungiKami, btnBantuan;
    LinearLayout layAkun;

    TextView tvName, tvEmail, tvNope;

    public PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        preferenceHelper = new PreferenceHelper(this);

        tvName = (TextView) findViewById(R.id.nama);
        tvEmail = (TextView) findViewById(R.id.email);
        tvNope = (TextView) findViewById(R.id.notlp);

        tvName.setText("Nama : " + preferenceHelper.getUsername());
        tvEmail.setText("Email   : " + preferenceHelper.getEmail());
        tvNope.setText("No Hp : " + preferenceHelper.getNumber());
        getSupportActionBar().setTitle("Akun");

        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnPengaturan = (Button) findViewById(R.id.btn_pengaturan);
        btnHubungiKami = (Button) findViewById(R.id.btn_hubungi);
        btnBantuan = (Button) findViewById(R.id.btn_Bantuan);
        layAkun = (LinearLayout) findViewById(R.id.lay_1);
        layAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AkunActivity.this, Profil.class));

            }
        });

        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AkunActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        btnBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AkunActivity.this, Bantuan.class));
            }
        });
        btnHubungiKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AkunActivity.this, HubungiKami.class));
            }
        });

        btnTentang = (Button) findViewById(R.id.btn_Tentangkami);
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AkunActivity.this, Tentang_Kami.class);
                startActivity(intent);
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_logo:
                        break;
                    case R.id.ic_connect:
                        startActivity(new Intent(AkunActivity.this, PesananActivity.class));
                        break;
                    case R.id.ic_back:
                        startActivity(new Intent(AkunActivity.this, BerandaActivity.class));
                        break;
                }
                return false;
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });


    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setMessage("Keluar dari AkunActivity?")
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        preferenceHelper.putIsLogin(false);
                        preferenceHelper.putIsRegis(false);
                        Intent intent = new Intent(AkunActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
