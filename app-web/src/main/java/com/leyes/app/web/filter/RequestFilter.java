package com.leyes.app.web.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.leyes.app.util.DateUtil;
import com.leyes.app.util.UuidUtil;

public class RequestFilter implements Filter {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		/*
		 * HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		 * logger.info("uri:"+httpServletRequest.getRequestURI());
		 * logger.info("token:"+ httpServletRequest.getHeader("token"));
		 * Enumeration<String> paramNames = request.getParameterNames(); while
		 * (paramNames.hasMoreElements()) { String paramName = (String)
		 * paramNames.nextElement(); String[] paramValues =
		 * request.getParameterValues(paramName); if (paramValues.length == 1) {
		 * String paramValue = paramValues[0]; if (paramValue.length() != 0) {
		 * logger.info("param:"+paramName+"-"+paramValue); } } }
		 */
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
public static void main(String[] args) {
	for (int i = 0; i < 10; i++) {
		System.out.println(UuidUtil.getUUIDString());
	}
	 
	
}
}
