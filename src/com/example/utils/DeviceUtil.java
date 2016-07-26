package com.example.utils;

import android.content.Context;
/**
 * ��ȡ������Ϣ
 *
 * @author wu
 *
 * 2016-6-7
 */
public class DeviceUtil {

	/**
	 * ��ȡ�ܶ�  dpתpx
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int dp2Px(Context context, int dp){
		float density = context.getResources().getDisplayMetrics().density;
		
		return (int) (dp*density + 0.5f);
	}
	/**
	 * ��ȡ�ܶ�  pxתdp
	 * @param context
	 * @param px
	 * @return
	 */
	public static int px2Dp(Context context, int px){
		float den = context.getResources().getDisplayMetrics().density;
		
		return (int) (px/den + 0.5f);
	}
	/**
	 * ��ȡ��Ļ��
	 * @param context
	 * @return
	 */
	public static int getWindowWidth(Context context){
		
		return context.getResources().getDisplayMetrics().widthPixels;
	}
	/**
	 * ��ȡ��Ļ��
	 * @param context
	 * @return
	 */
	public static int getWindowHeight(Context context){
		
		return context.getResources().getDisplayMetrics().heightPixels;
	}
}
