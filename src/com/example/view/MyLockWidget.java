package com.example.view;

import com.example.service.MyLockWidgetService;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
/**
 * 自定义锁屏小部件
 *
 * @author wu
 *
 * 2016-6-16
 */
public class MyLockWidget extends AppWidgetProvider {

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		
		Intent service = new Intent(context, MyLockWidgetService.class);
		context.startService(service );
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

	
	
}
