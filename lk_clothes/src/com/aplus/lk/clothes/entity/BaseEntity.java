package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 
 * @ClassName: BaseEntity
 * @Description: TODO 实体基类
 * @author w.gang wgang1130@163.com
 * @date 2015-7-16 上午11:26:27
 * 
 */
public class BaseEntity {

	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** ID */
	private long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
