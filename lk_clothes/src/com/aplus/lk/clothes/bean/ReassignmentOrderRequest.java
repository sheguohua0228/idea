package com.aplus.lk.clothes.bean;

import java.io.Serializable;

public class ReassignmentOrderRequest implements Serializable{

	private String orderId;
	
	private String deliveryAddress;
	
	private long clothesAddressId;
	
	private String longitude;
	
	private String latitude;
	
	private String addressCenterId;
	
	private String fromEmployeeId;
	
	private String toEmployeeId;

	private Integer changeType;
	
	public long getClothesAddressId() {
		return clothesAddressId;
	}

	public void setClothesAddressId(long clothesAddressId) {
		this.clothesAddressId = clothesAddressId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddressCenterId() {
		return addressCenterId;
	}

	public void setAddressCenterId(String addressCenterId) {
		this.addressCenterId = addressCenterId;
	}

	public String getFromEmployeeId() {
		return fromEmployeeId;
	}

	public void setFromEmployeeId(String fromEmployeeId) {
		this.fromEmployeeId = fromEmployeeId;
	}

	public String getToEmployeeId() {
		return toEmployeeId;
	}

	public void setToEmployeeId(String toEmployeeId) {
		this.toEmployeeId = toEmployeeId;
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	
	
}
