package com.aplus.lk.clothes.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	public static String getRealPath(String path, HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath(path);
	}
	
}
