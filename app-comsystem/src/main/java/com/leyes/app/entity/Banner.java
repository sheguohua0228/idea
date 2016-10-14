package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.ValidStatus;



public class Banner {

	private String id;

	private Date createTime;

	private Date updateTime;

	private String imageUrl;// 图片路径

	private String description;// 描述

	private ValidStatus status; 

	private String linkUrl;//跳转连接地址

	private Date expirationTime;//过期时间

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
}
