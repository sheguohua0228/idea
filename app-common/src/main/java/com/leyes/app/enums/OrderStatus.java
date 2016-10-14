package com.leyes.app.enums;

public enum OrderStatus {
	
	UNPROCESSED("未处理"), PROCESSED("已处理"), COMPLETED("已完成"), INVALID("已作废");
	
	private String value;

	private OrderStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
