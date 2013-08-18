package com.dr.ac.widget;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dr.ac.R;

public abstract class DataRow extends LinearLayout {
	protected TextView mDescr;
	protected EditText mInput;

	public DataRow(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
	}

	public DataRow(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(getContext()).inflate(R.layout.layout_datarow, this);
		this.mDescr = (TextView) this.findViewById(R.id.descr);
		this.mInput = (EditText) this.findViewById(R.id.input);

	}

	public void init(String descr) {

		this.mDescr.setText(descr);
	}
	
	public String getInput(){
		return this.mInput.getText().toString();
	}
	
	public void setInput(String input){
		this.mInput.setText(input);
	}

}
