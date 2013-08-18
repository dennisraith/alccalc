package com.dr.ac;

import android.app.Application;
import android.content.Context;

public class ACApplication extends Application {

	private static ACApplication sInstance;
	
	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
	}
	
	public static ACApplication getInstance(){
		return sInstance;
	}
}
