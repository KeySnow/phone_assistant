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
 * ���������ͼ
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
		//�ֻ����ô��濨
		long phoneSelfSDCardSize = MemoryManager.getPhoneSelfSDCardSize();
		long phoneSelfSDCardFreeSize = MemoryManager.getPhoneSelfSDCardFreeSize();
		
		//�ֻ�������
		long phoneSelfSize = MemoryManager.getPhoneSelfSize();
		long phoneSelfFreeSize = MemoryManager.getPhoneSelfFreeSize();
		
		//�ֻ����ô���
		long phoneOutSDCardSize = MemoryManager.getPhoneOutSDCardSize(getApplicationContext());
		long phoneOutSDCardFreeSize = MemoryManager.getPhoneOutSDCardFreeSize(getApplicationContext());
		
		//�ֻ������ܿռ�
		long internalTotalSize = phoneSelfSDCardSize + phoneSelfSize;
		long internalFreeSize = phoneSelfSDCardFreeSize + phoneSelfFreeSize;
		
		//�ֻ��ܿռ�
		long phoneTotal = internalTotalSize+phoneOutSDCardSize;
		
		//���ú��������ڱ���
		int internalSizeAngle = (int) (internalTotalSize / phoneTotal) * 360;
		int outerSizeAngle = (int)(phoneOutSDCardSize / phoneTotal) * 360;
		
		mMy_space.setAngleAndAnimation(internalSizeAngle, outerSizeAngle);
		
		mMpv_progress_internal.setTitle("���ô���");
		mMpv_progress_internal.setMaxProgress((int) internalTotalSize);
		mMpv_progress_internal.setProgress((int) (internalTotalSize-internalFreeSize));
		mMpv_progress_internal.setTotal(CommonUtil.formatFileSize(internalTotalSize));
		mMpv_progress_internal.setOccupy(CommonUtil.formatFileSize(internalTotalSize-internalFreeSize));
		
		mMpv_progress_outer.setTitle("���ô���");
		mMpv_progress_outer.setMaxProgress((int) phoneOutSDCardSize);
		mMpv_progress_outer.setProgress((int) (phoneOutSDCardSize-phoneOutSDCardFreeSize));
		mMpv_progress_outer.setTotal(CommonUtil.formatFileSize(phoneOutSDCardSize));
		mMpv_progress_outer.setOccupy(CommonUtil.formatFileSize(phoneOutSDCardSize-phoneOutSDCardFreeSize));
		
		mMa_actionbar.setTitle("�������");
	}

	@Override
	protected void initListener() {

		mTv_sysApp.setOnClickListener(this);
		mTv_userApp.setOnClickListener(this);
		mMa_actionbar.setBackClickListener(this);
	}

	//���������
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

	//���ؼ���
	private void back() {

		finish();
	}

	//�û�Ӧ��
	private void userApp() {
		
		startActivity(UserAppActivity.class);
	}

	//ϵͳӦ��
	private void sysApp() {
		
		startActivity(SysAppActivity.class);
	}

}
