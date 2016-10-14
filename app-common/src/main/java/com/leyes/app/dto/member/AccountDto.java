package com.leyes.app.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountDto implements Serializable{

	private BigDecimal  balance;
	
	private int score;

	public AccountDto() {}

	public AccountDto(BigDecimal balance, int score) {
		super();
		this.balance = balance;
		this.score = score;
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

	 
}
