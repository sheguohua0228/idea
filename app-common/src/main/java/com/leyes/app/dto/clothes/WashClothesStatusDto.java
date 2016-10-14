package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class WashClothesStatusDto implements Serializable{

	private long time;
	
	private String desc;
	
	private String imageUrl;

	 

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public WashClothesStatusDto() {
		super();
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public WashClothesStatusDto(long time, String desc, String imageUrl) {
		super();
		this.time = time;
		this.desc = desc;
		this.imageUrl = imageUrl;
	}

 
	
}
