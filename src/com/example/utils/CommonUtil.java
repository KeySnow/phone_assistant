package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ���ù�����
 *
 * @author wu
 *
 * 2016-6-7
 */
public class CommonUtil {

	/**
	 * ����ʱ���ʽ
	 * @param time
	 * @return
	 */
	public static String formatDate(long time){
		String pattern = new String("yyyy-MM-dd kk:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		return format.format(new Date(time));
	}
	/**
	 * �ļ���С��ʽ
	 * @param size
	 * @return
	 */
	public static String formatFileSize(long size){
		
		StringBuffer sb = new StringBuffer();
		
		if(size < 1024){
			sb.append(size);
			sb.append("B");
		}else if(size < (1024*1024)){
			sb.append(size / 1024);
			sb.append("KB");
		}else if(size < (1024*1024*1024)){
			sb.append(size / (1024*1024));
			sb.append("MB");
		}else if(size < (1024*1024*1024*1024)){
			sb.append(size / (1024*1024*1024));
			sb.append("GB");
		}
		
		return sb.toString();
	}
}
