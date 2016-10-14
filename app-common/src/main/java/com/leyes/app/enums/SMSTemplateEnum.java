package com.leyes.app.enums;

import java.io.Serializable;

public enum SMSTemplateEnum implements Serializable {
	
	T_NormalIdentifyCodeSMS("您的验证码为：^，请在^分钟内完成输入"),
	
	T_NotifyEmployeeSMS("您好^，有用户在^打印护照，用户手机号为：^，需要护照申请表格，请尽快与其联系。"),
	
	t_notifyPassportOrder("您好，有一个新的护照订单需要处理，订单号码为：^");
	private String value;

	private SMSTemplateEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
