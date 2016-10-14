package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.RechargeCard;
import com.aplus.lk.clothes.service.RechargeCardService;

@Controller
@RequestMapping("rechargeCard")
public class RechargeCardController {

	
	@Autowired
	private RechargeCardService rechargeCardService;
	
	@RequestMapping("query")
	public String list(Pager pager,HttpServletRequest request){
		pager=rechargeCardService.query(pager);
		request.setAttribute("pager", pager);
		return "recharge_list";
	}
	@RequestMapping("input")
	public String input(){
		return "recharge_input";
	}
	@RequestMapping("group/input")
	public String inputGroup(){
		return "recharge_group_input";
	}
	@RequestMapping("save")
	public String save(RechargeCard rechargeCard){
		rechargeCardService.createRechargeCard(rechargeCard);
		return "recharge_input";
	}
	
}
