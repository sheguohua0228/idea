package com.leyes.app.dto.shop;

import java.io.Serializable;

public class BusinessDto implements Serializable{
	
	private String businessId;
	 
	private  String name;
	 
	private  String previewImage;
	 
	private  String address;
	 
	private  String telephone;
	 
	private  String serverTime;
	 
	private  double distance;

	private String desc;
	
	public BusinessDto() {
		super();
	}

	public BusinessDto(String businessId, String name, String previewImage,
			String address, String telephone, String serverTime,
			String description) {
		super();
		this.businessId = businessId;
		this.name = name;
		this.previewImage = previewImage;
		this.address = address;
		this.telephone = telephone;
		this.serverTime = serverTime;
		this.desc = description;
	}

	 

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BusinessDto(String businessId, String name, String previewImage,
			String address, String telephone, String serverTime, double distance) {
		super();
		this.businessId = businessId;
		this.name = name;
		this.previewImage = previewImage;
		this.address = address;
		this.telephone = telephone;
		this.serverTime = serverTime;
		this.distance = distance;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
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

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}

