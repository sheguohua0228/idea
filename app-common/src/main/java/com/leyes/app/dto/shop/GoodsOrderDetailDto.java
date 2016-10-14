package com.leyes.app.dto.shop;

import java.io.Serializable;


public class GoodsOrderDetailDto implements Serializable{

	private String orderNumber;
	
	private long placeOrderTime;
	
	private String price;
	
	private String balance;
	
	private int integral;

	private String addressId;
	
	private int payStatus;
	
	private int orderStatus;
	
	private int freight;
	
	
	public int getFreight() {
		return freight;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getPlaceOrderTime() {
		return placeOrderTime;
	}

	public void setPlaceOrderTime(long placeOrderTime) {
		this.placeOrderTime = placeOrderTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public GoodsOrderDetailDto(String orderNumber, long placeOrderTime,
			String price, String balance, int integral) {
		super();
		this.orderNumber = orderNumber;
		this.placeOrderTime = placeOrderTime;
		this.price = price;
		this.balance = balance;
		this.integral = integral;
	}

	public GoodsOrderDetailDto() {
		super();
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
	
	
}
