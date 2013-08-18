package com.dr.ac.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dr.ac.ACApplication;
import com.dr.ac.R;
import com.dr.ac.constants.ACConsts;
import com.dr.ac.model.ResultModel;

public class ConfigManager {

	private static ConfigManager sInstance = null;

	private boolean mSaveData = false;
	private Context mContext;
	private ArrayList<ResultModel> mResultData;

	private ConfigManager() {
		mContext = ACApplication.getInstance();
		this.mResultData = new ArrayList<ResultModel>();
	}

	public static ConfigManager getInstance() {
		if (sInstance == null) {
			sInstance = new ConfigManager();
			try {
				sInstance.loadData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sInstance;
	}

	public boolean doSave() {
		return this.mSaveData;
	}

	public ArrayList<ResultModel> getResultData() {
		return this.mResultData;
	}

	public void setDoSave(boolean save) {
		this.mSaveData = save;
	}

	public void saveData(ResultModel model) throws JSONException, IOException {
		JSONArray data = new JSONArray();
		data.put(model.toJSON());
		FileOutputStream fos = mContext.openFileOutput(ACConsts.DB_FILE_NAME,
				Context.MODE_PRIVATE);
		Writer out = new OutputStreamWriter(fos);
		out.write(data.toString());
		out.close();
		this.mResultData.add(model);
		Toast.makeText(mContext, R.string.toast_save_successful,
				Toast.LENGTH_SHORT).show();
	}

	public JSONArray loadData() throws IOException, JSONException {
		FileInputStream input = mContext.openFileInput(ACConsts.DB_FILE_NAME);
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder builder = new StringBuilder();
		String data;
		while ((data = reader.readLine()) != null) {
			builder.append(data);

		}

		JSONArray result = new JSONArray(builder.toString());
		for (int i = 0; i < result.length(); i++) {
			ResultModel model =ResultModel.fromJSON(result.getJSONObject(i));
			this.mResultData.add(model);
			Log.d("", model.toJSON().toString(2));
		}
		
		return result;
	}

}
