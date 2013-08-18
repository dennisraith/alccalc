package com.dr.ac.ui;

import java.util.ArrayList;

import com.dr.ac.BaseFragment;
import com.dr.ac.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavigationFragment extends BaseFragment {

	private ListView mListView;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String>mEntries;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.setTitle(R.string.fragment_navigation);

		View view = inflater.inflate(R.layout.layout_navigation, null);
		this.mEntries = new ArrayList<String>();
		mEntries.add("1");
		mEntries.add("2");
		this.mListView = (ListView) view.findViewById(R.id.listview);
		mAdapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,mEntries);
		this.mListView.setAdapter(mAdapter);
		return view;
	}
	
}
