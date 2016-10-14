package com.leyes.app.dto.statistics;

import java.io.Serializable;
import java.util.List;



public class DailyRegisterDto implements Serializable {
	//每日的注册量
    private List<Integer> number;
    //时间
    private List<String> time;
    //注册总量
    private Integer totalNumber;
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
