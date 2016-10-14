package com.leyes.app.dto.comsystem;

import java.io.Serializable;

public class ServiceChargeDto implements Serializable{

	private int expense;//费用
	
	private int condition;//(如为洗衣单则表示件数，值得买则表示订单总价格)

	public ServiceChargeDto(int expense, int condition) {
		super();
		this.expense = expense;
		this.condition = condition;
	}

	public ServiceChargeDto() {
		super();
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}
	
	
}
