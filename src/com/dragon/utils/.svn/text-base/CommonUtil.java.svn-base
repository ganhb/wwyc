package com.dragon.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dragon.common.MyX509TrustManager;
import com.dragon.pojo.token.Token;


/** 
 * 
 *  
 * @author ganhb 
 * @date 2016-5-26
 */

public class CommonUtil {
	private static Logger logger=LoggerFactory.getLogger(CommonUtil.class);
	
	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 转化时间格式 yyyy-MM-dd
	 */
	public static String getFormatTime(Date date,String formatStr){
		DateFormat dateFormat = new SimpleDateFormat(formatStr);
		return dateFormat.format(date);
	}

	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			logger.info("输入流读取返回的内容为 => "+buffer);
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error("连接超时：{}", ce);
		} catch (Exception e) {
			logger.error("https请求异常：{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return token
	 */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		logger.info("普通接口访问凭证的access_token=="+jsonObject);
		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;
				logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
		
		
	}
	
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	//获得本周一的时间
	public static String getMondayOfThisWeek() {
		DateFormat dateFormat = new SimpleDateFormat("\""+"yyyy-MM-dd"+" 00:00:00"+"\"");
		Calendar calendar = Calendar.getInstance();
		// 今天是星期几
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		// 对星期天的特殊处理
		if (dayOfWeek == 0)
			dayOfWeek = 7;
		// 假如今天是星期四，往前推3天就是星期一，（4-1）
		calendar.add(Calendar.DATE, -(dayOfWeek - 1));

		return dateFormat.format(calendar.getTime());
	}
	//获取当前时间
	public static void getTimeNow(){
		 	Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String nowTime = sdf.format(date);
	}
	
	//时间戳转化为日期格式
	public static String changeTimeStamp(String timeStamp){
		String finalyDate = timeStamp+"000";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		String normalDate = sdf.format(new Date(Long.parseLong(finalyDate)));
		return normalDate;
	}
	
	 //计算2个时间之间的时间差
	public static double timeDiff(String nowTime,String pastTime) {

		Date dateNowTemp = null; 
		Date datePastTemp = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try { 
			dateNowTemp = sdf.parse(nowTime); 
			datePastTemp = sdf.parse(pastTime); 
		} catch (ParseException pe){ 
		} 
		
		long longNowTemp = dateNowTemp.getTime(); 
		long longPasttemp = datePastTemp.getTime(); 
		double hours = (double)(longNowTemp-longPasttemp)/3600/1000; 
		return hours;
	} 
	//test
	public static void main (String args[]){
		String urlString="http://1524905ns8.imwork.net/ycgo/CarSign.jsp";
		System.out.println("编码后的url   =>   "+urlEncodeUTF8(urlString));
		System.out.println("这周一的时间为 =>"+getMondayOfThisWeek());
		System.out.println("timestamp=="+changeTimeStamp("1468995716"));
	}
}
