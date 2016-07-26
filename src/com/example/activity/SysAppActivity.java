package com.example.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.adapter.SysAppAdapter;
import com.example.base.BaseActivity;
import com.example.entities.AppInfo;
import com.example.entities.RuningAppInfo;
import com.example.phone_assistant.R;
import com.example.utils.AppInfoManager;
import com.example.view.MyActionbar;
/**
 * 系统应用
 *
 * @author wu
 *
 * 2016-6-14
 */
public class SysAppActivity extends BaseActivity {

	private MyActionbar mMa_actionbar;
	private ListView mLv_sysApp;

	@Override
	protected int getContentId() {
		return R.layout.activity_sysapp;
	}

	@Override
	protected void initView() {

		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_sysapp_actionbar);
		mLv_sysApp = (ListView) findViewById(R.id.lv_activity_sysapp_sys);
	}

	@Override
	protected void initData() {

		List<RuningAppInfo> sysApp = new ArrayList<RuningAppInfo>();

		List<AppInfo> systemPackageInfo = AppInfoManager.getAppInfoManager(getApplicationContext()).getSystemPackageInfo(true);

		for (AppInfo appInfo : systemPackageInfo) {
			PackageInfo packageInfo = appInfo.getPackageInfo();
			Drawable appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
			String appName = (String) packageInfo.applicationInfo.loadLabel(getPackageManager());
			String sourceDir = packageInfo.applicationInfo.sourceDir;
			File file = new File(sourceDir);
			long appSize = file.length();

			RuningAppInfo runingAppInfo = new RuningAppInfo();
			runingAppInfo.setIcon(appIcon);
			runingAppInfo.setLableName(appName);
			runingAppInfo.setSize(appSize);
			runingAppInfo.setDir(sourceDir);

			sysApp.add(runingAppInfo);

		}

		SysAppAdapter sysAppAdapter = new SysAppAdapter(getApplicationContext(), sysApp);
		mLv_sysApp.setAdapter(sysAppAdapter);

		mMa_actionbar.setTitle("系统应用");
	}

	@Override
	protected void initListener() {

		//返回监听
		mMa_actionbar.setBackClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

}
