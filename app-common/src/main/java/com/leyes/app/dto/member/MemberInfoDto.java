package com.leyes.app.dto.member;

import java.io.Serializable;

public class MemberInfoDto implements Serializable{

private String gradeName;
	
	private String integral;
	
	private String balance;

	
	public MemberInfoDto(String gradeName, String integral, String balance) {
		super();
		this.gradeName = gradeName;
		this.integral = integral;
		this.balance = balance;
	}

	public MemberInfoDto() {
		super();
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
}
