package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class ClothesOrderUserIdDto implements Serializable{

	private String userId;
	
	private String communityId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public ClothesOrderUserIdDto() {
		super();
	}

	public ClothesOrderUserIdDto(String userId, String communityId) {
		super();
		this.userId = userId;
		this.communityId = communityId;
	}
	
	
}
