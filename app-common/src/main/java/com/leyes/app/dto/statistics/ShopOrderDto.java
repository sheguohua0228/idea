package com.leyes.app.dto.statistics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ShopOrderDto implements Serializable{
	 //每日的订单总额
     public List<BigDecimal> price;
     //每日的订单量
     public List<Integer> orderNumber;
     //时间
     public List<String> time;
	public List<BigDecimal> getPrice() {
		return price;
	}
	public void setPrice(List<BigDecimal> price) {
		this.price = price;
	}
	public List<Integer> getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(List<Integer> orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
}
