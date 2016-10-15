package com.leyes.app.web.request.message;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryMessageRequest {
	@ApiModelProperty(value = "消息类型,-1:全部,0:社区消息,1:个人消息,2:活动消息")
	private int messageType; 
	@ApiModelProperty(value = "当前页0")
	private int page;

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getPage() {
		return page < 0 ? 0 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
