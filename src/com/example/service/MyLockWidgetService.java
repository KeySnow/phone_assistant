package com.example.service;

import com.example.phone_assistant.R;
import com.example.view.MyLockWidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
/**
 * 自定义锁屏小部件后台服务
 *
 * @author wu
 *
 * 2016-6-16
 */
public class MyLockWidgetService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		AppWidgetManager awm = AppWidgetManager.getInstance(getApplicationContext());
		ComponentName provider = new ComponentName(this, MyLockWidget.class);
		RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_lock_my);
		Intent intent = new Intent(this, LockPhoneService.class);
		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.iv_widget_lock_my, pendingIntent);
		awm.updateAppWidget(provider, views );
	}

}
