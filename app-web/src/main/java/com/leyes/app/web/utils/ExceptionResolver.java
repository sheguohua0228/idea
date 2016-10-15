package com.leyes.app.web.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.leyes.app.exceptions.CustomException;
import com.leyes.app.exceptions.DisplayException;

/**
 * 参数集中处理类
* @TypeName: ExceptioinResolver 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月8日 下午1:27:06 
*
 */
public class ExceptionResolver implements HandlerExceptionResolver {
	
	private Logger logger=LogManager.getLogger();
 
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		 ex.printStackTrace();
		ReturnResult rst = null;
		if (ex instanceof MissingServletRequestParameterException) {
			MissingServletRequestParameterException pe = (MissingServletRequestParameterException) ex;
			rst = ReturnResult.FAILUER(ReturnResult.FAULURE_PARAMTER_REQUIRED, "关键参数【" + pe.getParameterName() + ":" + pe.getParameterType() + "】不能缺失！");
		}
		else if (ex instanceof TypeMismatchException) {
			TypeMismatchException pe = (TypeMismatchException) ex;
			rst = ReturnResult.FAILUER(ReturnResult.FAULURE_PARAMTER_REQUIRED, "参数【" + pe.getPropertyName() + ":" + pe.getRequiredType() + "】类型错误！");
		}else if(ex instanceof CustomException){
			Throwable t = ex.getCause();
			if(t instanceof NullPointerException){
				rst=ReturnResult.FAILUER(ReturnResult.FAULURE_PARAMTER_NULL_ERROR,ex.getMessage());
			}else if(t instanceof DisplayException){
				rst=ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, ex.getMessage());
			}else{
				rst=ReturnResult.FAILUER(ReturnResult.FAULURE_INTERNAL_ERROR,ex.getMessage());
			}
		}else {
			rst = ReturnResult.FAILUER(ReturnResult.FAULURE_INTERNAL_ERROR, "系统内部异常！");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			PrintWriter writer = response.getWriter();
			
			writer.write(JSONObject.toJSONString(rst));
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}