package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.ContactsAdapter;
import com.example.base.BaseActivity;
import com.example.entities.MyContact;
import com.example.phone_assistant.R;
import com.example.view.MyActionbar;
/**
 * 联系人视图
 *
 * @author wu
 *
 * 2016-6-11
 */
public class ContactsActivity extends BaseActivity {

	private MyActionbar mMa_actionbar;
	private ListView mLv_contacts;
	private List<MyContact> contacts;

	@Override
	protected int getContentId() {
		return R.layout.activity_contacts;
	}

	@Override
	protected void initView() {

		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_contacts_my_actionbar);
		mLv_contacts = (ListView) findViewById(R.id.lv_activity_contacts_contacts);
	}

	@Override
	protected void initData() {

		contacts = getContent();
		ContactsAdapter contactsAdapter = new ContactsAdapter(getApplicationContext(), contacts);
		mLv_contacts.setAdapter(contactsAdapter);

		mMa_actionbar.setTitle("联系人");
	}

	//解析获取联系人数据方法
	private List<MyContact> getContent() {
		List<MyContact> contacts = new ArrayList<MyContact>();

		ContentResolver resolver = getContentResolver();

		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		String[] projection = new String[]{"contact_id"};
		Cursor cursor = resolver.query(uri, projection, null, null, null);

		while(cursor.moveToNext()){

			Uri uri2 = Uri.parse("content://com.android.contacts/data");
			String[] projection2 = new String[]{"mimetype","data1"};
			String[] selectionArgs = new String[]{cursor.getInt(0) + ""};
			Cursor cursor2 = resolver.query(uri2, projection2, "contact_id = ?", selectionArgs, null);

			MyContact myContact = new MyContact();
			while(cursor2.moveToNext()){
				String mimetype = cursor2.getString(0);
				if(mimetype.equals("vnd.android.cursor.item/email_v2")){
					myContact.setEmail(cursor2.getString(1));
				}else if(mimetype.equals("vnd.android.cursor.item/phone_v2")){
					myContact.setPhoneNum(cursor2.getString(1));
				}else if(mimetype.equals("vnd.android.cursor.item/name")){
					myContact.setName(cursor2.getString(1));
				}else if(mimetype.equals("vnd.android.cursor.item/postal-address_v2")){
					myContact.setAddress(cursor2.getString(1));
				}
			}
			contacts.add(myContact);
		}
		return contacts;
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
