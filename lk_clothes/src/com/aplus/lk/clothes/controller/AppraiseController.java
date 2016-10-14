package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.service.IAppraiseSerivce;

@Controller
@RequestMapping("appraise")
public class AppraiseController {

	@Autowired
	private IAppraiseSerivce appraiseSerivce;
	
	@RequestMapping("query")
	public String query(Pager pager,String phoneNumber,String orderNumber,HttpServletRequest request){
		pager=appraiseSerivce.query(pager,phoneNumber, orderNumber);
		request.setAttribute("pager", pager);
		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("phoneNumber", phoneNumber);
		return "appraise_list";
	}
}
