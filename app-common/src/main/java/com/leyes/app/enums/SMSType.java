package com.leyes.app.enums;

public enum SMSType {

	verifyCode$order("订单消息"),advert("推广消息");
	
	private String value;
	
	private SMSType(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
}
