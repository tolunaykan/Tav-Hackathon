package com.kandirmaz.tolunay.tavhackaathon;

import android.content.Context;
import android.content.Intent;
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

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String,String>> veriler;
    private static LayoutInflater layoutInflater = null;


    public GridViewAdapter(Context context, List<HashMap<String,String>> veriler) {
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
        rowView = layoutInflater.inflate(R.layout.gridview_layout,null);

        TextView baslik = (TextView)rowView.findViewById(R.id.os_texts);
        TextView aciklama = (TextView)rowView.findViewById(R.id.textView6);

        baslik.setText(veriler.get(position).get("name"));
        aciklama.setText(veriler.get(position).get("aciklama"));

        final Intent intent = new Intent(context,Game.class);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                context.startActivity(intent);
            }
        });


        return rowView;
    }
}
