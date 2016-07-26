package com.example.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
/**
 * 自定义储存情况饼图
 *
 * @author wu
 *
 * 2016-6-11
 */
public class MySpaceCircleView extends View{

	private Paint mPaint = new Paint();
	private RectF oval;
	private int step = 1;
	
	private int backgroudColor = Color.BLUE;
	private int firstColor = Color.RED;
	private int secondColor = Color.GREEN;
	private int firstAngle;
	private int secondAngle;
	
	public MySpaceCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setAngleAndAnimation(final int firstTargeAngle, final int secondTargeAngle){
		
		final Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				
				firstAngle += step;
				secondAngle += step;
				if(firstAngle >= firstTargeAngle){
					firstAngle = firstTargeAngle;
				}
				
				if(secondAngle >= secondTargeAngle){
					secondAngle = secondTargeAngle;
				}
				
				postInvalidate();
				
				if(firstAngle == firstTargeAngle && secondAngle == secondTargeAngle){
					timer.cancel();
				}
			}
		};
		timer.schedule(task , 25, 25);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		
		oval = new RectF(0, 0, width, height);
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		mPaint.setAntiAlias(true);
		mPaint.setColor(backgroudColor);
		canvas.drawArc(oval, 0, 360, true, mPaint);
		mPaint.setColor(firstColor);
		canvas.drawArc(oval, -90, firstAngle, true, mPaint);
		mPaint.setColor(secondColor);
		canvas.drawArc(oval, -90+firstAngle, secondAngle, true, mPaint);
	}

}
