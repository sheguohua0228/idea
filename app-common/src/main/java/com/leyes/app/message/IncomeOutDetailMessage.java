package com.leyes.app.message;

import java.io.Serializable;

import com.leyes.app.enums.PayType;

public class IncomeOutDetailMessage implements Serializable{

	private String userId;
	
	private String money;
	// 币种类型
	private PayType currencyType;

	private String remark;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public PayType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(PayType currencyType) {
		this.currencyType = currencyType;
	}

	 

}
