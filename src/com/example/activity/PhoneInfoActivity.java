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
 * �ֻ���Ϣ
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

		mMa_actionbar.setTitle("�����ֻ�");
	}

	private List getPhoneInfo() {

		List datas = new ArrayList();
		PhoneManager phoneManage = PhoneManager.getPhoneManage(getApplicationContext());
		datas.add(phoneManage.getPhoneNumber());//��ȡ�ֻ�����
		datas.add(phoneManage.getPhoneTelSimName());//��ȡ��Ӫ��
		datas.add(phoneManage.getPhoneIMEI());//��ȡ����
		datas.add(phoneManage.getPhoneSystemBasebandVersion());//��ȡϵͳ�����汾
		datas.add(phoneManage.getPhoneSystemVersion());//��ȡϵͳ�汾��
		datas.add(phoneManage.getPhoneSystemVersionSDK() + "");//��ȡϵͳsdk�汾��
		datas.add(phoneManage.getPhoneSystemVersionID());//���ð汾��
		datas.add(phoneManage.getPhoneModelName());//�ͺ�����
		datas.add(phoneManage.getPhoneName1());//Ʒ��
		datas.add(phoneManage.getPhoneName2());//������
		datas.add(phoneManage.getPhoneCPUName());//CPU��������
		datas.add(phoneManage.getPhoneCpuName());//cpu����

		return datas;
	}

	@Override
	protected void initListener() {

		//���ؼ���
		mMa_actionbar.setBackClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

}
