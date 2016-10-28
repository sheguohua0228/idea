package com.leyes.app.web.response.employee;

public class PropertyQueryTakeClothesOrderResponse {

	private String orderId;
	private String orderNumber;
	private long orderTime;
	private long bespeakTime;
	private String userName;
	private String phone;
	private String address;
	private int receiveStatus;// 0未领取 1自己领取 
	
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
	public long getBespeakTime() {
		return bespeakTime;
	}
	public void setBespeakTime(long bespeakTime) {
		this.bespeakTime = bespeakTime;
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
	public int getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(int receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
 
	
}
