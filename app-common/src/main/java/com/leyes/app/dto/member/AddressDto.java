package com.leyes.app.dto.member;

import java.io.Serializable;


public class AddressDto implements Serializable{

	private String userName;
	private String phone;
	private String address;
	private String addressDetail;
	
	public AddressDto() {
		super();
	}
	public AddressDto(String userName, String phone, String address,
			String addressDetail) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.addressDetail = addressDetail;
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
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
 
}
