package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * ��������
 *
 * @author wu
 *
 * 2016-6-7
 */
public class StreamUtil {

	/**
	 * �Ѷ�ȡ������д���ֽ����������
	 * @param in �ֽ�������
	 * @return ���ֽ��������ַ�������ʽ����
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
