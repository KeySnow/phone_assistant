package com.example.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.utils.LogUtil;
/**
 * 活动基类
 *
 * @author wu
 *
 * 2016-5-29
 */
public abstract class BaseActivity extends Activity {

	/*▼=====以下是活动生命周期=====▼*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentId());
		
		LogUtil.showDebug(this, "onCreate...");
		
		initView();
		
		initData();
		
		initListener();
	}


	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.showDebug(this, "onCreate...");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.showDebug(this, "onRestart...");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.showDebug(this, "onResume...");
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.showDebug(this, "onPause...");
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.showDebug(this, "onStop...");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.showDebug(this, "onDestroy...");
	}
	/*▲=====以上是活动生命周期=====▲*/
	
	/*▼=====活动跳转=====▼*/
	/**
	 * 普通活动跳转
	 * @param targetClass 传入要跳转的活动
	 */
	protected void startActivity(Class<?> targetClass){
		
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
		
	}
	/**
	 * 带数据捆活动跳转
	 * @param targetClass 传入要跳转的活动
	 * @param bundle 传递数据捆
	 */
	protected void startActivity(Class<?> targetClass, Bundle bundle){
		
		Intent intent = new Intent(this, targetClass);
		intent.putExtras(bundle);
		startActivity(intent);
		
	}
	/**
	 * 带有动画特效的活动跳转
	 * @param targetClass 传入要跳转的活动
	 * @param enterAnim 进入动画
	 * @param exitAnim 退出动画
	 */
	protected void startActivity(Class<?> targetClass, int enterAnim, int exitAnim){
		
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
		overridePendingTransition(enterAnim, exitAnim);
		
	}
	/*▲=====活动跳转=====▲*/

	/*▼=====以下是子类必须重写的方法=====▼*/
	protected abstract int getContentId();
	
	protected abstract void initView();
	
	protected abstract void initData();
	
	protected abstract void initListener();
	/*▲=====以上是子类必须重写的方法=====▲*/
}
