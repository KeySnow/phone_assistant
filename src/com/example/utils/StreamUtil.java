package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 流工具类
 *
 * @author wu
 *
 * 2016-6-7
 */
public class StreamUtil {

	/**
	 * 把读取的数据写入字节数组输出流
	 * @param in 字节输入流
	 * @return 把字节数组以字符串的形式返回
	 */
	public static String streamToString(InputStream in){
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len = -1;
		byte[] buf = new byte[1024];
		
		try {
			while((len = in.read(buf)) != -1){
				baos.write(buf, 0, len);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(baos.toByteArray());
	}
}
