package com.dragon.service;

import com.dragon.entity.ParkReportInfo;
import com.dragon.entity.SysUserInfo;

/** 
 * @Title ParkReportService.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-6
 */

public interface ParkReportService {
	//添加报备信息
	void SaveParkReport(String openId,String message);
	//根据openid报备信息
	ParkReportInfo findReportById(String openId);
	//根据车牌和openID获取报备消息
	String getParkReportByOpenId(String openId,String cphm);
	//获取车牌号码
	SysUserInfo findCphmByOpenId(String openId);
	
	
}
