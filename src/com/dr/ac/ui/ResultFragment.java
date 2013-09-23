package com.dr.ac.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dr.ac.BaseFragment;
import com.dr.ac.R;
import com.dr.ac.model.ResultModel;
import com.dr.ac.widget.ResultEntry;

public class ResultFragment extends BaseFragment {

	private ResultModel model;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.setTitle(R.string.fragment_result);
		ResultEntry entry = new ResultEntry(getActivity());
		entry.initWithmodel(model);
		model.save();
		return entry;
	}

	public void setModel(ResultModel model) {
		this.model = model;
	}
}
