package com.example.service;

import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.IBinder;
/**
 * ËøÆÁ·þÎñ
 *
 * @author wu
 *
 * 2016-6-16
 */
public class LockPhoneService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("ÒÑËøÆÁ¡£¡£¡£¡£¡£");
		DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		dpm.lockNow();
		
		return super.onStartCommand(intent, flags, startId);
	}
}
