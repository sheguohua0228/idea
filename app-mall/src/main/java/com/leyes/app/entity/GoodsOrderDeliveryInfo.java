package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.GoodsOrderSatatus;

/**
 * 商品订单步骤描述
 *
* @TypeName: GoodsOrderStatusDescription 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月26日 下午6:43:51 
*
 */
public class GoodsOrderDeliveryInfo {

	private String id;

	private Date createTime;

	private Date updateTime;
	
	private String orderId;
	
	private GoodsOrderSatatus orderStatus;
	
	private String description;//此状态时衣服描述语言
	
	private String operator;

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

	public GoodsOrderSatatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(GoodsOrderSatatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
 
}
