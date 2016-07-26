package com.example.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.phone_assistant.R;
import com.example.utils.CommonUtil;
import com.example.utils.MemoryManager;
import com.example.view.MyActionbar;
import com.example.view.MyProgressView;
import com.example.view.MySpaceCircleView;
/**
 * 储存情况视图
 *
 * @author wu
 *
 * 2016-6-11
 */
public class SpaceActivity extends BaseActivity implements OnClickListener{

	private MyActionbar mMa_actionbar;
	private MySpaceCircleView mMy_space;
	private MyProgressView mMpv_progress_internal;
	private MyProgressView mMpv_progress_outer;
	private TextView mTv_sysApp;
	private TextView mTv_userApp;
	@Override
	protected int getContentId() {
		return R.layout.activity_space;
	}

	@Override
	protected void initView() {
		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_space_actionbar);
		mMy_space = (MySpaceCircleView) findViewById(R.id.space_activity_circle_view);
		mMpv_progress_internal = (MyProgressView) findViewById(R.id.mpv_my_progress_internal);
		mMpv_progress_outer = (MyProgressView) findViewById(R.id.mpv_my_progress_outer);
		mTv_sysApp = (TextView) findViewById(R.id.tv_activity_space_sys);
		mTv_userApp = (TextView) findViewById(R.id.tv_activity_space_user);
		
		
	}

	@Override
	protected void initData() {
		//手机内置储存卡
		long phoneSelfSDCardSize = MemoryManager.getPhoneSelfSDCardSize();
		long phoneSelfSDCardFreeSize = MemoryManager.getPhoneSelfSDCardFreeSize();
		
		//手机自身储存
		long phoneSelfSize = MemoryManager.getPhoneSelfSize();
		long phoneSelfFreeSize = MemoryManager.getPhoneSelfFreeSize();
		
		//手机外置储存
		long phoneOutSDCardSize = MemoryManager.getPhoneOutSDCardSize(getApplicationContext());
		long phoneOutSDCardFreeSize = MemoryManager.getPhoneOutSDCardFreeSize(getApplicationContext());
		
		//手机内置总空间
		long internalTotalSize = phoneSelfSDCardSize + phoneSelfSize;
		long internalFreeSize = phoneSelfSDCardFreeSize + phoneSelfFreeSize;
		
		//手机总空间
		long phoneTotal = internalTotalSize+phoneOutSDCardSize;
		
		//内置和外置所在比例
		int internalSizeAngle = (int) (internalTotalSize / phoneTotal) * 360;
		int outerSizeAngle = (int)(phoneOutSDCardSize / phoneTotal) * 360;
		
		mMy_space.setAngleAndAnimation(internalSizeAngle, outerSizeAngle);
		
		mMpv_progress_internal.setTitle("内置储存");
		mMpv_progress_internal.setMaxProgress((int) internalTotalSize);
		mMpv_progress_internal.setProgress((int) (internalTotalSize-internalFreeSize));
		mMpv_progress_internal.setTotal(CommonUtil.formatFileSize(internalTotalSize));
		mMpv_progress_internal.setOccupy(CommonUtil.formatFileSize(internalTotalSize-internalFreeSize));
		
		mMpv_progress_outer.setTitle("外置储存");
		mMpv_progress_outer.setMaxProgress((int) phoneOutSDCardSize);
		mMpv_progress_outer.setProgress((int) (phoneOutSDCardSize-phoneOutSDCardFreeSize));
		mMpv_progress_outer.setTotal(CommonUtil.formatFileSize(phoneOutSDCardSize));
		mMpv_progress_outer.setOccupy(CommonUtil.formatFileSize(phoneOutSDCardSize-phoneOutSDCardFreeSize));
		
		mMa_actionbar.setTitle("储存情况");
	}

	@Override
	protected void initListener() {

		mTv_sysApp.setOnClickListener(this);
		mTv_userApp.setOnClickListener(this);
		mMa_actionbar.setBackClickListener(this);
	}

	//点击监听器
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tv_activity_space_sys:
			sysApp();
			break;
		case R.id.tv_activity_space_user:
			userApp();
			break;
		case R.id.actionbat_back:
			back();
			break;

		default:
			break;
		}
	}

	//返回监听
	private void back() {

		finish();
	}

	//用户应用
	private void userApp() {
		
		startActivity(UserAppActivity.class);
	}

	//系统应用
	private void sysApp() {
		
		startActivity(SysAppActivity.class);
	}

}
