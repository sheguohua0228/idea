package com.leyes.app.dto.member;

import java.io.Serializable;

public class AddressInfoDto implements Serializable{

	private String userName;
	 
	private String phone;
 
	private String communityName;
	 
	private String addressDetail;
	 
	private String communityId;
	 
	private String addressId;
	
	private String remarkAddress;

	public AddressInfoDto() {
		super();
	}

	public AddressInfoDto(String userName, String phone, String communityName,
			String addressDetail, String communityId, String addressId,String remarkAddress) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.communityName = communityName;
		this.addressDetail = addressDetail;
		this.communityId = communityId;
		this.addressId = addressId;
		this.remarkAddress=remarkAddress;
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

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getRemarkAddress() {
		return remarkAddress;
	}

	public void setRemarkAddress(String remarkAddress) {
		this.remarkAddress = remarkAddress;
	}

 
	
}
