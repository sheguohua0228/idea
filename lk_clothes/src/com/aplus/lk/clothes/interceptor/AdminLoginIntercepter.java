package com.aplus.lk.clothes.interceptor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.service.AdminService;

public class AdminLoginIntercepter extends HandlerInterceptorAdapter {

	public static final String LOGIN_PAGE = "/toLogin";
	@Autowired
	private AdminService adminService;

	private static final Set<String> noMappingSet = new HashSet<String>();
	static {
		noMappingSet.add("/toLogin");
		noMappingSet.add("/admin/check");
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Iterator<String> it = noMappingSet.iterator();
		while (it.hasNext()) {
			String str = it.next().toString();
			if (request.getRequestURI().indexOf(str) > 0) {
				return true;
			}
		}

		Admin loginAdmin = adminService.getLoginAdmin();
		if (loginAdmin == null) {
			response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
			return false;
		}
		return true;
	}

}
