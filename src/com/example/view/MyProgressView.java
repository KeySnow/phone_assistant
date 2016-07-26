package com.example.view;

import com.example.phone_assistant.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * �Զ����ڴ�״̬������
 *
 * @author wu
 *
 * 2016-6-6
 */
public class MyProgressView extends LinearLayout {

	private ProgressBar mPb_progress;
	private TextView mTv_title;
	private TextView mTv_occupy;
	private TextView mTv_total;
	
	public MyProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public MyProgressView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyProgressView(Context context) {
		this(context, null);
	}

	private void initView() {
		inflate(getContext(), R.layout.layout_progress, this);
		
		mPb_progress = (ProgressBar) findViewById(R.id.pb_layout_progress_progress);
		mTv_title = (TextView) findViewById(R.id.tv_layout_progress_title);
		mTv_occupy = (TextView) findViewById(R.id.tv_layout_progress_occupy);
		mTv_total = (TextView) findViewById(R.id.tv_layout_progress_total);
	}
	/**
	 * ���ý���������
	 * @param progress
	 */
	public void setProgress(int progress){
		mPb_progress.setProgress(progress);
	}
	/**
	 * ���ý��������ֵ
	 * @param max
	 */
	public void setMaxProgress(int max){
		mPb_progress.setMax(max);
	}
	/**
	 * ���ñ���
	 * @param title
	 */
	public void setTitle(String title){
		mTv_title.setText(title);
	}
	
	public void setOccupy(String text){
		mTv_occupy.setText("�����ڴ�" + text);
	}
	
	public void setTotal(String text){
		mTv_total.setText("���ڴ�" + text);
	}

	
}
