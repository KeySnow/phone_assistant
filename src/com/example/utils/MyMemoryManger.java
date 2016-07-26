package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
/**
 * ��ȡ�ڴ���Ϣ������
 *
 * @author wu
 *
 * 2016-6-2
 */
public class MyMemoryManger {
	/**
	 * ��ȡָ��Ŀ¼�Ĵ����ܿռ��С
	 * 
	 * @param path
	 * @return
	 */
	public static long getDirDiskTotalSize(String path) {
		StatFs statFs = new StatFs(path);
		int blockCount = statFs.getBlockCount();
		int blockSize = statFs.getBlockSize();

		return blockCount * blockSize;
	}

	/**
	 * ��ȡָ��Ŀ¼�Ĵ��̿��ÿռ��С
	 * 
	 * @param path
	 * @return
	 */
	public static long getDirDiskFreeSize(String path) {
		StatFs statFs = new StatFs(path);
		int availableBlocks = statFs.getAvailableBlocks();
		int blockSize = statFs.getBlockSize();

		return availableBlocks * blockSize;
	}

	/**
	 * ��ȡ�ⲿ����ռ��ܴ�С
	 * 
	 * @return
	 */
	public static long getExternalDiskTotalSize() {
		String state = Environment.getExternalStorageState();
		if (state == Environment.MEDIA_UNMOUNTED) {
			return 0;
		}
		File esd = Environment.getExternalStorageDirectory();
		StatFs statFs = new StatFs(state);

		return statFs.getBlockCount() * statFs.getBlockSize();
	}

	/**
	 * ��ȡ�ⲿ������ÿռ��С
	 * 
	 * @return
	 */
	public static long getExternalDiskFreeSize() {
		String state = Environment.getExternalStorageState();
		if (state == Environment.MEDIA_UNMOUNTED) {
			return 0;
		}
		File esd = Environment.getExternalStorageDirectory();
		StatFs statFs = new StatFs(state);

		return statFs.getAvailableBlocks() * statFs.getBlockSize();
	}
	/**
	 * ��ȡ�ֻ��ڲ�����ռ��ܴ�С
	 * @return
	 */
	public static long getAllnnerDiskTotalSize() {
		File dataDirectory = Environment.getDataDirectory();
		StatFs dataDir = new StatFs(dataDirectory.toString());
		long dataDirSize = dataDir.getBlockCount() * dataDir.getBlockSize();

		File rootDirectory = Environment.getRootDirectory();
		StatFs rootDir = new StatFs(rootDirectory.toString());
		long rootDirSize = rootDir.getBlockCount() * rootDir.getBlockSize();

		File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
		StatFs cacheDir = new StatFs(downloadCacheDirectory.toString());
		long cacheDieSize = cacheDir.getBlockCount() * cacheDir.getBlockSize();

		return dataDirSize + rootDirSize + cacheDieSize;
	}
	/**
	 * ��ȡ�ֻ��ڲ�������ÿռ��С
	 * @return
	 */
	public static long getAllnnerDiskFreeSize() {
		File dataDirectory = Environment.getDataDirectory();
		StatFs dataDir = new StatFs(dataDirectory.toString());
		long dataDirSize = dataDir.getAvailableBlocks() * dataDir.getBlockSize();
		
		File rootDirectory = Environment.getRootDirectory();
		StatFs rootDir = new StatFs(rootDirectory.toString());
		long rootDirSize = rootDir.getAvailableBlocks() * rootDir.getBlockSize();
		
		File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
		StatFs cacheDir = new StatFs(downloadCacheDirectory.toString());
		long cacheDieSize = cacheDir.getAvailableBlocks() * cacheDir.getBlockSize();
		
		return dataDirSize + rootDirSize + cacheDieSize;
	}
	/**
	 * ��ȡ���������ڴ��С
	 * @param context
	 * @return
	 */
	public static long getFreeMemory(Context context){
		
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo = new MemoryInfo();
		am.getMemoryInfo(outInfo );
		
		return outInfo.availMem;
	}
	/**
	 * ��ȡ�����ڴ��ܴ�С
	 * @param context
	 * @return
	 */
	public static long getTotalMemory(Context context){
		String result = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("/proc/meminfo"));
			String str = br.readLine();
			String[] split = str.split("\\s+");
			result = split[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Long.parseLong(result)*1024;
	}

}
