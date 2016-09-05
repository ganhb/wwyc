package com.dragon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.dragon.pojo.MapPosition;
import com.dragon.pojo.message.response.Article;
import com.dragon.pojo.message.response.Music;
import com.dragon.pojo.snsuser.SNSUserInfo;
import com.dragon.pojo.token.WeixinOauth2Token;
import com.dragon.pojo.weixin.WeixinGroup;
import com.dragon.pojo.weixin.WeixinMedia;
import com.dragon.pojo.weixin.WeixinQRCode;
import com.dragon.pojo.weixin.WeixinUserInfo;
import com.dragon.pojo.weixin.WeixinUserList;

/**
 * 高级接口工具类
 * 
 */
public class AdvancedUtil {
	private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

	/**
	 * 组装文本客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		log.info("正确返回地理解析信息：" + jsonMsg);
		return String.format(jsonMsg, openId, content);
	}

	/**
	 * 组装图片客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @param thumbMediaId
	 *            视频消息缩略图的媒体id
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, String mediaId,
			String thumbMediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId, thumbMediaId);
	}

	/**
	 * 组装音乐客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param music
	 *            音乐对象
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music)
				.toString());
		// 将jsonMsg中的thumbmediaid替换为thumb_media_id
		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
		return jsonMsg;
	}

	/**
	 * 组装图文客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param articleList
	 *            图文消息列表
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId,
			List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(
				jsonMsg,
				openId,
				JSONArray.fromObject(articleList).toString()
						.replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}

	/**
	 * 发送客服消息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
		log.info("消息内容：{}", jsonMsg);
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("客服消息发送成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("客服消息发送失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}

		return result;
	}

	/**
	 * 获取地理街道位置
	 * 
	 * @param longitude
	 * @param latitude
	 * @return mapPosition
	 */
	public static MapPosition getPosition(String longitude, String latitude) {
		MapPosition mapPosition = new MapPosition();
		String requestUrl = "https://apis.map.qq.com/ws/geocoder/v1/?location=LATITUDE,LONGITUDE&key=TVSBZ-2OU33-LTF3M-3S6OJ-JNUOZ-I5FXG&get_poi=0";
		requestUrl = requestUrl.replace("LONGITUDE", longitude);
		requestUrl = requestUrl.replace("LATITUDE", latitude);
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {// /jsonObject.getJSONArray(),List.class
				mapPosition.setStatus(jsonObject.getInt("status"));
				mapPosition.setMessage(jsonObject.getString("message"));
				mapPosition.setResult(jsonObject.getJSONObject("result"));
				mapPosition.setResult(jsonObject.getJSONObject("result").get(
						"address"));
				log.info("正确返回地理解析信息：" + jsonObject);
				// System.out.println(result.setAddress(jsonObject.getString("address")));
			} catch (Exception e) {
				log.error("获取失败，失败的jsonObejct值为：null");
			}
		}
		return mapPosition;
	}
	
	public static void main (String[] args){
		
		MapPosition mapPosition = new MapPosition();
		MapPosition str = getPosition(String.valueOf(118.117493),
				String.valueOf(24.522938));
		System.out.println(str.getResult());
	}
	
	/**
	 * 获取网页授权凭证（请求方的openid）
	 * 
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
				log.info("access_token的json格式数据包：" + jsonObject);
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 刷新网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,
			String refreshToken) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		log.info("刷新后的access_token:" + jsonObject);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("刷新网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				snsUserInfo.setCountry(jsonObject.getString("country"));
				snsUserInfo.setProvince(jsonObject.getString("province"));
				snsUserInfo.setCity(jsonObject.getString("city"));
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				snsUserInfo.setPrivilegeList(JSONArray.toList(
						jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}

	/**
	 * 创建临时带参二维码
	 * 
	 * @param accessToken
	 * @param expireSeconds
	 * @param sceneId
	 * @return WeixinQRCode
	 */
	public static WeixinQRCode createTemporaryQRCode(String accessToken,
			int expireSeconds, int sceneId) {
		WeixinQRCode weixinQRCode = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));

		if (null != jsonObject) {
			try {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject
						.getInt("expire_seconds"));
				log.info("创建临时带参二维码成功 ticket:{} expire_seconds:{}",
						weixinQRCode.getTicket(),
						weixinQRCode.getExpireSeconds());
			} catch (Exception e) {
				weixinQRCode = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建临时带参二维码失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return weixinQRCode;
	}

	

	/**
	 * 创建永久带参二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return ticket
	 */
	public static String createPermanentQRCode(String accessToken, int sceneId) {
		String ticket = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		requestUrl = requestUrl.replace("TOKEN", accessToken);

		String jsonMsg = "{\"action_name\":\"%d\",\"action_info\":{\"scene\":{\"%d\"}}}";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				String seconds = jsonObject.getString("expire_seconds");
				String url = jsonObject.getString("url");
				// log.info(String.format("创建永久二维码成功，ticket是=>", ticket);
				log.info("创建永久二维码成功，ticket是=>" + ticket);
				log.info("创建永久二维码成功，有效时间是=>" + seconds);
				log.info("创建永久二维码成功，图片解析的url是=>" + url);
			} catch (Exception e) {
				e.printStackTrace();
				int errcode = jsonObject.getInt("errcode");
				String errMsg = jsonObject.getString("errmsg");
				log.error(String.format("创建永久二维码失败:errcode:%s,errMsg:%s",
						errcode, errMsg));

			}
		}

		return ticket;

	}

	/**
	 * 根据ticket换取二维码
	 * 
	 * @param ticket
	 *            二维码ticket
	 * @param savePath
	 *            保存路径
	 */
	public static String getQRCode(String ticket, String savePath) {
		String filePath = null;
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET",
				CommonUtil.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + ticket + ".jpg";
			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			log.info("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			log.error("根据ticket换取二维码失败：{}", e);
		}
		return filePath;
	}

	/**
	 * 获取用户信息(单个)
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return WeixinUserInfo
	 */
	public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
		WeixinUserInfo weixinUserInfo = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 1是男性，2是女性，0是未知
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				weixinUserInfo
						.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
							errorMsg);
				}
			}
		}
		return weixinUserInfo;
	}

	/**
	 * 获取用户信息(为了迎合多个openid)
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return WeixinUserInfo
	 */
	public static WeixinUserInfo getUserInfoByOpenId(String accessToken) {
		WeixinUserInfo weixinUserInfo = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		
		WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken,
		"");
		List<String> userList = weixinUserList.getOpenIdList();
		for (int i = 0; i < userList.size(); i++) {
			String openId = userList.get(i);
			System.out.println(openId);
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				weixinUserInfo.setSubscribeTime(jsonObject
						.getString("subscribe_time"));
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 1是男性，2是女性，0是未知
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				weixinUserInfo.setCity(jsonObject.getString("city"));
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				weixinUserInfo
						.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
							errorMsg);
				}
			}
			System.out.println("weixinUserInfo=="+weixinUserInfo.getNickname());
		}
		return weixinUserInfo;
	}
		return weixinUserInfo;

}
	
	
	
	/**
	 * 获取关注者列表
	 * 
	 * @param accessToken
	 * @param nextOpenId
	 * @return WeixinUserList
	 */
	public static WeixinUserList getUserList(String accessToken,
			String nextOpenId) {
		WeixinUserList weixinUserList = null;

		if (null == nextOpenId)
			nextOpenId = "";

		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"NEXT_OPENID", nextOpenId);
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				weixinUserList = new WeixinUserList();
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
			} catch (JSONException e) {
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取关注者列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinUserList;
	}

	/**
	 * 查询分组
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<WeixinGroup> getGroups(String accessToken) {
		List<WeixinGroup> weixinGroupList = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 查询分组
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinGroupList = JSONArray.toList(
						jsonObject.getJSONArray("groups"), WeixinGroup.class);
			} catch (JSONException e) {
				weixinGroupList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("查询分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinGroupList;
	}

	/**
	 * 创建分组
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param groupName
	 *            分组名称
	 * @return
	 */
	public static WeixinGroup createGroup(String accessToken, String groupName) {
		WeixinGroup weixinGroup = null;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"group\" : {\"name\" : \"%s\"}}";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupName));

		if (null != jsonObject) {
			try {
				weixinGroup = new WeixinGroup();
				weixinGroup.setId(jsonObject.getJSONObject("group")
						.getInt("id"));
				weixinGroup.setName(jsonObject.getJSONObject("group")
						.getString("name"));
			} catch (JSONException e) {
				weixinGroup = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("创建分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinGroup;
	}

	/**
	 * 修改分组名
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param groupId
	 *            分组id
	 * @param groupName
	 *            修改后的分组名
	 * @return true | false
	 */
	public static boolean updateGroup(String accessToken, int groupId,
			String groupName) {
		boolean result = false;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("修改分组名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("修改分组名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 移动用户分组
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @param groupId
	 *            分组id
	 * @return true | false
	 */
	public static boolean updateMemberGroup(String accessToken, String openId,
			int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
		// 移动用户分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, openId, groupId));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				log.info("移动用户分组成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				log.error("移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 上传媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param type
	 *            媒体文件类型（image、voice、video和thumb）
	 * @param mediaFileUrl
	 *            媒体文件的url
	 */
	public static WeixinMedia uploadMedia(String accessToken, String type,
			String mediaFileUrl) {
		WeixinMedia weixinMedia = null;
		// 拼装请求地址
		String uploadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken)
				.replace("TYPE", type);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
					.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
					.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");
			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = CommonUtil.getFileExt(contentType);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream
					.write(String
							.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",fileExt).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n",
					contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(
					meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			weixinMedia = new WeixinMedia();
			weixinMedia.setType(jsonObject.getString("type"));
			// type等于thumb时的返回结果和其它类型不一样
			if ("thumb".equals(type))
				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			else
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
			weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
		} catch (Exception e) {
			weixinMedia = null;
			log.error("上传媒体文件失败：{}", e);
		}
		return weixinMedia;
	}

	/**
	 * 下载媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param mediaId
	 *            媒体文件标识
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * @return
	 */
	public static String getMedia(String accessToken, String mediaId,
			String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"MEDIA_ID", mediaId);
		System.out.println(requestUrl);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = CommonUtil.getFileExt(conn
					.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;

			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			log.info("下载媒体文件成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			log.error("下载媒体文件失败：{}", e);
		}
		return filePath;
	}

}
