package com.dragon.pojo.message.event;

/**
 * 
 * 
 * @author ganhb
 * @date 2016-5-26
 */

public class BaseEvent {
	/*
	 * ToUserName 开发者微信号 FromUserName 发送方帐号（一个OpenID） CreateTime 消息创建时间 （整型）
	 * MsgType 消息类型，event Event 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	 */
	private String ToUserName;
	private String FormUserName;
	private long CreateTime;
	private String MsgType;
	private String Event;
	
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFormUserName() {
		return FormUserName;
	}

	public void setFormUserName(String formUserName) {
		FormUserName = formUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

}
