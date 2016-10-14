package com.leyes.app.dto.statistics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RechargeDto implements Serializable {
    //日充值人数
	public List<Integer> number;
	//日充值金额
	public List<BigDecimal> money;
	//时间
	public List<String> time;
	//总人数
	public Integer totalNumber;
	//总金额
	public BigDecimal totalPrice;
	public List<Integer> getNumber() {
		return number;
	}
	public void setNumber(List<Integer> number) {
		this.number = number;
	}
	public List<BigDecimal> getMoney() {
		return money;
	}
	public void setMoney(List<BigDecimal> money) {
		this.money = money;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
} 
