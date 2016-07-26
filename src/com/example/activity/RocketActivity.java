package com.example.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ProcessAdapter;
import com.example.base.BaseActivity;
import com.example.entities.RuningAppInfo;
import com.example.phone_assistant.R;
import com.example.utils.AppInfoManager;
import com.example.utils.CommonUtil;
import com.example.utils.MemoryManager;
import com.example.view.MyActionbar;
import com.example.view.MyProgressView;
/**
 * 进程视图
 *
 * @author wu
 *
 * 2016-6-11
 */
public class RocketActivity extends BaseActivity {

	private MyActionbar mMa_actionbar;
	private MyProgressView mMpv_progress;
	private ListView mLv_process;
	private CheckBox mCb_check;
	private TextView mTv_kill;
	private ProgressBar mPb_wait;
	
	private List<RuningAppInfo> sysData;
	ProcessAdapter adapter;
	
	@Override
	protected int getContentId() {
		return R.layout.activity_rocket;
	}

	@Override
	protected void initView() {

		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_rocket_my_actionbar);
		mMpv_progress = (MyProgressView) findViewById(R.id.mpv_activity_rocket_my_progress);
		mLv_process = (ListView) findViewById(R.id.lv_activity_rocket_progress);
		mCb_check = (CheckBox) findViewById(R.id.cb_activity_rocket_check);
		mTv_kill = (TextView) findViewById(R.id.tv_activity_rocket_kill);
		mPb_wait = (ProgressBar) findViewById(R.id.pb_activity_rocket_wait);
		mLv_process.setVisibility(View.INVISIBLE);
		
	}

	@Override
	protected void initData() {
		mMa_actionbar.setTitle("正在运行");
		
		long totalRamMemory = MemoryManager.getPhoneTotalRamMemory();
		long freeRamMemory = MemoryManager.getPhoneFreeRamMemory(getApplicationContext());
		long occupyRamMemory = totalRamMemory - freeRamMemory;
		mMpv_progress.setTitle("运行内存");
		mMpv_progress.setMaxProgress((int) totalRamMemory);
		mMpv_progress.setProgress((int) occupyRamMemory);
		mMpv_progress.setOccupy(CommonUtil.formatFileSize(occupyRamMemory));
		mMpv_progress.setTotal(CommonUtil.formatFileSize(totalRamMemory));

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Map<Integer, List<RuningAppInfo>> runingAppInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getRuningAppInfos();
				
				sysData = runingAppInfos.get(AppInfoManager.RUNING_APP_TYPE_SYS);
				
				AppInfoManager.getAppInfoManager(getApplicationContext()).getUserPackageInfo(true);
				
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						
						mLv_process.setVisibility(View.VISIBLE);
						mPb_wait.setVisibility(View.INVISIBLE);
						
						adapter = new ProcessAdapter(getApplicationContext(), sysData);
						mLv_process.setAdapter(adapter);
					}
				});
			}
		}).start();
	}

	@Override
	protected void initListener() {

		//勾选框监听
		mCb_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				for (RuningAppInfo info : sysData) {
					info.setClear(isChecked);
				}
				//会使ListView重绘
				adapter.notifyDataSetChanged();
			}
		});
		//返回监听
		mMa_actionbar.setBackClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				finish();
			}
		});
		
		//清理指定进程监听
		mTv_kill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int count = 0;
				List<RuningAppInfo> list = new ArrayList<RuningAppInfo>();
				
				for (RuningAppInfo info : sysData) {
					if(info.isClear()){
						AppInfoManager.getAppInfoManager(getApplicationContext()).killProcesses(info.getPackageName());
						list.add(info);
						count++;
					}
				}
				sysData.removeAll(list);
				list.clear();
				adapter.notifyDataSetChanged();
				Toast.makeText(getApplicationContext(), "清理了" + count + "个进程", 0).show();
			}
		});
	}

}
