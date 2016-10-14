package com.leyes.app.dto.comsystem;

import java.io.Serializable;

public class BannerDto implements Serializable{

	
	private String imageUrl;// 图片路径

	private String description;// 描述
	
	private String linkUrl;

	
	public BannerDto() {}

	public BannerDto(String imageUrl, String description, String linkUrl) {
		super();
		this.imageUrl = imageUrl;
		this.description = description;
		this.linkUrl = linkUrl;
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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	 
	
}
