package com.example.utils;

import android.util.Log;
/**
 * ��־������
 *
 * @author wu
 *
 * 2016-5-29
 */
public class LogUtil {
	//�����Ƿ����־�������׶�Ϊtrue����Ŀ����������Ϊfalse
	private static boolean isOpenLog = true;
	/**
	 * ��ʾ��־
	 * @param obj ���뵱ǰ��
	 * @param msg ������Ҫ��ӡ����־��Ϣ
	 */
	public static void showDebug(Object obj, String msg){
		if(isOpenLog){
			Log.d(obj.getClass().getSimpleName(), msg);
		}
	}
}
