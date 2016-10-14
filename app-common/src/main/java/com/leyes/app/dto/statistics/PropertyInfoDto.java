package com.leyes.app.dto.statistics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PropertyInfoDto implements Serializable{
     //
	public List<Integer> number;
	//
	public List<BigDecimal> price;
	//
	public List<String> time;
	//
	public int totalOrderNumber;
	//
	public BigDecimal totalPrice;
	public List<Integer> getNumber() {
		return number;
	}
	public void setNumber(List<Integer> number) {
		this.number = number;
	}
	public List<BigDecimal> getPrice() {
		return price;
	}
	public void setPrice(List<BigDecimal> price) {
		this.price = price;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
	public int getTotalOrderNumber() {
		return totalOrderNumber;
	}
	public void setTotalOrderNumber(int totalOrderNumber) {
		this.totalOrderNumber = totalOrderNumber;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
