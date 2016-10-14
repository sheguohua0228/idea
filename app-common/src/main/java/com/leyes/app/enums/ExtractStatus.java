package com.leyes.app.enums;

public enum ExtractStatus {
	/**
	 * 打印订单
	 */
	UNEXTRACT("未提取"), EXTRACT("已提取");
	private String value;

	private ExtractStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	public int getIntValue() {
		return this.ordinal();
	}
}
