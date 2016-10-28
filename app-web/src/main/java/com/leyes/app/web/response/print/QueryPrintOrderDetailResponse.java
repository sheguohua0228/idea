package com.leyes.app.web.response.print;

import com.leyes.app.dto.print.VisaPhotoRequireInfoDto;

public class QueryPrintOrderDetailResponse {

	private String orderId;

	private String orderNumber;

	private String barCode;// 审核后的条码

	private int auditStatus;// 审核状态 1审核中 2 审核失败 3审核成功

	private String statusDesc;// 状态描述

	private VisaPhotoRequireInfoDto requiredInfo;

	private String serviceDesc;// 服务状态描述

	private String orderTime;

	private String fetchCode;

	private int noticeEmployee;// 是否通知小哥 0 未通知1已通知

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public VisaPhotoRequireInfoDto getRequiredInfo() {
		return requiredInfo;
	}

	public void setRequiredInfo(VisaPhotoRequireInfoDto requiredInfo) {
		this.requiredInfo = requiredInfo;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getFetchCode() {
		return fetchCode;
	}

	public void setFetchCode(String fetchCode) {
		this.fetchCode = fetchCode;
	}

	public int getNoticeEmployee() {
		return noticeEmployee;
	}

	public void setNoticeEmployee(int noticeEmployee) {
		this.noticeEmployee = noticeEmployee;
	}

}
