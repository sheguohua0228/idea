package com.leyes.app.web.request.message;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeleteMessageRequest {
	@ApiModelProperty(value="消息id")
	private String messageId;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
