package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 
* @TypeName: MessageReceiveInfo 
* @Description: 消息接收信息
* @author：Jingpeng 
* @date 2016年1月4日 上午11:48:20 
*
 */
public class MessageReceiveInfo {
	 
	private long id;
	
	 
	private Date createDate;

	 
	private Date modifyDate;
	
	 
	private long messageId;
	
	 
	private String memberId;
	
	 
	private boolean isRead;//是否阅读   false 0未读 1已读
	
	 
	private long communityId;//社区Id
	
 
	private String orderNumber;//订单id
 	
	private int status;
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
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public long getCommunityId() {
		return communityId;
	}
	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 
	
}
