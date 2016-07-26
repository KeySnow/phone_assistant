package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.adapter.PhoneInfoAdapter;
import com.example.base.BaseActivity;
import com.example.phone_assistant.R;
import com.example.utils.AppInfoManager;
import com.example.utils.PhoneManager;
import com.example.view.MyActionbar;
/**
 * 手机信息
 *
 * @author wu
 *
 * 2016-6-13
 */
public class PhoneInfoActivity extends BaseActivity {

	private MyActionbar mMa_actionbar;
	private ListView mLv_phoneInfo;

	@Override
	protected int getContentId() {
		return R.layout.activity_phoneinfo;
	}

	@Override
	protected void initView() {

		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_phoneinfo_actionbar);
		mLv_phoneInfo = (ListView) findViewById(R.id.lv_activity_phoneinfo_phone);
	}

	@Override
	protected void initData() {

		List datas = getPhoneInfo();
		PhoneInfoAdapter phoneInfoAdapter = new PhoneInfoAdapter(getApplicationContext(), datas);
		mLv_phoneInfo.setAdapter(phoneInfoAdapter);

		mMa_actionbar.setTitle("关于手机");
	}

	private List getPhoneInfo() {

		List datas = new ArrayList();
		PhoneManager phoneManage = PhoneManager.getPhoneManage(getApplicationContext());
		datas.add(phoneManage.getPhoneNumber());//获取手机号码
		datas.add(phoneManage.getPhoneTelSimName());//获取运营商
		datas.add(phoneManage.getPhoneIMEI());//获取串号
		datas.add(phoneManage.getPhoneSystemBasebandVersion());//获取系统基带版本
		datas.add(phoneManage.getPhoneSystemVersion());//获取系统版本号
		datas.add(phoneManage.getPhoneSystemVersionSDK() + "");//获取系统sdk版本号
		datas.add(phoneManage.getPhoneSystemVersionID());//设置版本号
		datas.add(phoneManage.getPhoneModelName());//型号名称
		datas.add(phoneManage.getPhoneName1());//品牌
		datas.add(phoneManage.getPhoneName2());//制造商
		datas.add(phoneManage.getPhoneCPUName());//CPU类型名称
		datas.add(phoneManage.getPhoneCpuName());//cpu名称

		return datas;
	}

	@Override
	protected void initListener() {

		//返回监听
		mMa_actionbar.setBackClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

}
