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

import com.dragon.service.ParkReportService;
import com.dragon.service.impl.ParkReportImpl;
import com.dragon.utils.TemplateUtil;

/** 
 * @Title 停车报备控制器
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-11
 */

public class ParkReportServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(ParkReportServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ParkReportService prService = new ParkReportImpl();
		
		//根据车牌判断车主是否有填写报备消息
		
			//获取用户的openid
		HttpSession session = request.getSession();
		String openId = (String) session.getAttribute("openId");
		
			//根据获取用户车牌号码 
		String cphm = prService.findCphmByOpenId(openId).getCphm();
		String reportMessage = prService.getParkReportByOpenId(openId, cphm);
		
		//TEST
		logger.info("parkReportServlet 中的session_openId为 "+openId);
		logger.info("车牌号码是："+cphm);
		logger.info("报备消息是："+reportMessage);
		
		
		//如果有报备消息，将该消息发送给该用户
		if (null != reportMessage) {
			//调用模板消息(这里的openId是发出请求的用户)
			TemplateUtil.compTempMsg(openId, reportMessage);
		}else {
			//如果没有直接跳转到我要移车界面
			RequestDispatcher rd = request.getRequestDispatcher("wyyc.jsp");
			rd.forward(request, response);
		}
		out.flush();
		out.close();
	}

}
