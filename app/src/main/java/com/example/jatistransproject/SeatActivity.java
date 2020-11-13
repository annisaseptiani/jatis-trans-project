package com.example.jatistransproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jatistransproject.model.BusModel;
import com.example.jatistransproject.model.SeatModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.jatistransproject.Pemesanan.KEY_PRICE1;

public class SeatActivity extends AppCompatActivity {


    ArrayList<ImageView> seatList;
    TextView cost, txt_date, txt_class, txt_dari, txt_tujuan;
    int costPerTicket;
    Button done;
    BusModel thisBus = new BusModel();
    int Total;

    public static String KEY_PRICE = "harga";
    public static String KEY_DATE = "tanggal dan waktu";
    public static String KEY_CLASS = "kelas bus";

    ArrayList<String> selectedSeats; //Selected by this user.

    ArrayList<SeatModel> filledSeats; //Already booked by other users.


    private String sPrice, sDate, sClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        cost = findViewById(R.id.tv);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_class = (TextView) findViewById(R.id.txt_class);

        sPrice = getIntent().getStringExtra(KEY_PRICE);
        sDate = getIntent().getStringExtra(KEY_DATE);
        sClass = getIntent().getStringExtra(KEY_CLASS);

        getSupportActionBar().setTitle("Pilih Kursi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cost.setText(sPrice);
        txt_date.setText(sDate);
        txt_class.setText(sClass);


        int mDana = Integer.parseInt(sPrice);
        costPerTicket = mDana;

        done = findViewById(R.id.donebtn);


        seatList = new ArrayList<>();
        seatList.add((ImageView) findViewById(R.id.seat1));
        seatList.add((ImageView) findViewById(R.id.seat2));
        seatList.add((ImageView) findViewById(R.id.seat3));
        seatList.add((ImageView) findViewById(R.id.seat4));
        seatList.add((ImageView) findViewById(R.id.seat5));
        seatList.add((ImageView) findViewById(R.id.seat6));
        seatList.add((ImageView) findViewById(R.id.seat7));
        seatList.add((ImageView) findViewById(R.id.seat8));
        seatList.add((ImageView) findViewById(R.id.seat9));
        seatList.add((ImageView) findViewById(R.id.seat10));
        seatList.add((ImageView) findViewById(R.id.seat11));
        seatList.add((ImageView) findViewById(R.id.seat12));
        seatList.add((ImageView) findViewById(R.id.seat13));
        seatList.add((ImageView) findViewById(R.id.seat14));
        seatList.add((ImageView) findViewById(R.id.seat15));
        seatList.add((ImageView) findViewById(R.id.seat16));
        seatList.add((ImageView) findViewById(R.id.seat17));
        seatList.add((ImageView) findViewById(R.id.seat18));
        seatList.add((ImageView) findViewById(R.id.seat19));
        seatList.add((ImageView) findViewById(R.id.seat20));
        seatList.add((ImageView) findViewById(R.id.seat21));
        seatList.add((ImageView) findViewById(R.id.seat22));

        selectedSeats = new ArrayList<>();
        filledSeats = new ArrayList<>();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < seatList.size(); i++) {
                    if (seatList.get(i).getId() == view.getId()) {

                        //Unselecting selected seat
                        if (isSelectedByUser(i + 1))
                        {
                            if (contains(selectedSeats, i + 1)) {
                                selectedSeats.remove(String.valueOf(i + 1));
                                seatList.get(i).setColorFilter(Color.parseColor("#000000"));
                            }
                            Total = selectedSeats.size() * costPerTicket;
//                            cost.setText(new StringBuilder(Total));
                            ;
//                            seatList.get(i).setColorFilter(Color.parseColor("#000000"));
//                            //Toast.makeText(SelectSeat.this, "Seat No. " + (i + 1) + " unselected", Toast.LENGTH_SHORT).show();
//                            int j = 0;
//                            while (j < selectedSeats.size())
//                            {
//                                if (selectedSeats.get(j).getSeatNo() == (i + 1))
//                                {
//                                    selectedSeats.remove(j);
//                                    break;
//                                }
//                            }
//                            cost.setText(new StringBuilder(selectedSeats.size()*costPerTicket+ ""));
                        }
                        //Selecting
                        else if (!isSelected((i + 1)))
                        {
                            seatList.get(i).setColorFilter(Color.parseColor("#2ecc71"));
                            selectedSeats.add(String.valueOf(i + 1));
                            Toast.makeText(SeatActivity.this, "Seat No. " + (i + 1) + " selected", Toast.LENGTH_SHORT).show();

                            cost.setText(new StringBuilder(selectedSeats.size() * costPerTicket + ""));
                        }
                        //Cannot select
                        else {
                            Toast.makeText(SeatActivity.this, "Seat already booked", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };

        for (int i = 0; i < seatList.size(); i++) {
            seatList.get(i).setOnClickListener(onClickListener);
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Context context = null;

                if (selectedSeats.isEmpty()) {
                    Toast.makeText(SeatActivity.this, "No seat selected!", Toast.LENGTH_SHORT).show();
                } else {

                    for (int i = 0; i < selectedSeats.size(); i++) {
                        filledSeats.add(new SeatModel(selectedSeats.get(i)));
                    }

                    String newSeatsString = "";
                    for(int i = 0;i<filledSeats.size();i++)
                    {
                        newSeatsString = newSeatsString + filledSeats.get(i).getmSeat_no() + " ";
                    }

                    thisBus.setFilledSeats(newSeatsString.trim());

                    String seatString = "";

                    for (int i = 0; i < selectedSeats.size(); i++) {
                        seatString = seatString + " " + selectedSeats.get(i);
                    }

                }

                Intent intent = new Intent(SeatActivity.this, Pemesanan.class);
                intent.putExtra(Pemesanan.KEY_PRICE1, cost.getText().toString());
                intent.putExtra(Pemesanan.KEY_DATE1, txt_date.getText().toString());
                intent.putExtra(Pemesanan.KEY_CLASS1, txt_class.getText().toString());
//                intent.putExtra(Pemesanan.KEY_DARI1, txt_dari.getText().toString());
//                intent.putExtra(Pemesanan.KEY_KE1, txt_tujuan.getText().toString());
                startActivity(intent);

            }
        });
    }

    boolean contains(List<String> seatModels, int seat) {

        for (String seatModel : seatModels) {
            //System.out.println(">>>>>>>>   " + seatModel);
            //System.out.println("seat>>>>>>>>   " + seat);
            if (seatModel.equalsIgnoreCase(String.valueOf(seat))) {
                return true;
            }
        }
        return false;
    }

    boolean isSelected(int seatNo) {
        int i;
        for (i = 0; i < filledSeats.size(); i++) {
            if (filledSeats.get(i).getmSeat_no().equalsIgnoreCase(String.valueOf(seatNo))) {
                return true;
            }
        }
        return false;
    }

    boolean isSelectedByUser(int seatNo) {
        int i;
        for (i = 0; i < selectedSeats.size(); i++) {
            if (selectedSeats.get(i).equalsIgnoreCase(String.valueOf(seatNo))) {
                return true;
            }
        }
        return false;
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
