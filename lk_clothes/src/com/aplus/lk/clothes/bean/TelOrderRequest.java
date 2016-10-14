package com.aplus.lk.clothes.bean;

import java.io.Serializable;

public class TelOrderRequest implements Serializable{

	private String name;

	private String phone;
	
	private String deliveryAddress;
	
	private String longitude;
	
	private String latitude;
	
	private String addressCenterId;
	
	private String employeeId;

	private int backWash;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getBackWash() {
		return backWash;
	}

	public void setBackWash(int backWash) {
		this.backWash = backWash;
	}
	
	
}
