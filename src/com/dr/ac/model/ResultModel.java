package com.dr.ac.model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.dr.ac.constants.ACConsts;
@Table(name="Result")
public class ResultModel extends Model {

	private int STATUS_CODE = ACConsts.STATUS_ERROR;

	@Column(name="descr")
	private String mDescr = "";
	@Column(name="comment")
	private String mComment = "";
	@Column(name="created")
	private Date mDateCreated = new Date();

	@Column(name="stwrz")
	private double mStwrz;
	@Column(name="rstextr")
	private double mRstExtr;
	@Column(name="realrstextr")
	private double mRealRestExtr;
	@Column(name="dichte")
	private double mDichte;
	@Column(name="gewproalk")
	private double mGewProAlk;
	@Column(name="vol")
	private double mVol;
	@Column(name="endverg")
	private double mEndVergärung;
	@Column(name="brennwcal")
	private double mBrennwertCal;
	@Column(name="brennwjoule")
	private double mBrennwertJoule;

	public ResultModel() {

	}

	public ResultModel(double stwrz, double rstextra) {
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

	private double calcBrennwert() {
		double result;
		result = (6.9 * mGewProAlk + 4 * (mRealRestExtr - 0.1)) * 10 * 1
				* mDichte;
		return result;
	}

	private double calcBrennwertJoule() {
		double result;
		result = mBrennwertCal * 4.18684;
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

	public double getStWrz() {
		return this.mStwrz;
	}

	public double getRstExtrakt() {
		return this.mRstExtr;
	}

	public double getRealRestExtrakt() {
		return this.mRealRestExtr;
	}

	public double getDichte() {
		return this.mDichte;
	}

	public double getGewProAlk() {
		return this.mGewProAlk;
	}

	public double getVolume() {
		return this.mVol;
	}

	public double getEndvergärung() {
		return this.mEndVergärung;
	}

	public double getBrennwertCal() {
		return this.mBrennwertCal;
	}

	public double getBrennwertJoule() {
		return this.mBrennwertJoule;
	}
	
	public String getDescription(){
		return this.mDescr;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject data = new JSONObject();
		data.put("descr", mDescr);
		data.put("date", mDateCreated.getTime());
		data.put("comment", mComment);
		data.put("stwrz", mStwrz);
		data.put("rstextr", mRstExtr);
		data.put("realrstextr", mRealRestExtr);
		data.put("dichte", mDichte);
		data.put("gpa", mGewProAlk);
		data.put("vol", mVol);
		data.put("evg", mEndVergärung);
		data.put("bwcal", mBrennwertCal);
		data.put("bwjoule", mBrennwertJoule);
		return data;

	}

	public static ResultModel fromJSON(JSONObject data) throws JSONException {
		ResultModel model = new ResultModel();

		model.mDescr = data.optString("descr");
		model.setTime(data.optLong("date"));
		model.mComment = data.optString("comment");

		model.mStwrz = data.optDouble("stwrz");
		model.mRstExtr = data.optDouble("rstextr");
		model.mRealRestExtr = data.optDouble("realrstextr");
		model.mDichte = data.optDouble("dichte");
		model.mGewProAlk = data.optDouble("gpa");
		model.mVol = data.optDouble("vol");
		model.mEndVergärung = data.optDouble("evg");
		model.mBrennwertCal = data.optDouble("bwcal");
		model.mBrennwertJoule = data.optDouble("bwjoule");
		return model;

	}

	public Date getCreationDate(){
		return this.mDateCreated;
	}
	
	public void setTime(long time) {
		this.mDateCreated = new Date(time);
	}

}
