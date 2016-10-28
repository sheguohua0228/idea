package com.leyes.app.web.response.print;

public class PrintOrderResponse {

	private String orderNum;
	
	private String orderStatus;
	
	private String currentNumber;
	
	private String totalPrice;
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getCurrentNumber() {
		return currentNumber;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	
}
