package com.dr.ac.model;

import java.util.ArrayList;

import com.dr.ac.ACApplication;
import com.dr.ac.constants.ACConsts;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	private static MenuAdapter sInstance;
	private ArrayList<MenuItemModel> mItems;
	private Context mContext;
	private MenuAdapter(Context context){
		this.mContext = context;
		this.mItems = new ArrayList<MenuItemModel>();
		this.mItems.add(new MenuItemModel(com.dr.ac.R.string.fragment_input,ACConsts.TARGET_INPUT));
		this.mItems.add(new MenuItemModel(com.dr.ac.R.string.fragment_history,ACConsts.TARGET_HISTORY));
		this.mItems.add(new MenuItemModel(com.dr.ac.R.string.fragment_settings,ACConsts.TARGET_SETTINGS));
	}
	
	public static MenuAdapter getInstance(){
		if(sInstance==null){
			sInstance = new MenuAdapter(ACApplication.getInstance());
		}
		return sInstance;
	}
	
	
	
	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public MenuItemModel getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mItems.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.simple_list_item_1, null);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.id.text1);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv.setText(this.getItem(position).getLabel());
		
		return convertView;
	}

	
	class ViewHolder {
		TextView tv;
	}
}
