package com.example.jatistransproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FormPembayaran extends AppCompatActivity {

    public static String KEY_PRICE3 = "harga";
    public static String KEY_DATE3 = "date";
    public static String KEY_CLASS3 = "kelas";

    TextView txtDate, txtPrice, txtClass;
    Button btnSelesai;
    String sPrice, sDate, sClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pembayaran);

        txtDate = (TextView) findViewById(R.id.txt_date3);
        txtPrice = (TextView) findViewById(R.id.txt_price3);
        txtClass = (TextView) findViewById(R.id.txt_class3);

        sDate = getIntent().getStringExtra(KEY_DATE3);
        sPrice = getIntent().getStringExtra(KEY_PRICE3);
        sClass = getIntent().getStringExtra(KEY_CLASS3);

        txtDate.setText(sDate);
        txtPrice.setText(sPrice);
        txtClass.setText(sClass);

        btnSelesai = (Button) findViewById(R.id.btn_selesai);
        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormPembayaran.this, E_ticket.class);
                startActivity(i);
            }
        });

    }
}
