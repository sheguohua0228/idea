package com.leyes.app.enums;

public enum DealStatus {
	UNPROCESSED("未处理"), PROCESSED("已处理");
	private String value;

	private DealStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
