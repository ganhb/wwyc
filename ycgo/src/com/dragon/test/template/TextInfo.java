package com.dragon.test.template;

/** 
 * @Title TextInfo.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-7
 */

public class TextInfo {
	private int id;
	private String openId;
	private String address;
	private String mobilephonenumberString;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilephonenumberString() {
		return mobilephonenumberString;
	}
	public void setMobilephonenumberString(String mobilephonenumberString) {
		this.mobilephonenumberString = mobilephonenumberString;
	}
	public TextInfo() {
		super();
	}
	@Override
	public String toString() {
		return "TextInfo [id=" + id + ", openId=" + openId + ", address="
				+ address + ", mobilephonenumberString="
				+ mobilephonenumberString + "]";
	}
	
	
	
}
