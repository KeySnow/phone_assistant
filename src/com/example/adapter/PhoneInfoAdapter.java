package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.MyBaseAdapter;
import com.example.entities.AppInfo;
import com.example.phone_assistant.R;

public class PhoneInfoAdapter extends MyBaseAdapter<AppInfo> {

	private String[] phoneInfoItem = {"本机号码","运营商","串号","系统基带版本","系统版本号","系统sdk版本号", 
			"设置版本号","型号名称","品牌" ,"制造商","CPU类型","CPU名称"};
	
	public PhoneInfoAdapter(Context context, List<AppInfo> datas) {
		super(context, datas);
	}

	@Override
	protected View initItemView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		ViewHolder holder = null;
		
		if(convertView == null){
			
			view = View.inflate(context, R.layout.listview_phoneinfo_item, null);
			holder = new ViewHolder();
			holder.mTv_name = (TextView) view.findViewById(R.id.tv_listview_phoneinfo_name);
			holder.mTv_desc = (TextView) view.findViewById(R.id.tv_listview_phoneinfo_desc);
			view.setTag(holder);
			
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		
		initItemData(holder, position);
		
		return view;
	}
	
	private void initItemData(ViewHolder holder, int position) {
		
		holder.mTv_name.setText(phoneInfoItem[position]);
		holder.mTv_desc.setText((CharSequence) datas.get(position));
	}

	class ViewHolder{
		
		private TextView mTv_name;
		private TextView mTv_desc;
	}

}
