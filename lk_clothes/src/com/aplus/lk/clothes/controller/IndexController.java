package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.service.AdminService;

@Controller
public class IndexController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping({"","/","/index"})
	public String index(HttpServletRequest request){
		 Admin admin = adminService.getLoginAdmin();
		 if(admin!=null){
			 request.getSession().setAttribute("cno", admin.getCno());
		 }
		return "page_main";
	}
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	
}
