package com.aplus.lk.clothes.entity;

import java.util.Date;

public class Shedule{

	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/**
	 * ID
	 */
	private long id;
	
	private String monday; //周一
	
	private String mondayTime; //周一对应的日期
	
	private String tuesday;
	
	private String tuesdayTime;
	
	private String wednesday;
	
	private String wednesdayTime;
	
	private String thursday;
	
	private String thursdayTime;
	
	private String friday;
	
	private String fridayTime;
	
	private String saturday;
	
	private String saturdayTime;
	
	private String sunday;
	
	private String sundayTime;
	
	private Date beginTime;
	
	private Date endTime;

	public String getMonday() {
		return monday;
	}

	public String getMondayTime() {
		return mondayTime;
	}

	public String getTuesday() {
		return tuesday;
	}

	public String getTuesdayTime() {
		return tuesdayTime;
	}

	public String getWednesday() {
		return wednesday;
	}

	public String getWednesdayTime() {
		return wednesdayTime;
	}

	public String getThursday() {
		return thursday;
	}

	public String getThursdayTime() {
		return thursdayTime;
	}

	public String getFriday() {
		return friday;
	}

	public String getFridayTime() {
		return fridayTime;
	}

	public String getSaturday() {
		return saturday;
	}

	public String getSaturdayTime() {
		return saturdayTime;
	}

	public String getSunday() {
		return sunday;
	}

	public String getSundayTime() {
		return sundayTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public void setMondayTime(String mondayTime) {
		this.mondayTime = mondayTime;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public void setTuesdayTime(String tuesdayTime) {
		this.tuesdayTime = tuesdayTime;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public void setWednesdayTime(String wednesdayTime) {
		this.wednesdayTime = wednesdayTime;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public void setThursdayTime(String thursdayTime) {
		this.thursdayTime = thursdayTime;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public void setFridayTime(String fridayTime) {
		this.fridayTime = fridayTime;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public void setSaturdayTime(String saturdayTime) {
		this.saturdayTime = saturdayTime;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public void setSundayTime(String sundayTime) {
		this.sundayTime = sundayTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
