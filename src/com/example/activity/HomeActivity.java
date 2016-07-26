package com.example.activity;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog.Builder;
import android.app.admin.DevicePolicyManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.phone_assistant.R;
import com.example.utils.AppInfoManager;
import com.example.utils.MemoryManager;
import com.example.view.MyActionbar;
import com.example.view.MyCircleView;
import com.example.view.MyDialog;
/**
 * 主页
 *
 * @author wu
 *
 * 2016-6-11
 */
public class HomeActivity extends BaseActivity implements OnTouchListener, OnClickListener{

	private MyCircleView mMcv_show;
	private MyActionbar mMa_actionbar;
	private ImageView mIv_clean;
	private TextView mTv_percent;
	private TextView mTv_run;
	private TextView mTv_phoneInfo;
	private TextView mTv_storeInfo;
	private TextView mTv_contacts;
	private TextView mTv_deviceAdmin;
	private TextView mTv_about;
	private TextView mTv_register;
	private TextView mTv_exit;
	
	private WindowManager mWM;
	private TextView myToastView;
	
	private int startX;
	private int startY;
	private WindowManager.LayoutParams params;
	
	@Override
	protected int getContentId() {
		return R.layout.activity_home;
	}

	@Override
	protected void initView() {
		mMcv_show = (MyCircleView) findViewById(R.id.mcv_activity_home_my_circle);
		mIv_clean = (ImageView) findViewById(R.id.iv_activity_home_clean);
		mTv_percent = (TextView) findViewById(R.id.tv_activity_home_percent_number);
		mTv_run = (TextView) findViewById(R.id.tv_activity_home_run);
		mTv_contacts = (TextView) findViewById(R.id.tv_activity_home_contacts);
		mTv_phoneInfo = (TextView) findViewById(R.id.tv_activity_home_phoneinfo);
		mTv_storeInfo = (TextView) findViewById(R.id.tv_activity_home_storeinfo);
		mTv_deviceAdmin = (TextView) findViewById(R.id.tv_activity_home_device);
		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_home_actionbar);
		
	}

	@Override
	protected void initData() {
		long totalRamMemory = MemoryManager.getPhoneTotalRamMemory();
		long freeRamMemory = MemoryManager.getPhoneFreeRamMemory(getApplicationContext());
		int percent = (int) ((totalRamMemory-freeRamMemory)*100 / totalRamMemory);
		mTv_percent.setText(percent + "");
		
		int angle = (percent * 360) / 100;
		mMcv_show.setAngleAndAnimation(angle);
	}

	@Override
	protected void initListener() {
		mTv_contacts.setOnClickListener(this);
		mTv_run.setOnClickListener(this);
		mTv_phoneInfo.setOnClickListener(this);
		mTv_storeInfo.setOnClickListener(this);
		mTv_deviceAdmin.setOnClickListener(this);
		mMa_actionbar.setBackClickListener(this);
		mMa_actionbar.setChildClickListener(this);
		
	}
	
	//查看进程
	private void run(){
		
		startActivity(RocketActivity.class);
		
		//自定义吐司
		/*mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
		myToastView = new TextView(getApplicationContext());
		myToastView.setText("自定义吐司。。。。");
		myToastView.setTextColor(Color.RED);
		params = new WindowManager.LayoutParams();
		params.width = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
		params.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags = android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.type = android.view.WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
		myToastView.setOnTouchListener(this);
		mWM.addView(myToastView, params);*/
		
	}
	
	public void clean(View v){
		
		AppInfoManager.getAppInfoManager(getApplicationContext()).myKillALLProcesses();
		
		initData();
		//移除吐司
		/*mWM = (WindowManager) getSystemService(WINDOW_SERVICE);
		mWM.removeView(myToastView);*/
	}

	//移动拖拽监听
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println(event.getX());
			startX = (int) (event.getX() + 0.5f);
			startY = (int) (event.getY() + 0.5f);
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println(event.getX());
			int newX = (int) (event.getX() + 0.5f);
			int newY = (int) (event.getY() + 0.5f);
			
			int dX = newX - startX;
			int dY = newY - startY;
			
			params.x += dX;
			params.y += dY;
			
			mWM.updateViewLayout(myToastView, params);
			break;
		case MotionEvent.ACTION_UP:
			
			break;

		default:
			break;
		}
		return true;
	}


	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tv_activity_home_contacts:
			contacts();
			break;
		case R.id.tv_activity_home_run:
			run();
			break;
		case R.id.tv_activity_home_phoneinfo:
			phoneInfo();
			break;
		case R.id.tv_activity_home_storeinfo:
			storeInfo();
			break;
		case R.id.tv_activity_home_device:
			deviceAdmin();
			break;
		case R.id.actionbat_back:
			back();
			break;
		case R.id.actionbat_child:
			child();
			break;

		default:
			break;
		}
	}

	//actionbar的菜单监听
	private void child() {
		
		View contentView = View.inflate(getApplicationContext(), R.layout.popu_window_actionbar_child, null);
		mTv_about = (TextView) contentView.findViewById(R.id.tv_popu_window_actionbar_child_about);
		mTv_register = (TextView) contentView.findViewById(R.id.tv_popu_window_actionbar_child_register);
		mTv_exit = (TextView) contentView.findViewById(R.id.tv_popu_window_actionbar_child_exit);
		PopupWindow popupWindow = new PopupWindow(contentView , LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		popupWindow.showAsDropDown(mMa_actionbar.mIv_child, -1, 4);
		
		OnChildItemListener listener = new OnChildItemListener();
		mTv_about.setOnClickListener(listener);
		mTv_register.setOnClickListener(listener);
		mTv_exit.setOnClickListener(listener);
		
	}
	
	class OnChildItemListener implements OnClickListener{

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.tv_popu_window_actionbar_child_about:
				softIntro();
				break;
			case R.id.tv_popu_window_actionbar_child_register:
				register();
				break;
			case R.id.tv_popu_window_actionbar_child_exit:
				exit();
				break;

			default:
				break;
			}
		}

		//退出软件监听
		private void exit() {
			Builder builder = new Builder(HomeActivity.this);
			builder.setIcon(R.drawable.icon);
			builder.setTitle("确定退出？");
			builder.setCancelable(false);
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();//销毁当前活动
//					finishAffinity();//销毁全部活动，相当于退出整个app
				}
			});
			builder.show();
		}

		//登陆监听
		private void register() {
			MyDialog myDialog = new MyDialog(HomeActivity.this);
			myDialog.show();
		}

		//软件简介监听
		private void softIntro() {
			startActivity(SoftIntroActivity.class);
		}
		
	}

	//actionbar的返回
	private void back() {
		finish();
	}

	//手机防盗
	private void deviceAdmin() {

		DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		dpm.lockNow();//锁屏
		dpm.resetPassword("222", 0);//重置手机密码
//		dpm.wipeData(0);//清除数据，慎用
	}

	//联系人
	private void contacts() {
		
		startActivity(ContactsActivity.class);
	}

	//关于手机
	private void phoneInfo() {

		startActivity(PhoneInfoActivity.class);
		
	}

	//储存情况
	private void storeInfo() {
		
		startActivity(SpaceActivity.class);
	}
	
}





























