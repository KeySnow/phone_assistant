package com.example.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.example.phone_assistant.R;
import com.example.view.MyWidget;
/**
 * 自定义小部件服务
 *
 * @author wu
 *
 * 2016-6-15
 */
public class MyWidgetService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		final AppWidgetManager awm = AppWidgetManager.getInstance(getApplicationContext());
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			long i = 1;
			@Override
			public void run() {
				ComponentName provider = new ComponentName(getApplicationContext(), MyWidget.class);
				RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_my);
				if(i % 2 == 0){
					
					views.setTextViewText(R.id.tv_widget_my, "天空好想下雨");
				}else{
					
					views.setTextViewText(R.id.tv_widget_my, "我好想住你隔壁！");
				}
				i++;
				awm.updateAppWidget(provider, views);
				
			}
		};
		timer.schedule(task , 3000, 3000);
		
	}
}
