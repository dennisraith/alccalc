package com.dr.ac.widget;

import android.content.Context;
import android.util.AttributeSet;

public class ResultRow extends DataRow {

	public ResultRow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void set(String descr, String content) {
		this.mDescr.setText(descr);
		this.mInput.setText(content);
	}
}
