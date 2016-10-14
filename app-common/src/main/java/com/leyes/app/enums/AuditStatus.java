package com.leyes.app.enums;

public enum AuditStatus {

	NotAudited("未审核"),
	Audit("审核中"),
	AuditFailure("审核失败"),
	AuditSuccess("审核成功");
	
	private String value;
	
	private AuditStatus(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
