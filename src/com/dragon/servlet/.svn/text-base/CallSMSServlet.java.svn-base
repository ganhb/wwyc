package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.common.ConstantManager;
import com.dragon.pojo.snsuser.SNSUserInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.service.SMSService;
import com.dragon.service.impl.SMSServiceImpl;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;

/**
 * @Title 短信呼叫servlet类
 * @Description 短信
 * @author ganhb
 * @date 2016-7-4
 */

public class CallSMSServlet extends HttpServlet {
	private static final long serialVersionUID = -276554597306220839L;
	public static Logger logger = Logger.getLogger(CallSMSServlet.class);

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String code = request.getParameter("code");
		logger.info("该请求方的在我要移车时候的code是 ： " + code);
		
		WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(ConstantManager.APPID,ConstantManager.SECRET, code);
		String accessToken = CommonUtil.getToken(ConstantManager.APPID, ConstantManager.SECRET).getAccessToken();
		String openId = weixinOauth2Token.getOpenId();
		logger.info("我要移车时候用户的openid ： " + openId);

		HttpSession session = request.getSession();
		session.setAttribute("openId", openId);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("wyyc.jsp");
		requestDispatcher.forward(request, response);

		out.flush();
		out.close();
	}

}
