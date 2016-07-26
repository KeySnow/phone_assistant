package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.renderscript.Element.DataType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.MyBaseAdapter;
import com.example.entities.RuningAppInfo;
import com.example.phone_assistant.R;
import com.example.utils.CommonUtil;
/**
 * 进程列表适配器
 *
 * @author wu
 *
 * 2016-6-16
 */
public class ProcessAdapter extends MyBaseAdapter<RuningAppInfo> {

	public ProcessAdapter(Context context, List<RuningAppInfo> datas) {
		super(context, datas);
	}

	@Override
	protected View initItemView(int position, View convertView, ViewGroup parent) {
	
		View view = null;
		ViewHolder holder = null;
		if(convertView == null){
			view = View.inflate(context, R.layout.listview_process_litm, null);
			
			holder = new ViewHolder();
			holder.mIv_icon = (ImageView) view.findViewById(R.id.iv_listview_process_icon);
			holder.mTv_title = (TextView) view.findViewById(R.id.tv_listview_process_title);
			holder.mTv_desc = (TextView) view.findViewById(R.id.tv_listview_process_desc);
			holder.mCb_check = (CheckBox) view.findViewById(R.id.cb_listview_process_cb);
		
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		
		myCheckListener myCheckListener = new myCheckListener(position, datas);
		holder.mCb_check.setOnCheckedChangeListener(myCheckListener);
		
		initItemData(holder, position);
		
		return view;
	}
	
	private void initItemData(ViewHolder holder, int position) {
		
		RuningAppInfo runInfo = datas.get(position);
		holder.mIv_icon.setImageDrawable(runInfo.getIcon());
		holder.mTv_title.setText(runInfo.getLableName());
		holder.mTv_desc.setText(CommonUtil.formatFileSize(runInfo.getSize()));
		holder.mCb_check.setChecked(runInfo.isClear());
		if(runInfo.isSystem()){
			holder.mCb_check.setTextColor(Color.RED);
		}
	}

	class ViewHolder{
		private ImageView mIv_icon;
		private TextView mTv_title;
		private TextView mTv_desc;
		private CheckBox mCb_check;
	}
	
	class myCheckListener implements OnCheckedChangeListener{

		private int pos;
		private List<RuningAppInfo> data;
		public myCheckListener(int position, List<RuningAppInfo> datas) {
			this.pos = position;
			this.data = datas;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			data.get(pos).setClear(isChecked);
		}
		
	}


}
