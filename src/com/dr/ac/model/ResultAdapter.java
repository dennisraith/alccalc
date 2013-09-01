package com.dr.ac.model;

import java.util.ArrayList;

import com.dr.ac.widget.ResultEntry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ResultAdapter extends BaseExpandableListAdapter {

	private ArrayList<ResultModel> mData;
	private Context mContext;

	public ResultAdapter(Context context, ArrayList<ResultModel> data) {
		super();
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public ResultModel getChild(int groupPosition, int childPosition) {
		return mData.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition + childPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		
		if(convertView==null || convertView.getTag()==null){
			
			convertView =LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, null);

			
		}
		ResultModel model = mData.get(groupPosition);
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1); 		
		tv.setText(model.getBrennwertCal()+"");
		
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		
		if (convertView == null || convertView.getTag() == null) {
			convertView = new ResultEntry(mContext);
		}

		((ResultEntry) convertView)
				.initWithmodel(this.mData.get(groupPosition));
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public ResultModel getGroup(int groupPosition) {
		return mData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.mData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
