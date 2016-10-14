package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinishClothesOrderDto implements Serializable{

	private String id;
	private String orderNumber;
	private long createTime;
	private String  userId;
	private String addressId;
	private int  payStatus;
	private BigDecimal price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	 
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public FinishClothesOrderDto() {
		super();
	}
	public FinishClothesOrderDto(String id, String orderNumber,
			long createTime, String userId, String addressId, int payStatus) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.createTime = createTime;
		this.userId = userId;
		this.addressId = addressId;
		this.payStatus = payStatus;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
