package com.leyes.app.dto.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsOrderBaseInfoDto implements Serializable{


	private String orderId;
	
	private String orderNumber;
	
	private BigDecimal price;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public GoodsOrderBaseInfoDto() {
		super();
	}

	public GoodsOrderBaseInfoDto(String orderId, String orderNumber,
			BigDecimal price) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.price = price;
	}
	
	
}
