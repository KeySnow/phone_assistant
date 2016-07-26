package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 重启广播接收
 *
 * @author wu
 *
 * 2016-6-12
 */
public class MyBootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		System.out.println("重启成功");
		
	}

}
