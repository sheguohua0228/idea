package com.leyes.app.web.response.user;

public class QueryUserCommunityResponse {
	int communityId;
	int parentId;
	String latitude;
	String longitude;
	String name;
	
	public QueryUserCommunityResponse() {
		super();
	}
	public QueryUserCommunityResponse(int communityId, int parentId,
			String latitude, String longitude, String name) {
		super();
		this.communityId = communityId;
		this.parentId = parentId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
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
	 
}
