package com.dragon.service;

/** 
 * @Title SMSService.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public interface SMSService {
	// 保存用户短信留言
	 void saveCallSMS(String openId, String message);
	// 查看短信留言
	 void findByOpenId(String openId);
}
