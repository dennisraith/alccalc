package com.dr.ac.ui;

import com.dr.ac.R;
import com.dr.ac.model.ResultModel;
import com.dr.ac.widget.ResultRow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultFragment extends Fragment {

	private ResultModel mResult;
	private ResultRow mWuerze;
	private ResultRow mRestExtrakt;
	private ResultRow mRealerRestExtr;
	private ResultRow mDichte;
	private ResultRow mGewProAlk;
	private ResultRow mVol;
	private ResultRow mEndVerg;
	private ResultRow mBrennWCal;
	private ResultRow mBrennWJou;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.layout_result_widget, null);

		mWuerze = (ResultRow) view.findViewById(R.id.stWuerze);
		mRestExtrakt = (ResultRow) view.findViewById(R.id.rstExtr);
		mRealerRestExtr = (ResultRow) view.findViewById(R.id.realRestExtr);
		mDichte = (ResultRow) view.findViewById(R.id.dichte);
		mGewProAlk = (ResultRow) view.findViewById(R.id.GewProAlk);
		mVol = (ResultRow) view.findViewById(R.id.Vol);
		mEndVerg = (ResultRow) view.findViewById(R.id.EndVergaerung);
		mBrennWCal = (ResultRow) view.findViewById(R.id.brennw_cal);
		mBrennWJou = (ResultRow) view.findViewById(R.id.brennw_j);

		this.init();
		return view;
	}

	private void init() {

		if (this.mResult == null) {
			return;
		}

		this.mWuerze.set(R.string.stWuerze, this.mResult.getStWrz() + "");
		this.mRestExtrakt.set(R.string.rstExtrakt, this.mResult.getRstExtrakt()
				+ "");

		this.mRealerRestExtr.set(R.string.realExtrakt,
				this.mResult.getRealRestExtrakt() + "");
		this.mDichte.set(R.string.dichte, this.mResult.getDichte() + "");

		this.mGewProAlk.set(R.string.gewProAlk, this.mResult.getGewProAlk()
				+ "");
		this.mVol.set(R.string.volume, this.mResult.getVolume() + "");

		this.mEndVerg.set(R.string.endvergaerung,
				this.mResult.getEndvergärung() + "");
		this.mBrennWCal.set(R.string.brennwcal, this.mResult.getBrennwertCal()
				+ "");
		this.mBrennWJou.set(R.string.brennwjoule,
				this.mResult.getBrennwertJoule() + "");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void setResultModel(ResultModel model) {
		this.mResult = model;
	}

}
