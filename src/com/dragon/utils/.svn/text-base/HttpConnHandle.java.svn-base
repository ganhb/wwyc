/**
 * 
 */
package com.dragon.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;



/**
 * httpconnection 帮助类
 * 
 * @author linxw
 * 
 */
public class HttpConnHandle {

	private final static Logger logger = Logger.getLogger(HttpConnHandle.class);

	private final static String MSG_CHARSET = "UTF-8";
	
	private HttpURLConnection httpConn;

	private String message;

	private BufferedReader input;

	/**
	 * @param url
	 * @throws IOException
	 */
	public HttpConnHandle(String url) {
		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(40000);
		} catch (IOException e) {
			message = "连接超时";
//			logger.info(url + "连接超时");
		} catch (Exception e) {
			message = "出现异常";
			e.printStackTrace();
		}
	}

	public void send() throws IOException {
		input = new BufferedReader(new InputStreamReader(
				httpConn.getInputStream(), MSG_CHARSET));
	}
	
	public void send(String content) throws IOException {
		BufferedOutputStream bufferOutput = new BufferedOutputStream(
				httpConn.getOutputStream());
		bufferOutput.write(content.getBytes(MSG_CHARSET));
		bufferOutput.flush();
		bufferOutput.close();
		input = new BufferedReader(new InputStreamReader(
				httpConn.getInputStream(), MSG_CHARSET));
	}

	public void sent(Object obj) throws IOException {
		httpConn.setUseCaches(false);
		httpConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
		httpConn.setRequestMethod("POST");
		
		ObjectOutputStream output = new ObjectOutputStream(httpConn.getOutputStream());
		output.writeObject(obj);
		output.flush();
		output.close();
		
		input = new BufferedReader(new InputStreamReader(
				httpConn.getInputStream(), MSG_CHARSET));
	}
	
	public String reveice() {
		StringBuffer receiveStr = new StringBuffer();
		try {
			while (true) {
				String returnMsg = input.readLine();
				if (returnMsg == null) {
					break;
				} else {
					receiveStr.append(returnMsg);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("\nurl地址处理后返回结果为:"+receiveStr.toString());
		return receiveStr.toString();
	}

	public void close(){
		if(input!=null){
			try {
				input.close();
			} catch (IOException e) {
//				logger.error("关闭输入流失败");
			}
		}
	}
	
	public HttpURLConnection getHttpConn() {
		return httpConn;
	}

	public String getMessage() {
		return message;
	}

}
