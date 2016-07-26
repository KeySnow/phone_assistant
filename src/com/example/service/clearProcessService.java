package com.example.service;

import com.example.utils.AppInfoManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class clearProcessService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("开始清理。。。。。");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("清理中。。。。。");
		AppInfoManager.getAppInfoManager(getApplicationContext()).myKillALLProcesses();
		return super.onStartCommand(intent, flags, startId);
	}
	
	

}
