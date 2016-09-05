package com.dragon.pojo.message.event;

/**
 * 
 * EventKey 事件KEY值，与自定义菜单接口中KEY值对应
 * 
 * @author ganhb
 * @date 2016-5-26
 */

public class MenuEvent extends BaseEvent {
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
