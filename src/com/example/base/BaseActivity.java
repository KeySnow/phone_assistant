package com.example.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.utils.LogUtil;
/**
 * �����
 *
 * @author wu
 *
 * 2016-5-29
 */
public abstract class BaseActivity extends Activity {

	/*��=====�����ǻ��������=====��*/
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
	/*��=====�����ǻ��������=====��*/
	
	/*��=====���ת=====��*/
	/**
	 * ��ͨ���ת
	 * @param targetClass ����Ҫ��ת�Ļ
	 */
	protected void startActivity(Class<?> targetClass){
		
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
		
	}
	/**
	 * �����������ת
	 * @param targetClass ����Ҫ��ת�Ļ
	 * @param bundle ����������
	 */
	protected void startActivity(Class<?> targetClass, Bundle bundle){
		
		Intent intent = new Intent(this, targetClass);
		intent.putExtras(bundle);
		startActivity(intent);
		
	}
	/**
	 * ���ж�����Ч�Ļ��ת
	 * @param targetClass ����Ҫ��ת�Ļ
	 * @param enterAnim ���붯��
	 * @param exitAnim �˳�����
	 */
	protected void startActivity(Class<?> targetClass, int enterAnim, int exitAnim){
		
		Intent intent = new Intent(this, targetClass);
		startActivity(intent);
		overridePendingTransition(enterAnim, exitAnim);
		
	}
	/*��=====���ת=====��*/

	/*��=====���������������д�ķ���=====��*/
	protected abstract int getContentId();
	
	protected abstract void initView();
	
	protected abstract void initData();
	
	protected abstract void initListener();
	/*��=====���������������д�ķ���=====��*/
}
