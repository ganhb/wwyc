package com.dragon.service;

/** 
 * @Title UserService.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public interface UserService {
	//存储用户基本信息（注册）
	void saveUserInfo(String openId,String userName,String mobilePhone,String cpsf,String cpqh,String cphm);
	//保存用户签到后信息（用户表）
	void SaveWeixinUser(String openId);
	//保存签到时信息（签到表）
	void saveWeixinSign(String openId, int signPoints);
	//更新用户总积分
	void updateUserPoints(String openId, int signPoints);
	// 判断用户今天是否签到过
	boolean isTodaySigned(String openId);
	// 判断用户本周是否第七次签到
	boolean isWeekSigned(String openId, String monday);
	// 查询用户积分
	int queryUserPoints(String openId);
}

