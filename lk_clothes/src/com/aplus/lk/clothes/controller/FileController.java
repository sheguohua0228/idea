package com.aplus.lk.clothes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.utils.FileUtil;
import com.aplus.lk.clothes.utils.JsonUtils;

/**
 * @Title: FileController.java
 * @Package com.aplus.core.action.student
 * @Description: TODO 文件操作控制器
 * @author w.gang
 * @date 2015-6-11 上午10:50:33
 * @version V1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {
	
	@RequestMapping(value="upload",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(MultipartFile imgFile, String type ,
			HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
        if(imgFile.isEmpty()){
        	resultMap.put("error", 1);
        	resultMap.put("message", "未上传任何文件");
        }else{  
            try {
            	// 文件保存名
				resultMap.put("error", 0);
				resultMap.put("url", FileUtil.copyImage(imgFile, request, "/upload/"+type));
				resultMap.put("message", "文件上传成功");
			} catch (IllegalStateException e) {
				resultMap.put("error", 1);
	        	resultMap.put("message", e.getMessage());
			} catch (IOException e) {
				resultMap.put("error", 1);
	        	resultMap.put("message", e.getMessage());
			} 
        }
      
		return resultMap;
	}
	
	@RequestMapping(value="factoryUpload",method = RequestMethod.POST)
	public void factoryUpload(MultipartFile imgFile, String type ,
			HttpServletRequest request,HttpServletResponse response){
		response.reset();
		Map<String, Object> resultMap = new HashMap<String, Object>();
        if(imgFile.isEmpty()){
        	resultMap.put("error", 1);
        	resultMap.put("message", "未上传任何文件");
        }else{  
            try {
            	// 文件保存名
				resultMap.put("error", 0);
				resultMap.put("url", FileUtil.copyImage(imgFile, request, "/upload/"+type));
				resultMap.put("message", "文件上传成功");
			} catch (IllegalStateException e) {
				resultMap.put("error", 1);
	        	resultMap.put("message", e.getMessage());
			} catch (IOException e) {
				resultMap.put("error", 1);
	        	resultMap.put("message", e.getMessage());
			} 
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(JsonUtils.toJson(resultMap));   
            writer.flush();
        } catch (IOException e) {
             
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                     
                }
            }
        }
	}
	
}
