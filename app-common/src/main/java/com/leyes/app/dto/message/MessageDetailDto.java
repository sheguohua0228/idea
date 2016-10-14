package com.leyes.app.dto.message;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MessageDetailDto implements Serializable{
	 
	private String content;
	
	private int contentType;

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

	public MessageDetailDto() {
		super();
	}

	public MessageDetailDto(String content, int contentType) {
		super();
		this.content = content;
		this.contentType = contentType;
	}
	

}
