package com.example.jatistransproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jatistransproject.R;
import com.example.jatistransproject.model.ModelListView;

import java.util.ArrayList;

public class RetroAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelListView> dataModelArrayList;

    public RetroAdapter(Context context, ArrayList<ModelListView> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_bus, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvname = (TextView) convertView.findViewById(R.id.class_name);
            holder.tvcountry = (TextView) convertView.findViewById(R.id.price);
            holder.tvcity = (TextView) convertView.findViewById(R.id.time_dari);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

     //   Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvname.setText("Kelas: "+dataModelArrayList.get(position).getClass_name());
        holder.tvcountry.setText("Harga: "+dataModelArrayList.get(position).getPrice());
        holder.tvcity.setText("Keberangkatan: "+dataModelArrayList.get(position).getTime_dari());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvcountry, tvcity;
        protected ImageView iv;
    }

}
