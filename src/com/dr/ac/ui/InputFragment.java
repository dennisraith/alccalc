package com.dr.ac.ui;

import com.dr.ac.MainActivity;
import com.dr.ac.R;
import com.dr.ac.constants.ACConsts;
import com.dr.ac.model.ResultModel;
import com.dr.ac.widget.NumberDataRow;
import com.dr.ac.widget.TextDataRow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class InputFragment extends Fragment implements OnClickListener {

	
	private TextDataRow mDescr;
	private NumberDataRow mStWrz;
	private NumberDataRow mRstExtr;
	private Button mCalc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.layout_input_fragment, null);
		mDescr = (TextDataRow)view.findViewById(R.id.description);
		mStWrz = (NumberDataRow) view.findViewById(R.id.stWuerze);
		mRstExtr = (NumberDataRow) view.findViewById(R.id.rstExtr);
		mCalc = (Button) view.findViewById(R.id.calc);

		mDescr.init(this.getString(R.string.description));
		mStWrz.init(this.getString(R.string.stWuerze));
		mRstExtr.init(this.getString(R.string.rstExtrakt));

		this.mCalc.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		ResultModel result = ACConsts.calc(this.mStWrz.getInput(),
				this.mRstExtr.getInput());
		if (result == null) {
			Toast.makeText(this.getActivity(), R.string.parserError, Toast.LENGTH_SHORT).show();
		}
		else {
			ResultFragment fragment = (ResultFragment) ((MainActivity)this.getActivity()).navigateTo(new ResultFragment());
			fragment.setResultModel(result);
		}
	}
}
