package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.common.ConstantManager;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.service.CarService;
import com.dragon.service.UserService;
import com.dragon.service.impl.CarServiceImpl;
import com.dragon.service.impl.UserServiceImpl;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;

public class UserCardServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(UserCardServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		logger.info("我的移车名片的授权码是：" + code);
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (null != code) {
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(ConstantManager.APPID,ConstantManager.SECRET, code);
			String accessToken = CommonUtil.getToken(ConstantManager.APPID,ConstantManager.SECRET).getAccessToken();
			String openId = weixinOauth2Token.getOpenId();
			session.setAttribute("openId", openId);
			
			UserService userService = new UserServiceImpl();
			String userCphm = userService.getPersonCphmByOpenId(openId);
			String userCpsf = userService.getPersonCpsfByOpenId(openId);
			session.setAttribute("userCphm", userCphm);
			session.setAttribute("userCpsf", userCpsf);
			
			/*CarService carService = new CarServiceImpl();
			String username = request.getParameter("userName");
			String mobile = request.getParameter("userMobile");
			String cpsf = request.getParameter("userCpsf");
			String cphm = request.getParameter("userCphm");
			String cpqh = request.getParameter("userCpqh");
			String method = request.getParameter("userMethod");
			String province = request.getParameter("userProvice");
			String city = request.getParameter("userCity");
			String county = request.getParameter("userCounty");
			if(null != username){
				carService.addStickerRecord(openId, username, mobile, cpsf, cphm, cpqh, method, province, city, county);
			}*/
			request.getRequestDispatcher("ycmp.jsp").forward(request,response);
			
		} else {
			logger.error("个人名片没有办法获取到code的值" + code);
		}
	
	}

}
