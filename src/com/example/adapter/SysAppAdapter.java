package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.provider.Telephony.Sms.Conversations;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.MyBaseAdapter;
import com.example.entities.RuningAppInfo;
import com.example.phone_assistant.R;
import com.example.utils.CommonUtil;
/**
 * 系统应用列表适配器
 *
 * @author wu
 *
 * 2016-6-14
 */
public class SysAppAdapter extends MyBaseAdapter<RuningAppInfo> {

	public SysAppAdapter(Context context, List<RuningAppInfo> datas) {
		super(context, datas);
	}

	@Override
	protected View initItemView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		ViewHolder holder = null;
		
		if(convertView == null){
			
			view = View.inflate(context, R.layout.listview_sysapp_item, null);
			holder = new ViewHolder();
			holder.mIv_icon = (ImageView) view.findViewById(R.id.iv_listview_sysapp_icon);
			holder.mTv_name = (TextView) view.findViewById(R.id.tv_listview_sysapp_name);
			holder.mTv_desc = (TextView) view.findViewById(R.id.tv_listview_sysapp_desc);
			holder.mTv_dir = (TextView) view.findViewById(R.id.tv_listview_sysapp_dir);
			view.setTag(holder);
			
		}else{
			
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		
		initItemData(holder, position);
		
		return view;
	}
	
	private void initItemData(ViewHolder holder, int position) {
		 
		RuningAppInfo runingAppInfo = datas.get(position);
		holder.mIv_icon.setImageDrawable(runingAppInfo.getIcon());
		holder.mTv_name.setText(runingAppInfo.getLableName());
		holder.mTv_desc.setText(CommonUtil.formatFileSize(runingAppInfo.getSize()));
		holder.mTv_dir.setText(runingAppInfo.getDir());
	}

	class ViewHolder{
		
		private ImageView mIv_icon;
		private TextView mTv_name;
		private TextView mTv_desc;
		private TextView mTv_dir;
	}

}
