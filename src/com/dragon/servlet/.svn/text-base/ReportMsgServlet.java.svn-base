package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.service.ParkReportService;
import com.dragon.service.impl.ParkReportImpl;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.TemplateUtil;

import snippet.TimeDiff;

public class ReportMsgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String openId = (String)session.getAttribute("openId");
		
		ParkReportService prService = new ParkReportImpl();
		String cphm = request.getParameter("cphm");
		String userPhoneNumber = prService.getPhoneNumberByCphm(cphm);
		String reportMsg = prService.getMsgByCphm(cphm);
		
		if (null != reportMsg) {
			//判断报备消息是否在3个小时之内 
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = sdf.format(date);
			String reportTimeStamp = prService.getTimeByCphm(cphm);
			String reportTimeDate = CommonUtil.changeTimeStamp(reportTimeStamp);
			if (null == userPhoneNumber) {
				userPhoneNumber = "车主并未留下号码，客服人员将及时登记并联系该车主";
			}
			if (TimeDiff.timeDiff(nowTime, reportTimeDate) < 3) {
				session.setAttribute("reportMsg", reportMsg);
				TemplateUtil.compTempMsg(openId, reportMsg, reportTimeDate, cphm, userPhoneNumber);

			}
		}
		
		//这里需要页面跳转吗？
		out.flush();
		out.close();
	}

}
