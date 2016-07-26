package com.example.entities;
/**
 * 联系人实体类
 *
 * @author wu
 *
 * 2016-6-11
 */
public class MyContact {

	private String name;//姓名
	private String email;//邮件
	private String phoneNum;//手机号码
	private String address;//地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "MyContact [name=" + name + ", email=" + email + ", phoneNum="
				+ phoneNum + ", address=" + address + "]";
	}
	
	
}
