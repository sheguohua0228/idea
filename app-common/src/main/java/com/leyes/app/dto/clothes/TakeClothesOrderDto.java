package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class TakeClothesOrderDto implements Serializable{

	private String orderId;
	private String orderNumber;
	private long orderTime;
	private String userId;
	private String addressId;
	private long bespeakTime;
	private int clothesNumber;
//	private String propertyId;//取件人员ID
	private String remark;
	private String barCode;
	public TakeClothesOrderDto(String orderId, String orderNumber,
			long orderTime, String userId, String addressId, long bespeakTime,
			int clothesNumber) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.orderTime = orderTime;
		this.userId = userId;
		this.addressId = addressId;
		this.bespeakTime = bespeakTime;
		this.clothesNumber = clothesNumber;
	}
	public TakeClothesOrderDto() {
		super();
	}
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public long getBespeakTime() {
		return bespeakTime;
	}
	public void setBespeakTime(long bespeakTime) {
		this.bespeakTime = bespeakTime;
	}
	public int getClothesNumber() {
		return clothesNumber;
	}
	public void setClothesNumber(int clothesNumber) {
		this.clothesNumber = clothesNumber;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	
}
