package com.dr.ac.constants;

import com.dr.ac.model.ResultModel;

public class ACConsts {

	public static final int STATUS_OK = 232;
	public static final int STATUS_ERROR = 354;

	public static final int INPUT_NUMBER = 332;
	public static final int INPUT_TEXT = 342;


	public static ResultModel calc(String stwrz, String rstextr) {

		double stwz = 0;
		double rstex = 0;

		try {
			stwz = Double.valueOf(stwrz);
			rstex = Double.valueOf(rstextr);

			return new ResultModel(stwz, rstex);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}