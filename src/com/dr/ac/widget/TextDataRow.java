package com.dr.ac.widget;

import com.dr.ac.R;
import com.dr.ac.constants.ACConsts;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextDataRow extends DataRow {

	public TextDataRow(Context context, AttributeSet attrs) {
		super(context, attrs);
	}



	public void init(String descr) {

		this.mDescr.setText(descr);
		this.mInput.setInputType(InputType.TYPE_CLASS_TEXT);
	}
	

}
