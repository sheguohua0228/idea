package com.leyes.app.web.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.leyes.app.annotation.Security;
import com.leyes.app.redis.IRedisCache;
import com.leyes.app.redis.RedisObject;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.utils.ReturnResult;
import com.leyes.app.web.utils.SessionContextUtils;

public class ResponseInterceptor extends HandlerInterceptorAdapter {

	private Logger logger=LogManager.getLogger();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("token");
		logger.info("uri:"+request.getRequestURI());
		logger.info("token:"+ token);
		logger.info("version_code:"+request.getHeader("versionCode"));
		logger.info("client:"+request.getHeader("client"));
	    /*Enumeration<String> paramNames = request.getParameterNames();request.getParameterMap()
	    while (paramNames.hasMoreElements()) {
	      String paramName = (String) paramNames.nextElement();
	      String[] paramValues = request.getParameterValues(paramName);
	      if (paramValues.length == 1) {
	        String paramValue = paramValues[0];
	        if (paramValue.length() != 0) {
        		logger.info("param:"+paramName+"-->"+paramValue);
	        }
	      }
	    }*/
	    response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		boolean flag = true;
		try {
			if (handler instanceof HandlerMethod) {
				HandlerMethod method = (HandlerMethod) handler;
				Security secutity = method.getMethodAnnotation(Security.class);
				if (secutity != null) {
					ReturnResult ret = new ReturnResult();
					
					if (StringUtils.isBlank(token)) {
						ret = ReturnResult.FAILUER(ReturnResult.FAULURE_USER_NOT_LOGIN, "用户未登录");
						
						PrintWriter writer = response.getWriter();
						writer.write(JSONObject.toJSONString(ret));
						writer.flush();
						flag = false;
					} else {
						IRedisCache<RedisObject> authRedisCache = SessionContextUtils.getRedisCache();
						UserSession userSession = authRedisCache.get(token,UserSession.class);
						if (userSession == null) {
							ret = ReturnResult.FAILUER(ReturnResult.FAULURE_USER_NOT_LOGIN,"用户身份失效");
							PrintWriter writer = response.getWriter();
							writer.write(JSONObject.toJSONString(ret));
							writer.flush();
							flag = false;
						}
					}
				}

			}
		} catch (IOException e) {
			flag = false;
		}

		return flag;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		logger.info("response :"+JSONObject.toJSONString(response));
	}

}
