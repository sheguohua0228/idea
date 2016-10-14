package com.leyes.app.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.leyes.app.annotation.NotNull;
import com.leyes.app.exceptions.MallException;

@Aspect
@Configuration
public class ParameterInterceptor {

	private Logger logger=LogManager.getLogger(ParameterInterceptor.class.getSimpleName());
	
	
	@Pointcut("execution(* com.leyes.app.service.*.*(..))")  
    private void anyMethod(){}//定义一个切入点  
      
    @Around("anyMethod()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
    	String methodName = pjp.getSignature().getName();
    	Object[] parameters = pjp.getArgs();
    	Object target = pjp.getTarget();
    	//拦截的放参数类型
    	Class<?>[] parameterTypes = ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes();
    	logger.info("request method name is: "+methodName);
    	logger.info("request parameters are: "+JSONObject.toJSONString(parameters));
    	Method method = target.getClass().getMethod(methodName, parameterTypes);
    	 
    	Annotation[][] an = method.getParameterAnnotations();
    	if(an.length>0){
    		for(int i=0;i<an.length;i++){  
                for(int j=0;j<an[i].length;j++){  
                	Annotation t = an[i][j];  
                	if(t instanceof NotNull){
                		Object parameter = parameters[i];
                		if(parameter==null || StringUtils.isBlank(parameter.toString())){
                			NotNull notNul = (NotNull)t;
                			MallException e = new MallException(notNul.message());
                			e.initCause(new NullPointerException());
                			throw e;
                		}
                	}
                }  
            }  
    	}
        Object result = pjp.proceed();//执行该方法  
        logger.info("request result is:"+JSONObject.toJSONString(result));
        return result;  
    }  
    
    @AfterThrowing(pointcut= "anyMethod()",throwing="e") 
    public void handleException(Exception e){
    	logger.error("request occurred exception,The reason for the exception is:"+e.getMessage()+"---- "+e.getCause());
    	logger.error("exception message is:"+""+e.fillInStackTrace());
    }
}
