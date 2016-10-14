package com.leyes.app.enums;

public enum PayStatus {
	
	UNPAY("未支付"),PARTPAY("部分支付"), PAID("支付成功"), REFUNDED("退款");
	
	private String value;

	private PayStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
