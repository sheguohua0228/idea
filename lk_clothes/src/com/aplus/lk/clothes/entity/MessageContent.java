package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 
* @TypeName: MessageContent 
* @Description: 消息内容体
* @author：Jingpeng 
* @date 2016年1月5日 下午1:49:05 
*
 */
public class MessageContent {

	private long id;
	
	private Date createDate;

 
	private Date modifyDate;
	
 
	private long messageId;//消息id
	
	 
	private int contentType;//0 文本  1图片  2 视频
	 
	private String content;//文本内容
	 
	private int sort;//顺序
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
	 
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getContentType() {
		return contentType;
	}
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
