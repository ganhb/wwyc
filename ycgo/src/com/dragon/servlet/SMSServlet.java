package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dragon.pojo.snsuser.SNSUserInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.test.UserInfoDao;
import com.dragon.utils.AdvancedUtil;

/**
 * @Title SMSServlet.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-1
 */

public class SMSServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SMSServlet.class);

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String code = request.getParameter("code");
		logger.info("[info]callsms的code=>" + code);

		String openIdString;
		String addressString;
		String mobilePhoneNumberString;

		if (null != code) {
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil
					.getOauth2AccessToken("wxa03d062f5ae74167",
							"072ca0cabb79357c5c2421a25902ec4a", code);
			// test
			logger.info(String
					.format("[info]access_token:%s,\nexpiresIn:%s,\nopen_id:%s,\nscope:%s\n",
							weixinOauth2Token.getAccessToken(),
							weixinOauth2Token.getExpiresIn(),
							weixinOauth2Token.getOpenId(),
							weixinOauth2Token.getScope()));

			String accessTokenString = weixinOauth2Token.getAccessToken();
			openIdString = weixinOauth2Token.getOpenId();

			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(
					accessTokenString, openIdString);
			logger.info("[info]snsUserInfo=>" + snsUserInfo.getOpenId());

			//test
			System.out.println("这里没哟执行");
			
			addressString = request.getParameter("address");
			mobilePhoneNumberString = request.getParameter("mobilephonenumber");
			request.setAttribute("openid", openIdString);
			request.setAttribute("address", addressString);
			request.setAttribute("mobilephonenumber", mobilePhoneNumberString);

		} else {
			openIdString = request.getParameter("openid");
			addressString = request.getParameter("address");
			mobilePhoneNumberString = request.getParameter("mobilephonenumber");
			request.setAttribute("openid", openIdString);
			request.setAttribute("address", addressString);
			request.setAttribute("mobilephonenumber", mobilePhoneNumberString);
			System.out.println("OPENID==="+openIdString);
			System.out.println("address==="+addressString);
			System.out.println("MOBILEPHONE====="+mobilePhoneNumberString);

		}
		// test
		//logger.info(String.format("[info]address:%s,\nmobilephonenumber:%s",addressString, mobilePhoneNumberString));
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher("test.jsp");
		requestDispatcher.forward(request, response);
		UserInfoDao.saveTestInfo(openIdString, addressString,
				mobilePhoneNumberString);
		out.flush();
		out.close();

	}

}
