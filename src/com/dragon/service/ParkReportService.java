package com.dragon.service;

import com.dragon.pojo.ParkReportInfo;
import com.dragon.pojo.SysUserInfo;

/**
 * @Title ParkReportService.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-6
 */

public interface ParkReportService {
	// 添加报备信息
	void SaveParkReport(String openId, String message, String reportTime, String cphm , String cpsf);

	// 根据openid报备信息
	ParkReportInfo findReportById(String openId);

	// 根据车牌和openID获取报备消息
	String getParkReportByOpenId(String openId, String cphm);

	// 获取车牌号码
	SysUserInfo findCphmByOpenId(String openId);

	// 根据车牌号，openid，分组查询
	String getMsgByCphmAndOpenId(String cphm, String openId);

	// 根据车牌号获取报备消息，分组，对方的
	String getMsgByCphm(String cphm);

	// 根据车牌号查用户手机
	String getPhoneNumberByCphm(String Cphm);
	
	//第二手机
	String getPhoneNumberSecondByCphm(String Cphm);

	String getTimeByCphm(String cphm);
	
	//根据openId来获取自己的手机号码（呼叫过程用）
	String getMobileByself(String openId);

}
