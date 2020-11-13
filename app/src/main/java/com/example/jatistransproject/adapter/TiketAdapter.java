package com.example.jatistransproject.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jatistransproject.R;
import com.example.jatistransproject.model.NamaBank;

import java.util.ArrayList;

public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.ViewHolders> {

    private ArrayList<NamaBank> banks;
    private ArrayList<NamaBank> filteredBank;
    Context ctx;

    public TiketAdapter(ArrayList<NamaBank> banks, ArrayList<NamaBank> fileredBank, Context ctx) {
        this.banks = banks;
        this.filteredBank = fileredBank;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public TiketAdapter.ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank, parent, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiketAdapter.ViewHolders holder, int position) {
        holder.txtBank.setText(banks.get(position).getNama_bank());
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView txtBank;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            txtBank = (TextView) itemView.findViewById(R.id.txt_bank);


        }
    }
}
