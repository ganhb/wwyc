package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.pojo.snsuser.SNSUserInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.service.SMSService;
import com.dragon.service.impl.SMSServiceImpl;
import com.dragon.utils.AdvancedUtil;

/**
 * @Title 短信呼叫servlet类
 * @Description TODO
 * @author ganhb
 * @date 2016-7-4
 */

public class CallSMSServlet extends HttpServlet {
	private static final long serialVersionUID = -276554597306220839L;
	public static Logger logger = Logger.getLogger(CallSMSServlet.class);

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String openId = (String) session.getAttribute("openId");
		String message = request.getParameter("message");
		//test
		System.out.println("callsms openid "+openId);
		SMSService smsService = new SMSServiceImpl();
		if (null != openId) {
			smsService.saveCallSMS(openId, message);
		}else{
			String Msg="无效用户，请重新关注";
			session.setAttribute("Msg", Msg);
			logger.error("无效用户，请重新关注");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("wyyc.jsp");
		requestDispatcher.forward(request, response);
		
		out.flush();
		out.close();
	}
	
}
