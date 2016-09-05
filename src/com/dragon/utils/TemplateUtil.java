package com.dragon.utils;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dragon.common.ConstantManager;
import com.dragon.pojo.template.Template;
import com.dragon.pojo.template.TemplateParam;

/** 
 * @Title TemplateUtil.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-6-29
 */

public class TemplateUtil {
	private static Logger log = LoggerFactory.getLogger(TemplateUtil.class);

	public static boolean sendTemplateMessage(String access_token,Template template) {
		boolean result = false;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOEKN";
		requestUrl = requestUrl.replace("ACCESS_TOEKN", access_token);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",template.toJSON());
		log.info("模板消息的jsonobject的格式为" + jsonObject);
		if (null != jsonObject) {
			int errcode = jsonObject.getInt("errcode");
			String errMsg = jsonObject.getString("errmsg");
			if (0 == errcode) {
				result = true;
				log.info(String.format("发送模板消息成功：errcode=%s,errMsg=%s", errcode,errMsg));
			} else {
				log.info(String.format("发送模板消息失败：errcode=%s,errMsg=%s", errcode,errMsg));
			}
		}
		return result;
	}

	public static void compTempMsg(String openId,String reportMessage,String reportTime,String cphm,String userPhoneNumber) {
		String access_token = CommonUtil.getToken(ConstantManager.APPID,ConstantManager.SECRET).getAccessToken();
		
		//按照模板格式添加模板消息
		List<TemplateParam> templateParamsList = new ArrayList<TemplateParam>();
		templateParamsList.add(new TemplateParam("first"   ,"--临时停车报备--", "#000000"));
		templateParamsList.add(new TemplateParam("keyword1", cphm.toUpperCase() , "#333333"));
		templateParamsList.add(new TemplateParam("keyword2", userPhoneNumber, "#333333"));
		templateParamsList.add(new TemplateParam("keyword3", reportMessage	, "#333333"));
		templateParamsList.add(new TemplateParam("remark", "报备时间 : "+reportTime, "#000000"));
		
		Template template = new Template();
		template.setTemplateId("gJAzsApZEw7o7x66ew0F5m41E_x8KnOZihblxze0htY");
		template.setTouser(openId);
		template.setUrl("");
		template.setTopcolor("#EC6A12");
		template.setTemplateParamsList(templateParamsList);
		
		sendTemplateMessage(access_token,template);
	}
	
	
}
