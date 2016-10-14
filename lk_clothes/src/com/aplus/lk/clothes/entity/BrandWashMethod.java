package com.aplus.lk.clothes.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 品牌洗涤方式
* @TypeName: BrandWashMethod 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年3月16日 上午10:33:14 
*
 */
public class BrandWashMethod implements Serializable{
	 
	private long id;

	private Date createTime;

	private Date updateTime;
	
	private String brandName;
	/**
	 * 品牌级别 目前为一线品牌 二线品牌
	 */
	private int brandGrade;
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	 
	public int getBrandGrade() {
		return brandGrade;
	}
	public void setBrandGrade(int brandGrade) {
		this.brandGrade = brandGrade;
	}
	
	
}
