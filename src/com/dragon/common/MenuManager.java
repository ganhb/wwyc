package com.dragon.common;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;


import com.dragon.pojo.menu.Button;
import com.dragon.pojo.menu.ClickButton;
import com.dragon.pojo.menu.ComplexButton;
import com.dragon.pojo.menu.Menu;
import com.dragon.pojo.menu.ViewButton;
import com.dragon.pojo.token.Token;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.MenuUtil;

/**
 * 菜单管理器
 * 
 */
public class MenuManager {
	private static Logger logger = Logger.getLogger(MenuManager.class);

	public static Menu getMenu() {

		ViewButton indexButton = new ViewButton();
		indexButton.setName("首页");
		indexButton.setType("view");
		indexButton.setUrl("http://1524905ns8.imwork.net/ycgo/home.jsp");

		ClickButton tingcheButton = new ClickButton();
		tingcheButton.setName("停车报备");
		tingcheButton.setType("click");
		tingcheButton.setKey("stop");
		
		ClickButton caozuoButton = new ClickButton();
		caozuoButton.setName("操作指南");
		caozuoButton.setType("click");
		caozuoButton.setKey("operate");
		
		ViewButton yicheButton = new ViewButton();
		yicheButton.setName("我要移车");
		yicheButton.setType("view");
		yicheButton.setUrl(	 "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa03d062f5ae74167&"
				+		   	 "redirect_uri=http%3A%2F%2F1524905ns8.imwork.net%2Fycgo%2FcallSMS.do" 
				+			 "&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");

		ComplexButton complexButton = new ComplexButton();
		complexButton.setName("我要移车");
		complexButton.setSub_button(new Button[] { yicheButton, tingcheButton });

		ViewButton mingpianButton = new ViewButton();
		mingpianButton.setName("申请移车名片");
		mingpianButton.setType("view");
		mingpianButton.setUrl(	"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa03d062f5ae74167&" 
				+				"redirect_uri=http%3A%2F%2F1524905ns8.imwork.net%2Fycgo%2FuserCard.do"
				+				"&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1#wechat_redirect");

		ViewButton gerenButton = new ViewButton();
		gerenButton.setName("个人中心");
		gerenButton.setType("view");
		gerenButton.setUrl(	"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa03d062f5ae74167&" 
				+			"redirect_uri=http%3A%2F%2F1524905ns8.imwork.net%2Fycgo%2FoauthServlet"
				+			"&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect");
		System.out.println(gerenButton .getUrl());

		ComplexButton complexButton2 = new ComplexButton();
		complexButton2.setName("我是车主");
		complexButton2.setSub_button(new Button[] {mingpianButton, gerenButton });

		Menu menu = new Menu();
		menu.setButton(new Button[] { indexButton, complexButton,complexButton2 });
		String jsonString=JSONObject.fromObject(menu).toString();
		return menu;
	}

	public static void main(String[] args) {
		Token token = CommonUtil.getToken(ConstantManager.APPID, ConstantManager.SECRET);
		ViewButton gerenButton = new ViewButton();
		System.out.println(gerenButton .getUrl());
		if (null != token) {
			boolean result = MenuUtil.createMenu(getMenu(),token.getAccessToken());
			if (result)
				logger.info("菜单创建成功！");
			else
				logger.info("菜单创建失败！");
		}
	}
}
