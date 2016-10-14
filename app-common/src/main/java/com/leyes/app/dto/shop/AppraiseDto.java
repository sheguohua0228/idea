package com.leyes.app.dto.shop;

import java.io.Serializable;

public class AppraiseDto implements Serializable{

	private String image;
	
	private String userName;
	
	private int star;

	private String content;
	
	private String userId;
	
	public AppraiseDto() {
		super();
	}

	 

	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public AppraiseDto(int star, String userId) {
		super();
		this.star = star;
		this.userId = userId;
	}



	public AppraiseDto(String image, String userName, int star) {
		super();
		this.image = image;
		this.userName = userName;
		this.star = star;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}
	
	
}
