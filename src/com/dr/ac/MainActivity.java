package com.dr.ac;

import com.dr.ac.constants.ACConsts;
import com.dr.ac.model.ResultModel;
import com.dr.ac.widget.NumberDataRow;
import com.dr.ac.widget.TextDataRow;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private TextDataRow mDescr;
	private NumberDataRow mStWrz;
	private NumberDataRow mRstExtr;
	private Button mCalc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);

		mDescr = (TextDataRow) this.findViewById(R.id.description);
		mStWrz = (NumberDataRow) this.findViewById(R.id.stWuerze);
		mRstExtr = (NumberDataRow) this.findViewById(R.id.rstExtr);
		mCalc = (Button) this.findViewById(R.id.calc);

		mDescr.init(this.getString(R.string.description));
		mStWrz.init(this.getString(R.string.stWuerze));
		mRstExtr.init(this.getString(R.string.rstExtrakt));

		this.mCalc.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		ResultModel result = ACConsts.calc(this.mStWrz.getInput(),
				this.mRstExtr.getInput());
		if (result == null) {
			Toast.makeText(this, R.string.parserError, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
