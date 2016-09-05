package com.dragon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.dragon.service.ParkReportService;
import com.dragon.service.SMSService;
import com.dragon.service.impl.ParkReportImpl;
import com.dragon.service.impl.SMSServiceImpl;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.ResourceUtils;
import com.dragon.utils.TemplateUtil;

import net.sf.json.JSONObject;
import snippet.TimeDiff;

/**
 * @Title 停车报备控制中心
 * @Description 
 * @author ganhb
 * @date 2016-7-11
 */

public class ParkReportServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6412995354531618369L;
	private static Logger logger = Logger.getLogger(ParkReportServlet.class);
	
	//临时设置只显示一次模板消息
	//private static int flag = 0;

	//private ThreadLocal<Integer> userMap = new ThreadLocal<Integer>();
	
	private Map<String,Boolean> userFlag = new HashMap<String,Boolean>();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	 
	@SuppressWarnings({ "unused" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		ParkReportService prService = new ParkReportImpl();

// 		获取用户的openid
		HttpSession session = request.getSession();
		
//		String openId = "ovrT2wM-prYnJWxoxMEb5OpOLkz4";
		String openId = (String) session.getAttribute("openId");
		logger.info("用户的openId=" + openId);
		// 获取用户车牌号码
		String cphm = request.getParameter("cphm");
		String SMSMsg = request.getParameter("message");
		if (null == cphm) {
			logger.info("cphm为空");
		}
		String reportMsg = prService.getMsgByCphm(cphm);
		//呼叫方
		String mobileMyself = prService.getMobileByself(openId);
		//session.setAttribute("callMobile",mobileMyself);
		//被呼叫方,模板消息的电话，这里我只让它默认走值选择一个号码()
		String userPhoneNumber = prService.getPhoneNumberByCphm(cphm);
		//session.setAttribute("beCalledMobile", userPhoneNumber);
		//获取备用号码
		String userPhoneNumberSecond = prService.getPhoneNumberSecondByCphm(cphm);
		session.setAttribute("userPhoneNumberSecond", userPhoneNumberSecond);
		if (null == userPhoneNumber){
			userPhoneNumber ="";
		}
		
		session.setAttribute("userPhoneNumber", userPhoneNumber);
		logger.info("userPhoneNumber" + userPhoneNumber);

		SMSService smsService = new SMSServiceImpl();
		String resultMsg = "";
		Double reportTime = 0.0;
		
		//获取参数
		Resource res = ResourceUtils.getResource("classpath:config/config.properties");
		Properties pro = PropertiesLoaderUtils.loadProperties(res); 
		Enumeration enumname = pro.propertyNames(); 
		
		while(enumname.hasMoreElements()){
			String name = (String) enumname.nextElement();
			String value = pro.getProperty(name);
			
			if("reportTime".equals(name)){
				reportTime = Double.valueOf(value);
			}
		}
		
		if (null != reportMsg) {
			// 判断时间差
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = sdf.format(date);
			String reportTimeStamp = prService.getTimeByCphm(cphm);
			String reportTimeDate = CommonUtil.changeTimeStamp(reportTimeStamp);
			logger.info("当前的报备时间是 : "+reportTimeDate);
			if (null == userPhoneNumber) {
				userPhoneNumber = "车主并未留下号码，后续开发中我们将继续完善个人信息的补全";
			}
			if (TimeDiff.timeDiff(nowTime, reportTimeDate) < reportTime) {
				// 如果有报备消息，将该消息发送给该用户
				// 重新赋值报备消息
				resultMsg = reportMsg;
				session.setAttribute("resultMsg", resultMsg);
				
				if(null==userFlag.get(openId)||!userFlag.get(openId)){
					TemplateUtil.compTempMsg(openId, resultMsg, reportTimeDate, "闽"+cphm, userPhoneNumber);
					userFlag.put(openId, Boolean.TRUE);
				}
				
			}
		}
		//存储呼叫记录
		smsService.saveCallSMS(openId, SMSMsg, cphm, "闽", "1");
		
		JSONObject json = new JSONObject();
		json.put("cphm", cphm);
		json.put("userPhoneNumber", userPhoneNumber);
		json.put("userPhoneNumberSecond", userPhoneNumberSecond);
		json.put("resultMsg", resultMsg );
	
		out.println(    "{\"cphm\":\"" 		 	 + cphm 			+ "\","
					+ 	"\"userPhoneNumber\":\"" + userPhoneNumber  + "\","
					+ 	"\"userPhoneNumberSecond\":\"" + userPhoneNumberSecond  + "\","
				    + 	"\"resultMsg\":\""       + resultMsg		+ "\"}");  
	}

}
