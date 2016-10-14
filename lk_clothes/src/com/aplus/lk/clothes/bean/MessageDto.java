package com.aplus.lk.clothes.bean;

import java.util.Date;
import java.util.List;

import com.aplus.lk.clothes.entity.MessageContent;
import com.aplus.lk.clothes.entity.MessageReceiveInfo;

public class MessageDto {

	private Date createDate;
	 
	private String title;
	
	private String  previewImageUrl;//预览图片
	
	private String publisher;//发布者
	
	private int messageType;//消息类型  0社区消息  1我的消息 2 促销消息
	
	private int messageStatus;//消息状态  0有效 1无效
	
	private List<MessageContent> contents;
	
	private int receiverNum;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreviewImageUrl() {
		return previewImageUrl;
	}

	public void setPreviewImageUrl(String previewImageUrl) {
		this.previewImageUrl = previewImageUrl;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(int messageStatus) {
		this.messageStatus = messageStatus;
	}

	public List<MessageContent> getContents() {
		return contents;
	}

	public void setContents(List<MessageContent> contents) {
		this.contents = contents;
	}

	public int getReceiverNum() {
		return receiverNum;
	}

	public void setReceiverNum(int receiverNum) {
		this.receiverNum = receiverNum;
	}

	 

	
}
