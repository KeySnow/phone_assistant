package com.example.base;

import java.util.List;

import android.content.Context;
import android.renderscript.Element.DataType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<DataType> extends BaseAdapter {

	protected Context context;
	protected List<DataType> datas;
	
	public MyBaseAdapter(Context context, List<DataType> datas){
		this.context = context;
		this.datas = datas;
	}
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return initItemView(position, convertView, parent);
	}

	protected abstract View initItemView(int position, View convertView, ViewGroup parent);

}
