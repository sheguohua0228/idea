package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.ValidStatus;

public class AddressInfo {
	 
	private String id;

	private Date createTime;

	private Date updateTime;

	private String userId;

	private String address;

	private String phoneNumber;

	private String userName;

	private String communityId;
	
	private ValidStatus status;//0--有效 ， 1--无效
	 
	private String addressDetail;//地址详情
	 
	private boolean hasOrdered;//该地址是否下过订单 默认否
	
	private String remarkAddress;//小哥补充地址

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public ValidStatus getStatus() {
		return status;
	}

	public void setStatus(ValidStatus status) {
		this.status = status;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	 

	public String getRemarkAddress() {
		return remarkAddress;
	}

	public void setRemarkAddress(String remarkAddress) {
		this.remarkAddress = remarkAddress;
	}

	public boolean isHasOrdered() {
		return hasOrdered;
	}

	public void setHasOrdered(boolean hasOrdered) {
		this.hasOrdered = hasOrdered;
	}
	
	
}
