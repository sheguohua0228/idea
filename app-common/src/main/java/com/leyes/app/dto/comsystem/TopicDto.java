package com.leyes.app.dto.comsystem;

import java.io.Serializable;

public class TopicDto implements Serializable{

	private String name;
	
	private String url;
	
	private String imageUrl;
	 
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
