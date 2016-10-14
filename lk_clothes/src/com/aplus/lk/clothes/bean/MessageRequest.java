package com.aplus.lk.clothes.bean;

import java.util.List;
import java.util.Map;

public class MessageRequest {
 
	/**
	 * 标题
	 */
	private String title;
 
	
	private String  previewImageUrl;//预览图片
	
	private String publisher;//发布者
	
	private String messageType;//消息类型  0社区消息  1我的消息 2 促销消息
	
	
	private String communityId;//社区
	
	private String orderNumber;//订单id
	
	private List<Map<String,Object>> memberIdAndRegisterId;//用户id和极光推送id
	
	
	private String  sendObject;
	private String mobile;
	private MessageContentVo contents[];
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
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	 
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<Map<String, Object>> getMemberIdAndRegisterId() {
		return memberIdAndRegisterId;
	}
	public void setMemberIdAndRegisterId(
			List<Map<String, Object>> memberIdAndRegisterId) {
		this.memberIdAndRegisterId = memberIdAndRegisterId;
	}
	public String getSendObject() {
		return sendObject;
	}
	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public MessageContentVo[] getContents() {
		return contents;
	}
	public void setContents(MessageContentVo[] contents) {
		this.contents = contents;
	}
 
	 
}
