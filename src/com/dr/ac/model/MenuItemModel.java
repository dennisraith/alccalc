package com.dr.ac.model;

import com.dr.ac.ACApplication;
import com.dr.ac.BaseFragment;
import com.dr.ac.constants.ACConsts;
import com.dr.ac.ui.HistoryFragment;
import com.dr.ac.ui.InputFragment;
import com.dr.ac.ui.SettingsFragment;

public class MenuItemModel {

	private String mLabel;
	private int mFragTarget;

	public MenuItemModel(String label, int targetFragment) {
		this.mLabel = label;
		this.mFragTarget = targetFragment;
	}

	public MenuItemModel(int label, int targetFragment) {
		this.mLabel = ACApplication.getInstance().getString(label);
		this.mFragTarget = targetFragment;
	}

	public String getLabel() {

		return this.mLabel;
	}

	public BaseFragment getTargetFragment() {
		switch (mFragTarget) {
		case ACConsts.TARGET_INPUT:
			return new InputFragment();
		case ACConsts.TARGET_HISTORY:
			return new HistoryFragment();
		case ACConsts.TARGET_SETTINGS:
			return new SettingsFragment();
		default:
			return null;
		}
	}

}
