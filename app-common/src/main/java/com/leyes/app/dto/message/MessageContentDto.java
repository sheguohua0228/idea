package com.leyes.app.dto.message;

import java.io.Serializable;

public class MessageContentDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String messageId;
	
	private String content;
	
	private int contentType;
	
	private int sort;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 
	public int getContentType() {
		return contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	
}
