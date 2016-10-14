package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class DeliveryClothesOrderDto implements Serializable{

	private String orderId;
	private String orderNumber;
	private long orderTime;
	private long backTime;
	private String userId;
	private String addressId;
	private String propertyId;//取件人员ID
	private String remark;
	private int deliveryStatus;
	private int payStatus;
	private String factoryRemark;
	private String finisher;
	public DeliveryClothesOrderDto(String orderId, String orderNumber,
			long orderTime, long backTime, String userId, String addressId,
			String propertyId) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.orderTime = orderTime;
		this.backTime = backTime;
		this.userId = userId;
		this.addressId = addressId;
		this.propertyId = propertyId;
	}
	public long getBackTime() {
		return backTime;
	}
	public void setBackTime(long backTime) {
		this.backTime = backTime;
	}
	public DeliveryClothesOrderDto() {
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
	 
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
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
	public String getFinisher() {
		return finisher;
	}
	public void setFinisher(String finisher) {
		this.finisher = finisher;
	}
	
	
}
