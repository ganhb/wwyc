package com.dragon.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dragon.entity.ParkReportInfo;
import com.dragon.pojo.message.response.TextMessage;
import com.dragon.pojo.template.Template;
import com.dragon.pojo.template.TemplateParam;
import com.dragon.service.impl.ParkReportImpl;
import com.dragon.servlet.CoreServlet;
import com.dragon.test.template.TextDao;

/** 
 * @Title TemplateUtil.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-6-29
 */

public class TemplateUtil {
	private static Logger log = LoggerFactory.getLogger(TemplateUtil.class);

	public static boolean sendTemplateMessage(String access_token,
			Template template) {
		boolean result = false;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOEKN";
		requestUrl = requestUrl.replace("ACCESS_TOEKN", access_token);
		System.out.println(requestUrl);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				template.toJSON());
		log.info("模板消息的jsonobject的格式为" + jsonObject);
		if (null != jsonObject) {
			int errcode = jsonObject.getInt("errcode");
			String errMsg = jsonObject.getString("errmsg");
			if (0 == errcode) {
				result = true;
				log.info(String.format("发送模板消息成功：errcode=%s,errMsg=%s", errcode,
						errMsg));
			} else {
				log.info(String.format("发送模板消息失败：errcode=%s,errMsg=%s", errcode,
						errMsg));
			}
		}
		return result;
	}

	// test
/*	public static void main(String[] args) {
		String access_token = CommonUtil.getToken("wxa03d062f5ae74167",
				"072ca0cabb79357c5c2421a25902ec4a").getAccessToken();
		List<TemplateParam> templateParamsList = new ArrayList<TemplateParam>();
		templateParamsList.add(new TemplateParam("first","【移车提醒】", "#EC6A12"));
		templateParamsList.add(new TemplateParam("keyword1", "2016-05-04 11:12:15", "#EC6A12"));
		templateParamsList.add(new TemplateParam("keyword2", "占用停车位", "#EC6A12"));
		templateParamsList.add(new TemplateParam("remark", "您的车牌为桂A12345的车占用了车位，为了您的爱车不影响他人出行，请您尽快移车！", "#EC6A12"));


		Template template = new Template();
		template.setTemplateId("pgYe4ro1CTDzDvheQgxPdJv8axjOHgT12VpKdXZsVkU");
		template.setTouser("ovrT2wM-prYnJWxoxMEb5OpOLkz4");
		template.setUrl("");
		template.setTopcolor("#EC6A12");
		template.setTemplateParamsList(templateParamsList);

		sendTemplateMessage(access_token, template);
	}
*/	

	
	/*public static void main(String[] args){
		compTempMsg("ovrT2wM-prYnJWxoxMEb5OpOLkz4", "hello man");
	}*/

	public static void compTempMsg(String openId,String reportMessage) {
		String access_token = CommonUtil.getToken("wxa03d062f5ae74167","072ca0cabb79357c5c2421a25902ec4a").getAccessToken();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = df.format(new Date());
		//按照模板格式添加模板消息
		List<TemplateParam> templateParamsList = new ArrayList<TemplateParam>();
		templateParamsList.add(new TemplateParam("first","临时停车报备消息", "#000000"));
		templateParamsList.add(new TemplateParam("keyword1", dateTime, "#EC6A12"));
		templateParamsList.add(new TemplateParam("keyword2", reportMessage, "#EC6A12"));
		templateParamsList.add(new TemplateParam("remark", "【移车狗温馨提醒】", "#000000"));
		
		Template template = new Template();
		template.setTemplateId("pgYe4ro1CTDzDvheQgxPdJv8axjOHgT12VpKdXZsVkU");
		template.setTouser(openId);
		
		template.setUrl("");
		template.setTopcolor("#EC6A12");
		template.setTemplateParamsList(templateParamsList);
		
		sendTemplateMessage(access_token,template);
	}
}
