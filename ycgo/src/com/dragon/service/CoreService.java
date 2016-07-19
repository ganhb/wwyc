package com.dragon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dragon.pojo.MapPosition;
import com.dragon.pojo.message.response.Article;
import com.dragon.pojo.message.response.NewsMessage;
import com.dragon.pojo.message.response.TextMessage;
import com.dragon.service.impl.ParkReportImpl;
import com.dragon.service.impl.PositionServiceImpl;
import com.dragon.service.impl.UserServiceImpl;
import com.dragon.utils.AdvancedUtil;
import com.dragon.utils.CommonUtil;
import com.dragon.utils.MessageUtil;

/**
 * @Title CoreService.java
 * @Description TODO
 * @author ganhb
 * @date 2016-6-29
 */

public class CoreService {
	private static Logger logger = Logger.getLogger(CoreService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号,用户的openId(颇为重要)
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String createTime = requestMap.get("CreateTime");
			
			//把创建消息的时间存放session
			HttpSession session = request.getSession();
			session.setAttribute("CreateTime", createTime);

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 事件推送
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textMessage
							.setContent("欢迎关注，旺仔想你了[鼓掌]，急人所急，为中国上亿车主解决移车难题。");
					respXml = MessageUtil.messageToXml(textMessage);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

				}
				// 地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// user_x
					String longitude = requestMap.get("Longitude");
					// user_y
					String latitude = requestMap.get("Latitude");
					// user_p
					String precision = requestMap.get("Precision");
					try {
						MapPosition mapPosition = AdvancedUtil.getPosition(
								longitude, latitude);
						System.out.println("getAddress = "+ mapPosition.getResult());
						textMessage.setContent("您当前所在的位置是在:"
								+ mapPosition.getResult());
						PositionService positionService = new PositionServiceImpl();
						positionService.SaveUserPosition(longitude, latitude, precision, fromUserName);
					} catch (Exception e) {
						logger.error("位置显示失败，失败原因：" + e);
					}
					respXml = MessageUtil.messageToXml(textMessage);
					System.out.println(String.format("您目前经度是:%s,维度：%s,精度:%s",
							longitude, latitude, precision));
				}
				// 菜单点击
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("index")) {
						Article article = new Article();
						article.setTitle("欢迎开启移车之旅");
						article.setDescription("移车狗终于等到你了\n急人所急，为中国上亿车主解决移车难题\n欢迎使用移车狗，旺~~");
						article.setPicUrl("http://pic1.win4000.com/wallpaper/a/52047e11812fe.jpg");
						article.setUrl("http://1524905ns8.imwork.net/ycgo/home.jsp");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage
								.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					}
					// 填写报备信息
					//这里应该要存放用户要填写的报备消息
					//设计一个新的servlet上面，在上面响应报备的功能
					else if (eventKey.equals("stop")) {
						String content = requestMap.get("Content");
						textMessage.setContent("车主设置停车报备后，被堵人员点击菜单中【我要移车】模块，即可在微信自动获取到车主留言。");
						respXml = MessageUtil.messageToXml(textMessage);
						if (null == fromUserName) {
							logger.error("系统忙，获取不到正确的openid，请稍后再试");
							
						}if (null == content) {
							textMessage.setContent("请输入消息内容");
							respXml = MessageUtil.messageToXml(textMessage);
						} 
						else {
							//用户存储内容
							ParkReportService prService = new ParkReportImpl();
							//存储用户报备消息，不用管车牌号，车牌号在注册的时候绑定
							prService.SaveParkReport(fromUserName, content);
							textMessage.setContent("你的报备消息，小狗已经存储");
							respXml = MessageUtil.messageToXml(textMessage);
							logger.info("用户:"+fromUserName+",报备消息已经存储好");
						}
					}//end of eventKey=stop
				}//end of button_CLICK
			}//end of  MessageUtil.REQ_MESSAGE_TYPE_TEXT

			// 消息对话
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				UserService userService = new UserServiceImpl();
				ParkReportService parkReport = new ParkReportImpl();

				String openId = textMessage.getToUserName();
				// 获取用户输入的内容
				System.out.println("您所如数的内容-"+content);
				if (content.equals("233")) {
					System.out.println("zhelideopenid==" + openId); 
					String resultMessage = parkReport.findReportById(openId)
							.getMessage();
					System.out.println(resultMessage);
					textMessage.setContent("您的报备内容为" + resultMessage);
					respXml = MessageUtil.messageToXml(textMessage);

				}
				if (content.equals("签到")) {
					boolean result = userService.isTodaySigned(fromUserName);
					if (!result) {
						boolean flag = userService.isWeekSigned(fromUserName,
								CommonUtil.getMondayOfThisWeek());
						if (flag) {
							userService.saveWeixinSign(fromUserName, 12);
							userService.updateUserPoints(fromUserName, 12);
							textMessage
									.setContent("签到成功，获得2个积分\n 本周连续签到7天，额外赠送10个积分");
							respXml = MessageUtil.messageToXml(textMessage);
						} else {
							userService.saveWeixinSign(fromUserName, 2);
							userService.updateUserPoints(fromUserName, 2);
							textMessage.setContent("签到成功，获得2个积分");
							respXml = MessageUtil.messageToXml(textMessage);
						}
					} else {
						textMessage.setContent("您今天已经签到过");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}

				if (content.equals("积分")) {
					int userpoint = userService.queryUserPoints(fromUserName);
					textMessage.setContent(String.format("您目前的积分为：%s",
							userpoint));
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// test
				if (content.equals("1")) {

					textMessage.setContent("I already 1");
					respXml = MessageUtil.messageToXml(textMessage);
				}
				if (content.equals("3")) {
					textMessage.setContent("I already 3");
					respXml = MessageUtil.messageToXml(textMessage);
				}

				else {
				}
			}
		} catch (Exception e) {
			logger.error("微信暂时无法提供服务"+e);
			e.printStackTrace();
		}
		return respXml;
	}
}
