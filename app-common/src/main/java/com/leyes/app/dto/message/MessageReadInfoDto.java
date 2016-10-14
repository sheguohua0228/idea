package com.leyes.app.dto.message;

import java.io.Serializable;

public class MessageReadInfoDto implements Serializable{
	// 社区消息数量
	private long communityNumber;
	// 个人消息数量
	private long personalNumber;
	// 优惠活动消息数量
	private long activityNumber;
	public long getCommunityNumber() {
		return communityNumber;
	}
	public void setCommunityNumber(long communityNumber) {
		this.communityNumber = communityNumber;
	}
	public long getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(long personalNumber) {
		this.personalNumber = personalNumber;
	}
	public long getActivityNumber() {
		return activityNumber;
	}
	public void setActivityNumber(long activityNumber) {
		this.activityNumber = activityNumber;
	}


	 
}
