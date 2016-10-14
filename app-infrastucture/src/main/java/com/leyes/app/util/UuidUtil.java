package com.leyes.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UuidUtil {
	public static String getUUIDString() {
		// 创建 GUID 对象
		UUID uuid = UUID.randomUUID();
		// 得到对象产生的ID
		String guid = uuid.toString();
		// 转换为大写
		guid = guid.toUpperCase();
		// 替换 -
		guid = guid.replaceAll("-", "");
		return guid;
	}
	private static SimpleDateFormat sdf;
	
	public static String orderNumber(){
		if(sdf==null){
			sdf=new SimpleDateFormat("yyMMddHHmmssS");
		}
		String strDate = sdf.format(new Date());
		Random random = new Random();

		int x = random.nextInt(899999);
		x=x+100000;
		return strDate+x;
	}
	
	public static String extractCode(){
		return String.valueOf(System.currentTimeMillis()/1000);
	}
}
