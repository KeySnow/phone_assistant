package com.example.base;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BasePagerAdapter extends PagerAdapter{

	private ArrayList<ImageView> arrImg = new ArrayList<ImageView>();
	
	public void addView(ImageView iv){
		arrImg.add(iv);
	}
	
	@Override
	public int getCount() {
		return arrImg.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	
		container.removeView(arrImg.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		container.addView(arrImg.get(position));
		
		return arrImg.get(position);
	}
	
	

}
