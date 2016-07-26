package com.example.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.BasePagerAdapter;
import com.example.constant.Constant;
import com.example.phone_assistant.R;
import com.example.utils.SharedPreferenceUtil;

/**
 * 引导页
 *
 * @author wu
 *
 * 2016-5-29
 */
public class LeadActivity extends BaseActivity {

	private ViewPager mVp_lead;
	private int[] imgs = {R.drawable.adware_style_applist, R.drawable.adware_style_banner, R.drawable.adware_style_creditswall};
	private BasePagerAdapter mBasePagerAdapter;
	
	private TextView mTv_enter;
	private ImageView[] ivs = new ImageView[3];
	
	@Override
	protected int getContentId() {
		return R.layout.activity_lead;
	}

	@Override
	protected void initView() {
		
		mVp_lead = (ViewPager) findViewById(R.id.vp_lead);
		mTv_enter = (TextView) findViewById(R.id.tv_enter);
		ivs[0] = (ImageView) findViewById(R.id.iv_index1);
		ivs[1] = (ImageView) findViewById(R.id.iv_index2);
		ivs[2] = (ImageView) findViewById(R.id.iv_index3);
		
		mBasePagerAdapter = new BasePagerAdapter();
		mVp_lead.setAdapter(mBasePagerAdapter);
		mVp_lead.setPageTransformer(false, new DepthPageTransformer());
		//设置文本视图可见状态，初始状态为不可见
		mTv_enter.setVisibility(View.INVISIBLE);
		//初始化图片视图
		ivs[0].setImageResource(R.drawable.adware_style_selected);
	}

	@Override
	protected void initData() {
		
		for(int i = 0; i < imgs.length; i++){
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgs[i]);
			mBasePagerAdapter.addView(iv);
		}
		//通知数据已发生改变
		mBasePagerAdapter.notifyDataSetChanged();
	}

	@Override
	protected void initListener() {
		//图片轮播监听器
		mVp_lead.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if(arg0 >= 2){
					mTv_enter.setVisibility(View.VISIBLE);
				}else{
					mTv_enter.setVisibility(View.INVISIBLE);
				}
				
				for(int i = 0; i < ivs.length; i++){
					ivs[i].setImageResource(R.drawable.adware_style_default);
				}
				
				ivs[arg0].setImageResource(R.drawable.adware_style_selected);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		//判断是否是第一次进入软件，如果不是第一次则跳过引导页
		if(!SharedPreferenceUtil.getBoolean(this, Constant.IS_FIRST_RUN)){
			startActivity(LoadingActivity.class);
			finish();
		}else{
			SharedPreferenceUtil.putBoolean(this, Constant.IS_FIRST_RUN, false);
		}
	}
	//监听enter点击事件
	public void enter(View v){
		
		startActivity(LoadingActivity.class);
		finish();
	}
	
	//图片切换动画效果
	class DepthPageTransformer implements ViewPager.PageTransformer {
	    private static final float MIN_SCALE = 0.75f;

	    public void transformPage(View view, float position) {
	        int pageWidth = view.getWidth();

	        if (position < -1) { // [-Infinity,-1)
	            // This page is way off-screen to the left.
	            view.setAlpha(0);

	        } else if (position <= 0) { // [-1,0]
	            // Use the default slide transition when moving to the left page
	            view.setAlpha(1);
	            view.setTranslationX(0);
	            view.setScaleX(1);
	            view.setScaleY(1);

	        } else if (position <= 1) { // (0,1]
	            // Fade the page out.
	            view.setAlpha(1 - position);

	            // Counteract the default slide transition
	            view.setTranslationX(pageWidth * -position);

	            // Scale the page down (between MIN_SCALE and 1)
	            float scaleFactor = MIN_SCALE
	                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
	            view.setScaleX(scaleFactor);
	            view.setScaleY(scaleFactor);

	        } else { // (1,+Infinity]
	            // This page is way off-screen to the right.
	            view.setAlpha(0);
	        }
	    }
	}
	
	

}
