package com.leyes.app.web.response.employee;

import java.io.Serializable;

public class QueryUserInfoResponse implements Serializable{

	private String rank;
	
	private String score;
	
	private String balance;
	
	private String community;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
}
