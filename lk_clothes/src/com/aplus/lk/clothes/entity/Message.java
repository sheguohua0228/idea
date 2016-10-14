package com.aplus.lk.clothes.entity;

import java.util.Date;
import java.util.List;
 
public class Message{
	
	
	private long id;
	
	private Date createDate;

	private Date modifyDate;
	/**
	 * 标题
	 */
	private String title;
	
	private String  previewImageUrl;//预览图片
	
	private String publisher;//发布者
	
	private int messageType;//消息类型  0社区消息  1我的消息 2 促销消息
	
	private int messageStatus;//消息状态  0有效 1无效

	
	private List<MessageContent> messageContents;
	public Message() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getPreviewImageUrl() {
		return previewImageUrl;
	}

	public void setPreviewImageUrl(String previewImageUrl) {
		this.previewImageUrl = previewImageUrl;
	}

	public List<MessageContent> getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(List<MessageContent> messageContents) {
		this.messageContents = messageContents;
	}

	 
	
}
