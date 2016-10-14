package com.leyes.app.dto.query;

import java.io.Serializable;


public class AccountFlowDto implements Serializable{

	private long time;

	// 币种类型
	private String currencyType;
	// 金额
	private String money;
	// 备注
	private String remark;
	 
	 
	 
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public AccountFlowDto() {
		super();
	}
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public AccountFlowDto(long time, String currencyType,
			String money, String remark) {
		super();
		this.time = time;
		this.currencyType = currencyType;
		this.money = money;
		this.remark = remark;
	}
	
}
