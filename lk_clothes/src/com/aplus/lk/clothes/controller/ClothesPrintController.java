package com.aplus.lk.clothes.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.entity.BrandWashMethod;
import com.aplus.lk.clothes.service.IBrandWashService;

@Controller
@RequestMapping("clothesPrint")
public class ClothesPrintController {
	
	@Autowired
	private IBrandWashService brandWashService;
	
	@RequestMapping("view")
	public String brandview(Model model,Integer type){
		model.addAttribute("brands", brandWashService.queryAllBrand(type));
		model.addAttribute("type",type);
		return "clothes_brand_list";
	}
	
	@RequestMapping("updateBrand")
	public String updateBrand(String[] abrand, String[] bbrand,HttpServletRequest request) throws IOException{
		String savePath= request.getSession().getServletContext().getRealPath("resource/common/editor");
		try{
		File file =new File(savePath + "/" + "brand.txt");
	     if(!file.exists()){
	       file.createNewFile();
	      }
	      FileWriter fw = new FileWriter(savePath + "/" + "brand.txt");
	      BufferedWriter bw = new BufferedWriter(fw); 
		  bw.write("var brandStr={\"a\":[");
		  if(abrand.length>=1){
		    for(int i=0;i<abrand.length-1;++i){
			  bw.write("\""+abrand[i]+"\",");
		    }
		    bw.write("\""+abrand[abrand.length-1]+"\"");
		  }
		  bw.write("],\"b\":[");
		  if(bbrand.length>=1){
		    for(int i=0;i<bbrand.length-1;++i){
			  bw.write("\""+bbrand[i]+"\",");
		    }
		    bw.write("\""+bbrand[bbrand.length-1]+"\"");
		  }
		  
		  bw.write("]}");
		   bw.close();
		}catch(IOException e){
		      e.printStackTrace();
	     }
		return "clothes_brand_list";
	}
	
	
	@RequestMapping("/brand/delete")
	@ResponseBody
	public AjaxResult delete(long brandId){
		AjaxResult result = new AjaxResult();
		brandWashService.delete(brandId);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	@RequestMapping("/brand/add")
	@ResponseBody
	public AjaxResult add(String brandName,int grade){
		AjaxResult result = new AjaxResult();
		if(StringUtils.isNotBlank(brandName)){
			BrandWashMethod brand = new BrandWashMethod();
			brand.setBrandGrade(grade);
			brand.setBrandName(brandName);
			brandWashService.add(brand);
			
			result.setData(brand.getId());
			result.setStatus(Status.success);
			result.setMessage("添加成功");
		}else{
			result.setStatus(Status.error);
			result.setMessage("品牌名不能为空");
		}
		return result;
	}
}
