package com.leyes.app.dto.employee;

import java.io.Serializable;

public class ClothesOrdersDto implements Serializable {

	private static long serialVersionUID = 1L;
	private String id;
	private String orderNumber;
	private long orderTime;
	private String userId;
	private String addressId;
	private long bespeakTime;
	private String operator;//标记订单是否已被领取  0 未领取 1 已领取
	private String remark;
	
	private String finisher;//标记订单是否分配给物业
	
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public ClothesOrdersDto() {
		super();
	}

	public ClothesOrdersDto(String id, String orderNumber, long orderTime,
			String userId, String addressId, long bespeakTime) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.orderTime = orderTime;
		this.userId = userId;
		this.addressId = addressId;
		this.bespeakTime = bespeakTime;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFinisher() {
		return finisher;
	}

	public void setFinisher(String finisher) {
		this.finisher = finisher;
	}


}
