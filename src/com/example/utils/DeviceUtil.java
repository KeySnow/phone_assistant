package com.example.utils;

import android.content.Context;
/**
 * 获取屏显信息
 *
 * @author wu
 *
 * 2016-6-7
 */
public class DeviceUtil {

	/**
	 * 获取密度  dp转px
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2Px(Context context, int dp){
		float density = context.getResources().getDisplayMetrics().density;
		
		return (int) (dp*density + 0.5f);
	}
	/**
	 * 获取密度  px转dp
	 * @param context
	 * @param px
	 * @return
	 */
	public static int px2Dp(Context context, int px){
		float den = context.getResources().getDisplayMetrics().density;
		
		return (int) (px/den + 0.5f);
	}
	/**
	 * 获取屏幕宽
	 * @param context
	 * @return
	 */
	public static int getWindowWidth(Context context){
		
		return context.getResources().getDisplayMetrics().widthPixels;
	}
	/**
	 * 获取屏幕高
	 * @param context
	 * @return
	 */
	public static int getWindowHeight(Context context){
		
		return context.getResources().getDisplayMetrics().heightPixels;
	}
}
