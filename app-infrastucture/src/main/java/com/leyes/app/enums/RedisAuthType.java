package com.leyes.app.enums;

public enum RedisAuthType {

	clientUser("客户端用户", "userId_"), 
	employee("社区督导", "employeeId_"),
	property("物业众包", "property_");

	private String value;
	private String key;

	private RedisAuthType(String name, String key) {
		this.value = name;
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}

}
