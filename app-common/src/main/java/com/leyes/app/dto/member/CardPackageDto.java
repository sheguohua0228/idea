package com.leyes.app.dto.member;

import java.io.Serializable;
import java.util.Date;

public class CardPackageDto implements Serializable{

	private String userId;
	
	private String cardId;
	
	private int useStatus;// 使用状态 0未使用 1已使用 2使用中
	 
	private Date invalidTime;//失效时间
	
	private int time;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
}
