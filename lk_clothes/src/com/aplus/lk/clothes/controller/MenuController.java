package com.aplus.lk.clothes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("menu")
public class MenuController {
	
	// 洗衣管理
	@RequestMapping("clothes")
	public String clothes(){
		return "menu_clothes";
	}
	
	//乐E管家
	@RequestMapping("manager")
	public String manager(){
		return "menu_manager";
	}
	// 管理员
	@RequestMapping("admin")
	public String admin(){
		return "menu_admin";
	}
	//社区管理
	@RequestMapping("community")
	public String community(){
		return "menu_community";
	}
	//帮助反馈
	@RequestMapping("helpFeedBack")
	public String helpFeedBack(){
		return "menu_helpFeedBack";
	}
	//消息
	@RequestMapping("message")
	public String message(){
		return "menu_message";
	}
}
