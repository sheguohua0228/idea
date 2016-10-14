package com.leyes.app.message;

import java.io.Serializable;

public class UpdateCommunityMessage implements Serializable{

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

	public UpdateCommunityMessage() {
		super();
	}

	public UpdateCommunityMessage(String userId, String communityId) {
		super();
		this.userId = userId;
		this.communityId = communityId;
	}
	
	
}
