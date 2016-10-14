package com.leyes.app.dto.comsystem;

import java.io.Serializable;
import java.math.BigDecimal;

public class IncomeOutFlowDto implements Serializable{

	private String userId;
	
	private int useIntegral;
	
	private int getIntegral;
	
	private BigDecimal useBalance=BigDecimal.ZERO;
	
	private BigDecimal getBalance=BigDecimal.ZERO;
	
 
	public IncomeOutFlowDto() {
		super();
	}

	 

	public IncomeOutFlowDto(String userId, int useIntegral, int getIntegral,
			BigDecimal useBalance, BigDecimal getBalance) {
		super();
		this.userId = userId;
		this.useIntegral = useIntegral;
		this.getIntegral = getIntegral;
		this.useBalance = useBalance;
		this.getBalance = getBalance;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUseIntegral() {
		return useIntegral;
	}

	public void setUseIntegral(int useIntegral) {
		this.useIntegral = useIntegral;
	}

	public int getGetIntegral() {
		return getIntegral;
	}

	public void setGetIntegral(int getIntegral) {
		this.getIntegral = getIntegral;
	}

	public BigDecimal getUseBalance() {
		return useBalance;
	}

	public void setUseBalance(BigDecimal useBalance) {
		this.useBalance = useBalance;
	}

	public BigDecimal getGetBalance() {
		return getBalance;
	}

	public void setGetBalance(BigDecimal getBalance) {
		this.getBalance = getBalance;
	}

	
}
