package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.phone_assistant.R;

public class MyActionbar extends RelativeLayout {

	private ImageView mIv_back;
	public ImageView mIv_child;
	private TextView mTv_title;
	
	public MyActionbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initView(context);
	}

	private void initView(Context context) {

		inflate(context, R.layout.actionbar_layout, this);
		mIv_back = (ImageView) findViewById(R.id.actionbat_back);
		mIv_child = (ImageView) findViewById(R.id.actionbat_child);
		mTv_title = (TextView) findViewById(R.id.actionbar_title);
		
	}
	
	public void setTitle(String title){
		mTv_title.setText(title);
	}
	
	public void setBackImg(int Res){
		mIv_back.setImageResource(Res);
	}
	
	public void setChildImg(int Res){
		mIv_child.setImageResource(Res);
	}
	
	public void setBackClickListener(OnClickListener listenet){
		mIv_back.setOnClickListener(listenet);
	}
	
	public void setChildClickListener(OnClickListener listener){
		mIv_child.setOnClickListener(listener);
	}
	


}
