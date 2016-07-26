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

public class MyCircleView extends View {
	
	private RectF oval;
	private Paint paint = new Paint();
	private float startAngle = -90;
	private float sweepAngle;
	private boolean isRunning = false;
	private int state = 0;//1.增长 0.缩短
	private int step = 6;

	public MyCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAngle(360);
	}
	
	private void setAngle(int angle) {
		
		sweepAngle = angle;
		postInvalidate();//再次调用onDraw
	}
	
	public void setAngleAndAnimation(final int angle){
		if(isRunning){
			return;
		}
		isRunning = true;
		
		final Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			

			@Override
			public void run() {
				
				switch (state) {
				case 0://缩短
					sweepAngle = sweepAngle - step;
					postInvalidate();
					if(sweepAngle <= 0){
						sweepAngle = 0;
						state = 1;
					}
					break;
				case 1://增长
					sweepAngle = sweepAngle + step;
					postInvalidate();
					if(sweepAngle >= angle){
						sweepAngle = angle;
						timer.cancel();
						state = 0;
						isRunning = false;
					}
					break;

				default:
					break;
				}
			}
		};
		timer.schedule(task, 25, 25);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		
		oval = new RectF(0, 0, width, height);
		setMeasuredDimension(width, height);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		paint.setColor(Color.DKGRAY);
		paint.setAntiAlias(true);
		canvas.drawArc(oval, startAngle, sweepAngle, true, paint);
	}

}
