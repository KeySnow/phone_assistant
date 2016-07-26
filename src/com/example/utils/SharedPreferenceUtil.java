package com.example.utils;

import com.example.constant.Constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtil {

	public static void putBoolean(Context context, String key, boolean b){
		
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.FIRST_RUN_CONFIG, context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putBoolean(key, b);
		edit.commit();
		
	}
	
	public static boolean getBoolean(Context context, String key){
		
		SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.FIRST_RUN_CONFIG, context.MODE_PRIVATE);
		
		return sharedPreferences.getBoolean(key, true);
		
	}
}
