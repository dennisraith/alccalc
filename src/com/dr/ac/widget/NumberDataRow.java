package com.dr.ac.widget;

import com.dr.ac.R;
import com.dr.ac.constants.ACConsts;

import android.content.Context;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberDataRow extends DataRow {

	public NumberDataRow(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.mInput.setKeyListener(DigitsKeyListener.getInstance(false, true));
	}

}
