package com.leyes.app.dto.member;

import java.io.Serializable;

public class UserInfoDto implements Serializable{

	private String headImage;
	
	private String userName;
	
	private String gradeName;
	
	
	public UserInfoDto(String headImage, String userName) {
		super();
		this.headImage = headImage;
		this.userName = userName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public UserInfoDto(String headImage, String userName, String gradeName) {
		super();
		this.headImage = headImage;
		this.userName = userName;
		this.gradeName = gradeName;
	}

	public UserInfoDto() { }
	
	
}
