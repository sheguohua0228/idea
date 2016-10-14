package com.aplus.lk.clothes.listener;

import java.util.Date;

import javax.annotation.Resource;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.service.AdminService;

@Component("adminSecurityListener")
public class AdminSecurityListener implements ApplicationListener {

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		// 登录成功：记录登录IP、清除登录失败次数
		if (applicationEvent instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authenticationSuccessEvent = (AuthenticationSuccessEvent) applicationEvent;
			Authentication authentication = (Authentication) authenticationSuccessEvent.getSource();
			String loginIp = ((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress();
			Admin admin = (Admin) authentication.getPrincipal();
			admin.setLoginIp(loginIp);
			admin.setLoginDate(new Date());
			adminService.updateLoginIpAndDate(admin);
		}
	}

}