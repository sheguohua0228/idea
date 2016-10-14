package com.leyes.app.enums;

public enum MessageType {
	COMMUNITY("社区消息"), PERSONAL("个人消息"),ACTIVITY("活动消息");
	
	private String value;

	private MessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static MessageType valueOf(int code){
		MessageType messageType=null;
		switch (code) {
		case 0:
			messageType= MessageType.COMMUNITY;
			break;
		case 1:
			messageType= MessageType.PERSONAL;
			break;
		case 2:
			messageType= MessageType.ACTIVITY;
			break;
		}
		return messageType;
	}
}
