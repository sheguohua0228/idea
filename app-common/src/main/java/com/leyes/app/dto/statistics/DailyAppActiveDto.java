package com.leyes.app.dto.statistics;


import java.io.Serializable;
import java.util.List;

public class DailyAppActiveDto implements Serializable{
   //每日App使用量
	public List<Integer> number;
	//时间
	public List<String> time;
	//选择时间段活跃总量
	public Integer totalNumber;
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<Integer> getNumber() {
		return number;
	}
	public void setNumber(List<Integer> number) {
		this.number = number;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
}
