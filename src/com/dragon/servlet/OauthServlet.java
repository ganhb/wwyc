package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.common.ConstantManager;
import com.dragon.pojo.car.CarInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.pojo.weixin.WeixinUserInfo;
import com.dragon.service.UserService;
import com.dragon.service.impl.UserServiceImpl;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.GsonUtil;

/**
 * @Title OauthServlet.java
 * @Description 网页授权控制中心，自动存储用户基本信息
 * @author ganhb
 * @date 2016-6-29
 */

public class OauthServlet extends HttpServlet {

	private static final long serialVersionUID = 2438198195402922940L;
    private static Logger logger = Logger.getLogger(OauthServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String code = request.getParameter("code");
		logger.info("个人中心的授权码是：" + code);
		if (null != code) {
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(ConstantManager.APPID,ConstantManager.SECRET, code);
			String accessToken = CommonUtil.getToken(ConstantManager.APPID,ConstantManager.SECRET).getAccessToken();
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			WeixinUserInfo weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, openId);
			// session存储
			HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(-1);
			session.setAttribute("openId", WeixinOauth2Token.getOpenId());
			logger.info("用户发出请求的程序开始openID是"+openId);
			session.setAttribute("nickname", weixinUserInfo.getNickname());
			session.setAttribute("headImgUrl", weixinUserInfo.getHeadImgUrl());
			session.setAttribute("sex", weixinUserInfo.getSex());
			session.setAttribute("city", weixinUserInfo.getCity());
			session.setAttribute("province", weixinUserInfo.getProvince());

			String nickName = weixinUserInfo.getNickname();
			String sex = String.valueOf(weixinUserInfo.getSex());
			String city = weixinUserInfo.getCity();
			String subscribeTime = weixinUserInfo.getSubscribeTime();
			String subscribeStatus = String.valueOf(weixinUserInfo.getSubscribe());
			String province = weixinUserInfo.getProvince();
			String country = weixinUserInfo.getCountry();
			
			UserService userService = new UserServiceImpl();
			//根据openID来获取用户基本信息
			String personName = userService.getPersonNameByOpenId(openId); 
			String personMobile = userService.getPersonMobileByOpenId(openId);
			String personSex = userService.getPersonSexByOpenId(openId);
			String personCphm = userService.getPersonCphmByOpenId(openId);
			String personCpsf = userService.getPersonCpsfByOpenId(openId);
			String personAddress = userService.getAddressByOpenId(openId);
			
			session.setAttribute("personName", personName);
			session.setAttribute("personCphm", personCphm);
			session.setAttribute("personMobile", personMobile);
			session.setAttribute("personSex", personSex);
			session.setAttribute("personCpsf",personCpsf);
			session.setAttribute("personAddress", personAddress);
			
			
			//获取车辆信息
			List<CarInfo> list = userService.getAllCarList(openId);
			session.setAttribute("list", list);
			out.write(GsonUtil.getJsonString(list));
	//		logger.info("user car info is : "+list.get(0).getCarId());
			// 一关注就自动保存用户消息
			// 用户是否已经在数据库中
			int flag = userService.checkSysUserInfo(openId);
			if (flag == 0) {
				userService.saveWeixinUserInfo(openId, nickName, sex, country, province, city,subscribeTime, subscribeStatus);
				userService.saveOpenIdToCar(openId);
				
			} else {
				logger.info("用户" + weixinUserInfo.getNickname() + "已经存在");
			}
			
			request.getRequestDispatcher("userCenter.jsp").forward(request,response);
			out.flush();
			out.close();
			
		} else {
			logger.error("没有办法获取到code的值" + code);
		}
	}
}
