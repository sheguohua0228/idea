package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.ValidStatus;

/**
 * 商家
* @TypeName: Business 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年6月1日 上午11:01:35 
*
 */
public class Business {

	private String id;
	
	private Date createTime;
	
	private Date updateTime;
 
	private String name;//商家名字
	
	private String previewImage;//预览图
	 
	private String longitude;
	 
	private String latitude;
 
	private String address;
	 
	private String telephone;//电话号码（带区号）
	 
	private String categoryId;//商家类别  美食、娱乐、便命服务、酒店、医药服务(多个以英文逗号隔开)
 
	private String communityId;//所属社区
	
	private String serverTime;//服务时间
	 
	private String description;//商家详细描述
	 
	private ValidStatus status;//商家状态 0可用 1禁用
	 
	private double distance;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getServerTime() {
		return serverTime;
	}


	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ValidStatus getStatus() {
		return status;
	}


	public void setStatus(ValidStatus status) {
		this.status = status;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public String getCommunityId() {
		return communityId;
	}


	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	 
}
