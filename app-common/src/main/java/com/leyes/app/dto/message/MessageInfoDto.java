package com.leyes.app.dto.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.leyes.app.enums.MessageType;

public class MessageInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/*消息基本信息*/
	private String title;
	
	private String publisher;
	
	private MessageType messageType;
	
	private List<MessageContentDto> messageContents;
	
	private List<MessageReceiveInfoDto> messageReceiveInfos;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	 
	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public List<MessageContentDto> getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(List<MessageContentDto> messageContents) {
		this.messageContents = messageContents;
	}

	public void addMessageContents(MessageContentDto content){
		if(this.messageContents==null)
			messageContents=new ArrayList<MessageContentDto>();
		messageContents.add(content);
	}
	
	public List<MessageReceiveInfoDto> getMessageReceiveInfos() {
		return messageReceiveInfos;
	}

	public void setMessageReceiveInfos(
			List<MessageReceiveInfoDto> messageReceiveInfos) {
		this.messageReceiveInfos = messageReceiveInfos;
	}
	public void addMessageReceiveInfo(MessageReceiveInfoDto info){
		if(this.messageReceiveInfos==null)
			messageReceiveInfos=new ArrayList<MessageReceiveInfoDto>();
		messageReceiveInfos.add(info);
	}
	
	
}
