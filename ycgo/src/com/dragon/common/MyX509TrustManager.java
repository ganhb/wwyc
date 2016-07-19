package com.dragon.common;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/** 
 * 
 *  
 * @author ganhb 
 * @date 2016-5-26
 */

public class MyX509TrustManager implements X509TrustManager {

	@Override
	// 检查客户端证书
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		
	}

	@Override
	// 检查服务器证书
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		//不做处理信任任何证书
	}

	@Override
	// 返回受信任的X509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
