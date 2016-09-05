package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.service.UserService;
import com.dragon.service.impl.UserServiceImpl;

import net.sf.json.JSONObject;

/**
 * @Title RegisterServlet.java
 * @Description 注册处理
 * @author ganhb
 * @date 2016-7-5
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = -8360789370979307184L;
	public static Logger logger = Logger.getLogger(RegisterServlet.class);

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String openId = (String)session.getAttribute("openId");
		
		logger.info("更新会员信息时openid ： "+openId);
		UserService userService = new UserServiceImpl();
		
		String updateName = request.getParameter("updateName");
		String updateMobile = request.getParameter("updateMobile");
		String updateSex = request.getParameter("updateSex");
		String updateAddress = request.getParameter("updateAddress");
		session.setAttribute("userName", updateName);
		
		JSONObject json = new JSONObject();
		json.put("openId", openId);
		json.put("userName", updateName);
		json.put("mobilePhone", updateMobile);
		json.put("updateSex", updateSex );
		json.put("updateAddress", updateAddress);
		logger.info("打印出来看看 json "+json);
		out.println(    "{\"openId\":\"" 		 	 + openId 			+ "\","
					+ 	"\"userName\":\"" 			 + updateName	    + "\","
					+ 	"\"mobilePhone\":\"" 		 + updateMobile	    + "\","
					+ 	"\"updateSex\":\"" 		 	 + updateSex	   	+ "\"}"
					);  
		if (null != updateName && null != updateMobile ) {
			userService.updateMemberUserInfo(openId, updateName, updateMobile, updateSex,updateAddress);
		}else {
			logger.error("保存会员基本信息时候，获取到空值了");
		}
		
		request.getRequestDispatcher("userCenter.jsp").forward(request,response);
		out.flush();
		out.close();
	}
}
