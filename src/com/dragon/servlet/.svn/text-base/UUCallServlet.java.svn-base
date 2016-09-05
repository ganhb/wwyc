package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.dragon.utils.HttpClientUtil;
import com.dragon.utils.HttpConnHandle;
import com.dragon.utils.MD5Util;
import com.dragon.utils.ResourceUtils;

/**
 * @author uucall 调用过程
 */
public class UUCallServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(UUCallServlet.class);
	
	private static final String MSG_CALL_URL = "http://msg.uucall.com/message/msg?";
	
	private static final String PHONE_CALL_URL = "http://wx.uucall.com/call/callback?";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		//主叫方号码
//		String callerPhoneNum = request.getParameter("callerPhoneNum");
		String callerPhoneNum = (String)session.getAttribute("mobileMyself");
		//被叫方号码
//		String calleePhoneNum = request.getParameter("calleePhoneNum");
		String calleePhoneNum = (String)session.getAttribute("userPhoneNumber");
		String message = request.getParameter("message");

		String returnStr = "";

		String secretKey = "";
		String number = "";

		String username = "";
		String password = "";

		try {
			Resource res = ResourceUtils.getResource("classpath:config/config.properties");
			Properties p = PropertiesLoaderUtils.loadProperties(res);

			Enumeration names = p.propertyNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				String value = p.getProperty(name);

				if ("secretKey".equals(name)) {
					secretKey = value;
				} else if ("number".equals(name)) {
					number = value;
				} else if ("username".equals(name)) {
					username = value;
				} else if ("password".equals(name)) {
					password = value;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String time = String.valueOf(System.currentTimeMillis() / 1000);
		String sign = MD5Util.MD5(secretKey + time);
		logger.info("呼叫时间戳是 : " + time);
		logger.info("sign验证码是 : " + sign);

		Map<String, Object> params = new HashMap<String, Object>();

		String phoneCallUrl = PHONE_CALL_URL + "number=" + number + "&time=" + time + "&caller=" + callerPhoneNum
				+ "&callee=" + calleePhoneNum + "&sign=" + sign;
		try {
			HttpConnHandle conn = new HttpConnHandle(phoneCallUrl);
			conn.send();
			String phoneCallReturnStr = conn.reveice();
			returnStr += "调用手机拨号接口地址：" + phoneCallUrl + "\n结果为：" + phoneCallReturnStr;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (message != null && message != "") {
			String msgCallUrl = MSG_CALL_URL + "username=" + username + "&password=" + password + "&code=86&number="
					+ calleePhoneNum + "&content=" + message;
			String msgCallReturnStr = HttpClientUtil.post(msgCallUrl, params);
			returnStr += "\n调用短信拨号接口地址：" + msgCallUrl + "\n结果为：" + msgCallReturnStr;
		}

		// 返回页面
		try {
			response.getWriter().print(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
