package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class ClothesAppraiseDto implements Serializable{

	private int serviceStar;
	
	private String content;
	
	private long time;

	public ClothesAppraiseDto() {
		super();
	}

	public ClothesAppraiseDto(int serviceStar, String content, long time) {
		super();
		this.serviceStar = serviceStar;
		this.content = content;
		this.time = time;
	}

	public int getServiceStar() {
		return serviceStar;
	}

	public void setServiceStar(int serviceStar) {
		this.serviceStar = serviceStar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
