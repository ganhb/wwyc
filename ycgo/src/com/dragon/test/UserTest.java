package com.dragon.test;

import java.util.List;

import com.dragon.pojo.weixin.WeixinGroup;
import com.dragon.pojo.weixin.WeixinUserInfo;
import com.dragon.pojo.weixin.WeixinUserList;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;

/** 
 * @Title UserTest.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-8
 */

public class UserTest {
	
	
	public static void main (String[] args){
		String accessToken = CommonUtil.getToken("wxa03d062f5ae74167", "072ca0cabb79357c5c2421a25902ec4a").getAccessToken();
		WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken, "");
		System.out.println("=====================关注着列表==============================");
		System.out.println("总关注个书+"+weixinUserList.getCount());
		System.out.println("本次获取用户总数+"+weixinUserList.getTotal());
		System.out.println("openid 列表+"+weixinUserList.getOpenIdList());
		List<String> userList = weixinUserList.getOpenIdList();
		for (String ls : userList) {
			System.out.println(ls);
		}
		System.out.println("========");
		for (int i = 0; i < userList.size(); i++) {
			String ulist =userList.get(i);
			System.out.println("openid分别是"+ulist);
		}
		//for (int i = 0; i < weixinUserList.getOpenIdList().length; i++) {}
		System.out.println("next open id +"+weixinUserList.getNextOpenId());
		System.out.println("=======================关注人的基本信息============================");
		WeixinUserInfo weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, "ovrT2wP80_x0hAefKdhOuqwsYk8Q ");
		System.out.println(weixinUserInfo.getNickname());
		System.out.println(weixinUserInfo.getHeadImgUrl());
		System.out.println(weixinUserInfo.getCountry());
		System.out.println(weixinUserInfo.getCity());
		System.out.println(weixinUserInfo.getOpenId());
		System.out.println(weixinUserInfo.getProvince());
		System.out.println(weixinUserInfo.getSubscribe());
		System.out.println("subscribe time is "+weixinUserInfo.getSubscribeTime());
		System.out.println(weixinUserInfo.getSex());
		
		
		
		
		System.out.println("===================================================");
		
//		WeixinGroup group = AdvancedUtil.createGroup(accessToken, "第一批测试用户");
//		System.out.println(String.format("成功创建分组：%s  id:%d", group.getName(),group.getId()));
		
		System.out.println("===================================================");
		//获取分组列表
		List<WeixinGroup> grouList = AdvancedUtil.getGroups(accessToken);
		//遍历
		for (WeixinGroup groups : grouList) {
			System.out.println(String.format("用户的iD：%s,名称:%s,用户数:%s", groups.getId(),groups.getName(),groups.getCount()));
		}
	}
}
