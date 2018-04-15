package com.kandirmaz.tolunay.tavhackaathon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by tolunay on 14.04.2018.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String,String>> veriler;
    private static LayoutInflater layoutInflater = null;


    public ListAdapter(Context context, List<HashMap<String,String>> veriler) {
        this.context = context;
        this.veriler = veriler;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return veriler.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View rowView;
        rowView = layoutInflater.inflate(R.layout.list_layout,null);

        TextView isimTv = (TextView)rowView.findViewById(R.id.textView10);
        TextView skorTv = (TextView)rowView.findViewById(R.id.textView9);

        if(veriler.get(position).get("id").equals("1")){
            isimTv.setTextColor(Color.BLUE);
            skorTv.setTextColor(Color.BLUE);
            isimTv.setTextSize(18);
            skorTv.setTextSize(18);
        }

        isimTv.setText(veriler.get(position).get("isim"));
        skorTv.setText(veriler.get(position).get("skor"));



        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        return rowView;
    }
}
