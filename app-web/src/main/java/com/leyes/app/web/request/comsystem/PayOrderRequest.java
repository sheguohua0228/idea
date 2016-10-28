package com.leyes.app.web.request.comsystem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PayOrderRequest {

	@ApiModelProperty(value="订单id")
	private String orderId;
	@ApiModelProperty(value="订单类型 0 洗衣 1打印 2商品 3物业报修")
	private int orderType;
	@ApiModelProperty(value="是否使用余额 0 否 1是")
	private int useBalance;
	@ApiModelProperty(value="使用积分 0 否 1是")
	private int useIntegral;
	@ApiModelProperty(value="卡券ID 没有填-1")
	private String couponId;
	@ApiModelProperty(value="支付方式  0支付宝 1微信")
	private int payType;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isUseBalance() {
		return useBalance==0?false:true;
	}

	public void setUseBalance(int useBalance) {
		this.useBalance = useBalance;
	}

	public void setUseIntegral(int useIntegral) {
		this.useIntegral = useIntegral;
	}

	public boolean isUseIntegral() {
		return useIntegral==0?false:true;
	}

	 
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
}
