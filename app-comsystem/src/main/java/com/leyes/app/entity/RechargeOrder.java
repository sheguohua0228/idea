package com.leyes.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.PayType;

/**
 * 充值订单
* @TypeName: RechargeOrder 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年8月19日 下午7:40:06 
*
 */
public class RechargeOrder {

	private String id;

	private Date createTime;

	private Date updateTime;
	
	private String orderNumber;
	
	private PayType payType;//微信和支付宝
	
	private String userId;
	
	private BigDecimal price;
	
	private PayStatus payStatus;//默认订单未支付

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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	 

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public PayStatus getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(PayStatus payStatus) {
		this.payStatus = payStatus;
	}
 

}
