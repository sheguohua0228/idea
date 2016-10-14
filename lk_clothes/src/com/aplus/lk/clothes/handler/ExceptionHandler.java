package com.aplus.lk.clothes.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: ExceptionHandler
 * @Description: TODO 异常处理
 * @author w.gang wgang1130@163.com
 * @date 2015-7-28 下午3:50:23
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver{
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		logger.error(e.getMessage(),e);
		return new ModelAndView("error/error_page_500");
	}

}
