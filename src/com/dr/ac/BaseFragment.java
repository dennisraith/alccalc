package com.dr.ac;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {

	private String mTitle = "";
	private MainActivity mActivity;


	public String getTitle() {
		return this.mTitle;
	}

	protected void setTitle(String title) {
		this.mTitle = title;

		getMainActivity().setTitle(mTitle);
	}

	protected void setTitle(int title) {
		this.mTitle = this.getString(title);

		getMainActivity().setTitle(mTitle);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}
	
	protected MainActivity getMainActivity(){
		if(this.mActivity == null){
			mActivity = (MainActivity) this.getActivity();
		}
		return this.mActivity;
	}

}
