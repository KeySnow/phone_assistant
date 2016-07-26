package com.example.entities;

import android.graphics.drawable.Drawable;

public class MyProcessInfo {

	private String label;
	private String packageName;
	private Drawable icon;
	private long size;
	private boolean isSystemApp;
	
	
	public MyProcessInfo(String label, String packageName, Drawable icon,
			long size) {
		super();
		this.label = label;
		this.packageName = packageName;
		this.icon = icon;
		this.size = size;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isSystemApp() {
		return isSystemApp;
	}
	public void setSystemApp(boolean isSystemApp) {
		this.isSystemApp = isSystemApp;
	}
	@Override
	public String toString() {
		return "MyProcessInfo [label=" + label + ", packageName=" + packageName
				+ ", icon=" + icon + ", size=" + size + ", isSystemApp="
				+ isSystemApp + "]";
	}
	
	
}
