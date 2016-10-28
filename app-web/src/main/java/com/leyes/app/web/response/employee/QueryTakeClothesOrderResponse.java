package com.leyes.app.web.response.employee;

import java.io.Serializable;

public class QueryTakeClothesOrderResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private String orderId;
	private String orderNumber;
	private long orderTime;
	private String userName;
	private String phone;
	private String address;
	private long bespeakTime;
/*	private String propertyId;//取件人员ID
	private String propertyName;
	private String propertyPhone;*/
	private String remark;
	
	private String barCode;
	private int clothesNumber;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	 
	public long getBespeakTime() {
		return bespeakTime;
	}
	public void setBespeakTime(long bespeakTime) {
		this.bespeakTime = bespeakTime;
	}
	 
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public int getClothesNumber() {
		return clothesNumber;
	}
	public void setClothesNumber(int clothesNumber) {
		this.clothesNumber = clothesNumber;
	}
	
	
}
