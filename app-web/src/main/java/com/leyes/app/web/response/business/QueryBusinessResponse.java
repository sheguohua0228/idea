package com.leyes.app.web.response.business;


public class QueryBusinessResponse{

	String businessId;
	String branchName;
	String name;
	String previewImg;
	String address;
	String telephone;
	String distance;
	
	public QueryBusinessResponse() {
		super();
	}
	public QueryBusinessResponse(String businessId, String branchName,
			String name, String previewImg, String address, String telephone,
			String distance) {
		super();
		this.businessId = businessId;
		this.branchName = branchName;
		this.name = name;
		this.previewImg = previewImg;
		this.address = address;
		this.telephone = telephone;
		this.distance = distance;
	}
	 
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreviewImg() {
		return previewImg;
	}
	public void setPreviewImg(String previewImg) {
		this.previewImg = previewImg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}

}
