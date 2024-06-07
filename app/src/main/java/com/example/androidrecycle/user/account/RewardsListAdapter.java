package com.example.androidrecycle.user.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.androidrecycle.R;

import java.util.List;

public class RewardsListAdapter extends BaseAdapter {

    private final Context context;
    private final List<String> itemList;

    public RewardsListAdapter(Context context, List<String> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {return itemList.size();}

    @Override
    public Object getItem(int position) {return itemList.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.item_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String item = itemList.get(position);
        viewHolder.textView.setText(item);
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }
}
