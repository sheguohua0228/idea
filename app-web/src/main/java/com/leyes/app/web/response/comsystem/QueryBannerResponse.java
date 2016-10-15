package com.leyes.app.web.response.comsystem;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel
public class QueryBannerResponse {

	String name;
	String imageUrl;
	String linkUrl;
	
	public QueryBannerResponse(String name, String imageUrl, String linkUrl) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.linkUrl = linkUrl;
	}
	public QueryBannerResponse() {
		super();
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
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
