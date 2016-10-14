package com.aplus.lk.clothes.utils;

import com.google.gson.Gson;

public class JsonUtils {
	
	public static String toJson(Object data){
		return new Gson().toJson(data);
	}
	
	public static Object fromJson(String jsonStr, Class<?> classOfT){
		return new Gson().fromJson(jsonStr, classOfT);
	}
	
}
