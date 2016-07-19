package com.dragon.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.pojo.snsuser.SNSUserInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.utils.AdvancedUtil;

/** 
 * @Title OauthServlet.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-6-29
 */

public class OauthServlet extends HttpServlet {

Logger logger=Logger.getLogger(OauthServlet.class);
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code=request.getParameter("code");
		logger.info("授权码是："+code);
		if (null!=code) {
			WeixinOauth2Token weixinOauth2Token=AdvancedUtil
				.getOauth2AccessToken("wxa03d062f5ae74167", "072ca0cabb79357c5c2421a25902ec4a", code);
			String accessToken=weixinOauth2Token.getAccessToken();
			String openId=weixinOauth2Token.getOpenId();
			logger.info("网页授权】用户的openID："+openId);
		
			
			//获取用户信息
			SNSUserInfo userInfo=AdvancedUtil.getSNSUserInfo(accessToken, openId);
			System.out.println("昵称："+userInfo.getNickname());
			//把openid存储在session里面
			HttpSession session =request.getSession();
			session.setAttribute("openId",WeixinOauth2Token.getOpenId());
			session.setAttribute("nickname",userInfo.getNickname());
			session.setAttribute("sex",userInfo.getSex());
			session.setAttribute("country", userInfo.getCountry());
			session.setAttribute("city", userInfo.getCity());
			session.setAttribute("province", userInfo.getCountry());
			
			System.out.println("useInfo 对象获取到的 ooooopenidd=="+userInfo.getOpenId());
			System.out.println("session 里面获取到的 nnnnnickname=="+session.getAttribute("nickname"));
			
			HttpSession ss = request.getSession();
			String city = (String) ss.getAttribute("city");
			System.out.println("新创建session 获取到的ciiiiity ==== " +city );
			
			//保存用户消息
			request.getRequestDispatcher("userCenter.jsp").forward(request, response);
		}else{
			logger.error("没有办法获取到code的值"+code);
		}
	}
}
