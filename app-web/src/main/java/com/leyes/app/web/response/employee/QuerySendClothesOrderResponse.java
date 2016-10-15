package com.leyes.app.web.response.employee;

import java.io.Serializable;

public class QuerySendClothesOrderResponse implements Serializable{

	private String orderId;
	private String orderNumber;
	private long orderTime;
	private long backTime;
	private String userName;
	private String phone;
	private String address;
	private String remark;
	private int selfSend;//是否小哥自己送回  0否 1是
	private int deliveryStatus;//派送状态 0未派送 1正在派送
	
	private String factoryRemark;
	private int payStatus;
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
	public long getBackTime() {
		return backTime;
	}
	public void setBackTime(long backTime) {
		this.backTime = backTime;
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
	 
	public int getSelfSend() {
		return selfSend;
	}
	public void setSelfSend(int selfSend) {
		this.selfSend = selfSend;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public String getFactoryRemark() {
		return factoryRemark;
	}
	public void setFactoryRemark(String factoryRemark) {
		this.factoryRemark = factoryRemark;
	}
	
}
