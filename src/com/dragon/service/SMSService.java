package com.dragon.service;

/**
 * @Title SMSService.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-5
 */

public interface SMSService {
	// 存储用户的呼叫记录
	void saveCallSMS(String openId, String message,String cphm,String cpsf,String calling_result);

	// 查看短信留言
	void findByOpenId(String openId);

	// 根据openId查找用户手机
	String getPhoneNumberByOpenId(String openId);

	// 根据cphm查询对方用户的openId
	String getOpenIdByCphm(String cphm);

	// 根据车牌号获取报备的时间
	String getTimeByCphm(String cphm);
}
