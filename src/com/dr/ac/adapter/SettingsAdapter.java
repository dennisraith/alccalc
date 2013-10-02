
package com.dr.ac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dr.ac.model.Setting;

import java.util.List;


public class SettingsAdapter extends BaseAdapter {

    private final Context       mContext;
    private final List<Setting> mData;

    public SettingsAdapter(Context context, List<Setting> data) {
        this.mContext = context;
        this.mData = data;

    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Setting getItem(int position) {
        return this.mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(android.R.layout.simple_list_item_1, null);
        }
        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(this.getItem(position).getName());
        return convertView;
    }

}
