package com.dragon.service;

/** 
 * @Title PositionService.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-19
 */

public interface PositionService {
	//保存地址信息（停车报备表）
	void SaveUserPosition(String longitude,String latitude,String precision,String openId);
	//存储地址信息（呼叫记录表）
	void SaveCallPosition(String longitude,String latitude,String precision,String openId);
}
