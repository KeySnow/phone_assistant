package com.example.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

import com.example.base.BaseActivity;
import com.example.base.BasePagerAdapter;
import com.example.phone_assistant.R;
/**
 * Èí¼þ¼ò½é
 *
 * @author wu
 *
 * 2016-6-16
 */
public class SoftIntroActivity extends BaseActivity {

	private ViewPager mVp_softIntro;
	private BasePagerAdapter mBasePagerAdapter;
	private int[] arrImg = {R.drawable.logo_1,R.drawable.logo_2,R.drawable.logo_3,R.drawable.logo_4,R.drawable.logo_5};
	private int currentItem = 0;
	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Message newMsg = new Message();
				newMsg.what = 0;
				mVp_softIntro.setCurrentItem(currentItem++);
				handler.sendMessageDelayed(newMsg , 2000);
				break;
			case 1:
				if(msg.arg1 == 4){
					finish();
				}
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected int getContentId() {
		return R.layout.activity_softintro;
	}

	@Override
	protected void initView() {

		mBasePagerAdapter = new BasePagerAdapter();
		mVp_softIntro = (ViewPager) findViewById(R.id.vp_activity_softintro_intro);
		mVp_softIntro.setAdapter(mBasePagerAdapter);
	}

	@Override
	protected void initData() {

		for(int i = 0; i < arrImg.length; i++){
			ImageView iv = new ImageView(this);
			iv.setImageResource(arrImg[i]);
			mBasePagerAdapter.addView(iv);
		}
		mBasePagerAdapter.notifyDataSetChanged();
		
		Message msg = new Message();
		msg.what = 0;
		handler.sendMessageDelayed(msg , 2000);
	}

	@Override
	protected void initListener() {

		mVp_softIntro.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {

				Message msg = new Message();
				msg.arg1 = arg0;
				msg.what = 1;
				handler.sendMessageDelayed(msg, 2000);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

}
