package com.leyes.app.dto.message;

import java.io.Serializable;

public class MessageReceiveInfoDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String messageId;
	
	private String memberId;
	
	private String communityId;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	
	

}
