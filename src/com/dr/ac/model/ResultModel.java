package com.dr.ac.model;

import com.dr.ac.constants.ACConsts;

public class ResultModel {

	private int STATUS_CODE = ACConsts.STATUS_ERROR;

	private final double mStwrz;
	private final double mRstExtr;
	private double mRealRestExtr;
	private double mDichte;
	private double mGewProAlk;
	private double mVol;
	private double mEndVergärung;
	private double mBrennwertCal;
	private double mBrennwertJoule;
	
	
	public ResultModel(double stwrz, double rstextra){
		this.mStwrz = stwrz;
		this.mRstExtr = rstextra;
		this.calc();
	}
	
	
	public void calc() {
		if (this.generateResult()) {
			this.STATUS_CODE = ACConsts.STATUS_OK;
		}
	}
	
	
	

	private boolean generateResult() {
		this.mEndVergärung = this.calcEndVergärung();
		this.mRealRestExtr = this.calcRealRstExtr();
		this.mDichte = this.calcDichte();
		this.mGewProAlk = this.calcGewProAlk();
		this.mVol = this.calcVol();
		this.mBrennwertCal = this.calcBrennwert();
		this.mBrennwertJoule = this.calcBrennwertJoule();
		return true;
	}

	private double calcBrennwert(){
		double result;
		result = (6.9*mGewProAlk+4*(mRealRestExtr-0.1))*10*1*mDichte;
		return result;
	}
	
	private double calcBrennwertJoule(){
		double result;
		result = mBrennwertCal*4.18684;
		return result;
	}
	
	private double calcEndVergärung() {

		double result;

		result = 100 * (1 - (mRstExtr / mStwrz));
		return result;

	}

	private double calcRealRstExtr() {
		double result;

		result = 0.1808 * mStwrz + 0.8192 * mRstExtr;
		return result;
	}

	private double calcDichte() {
		double result;
		result = 261.1 / (261.53 - mRstExtr);

		return result;
	}

	private double calcGewProAlk() {
		double result;
		result = (mStwrz - mRealRestExtr) / (2.0665 - 0.010665 * mStwrz);

		return result;
	}

	private double calcVol() {
		double result;
		result = mDichte * mGewProAlk / 0.7894;
		return result;
	}

	public boolean hasError() {
		return this.STATUS_CODE != ACConsts.STATUS_OK;

	}
	public double getStWrz(){
		return this.mStwrz;
	}
	
	public double getRstExtrakt(){
		return this.mRstExtr;
	}
	
	public double getRealRestExtrakt(){
		return this.mRealRestExtr;
	}
	
	public double getDichte(){
		return this.mDichte;
	}
	
	public double getGewProAlk(){
		return this.mGewProAlk;
	}
	public double getVolume(){
		return this.mVol;
	}
	
	public double getEndvergärung(){
		return this.mEndVergärung;
	}
	
	public double getBrennwertCal(){
		return this.mBrennwertCal;
	}
	
	public double getBrennwertJoule(){
		return this.mBrennwertJoule;
	}

	

}
