package com.dragon.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import sun.security.ec.ECDHKeyAgreement;

import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.service.UserService;
import com.dragon.service.impl.RegisterServiceImpl;
import com.dragon.service.impl.UserServiceImpl;
import com.dragon.utils.AdvancedUtil;

/**
 * @Title RegisterServlet.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-5
 */

public class RegisterServlet extends HttpServlet {
	public static Logger logger = Logger.getLogger(RegisterServlet.class);

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/*
		 * 获取请求方的openId，这个将作为唯一表示
		 * */
	/*	String code = request.getParameter("code");
		WeixinOauth2Token weixinOauth2Token=AdvancedUtil
		.getOauth2AccessToken("wxa03d062f5ae74167", "072ca0cabb79357c5c2421a25902ec4a", code);
		String accessToken=weixinOauth2Token.getAccessToken();
		String openId=weixinOauth2Token.getOpenId(); */
		String openId ="kkkkkkkkkkkdddddddd";
		System.out.println("121"+openId);
		String userName = request.getParameter("userName");
		String mobilePhone = request.getParameter("mobilePhone");
		String cpsf = request.getParameter("cpsf");
		String cphm = request.getParameter("cphm");
		System.out.println(userName+mobilePhone+cpsf+cphm);
		
		if (null != userName && null != mobilePhone &&  null != cphm) {
			UserServiceImpl.saveRegMsg(openId, userName, mobilePhone, cpsf, cphm);
			request.getRequestDispatcher("useCenter.jsp").forward(request, response);
		}else {
			System.out.println("还没有输入值");
		}
		
		
	}
}
