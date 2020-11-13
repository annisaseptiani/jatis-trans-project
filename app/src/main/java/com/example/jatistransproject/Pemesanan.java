package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.jatistransproject.PilihBusActivity.KEY_DATE;

public class Pemesanan extends AppCompatActivity {

    public static String KEY_PRICE1 = "harga";
    public static String KEY_DATE1 = "tanggal dan waktu";
    public static String KEY_CLASS1 = "kelas bus";
    public static String KEY_DARI1 = "dari";
    public static String KEY_KE1  = "ke";


    TextView totalHarga, txtDate, txtClass, txtNama, txtEmail, txtDari, txtKe;
    Button btnLanjutkan;

    private String sTotalHarga, sDate, sClass, sDari, sKe;
    public PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        getSupportActionBar().setTitle("Pemesanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferenceHelper = new PreferenceHelper(this);

        totalHarga = (TextView) findViewById(R.id.total_harga);
        txtDate = (TextView) findViewById(R.id.txt_date1);
        txtClass = (TextView) findViewById(R.id.txt_class1);
        txtNama = (TextView) findViewById(R.id.txt_nama1);
        txtEmail = (TextView) findViewById(R.id.txt_email1);
        txtDari = (TextView) findViewById(R.id.txt_dari1);
        txtKe = (TextView) findViewById(R.id.txt_ke1);

        btnLanjutkan = (Button) findViewById(R.id.btn_lanjutkan);
        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pemesanan.this, MetodePembayaran.class);
//        intent.putExtra(MetodePembayaran.KEY_PRICE2, totalHarga.getText().toString());
//        intent.putExtra(MetodePembayaran.KEY_DATE2, txtDate.getText().toString());
//        intent.putExtra(MetodePembayaran.KEY_CLASS2, txtClass.getText().toString());
                startActivity(intent);
            }
        });

        sTotalHarga = getIntent().getStringExtra(KEY_PRICE1);
        sDate = getIntent().getStringExtra(KEY_DATE1);
        sClass = getIntent().getStringExtra(KEY_CLASS1);
        txtNama.setText("Nama : " + preferenceHelper.getUsername());
        txtEmail.setText("Email : " + preferenceHelper.getEmail());
        sDari = getIntent().getStringExtra(KEY_DARI1);
        sKe = getIntent().getStringExtra(KEY_KE1);


        totalHarga.setText(sTotalHarga);
        txtDate.setText(sDate);
        txtClass.setText(sClass);
        txtDari.setText(sDari);
        txtKe.setText(sKe);

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
