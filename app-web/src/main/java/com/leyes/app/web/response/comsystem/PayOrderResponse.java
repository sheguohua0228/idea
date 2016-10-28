package com.leyes.app.web.response.comsystem;

import com.leyes.app.enums.PayStatus;

public class PayOrderResponse {

	public int payStatus;
	
	public String payParams;

	public PayOrderResponse() {
		this.payStatus=PayStatus.UNPAY.ordinal();
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayParams() {
		return payParams;
	}

	public void setPayParams(String payParams) {
		this.payParams = payParams;
	}

	 
	
}
