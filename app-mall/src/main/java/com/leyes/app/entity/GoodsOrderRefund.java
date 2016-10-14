package com.leyes.app.entity;

import java.util.Date;

public class GoodsOrderRefund {
	
	private String id;
	
	private Date createTime; // 创建时间

	private Date updateTime;
	// 打印订单id
	private String orderId;
	
	// 退款原因
	private String reason;
	// 处理状态  0处理中 1处理完毕
	private int dealStatus;
	// 处理结果   0无结果（未处理） 1申请退款成功   2申请失败(失败原因)
	private String dealResult;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(int dealStatus) {
		this.dealStatus = dealStatus;
	}
	public String getDealResult() {
		return dealResult;
	}
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	
}
