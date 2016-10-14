package com.leyes.app.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 
	* @Title: isServiceTime 
	* @Description: TODO
	* @param time
	* @param now
	* @param hourBegin
	* @param minuteBegin
	* @param hourEnd
	* @param minuteEnd
	* @return
	* @return int     0 时间错误 1不在时间范围内 2正确
	* @throws
	 */
	public static int isServiceTime(Date time,Date now,
			int hourBegin,int minuteBegin,int hourEnd,int minuteEnd) {
		if(time.getTime() <now.getTime()){
			return 0;
		}
		//获取3天后的时间
		Date day = getDateOfBefore(3);
		if(time.after(day)){
			return 0;
		}
		 
		Timestamp timeDay = translateTime(time);
		Timestamp beginTime = translateTime(hourBegin, minuteBegin);
		Timestamp endTime = translateTime(hourEnd, minuteEnd);
		
		if(timeDay.compareTo(beginTime)!=-1  && timeDay.compareTo(endTime) !=1){
			return 2;
		}
		return 1;
	}
	 
	public static Timestamp translateTime(Date time){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, time.getHours());
		cal.set(Calendar.MINUTE, time.getMinutes());
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 001);
		return new Timestamp(cal.getTimeInMillis());
	}
	/**
	 * 获取当天7:30的时间戳
	 * @return
	 */
	public static Timestamp translateTime(int hour,int minute) {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.HOUR_OF_DAY,hour);
		  cal.set(Calendar.SECOND, 0);
		  cal.set(Calendar.MINUTE, minute);
		  cal.set(Calendar.MILLISECOND, 0);
		  return new Timestamp(cal.getTimeInMillis());
	}
	 /**
	 * 获取当前时间的前几天或后几天的时间
	 * @param day 正数表示day天后，负数表示day天前
	 * @return
	 */
	public static Date getDateOfBefore(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
		
}
