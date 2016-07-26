package com.example.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.adapter.UserAppAdapter;
import com.example.base.BaseActivity;
import com.example.entities.AppInfo;
import com.example.entities.RuningAppInfo;
import com.example.phone_assistant.R;
import com.example.utils.AppInfoManager;
import com.example.view.MyActionbar;
/**
 * 用户应用
 *
 * @author wu
 *
 * 2016-6-14
 */
public class UserAppActivity extends BaseActivity {

	private MyActionbar mMa_actionbar;
	private ListView mLv_userApp;
	private TextView mTv_run;
	private TextView mTv_uninstall;
	private List<RuningAppInfo> userApp;

	@Override
	protected int getContentId() {
		return R.layout.activity_userapp;
	}

	@Override
	protected void initView() {

		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_userapp_actionbar);
		mLv_userApp = (ListView) findViewById(R.id.lv_activity_userapp_user);
	}

	@Override
	protected void initData() {

		userApp = new ArrayList<RuningAppInfo>();

		List<AppInfo> userPackageInfo = AppInfoManager.getAppInfoManager(getApplicationContext()).getUserPackageInfo(true);

		for (AppInfo appInfo : userPackageInfo) {
			PackageInfo packageInfo = appInfo.getPackageInfo();
			Drawable appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
			String appName = (String) packageInfo.applicationInfo.loadLabel(getPackageManager());
			String sourceDir = packageInfo.applicationInfo.sourceDir;
			File file = new File(sourceDir);
			long appSize = file.length();
			String packageName = packageInfo.packageName;

			RuningAppInfo runingAppInfo = new RuningAppInfo();
			runingAppInfo.setIcon(appIcon);
			runingAppInfo.setLableName(appName);
			runingAppInfo.setSize(appSize);
			runingAppInfo.setPackageName(packageName);
			runingAppInfo.setDir(sourceDir);

			userApp.add(runingAppInfo);

		}

		UserAppAdapter userAppAdapter = new UserAppAdapter(getApplicationContext(), userApp);
		mLv_userApp.setAdapter(userAppAdapter);

		mMa_actionbar.setTitle("用户应用");
	}

	@Override
	protected void initListener() {

		//用户程序列表监听
		mLv_userApp.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				//小气泡
				View contentView = View.inflate(getApplicationContext(), R.layout.popu_window_content, null);
				mTv_run = (TextView) contentView.findViewById(R.id.tv_popu_window_content_run);
				mTv_uninstall = (TextView) contentView.findViewById(R.id.tv_popu_window_content_uninstall);
				PopupWindow popupWindow = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				popupWindow.setOutsideTouchable(true);
				popupWindow.setFocusable(true);
				popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				popupWindow.setAnimationStyle(R.style.MyPopuWindowStyle);
				popupWindow.showAsDropDown(view, view.getWidth(), -view.getHeight());

				final String packageName = userApp.get(position).getPackageName();
				//运行软件监听
				mTv_run.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(packageName);
						startActivity(launchIntentForPackage);
					}
				});
				//卸载软件监听
				mTv_uninstall.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_DELETE);
						Uri data = Uri.parse("package:" + packageName);
						intent.setData(data );
						startActivity(intent);
					}
				});
				return true;
			}
		});
		
		//返回监听
		mMa_actionbar.setBackClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

}
