package com.dr.ac;

import com.activeandroid.ActiveAndroid;

import android.app.Application;
import android.content.Context;

public class ACApplication extends Application {

	private static ACApplication sInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		ActiveAndroid.initialize(this);
	}
	
	@Override
	public void onTerminate() {
		ActiveAndroid.dispose();
		super.onTerminate();
	}
	public static ACApplication getInstance(){
		return sInstance;
	}
}
