package com.dragon.service;

import java.util.List;

import com.dragon.pojo.car.CarInfo;

/** 
 * @Title UserService.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public interface UserService {
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
	//保存用户基本信息（车辆表）
	void updateSysUserInfo(int carId, String userName, String mobilePhone, String cpsf, String cphm,String cllx,String clpp,String clxh,String cjh,String registTime,String insuranceTime);
	//存储用户基本信息（会员表）
	void updateMemberUserInfo(String openId, String userName, String mobilePhone, String sex,String address);
	//检查用户是否已经存在数据库中
	int checkSysUserInfo(String openId);
	//保存openid到车辆表
	void saveOpenIdToCar(String openId);
	//保存openid到报备表
	void saveOpenIdToRecord(String openId);
	//保存微信获取的基本用户信息
	 void saveWeixinUserInfo(String openId, String nickName, String sex,String country, String province, String city, String subscribeTime, String subscribeStatus);
	 String getMobileByIdToMember(String openId);//(会员表)
	
	 
	 String getPersonMobileByOpenId(String openId);
	 String getPersonNameByOpenId(String openId);
	 String getPersonCphmByOpenId(String openId);
	 String getPersonSexByOpenId(String openId);
	 String getPersonCpsfByOpenId(String openId);
	 String getAddressByOpenId(String openId);
	 //添加用户车量信息
	void insertSysUserInfo (String openId, String userName, String mobilePhone, String cpsf, String cphm,String cllx,String clpp,String clxh,String cjh,String registTime,String insuranceTime);
	 //获取用户基本车辆
	 List<CarInfo> getAllCarList(String openId);
	 //获取车辆carID
	 int getCarIdByCphm(String cpsf,String cphm);
}

