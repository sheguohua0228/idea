package com.leyes.app.dto.print;

import java.io.Serializable;
import java.math.BigDecimal;

public class PrintOrderBaseInfoDto implements Serializable{

	private String orderId;
	
	private String orderNumber;
	
	private int number;
	
	private BigDecimal price;

	public PrintOrderBaseInfoDto(String orderId, String orderNumber,
			int number, BigDecimal price) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.number = number;
		this.price = price;
	}

	public PrintOrderBaseInfoDto() {
		super();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	 
	
}
