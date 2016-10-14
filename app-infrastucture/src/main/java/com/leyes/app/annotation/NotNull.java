package com.leyes.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解相关设置
@Target({ElementType.PARAMETER})   
@Retention(RetentionPolicy.RUNTIME)   
public @interface NotNull {

	public String message();
}
