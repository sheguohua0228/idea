package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplus.lk.clothes.entity.ClothesAddress;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.ClothesAddressService;
import com.aplus.lk.clothes.service.ClothesOrderService;

@Controller
@RequestMapping("clothesAddress")
public class ClothesAddressController {
	
	@Autowired
	private AddressCenterService addressCenterService;
	@Autowired
	private ClothesOrderService clothesOrderService;
	@Autowired
	private ClothesAddressService clothesAddressService;
	
	@RequestMapping("edit")
	public String edit(long clothesOrderId, HttpServletRequest request){
		request.setAttribute("clothesOrder", clothesOrderService.queryClothesOrderById(clothesOrderId));
		request.setAttribute("addressCenterList", addressCenterService.queryAll());
		return "clothes_address_input";
	}
	
	@RequestMapping("update")
	public String update(ClothesAddress clothesAddress, String employeeId, long clothesOrderId , HttpServletRequest request){
		clothesAddress.setSendAddress(clothesAddress.getDeliveryAddress());
		clothesAddressService.update(clothesAddress, employeeId, clothesOrderId);
		return "redirect:/clothesOrder/appointment";
	}
	
}
