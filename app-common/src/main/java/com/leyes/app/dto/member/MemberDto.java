package com.leyes.app.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;

import com.leyes.app.enums.DeviceType;

public class MemberDto implements Serializable{

	private BigDecimal balance;
	
	private int score;
	
	private String userName;
	
	private String deviceToken;
	
	private DeviceType deviceType;

	public MemberDto(BigDecimal balance, int score, String userName,
			String deviceToken, DeviceType deviceType) {
		super();
		this.balance = balance;
		this.score = score;
		this.userName = userName;
		this.deviceToken = deviceToken;
		this.deviceType = deviceType;
	}

	public MemberDto() {
		super();
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
