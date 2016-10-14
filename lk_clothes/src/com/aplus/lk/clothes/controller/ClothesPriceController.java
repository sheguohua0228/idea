package com.aplus.lk.clothes.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.entity.ClothesPrice;
import com.aplus.lk.clothes.service.ClothesPriceService;
import com.aplus.lk.clothes.utils.StringUtils;

@Controller
@RequestMapping("clothesPrice")
public class ClothesPriceController {
	
	@Autowired
	private ClothesPriceService clothesPriceService;
	
	@RequestMapping("add")
	public String add(){
		return "clothes_price_input";
	}
	
	@RequestMapping("save")
	public String save(@ModelAttribute ClothesPrice clothesPrice, HttpServletRequest request){
		if(clothesPrice != null){
			clothesPrice.setCreateTime(new Date());
			clothesPrice.setUpdateTime(clothesPrice.getCreateTime());
			clothesPriceService.save(clothesPrice);
		}
		return "redirect:query";
	}
	
	@RequestMapping("query")
	public String query(String name, HttpServletRequest request){
		List<ClothesPrice> clothesPrices = clothesPriceService.query(name);
		request.setAttribute("clothesPriceList", clothesPrices);
		if(StringUtils.isNotEmpty(name)){
			request.setAttribute("name", name);
		}
		return "clothes_price_list";
	}
	
	@RequestMapping("edit")
	public String edit(long id, HttpServletRequest request){
		ClothesPrice clothesPrice =  clothesPriceService.queryById(id);
		request.setAttribute("clothesPrice", clothesPrice);
		return "clothes_price_input";
	}
	
	@RequestMapping("update")
	public String update(ClothesPrice clothesPrice, HttpServletRequest request){
		clothesPrice.setUpdateTime(new Date());
		clothesPriceService.update(clothesPrice);
		return "redirect:query";
	}
	
	/**
	 * 
	 * @Title: delete
	 * @Description: TODO ajax删除衣服价格
	 * @param ids 删除ID数组
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(long id , HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		clothesPriceService.delete(id);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	
}
