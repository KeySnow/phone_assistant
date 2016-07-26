package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.example.entities.RuningAppInfo;
import com.example.phone_assistant.R;
import com.example.utils.AppInfoManager;
import com.example.utils.CommonUtil;
import com.example.utils.MemoryManager;
import com.example.view.MyAssistantWidget;
/**
 * С��������
 *
 * @author wu
 *
 * 2016-6-14
 */
public class MyAssistantWidgetService extends Service {

	private AppWidgetManager mAwm;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mAwm = AppWidgetManager.getInstance(getApplicationContext());
		final Intent intent = new Intent(this, clearProcessService.class);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				
				final ComponentName provider = new ComponentName(getApplicationContext(), MyAssistantWidget.class);
				final RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_assistant_my);
				
				//��ȡ�������е�Ӧ��
				Map<Integer, List<RuningAppInfo>> runingAppInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getRuningAppInfos();
				List<RuningAppInfo> sysApp = runingAppInfos.get(AppInfoManager.RUNING_APP_TYPE_SYS);
				List<RuningAppInfo> userApp = runingAppInfos.get(AppInfoManager.RUNING_APP_TYPE_USER);
				//��ȡռ���ڴ�
				long freeRamMemory = MemoryManager.getPhoneFreeRamMemory(getApplicationContext());
				long totalRamMemory = MemoryManager.getPhoneTotalRamMemory();
				final long occupyRamMemory = totalRamMemory - freeRamMemory;
				
				views.setTextViewText(R.id.tv_widget_assistant_soft, "��������:" + (sysApp.size() + userApp.size()));
				views.setTextViewText(R.id.tv_widget_assistant_occupy, "ռ���ڴ�:" + CommonUtil.formatFileSize(occupyRamMemory));
				
				PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
				views.setOnClickPendingIntent(R.id.tv_widget_assistant_clear, pendingIntent );
				
				mAwm.updateAppWidget(provider, views);
			}
		};
		timer.schedule(task, 100, 100);
		
	}

}
