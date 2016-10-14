package com.aplus.lk.clothes.entity;

import java.util.Date;

public class Banner extends BaseEntity {

	// 未上架、已上架
	public enum Status {
		unshelve, shelved
	}
	
	public enum Type {
		print,clothes
	}

	// 名称
	private String name;
	// 图片路径
	private String imageUrl;
	// 描述
	private String description;
	// 状态： 0/未上架；1/已上架
	private Integer status;
	// 类型
	private Integer type;
	private Date expirationTime;//过期时间
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

}
