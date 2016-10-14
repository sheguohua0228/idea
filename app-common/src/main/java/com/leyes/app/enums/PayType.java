package com.leyes.app.enums;

public enum PayType {
	
	ALIPAY("支付宝"), WECHAT("微信"), CASH("现金"),
	INTEGRAL("积分"), BALANCE("余额"), COUPONS("卡券");

	private String value;

	private PayType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
	public static PayType translate(int code){
		PayType payType=null;
		switch (code) {
		case 0:
			payType=ALIPAY;
			break;
		case 1:
			payType=WECHAT;
			break;
		case 2:
			payType=CASH;
			break;
		case 3:
			payType=INTEGRAL;
			break;
		case 4:
			payType=BALANCE;
			break;
		case 5:
			payType=COUPONS;
			break;
		 
		}
		return payType;
	}
}
