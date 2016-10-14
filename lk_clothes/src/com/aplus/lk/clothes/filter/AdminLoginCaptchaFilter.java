package com.aplus.lk.clothes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component(value="adminLoginCaptchaFilter")
public class AdminLoginCaptchaFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String codeString=request.getSession().getAttribute("validCode").toString();
		String code = request.getParameter("validCode");
		if(codeString.equalsIgnoreCase(code)){
			filterChain.doFilter(request, response);
		}else{
			response.sendRedirect(request.getContextPath() + "/toLogin" + "?error=captcha");
		}
	}
	
	public void destroy() {}

}