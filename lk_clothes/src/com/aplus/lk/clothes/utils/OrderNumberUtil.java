package com.aplus.lk.clothes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

public class OrderNumberUtil {

	private static long orderNum = 0l;
	private static String date;

	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static synchronized String getOrderNo() {
		String str = new SimpleDateFormat("yyMMddHHmmssS").format(new Date());
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum++;
		long orderNo = Long.parseLong((date)) * 1000;
		orderNo += orderNum;
		return orderNo + "";
	}

}
