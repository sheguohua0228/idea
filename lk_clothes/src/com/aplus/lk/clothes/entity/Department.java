package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 实体 - 部门
 * 
 * @Title: Department.java
 * @Package com.aplus.lk.entity
 * @Description: TODO
 * @author w.gang
 * @date 2015-1-4 上午11:27:20
 * @version V1.0
 */
public class Department {

	private String id;
	
	private Date createTime;
	
	private Date updateTime;
	
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 是否启用
	 */
	private Boolean isEnabled;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 部门经理
	 */
	private String manager;
	/**
	 * 部门电话
	 */
	private String tel;
	
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

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
