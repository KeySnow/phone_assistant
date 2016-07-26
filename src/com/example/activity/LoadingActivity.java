package com.example.activity;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.base.BaseActivity;
import com.example.phone_assistant.R;
import com.example.service.MusicService;
/**
 * ����logoҳ��
 *
 * @author wu
 *
 * 2016-5-30
 */
public class LoadingActivity extends BaseActivity{

	private ImageView mIv_logo;
	private Intent service;
	@Override
	protected int getContentId() {
		return R.layout.activity_loading;
	}

	@Override
	protected void initView() {
		
		service = new Intent(this, MusicService.class);
		startService(service);
		
		mIv_logo = (ImageView) findViewById(R.id.iv_logo);
		//���䶯����Ч
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(3000);
		aa.setFillAfter(true);
		//��ת������Ч
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(3000);
		ra.setFillAfter(true);
		//���춯����Ч
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(3000);
		sa.setFillAfter(true);
		//������Ч����
		AnimationSet as = new AnimationSet(false);
		as.addAnimation(aa);
		as.addAnimation(ra);
		as.addAnimation(sa);
		as.setDuration(4000);
		as.setFillAfter(true);
		//��ʼ����
		mIv_logo.startAnimation(as);
		//��������
		aa.setAnimationListener(new AnimationListener() {
			//����������ʼ�¼�
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			//���������ظ��¼�
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			//�������������¼�
			@Override
			public void onAnimationEnd(Animation animation) {
				//���ת
				startActivity(HomeActivity.class);
				finish();
			}
		});
	}

	@Override
	protected void initData() {
		
	}

	@Override
	protected void initListener() {
		
	}

	@Override
	protected void onDestroy() {
		stopService(service);//ֹͣ����
		super.onDestroy();
	}
	
}
