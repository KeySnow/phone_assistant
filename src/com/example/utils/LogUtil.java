package com.example.utils;

import android.util.Log;
/**
 * 日志工具类
 *
 * @author wu
 *
 * 2016-5-29
 */
public class LogUtil {
	//控制是否打开日志，开发阶段为true，项目上线了设置为false
	private static boolean isOpenLog = true;
	/**
	 * 显示日志
	 * @param obj 传入当前类
	 * @param msg 传入需要打印的日志信息
	 */
	public static void showDebug(Object obj, String msg){
		if(isOpenLog){
			Log.d(obj.getClass().getSimpleName(), msg);
		}
	}
}
