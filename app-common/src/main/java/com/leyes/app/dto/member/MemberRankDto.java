package com.leyes.app.dto.member;

import java.io.Serializable;


public class MemberRankDto implements Serializable{

	private String name;// 等級名称

	private Double preferentialScale;// 优惠百分比

	private Integer score;// 所需积分

	private int lv;// 等级排名

	private Double discount;// 最低为0.8的那一类的折扣率

	
	
	public MemberRankDto(String name, Double preferentialScale, Integer score,
			int lv, Double discount) {
		super();
		this.name = name;
		this.preferentialScale = preferentialScale;
		this.score = score;
		this.lv = lv;
		this.discount = discount;
	}

	public MemberRankDto() {
		super();
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
