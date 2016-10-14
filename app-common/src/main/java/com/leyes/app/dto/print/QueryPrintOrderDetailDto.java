package com.leyes.app.dto.print;

import java.io.Serializable;




public class QueryPrintOrderDetailDto implements Serializable{
 
	private String orderId;
	
	private int auditStatusCode;//审核状态 1审核中 2 审核失败 3审核成功
	
	private String auditStatusValue;//审核状态值
	
	private String auditStatusDesc;//审核描述

	private VisaPhotoRequireInfoDto requiredInfo;
	
	private Long orderDate;
	
	private int noticeEmployee;//是否通知小哥 0 未通知1已通知
	
	private String fetchCode;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getAuditStatusCode() {
		return auditStatusCode;
	}

	public void setAuditStatusCode(int auditStatusCode) {
		this.auditStatusCode = auditStatusCode;
	}

	public String getAuditStatusValue() {
		return auditStatusValue;
	}

	public void setAuditStatusValue(String auditStatusValue) {
		this.auditStatusValue = auditStatusValue;
	}

	public String getAuditStatusDesc() {
		return auditStatusDesc;
	}

	public void setAuditStatusDesc(String auditStatusDesc) {
		this.auditStatusDesc = auditStatusDesc;
	}

	public VisaPhotoRequireInfoDto getRequiredInfo() {
		return requiredInfo;
	}

	public void setRequiredInfo(VisaPhotoRequireInfoDto requiredInfo) {
		this.requiredInfo = requiredInfo;
	}

	public Long getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}

	public int getNoticeEmployee() {
		return noticeEmployee;
	}

	public void setNoticeEmployee(int noticeEmployee) {
		this.noticeEmployee = noticeEmployee;
	}

	public String getFetchCode() {
		return fetchCode;
	}

	public void setFetchCode(String fetchCode) {
		this.fetchCode = fetchCode;
	}
	
}
