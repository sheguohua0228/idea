package com.leyes.app.dto.comsystem;

import java.io.Serializable;


public class CommunityDto implements Serializable{
	String communityId;
	String parentId;
	String latitude;
	String longitude;
	String name;
	String phone;
	
	public CommunityDto(String communityId, String parentId, String latitude,
			String longitude, String communityName) {
		super();
		this.communityId = communityId;
		this.parentId = parentId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = communityName;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	 
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CommunityDto() { }
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
