package com.aplus.lk.clothes.entity;

import java.util.Date;

public class AppraisePO {

	private long id;

	private Date createTime;

	private Date updateTime;

	private Integer washQuality;

	private Integer delivery_send;

	private String orderId;

	private Integer servceQuality;

	private String content;
	private int orderType;
	private String orderNumber;
	private String phoneNumber;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getServceQuality() {
		return servceQuality;
	}

	public void setServceQuality(Integer servceQuality) {
		this.servceQuality = servceQuality;
	}

	public long getId() {
		return id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getWashQuality() {
		return washQuality;
	}

	public Integer getDelivery_send() {
		return delivery_send;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setWashQuality(Integer washQuality) {
		this.washQuality = washQuality;
	}

	public void setDelivery_send(Integer delivery_send) {
		this.delivery_send = delivery_send;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
