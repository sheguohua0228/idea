package com.leyes.app.web.response.user;

import java.io.Serializable;

public class QueryAccountResponse implements Serializable{

	private String  balance;
	
	private int score;

	private String ratio;
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	 
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
	
}
