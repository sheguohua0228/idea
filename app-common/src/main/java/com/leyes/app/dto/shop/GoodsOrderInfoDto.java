package com.leyes.app.dto.shop;

import java.io.Serializable;

public class GoodsOrderInfoDto implements Serializable{

	private int orderStatus;
	
	private String orderId;
	
	private String price;
	
	private int payStatus;
	
	private int freight;
	
	private String orderNumber;
	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getFreight() {
		return freight;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	 
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

 

	public GoodsOrderInfoDto(int orderStatus, String orderId,  
			String price) {
		super();
		this.orderStatus = orderStatus;
		this.orderId = orderId;
		this.price = price;
	}
	public GoodsOrderInfoDto() {
		super();
	}

}
