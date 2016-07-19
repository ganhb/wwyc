/*package com.dragon.utils;

import com.dragon.pojo.message.event.LocationEvent;

import co.xiaodao.weixin.db.util.MessageDBUtil;
import co.xiaodao.weixin.message.request.ReqLocationMessage;
import co.xiaodao.weixin.util.WeixinUtil;

*//**
 * @Title MessageDBUtil.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-15
 *//*

public class MessageDBUtil {
	public static boolean insertReqLocationMsg(LocationEvent reqLocationMessage) {
		String insertLocationMsgSql = "insert into tb_message(is_request,msg_type,open_id,create_time,msg_id,location_x,location_y,scale,label) values('"
				+ WeixinUtil.IS_REQUEST
				+ "','"
				+ reqLocationMessage.getMsgType()
				+ "','"
				+ reqLocationMessage.getFromUserName()
				+ "','"
				+ reqLocationMessage.getCreateTime()
				+ "','"
				+ reqLocationMessage.getMsgId()
				+ "','"
				+ reqLocationMessage.getLocation_X()
				+ "','"
				+ reqLocationMessage.getLocation_Y()
				+ "','"
				+ reqLocationMessage.getScale()
				+ "','"
				+ reqLocationMessage.getLabel() + "')";
		return MessageDBUtil.insertMsg(insertLocationMsgSql);
	}
}
*/