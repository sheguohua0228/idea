package com.leyes.app.dto.print;

import java.io.Serializable;

public class QueryPrintFetchCodeDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fetchCode;// 提取码
	private Long orderDate; // 创建时间
	private int exStatus;// 提取码状态
	private String exStatusDesc;// 提取码状态描述
	private String orderNumber;
	private String price;
	private String orderId;
	private int printNumber;
	private String appraiseId; // -1为未评价
	private int photoType;//2护照
	private int isAppraise;
	
	public int getIsAppraise() {
		return isAppraise;
	}

	public void setIsAppraise(int isAppraise) {
		this.isAppraise = isAppraise;
	}

	public String getFetchCode() {
		return fetchCode;
	}

	public void setFetchCode(String fetchCode) {
		this.fetchCode = fetchCode;
	}

	 

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public int getExStatus() {
		return exStatus;
	}

	public void setExStatus(int exStatus) {
		this.exStatus = exStatus;
	}

	public String getExStatusDesc() {
		return exStatusDesc;
	}

	public void setExStatusDesc(String exStatusDesc) {
		this.exStatusDesc = exStatusDesc;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(int printNumber) {
		this.printNumber = printNumber;
	}
 
	public String getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}

	public int getPhotoType() {
		return photoType;
	}

	public void setPhotoType(int photoType) {
		this.photoType = photoType;
	}

	 
}
