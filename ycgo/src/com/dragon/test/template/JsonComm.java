package com.dragon.test.template;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @Title JsonComm.java
 * @Description TODO
 * @author ganhb
 * @date 2016-7-7
 */

public class JsonComm {
	public String toTempalteMsgText(String openId,String templateId){
		//getByOrderId(orderId);
		HttpServletResponse response;
		//查询订单
		List<TextInfo> textInfo = TextDao.getAllList();
		String first ="111";
		String remark="您的地址为"+((TextInfo) textInfo).getAddress()+",您的openid为"+((TextInfo) textInfo).getOpenId()+"您的手机号码为"+((TextInfo) textInfo).getMobilephonenumberString()
		+",您的id="+((TextInfo) textInfo).getId();
		
		//String jsonText="{"touser":"OPENID","template_id":"templateId","url":"","topcolor":"#FF0000","data":{"first": {"value":"firstData","color":"#173177"},"product": {"value":"productData","color":"#173177"},"price": {"value":"priceData","color":"#173177"},"time": {"value":"timeData","color":"#173177"},"remark": {"value":"remarkData","color":"#173177"}}}";
		
	//	jsonText= jsonText.replace("firstData", first).replace("templateId", templateId).replace("OPENID", order.getBuyer_openid()).replace("productData", order.getProduct_name()).replace("priceData",order.getOrder_total_price()/100f+"元").replace("timeData", order.getOrder_create_time()).replace("remarkData", remark);
		return null;
	}
}
