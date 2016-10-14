package com.leyes.app.enums;

public enum PrintType {
   /**
    * 打印订单
    */
	LOCAL("本地打印"),TELENT("远端打印");
   
   private String value;

	private PrintType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
