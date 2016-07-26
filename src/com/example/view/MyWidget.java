package com.example.view;

import com.example.phone_assistant.R;
import com.example.service.MyWidgetService;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
/**
 * 自定义小部件
 *
 * @author wu
 *
 * 2016-6-13
 */
public class MyWidget extends AppWidgetProvider{

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		System.out.println("onReceive");
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		System.out.println("onUpdate");
		
		ComponentName provider = new ComponentName(context, MyWidget.class);
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_my);
		appWidgetManager.updateAppWidget(provider, views);
		
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		System.out.println("onDeleted");
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		System.out.println("onEnabled");
		Intent service = new Intent(context, MyWidgetService.class);
		context.startService(service );
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		System.out.println("onDisabled");
	}

}
