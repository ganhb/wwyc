package com.dragon.pojo.message.event;

/**
 * EventKey 事件KEY值，qrscene_为前缀，后面为二维码的参数值 Ticket 二维码的ticket，可用来换取二维码图片
 * 
 * @author ganhb
 * @date 2016-5-26
 */

public class ORCodeEvent extends BaseEvent {
	private String EventKey;
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
