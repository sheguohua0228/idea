package com.leyes.app.web.response.user;


public class QueryAddressResponse {
	 
	private String userName;
	 
	private String phone;
 
	private String communityName;
	 
	private String addressDetail;
	 
	private int communityId;
	 
	private int addressId;

	public QueryAddressResponse(String userName, String phone,
			String communityName, String addressDetail, int communityId,
			int addressId) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.communityName = communityName;
		this.addressDetail = addressDetail;
		this.communityId = communityId;
		this.addressId = addressId;
	}

	public QueryAddressResponse() {
		super();
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

	 

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}
