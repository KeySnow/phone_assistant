package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.MyBaseAdapter;
import com.example.entities.MyContact;
import com.example.phone_assistant.R;
/**
 * 联系人列表视图适配器
 *
 * @author wu
 *
 * 2016-6-11
 */
public class ContactsAdapter extends MyBaseAdapter<MyContact> {

	public ContactsAdapter(Context context, List<MyContact> datas) {
		super(context, datas);
	}

	@Override
	protected View initItemView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		ViewHolder holder = null;
		
		if(convertView == null){
			view = View.inflate(context, R.layout.listview_contacts_item, null);
			holder = new ViewHolder();
			holder.mTv_contacts_name = (TextView) view.findViewById(R.id.tv_listview_contacts_name);
			holder.mTv_contacts_phoneNum = (TextView) view.findViewById(R.id.tv_listview_contacts_phone_num);
			holder.mIv_tel = (ImageView) view.findViewById(R.id.iv_listview_contacts_tel);
			holder.mIv_note = (ImageView) view.findViewById(R.id.iv_listview_contacts_note);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		
		initItemData(holder, position);
		initItemListener(holder, position);
		
		return view;
	}
	
	private void initItemListener(ViewHolder holder, int position) {

		final String phoneNum = datas.get(position).getPhoneNum();
		holder.mIv_tel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_CALL);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Uri data = Uri.parse("tel:" + phoneNum);
				intent.setData(data );
				context.startActivity(intent);
			}
		});
		holder.mIv_note.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SENDTO);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Uri data = Uri.parse("smsto:" + phoneNum);
				intent.setData(data );
//				intent.putExtra("sms_body", "hello!");
				context.startActivity(intent);
			}
		});
	}

	private void initItemData(ViewHolder holder, int position) {
		
		MyContact myContact = datas.get(position);
		holder.mTv_contacts_name.setText(myContact.getName());
		holder.mTv_contacts_phoneNum.setText("手机  " + myContact.getPhoneNum());
		
	}

	class ViewHolder{
		private TextView mTv_contacts_name;
		private TextView mTv_contacts_phoneNum;
		private ImageView mIv_tel;
		private ImageView mIv_note;
	}

}














