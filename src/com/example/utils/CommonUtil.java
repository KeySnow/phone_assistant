package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 常用工具类
 *
 * @author wu
 *
 * 2016-6-7
 */
public class CommonUtil {

	/**
	 * 设置时间格式
	 * @param time
	 * @return
	 */
	public static String formatDate(long time){
		String pattern = new String("yyyy-MM-dd kk:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		return format.format(new Date(time));
	}
	/**
	 * 文件大小格式
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
