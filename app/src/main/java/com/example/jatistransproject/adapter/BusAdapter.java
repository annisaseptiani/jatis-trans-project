package com.example.jatistransproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jatistransproject.Cobacoba;
import com.example.jatistransproject.R;
import com.example.jatistransproject.SeatActivity;
import com.example.jatistransproject.model.Bus;
import com.example.jatistransproject.model.SpinnerModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolders> {


    private ArrayList<Bus> buses;
    private ArrayList<Bus> filteredBuses;
    Context ctx;

    public BusAdapter(Context context, ArrayList<Bus> buses) {
        this.buses = buses;
        this.filteredBuses = buses;
        this.ctx = context;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus, parent, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolders holder, int position) {
        holder.class_name.setText(buses.get(position).getClass_name());
        holder.price.setText(buses.get(position).getPrice());
        holder.time_dari.setText(buses.get(position).getTime_dari());

    }

    @Override
    public int getItemCount() {
        return buses.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {
                    filteredBuses = buses;
                } else {
                    ArrayList<Bus> tempBus = new ArrayList<>();
                    for (Bus bus : buses) {
                        if (bus.getClass_name().toLowerCase().contains(searchString)) {
                            tempBus.add(bus);
                        }
                    }

                    filteredBuses = tempBus;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredBuses;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                filteredBuses = (ArrayList<Bus>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    class ViewHolders extends RecyclerView.ViewHolder {
        TextView class_name, price, time_dari;
        ImageView image;
        LinearLayout lay1;
        Button btnPilihBus;

        public ViewHolders(View itemView) {
            super(itemView);

            class_name = (TextView) itemView.findViewById(R.id.class_name);
            price = (TextView) itemView.findViewById(R.id.price);
            time_dari = (TextView) itemView.findViewById(R.id.time_dari);


            lay1 = (LinearLayout) itemView.findViewById(R.id.der1);
            lay1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ctx.startActivity(new Intent(ctx, SeatActivity.class)
                    .putExtra(SeatActivity.KEY_PRICE, price.getText().toString())
                    .putExtra(SeatActivity.KEY_DATE, time_dari.getText().toString())
                    .putExtra(SeatActivity.KEY_CLASS, class_name.getText().toString()));
                }
            });

        }

    }


}
