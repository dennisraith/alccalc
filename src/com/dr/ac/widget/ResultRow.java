package com.dr.ac.widget;

import java.text.NumberFormat;

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
	
	public void set(int descrid, String content) {
		this.mDescr.setText(descrid);
		
		
		try {
			Double d= Double.parseDouble(content);
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			content = nf.format(d);
			
		}catch (Exception e){
			e.printStackTrace();
			
		}
		
		this.mInput.setText(content);
	}
}
