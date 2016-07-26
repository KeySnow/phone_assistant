package com.example.utils;

import android.content.Context;
import android.widget.Toast;
/**
 * Toast工具类
 *
 * @author wu
 *
 * 2016-6-6
 */
public class ToastUtil {

	private static Toast toast = null;
	/**
	 * 显示Toast
	 * @param context 上下文
	 * @param msg 消息
	 * @param time 时间
	 */
	public static void showToast(Context context, String msg, int time){
		if(toast == null){
			toast.makeText(context, msg, time);
		}
		toast.setText(msg);
		toast.setDuration(time);
		toast.show();
	}
}
