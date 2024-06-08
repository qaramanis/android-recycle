package com.example.androidrecycle.admin.requests;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.androidrecycle.R;

import java.util.List;
import java.util.Map;

public class CustomSimpleAdapter extends SimpleAdapter {

    public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        TextView line2TextView = view.findViewById(android.R.id.text2);
        String line2Text = line2TextView.getText().toString();

        if (line2Text.contains("Accepted")) {
            line2TextView.setTextColor(Color.GREEN);
        } else if (line2Text.contains("Rejected")) {
            line2TextView.setTextColor(Color.RED);
        }

        return view;
    }
}