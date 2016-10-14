package com.aplus.lk.clothes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping("mian")
	public String main(){
		return "page_main";
	}
	
	@RequestMapping("index")
	public String index(){
		return "page_index";
	}
	
	@RequestMapping("header")
	public String header(){
		return "page_header";
	}
	
	@RequestMapping("menu")
	public String menu(){
		return "page_menu";
	}
	
	@RequestMapping("middle")
	public String middle(){
		return "page_middle";
	}
	
}
