package com.dragon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/** 
 * @Title EncodingFilter.java
 * @Description TODO
 * @author ganhb 
 * @date 2016-7-5
 */

public class EncodingFilter implements Filter {
	
	private FilterConfig config;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = config.getInitParameter("encoding");
		if (null == encoding) {
			encoding = "utf-8";
		}
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/plain;Utf-8");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
