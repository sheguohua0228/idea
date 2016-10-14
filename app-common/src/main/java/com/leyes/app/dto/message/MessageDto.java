package com.leyes.app.dto.message;

import java.io.Serializable;

public class MessageDto implements Serializable {
	
	private String messageId;
	private long time;
	private String title;
	private int isRead;
	private int messageType;

	public MessageDto() {
	}

	public MessageDto(String messageId, long time, String title, int isRead,
			int messageType) {
		this.messageId = messageId;
		this.time = time;
		this.title = title;
		this.isRead = isRead;
		this.messageType = messageType;
	}

	 

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

 
}
