package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Banner;
import com.aplus.lk.clothes.service.BannerService;

@Controller
@RequestMapping("banner")
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	@RequestMapping("add")
	public String add(){
		return "banner_input";
	}
	
	@RequestMapping("save")
	public String save(Banner banner, MultipartFile imageUpload , HttpServletRequest request){
		bannerService.save(banner,imageUpload, request);
		return "redirect:query";
	}
	
	@RequestMapping("edit")
	public String edit(long id , HttpServletRequest request){
		request.setAttribute("banner", bannerService.queryById(id));
		return "banner_input";
	}
	
	@RequestMapping("update")
	public String update(Banner banner, MultipartFile imageUpload , HttpServletRequest request){
		bannerService.update(banner,imageUpload, request);
		return "redirect:query";
	}
	
	@RequestMapping("query")
	public String query(Pager pager, String name, Integer status, Integer type , HttpServletRequest request){
		request.setAttribute("pager", bannerService.queryPager(pager, name, status,type));
		request.setAttribute("name", name);
		request.setAttribute("status", status);
		request.setAttribute("type", type);
		return "banner_list";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(long[] ids, HttpServletRequest request){
		AjaxResult result = new AjaxResult(Status.success, "删除成功");
		bannerService.delete(ids,request);
		return result;
	}
	
}
