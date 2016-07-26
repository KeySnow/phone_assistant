package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
/**
 * 电池广播接收
 *
 * @author wu
 *
 * 2016-6-12
 */
public class BatteryBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
			
			Bundle extras = intent.getExtras();
			String level = (String) extras.get(BatteryManager.EXTRA_LEVEL);
			String temp = (String) extras.get(BatteryManager.EXTRA_TEMPERATURE);
			Object health = extras.get(BatteryManager.EXTRA_HEALTH);
			System.out.println(level + " " + temp + " " + health);
		}
		
	}

}
