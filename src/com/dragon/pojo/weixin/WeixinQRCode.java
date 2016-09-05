package com.dragon.pojo.weixin;

/** 
 * @Title WeixinQRCode.java
 * @Description 临时二维码信息
 * @author ganhb 
 * @date 2016-6-29
 */

public class WeixinQRCode {
	// 获取的二维码ticket
	private String ticket;
	// 临时二维码最大的天数为30
	private int expireSeconds;
	//二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	private String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
