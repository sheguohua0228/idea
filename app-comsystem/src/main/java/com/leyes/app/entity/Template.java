package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.ValidStatus;

public class Template {
	
	private String id;

	private Date createTime;

	private Date updateTime;
	
	private String name;
	
	private String imageUrl;
	
	private String jumpUrl;
	
	private ValidStatus status;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public ValidStatus getStatus() {
		return status;
	}

	public void setStatus(ValidStatus status) {
		this.status = status;
	}
	
	
}
