package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.ValidStatus;
/**
 * 
* @TypeName: Community 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月19日 下午1:04:33 
*
 */
public class Community {
 
	private String id;
 
	private Date createTime;
 
	private Date updateTime;
 
	private String name;
	
	private String address;
	 
	private ValidStatus status;
	
	private String longitude; 
	
	private String latitude;

	private String parentId;
	 
	private int grade;//社区等级

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ValidStatus getStatus() {
		return status;
	}

	public void setStatus(ValidStatus status) {
		this.status = status;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	 
}
