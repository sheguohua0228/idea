package com.aplus.lk.clothes.entity;

import java.util.Date;

public class TicketNumber {

	/**
	 * ID
	 */
	private long id;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date updateTime;
	
	private Integer totalName;

	private Integer type;//0代表总数 1代表单次购买

	public long getId() {
		return id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getTotalName() {
		return totalName;
	}

	public Integer getType() {
		return type;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setTotalName(Integer totalName) {
		this.totalName = totalName;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
