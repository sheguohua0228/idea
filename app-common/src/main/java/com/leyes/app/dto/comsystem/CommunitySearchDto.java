package com.leyes.app.dto.comsystem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CommunitySearchDto implements Serializable{

	private String community_id;
	private String parent_id;
	private String latitude;
	private String longitude;
	private String title;
	private String address;
	private Map<String,String> extras;
	
	public CommunitySearchDto(String communityId, String parentId,
			String latitude, String longitude, String title, String address) {
		super();
		this.community_id = communityId;
		this.parent_id = parentId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
	}
	public CommunitySearchDto() {
		super();
	}
	 
	public String getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<String, String> getExtras() {
		return extras;
	}
	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}
	public void putExtars(String communityId, String parentId){
		this.extras=new HashMap<String, String>();
		extras.put("community_id", communityId);
		extras.put("parent_id", parentId);
	}
	
}
