package com.example.utils;

import android.content.Context;
import android.widget.Toast;
/**
 * Toast������
 *
 * @author wu
 *
 * 2016-6-6
 */
public class ToastUtil {

	private static Toast toast = null;
	/**
	 * ��ʾToast
	 * @param context ������
	 * @param msg ��Ϣ
	 * @param time ʱ��
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
