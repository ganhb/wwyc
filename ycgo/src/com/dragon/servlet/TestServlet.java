package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.service.CommentService;
import com.dragon.service.impl.CommentServiceImpl;
import com.dragon.utils.MessageUtil;

/**
 * @Title TestServlet.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-13
 */

public class TestServlet extends HttpServlet {

	public void doService(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		CommentService cService = new CommentServiceImpl();
		//获取请求方openId
		Map<String, String> requestMap = MessageUtil.parseXml(request);
		// 发送方帐号,用户的openId(颇为重要)
		String openId = requestMap.get("FromUserName");
		out.println("评论请求方的openID="+openId);
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CommentTime = df.format(new Date());
		
		String CommentOpenId="213123";
		String stars = "3";
		cService.addComment(openId, CommentOpenId, CommentTime, stars);
		
		out.flush();
		out.close();

	}


}
