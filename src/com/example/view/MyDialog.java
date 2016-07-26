package com.example.view;

import com.example.phone_assistant.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
/**
 * 自定义对话框视图
 *
 * @author wu
 *
 * 2016-6-12
 */
public class MyDialog extends Dialog{

	public MyDialog(Context context) {
		super(context, R.style.MyDialogStyle);
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.dialog_my_contentview);
		
		Window window = getWindow();
		LayoutParams attributes = window.getAttributes();
		attributes.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
		window.setAttributes(attributes);
	}

}
