package com.leyes.app.entity;

import java.util.Date;

public class MemberRank {

	private String id;
	
	private Date createTime;
	
	private Date updateTime;

	private String name;// 等級名称
	
	private Double preferentialScale;// 优惠百分比
	
	private Integer score;// 所需积分
	
	private Boolean isDefault;// 是否为默认等级
	
	private int lv;// 等级排名

	private Double discount;// 最低为0.8的那一类的折扣率

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

	public Double getPreferentialScale() {
		return preferentialScale;
	}

	public void setPreferentialScale(Double preferentialScale) {
		this.preferentialScale = preferentialScale;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
