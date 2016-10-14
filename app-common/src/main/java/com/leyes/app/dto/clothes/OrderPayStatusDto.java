package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.math.BigDecimal;

import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.PayType;

public class OrderPayStatusDto implements Serializable{

	private String id;
	
	private String orderNumber;
	
	private PayStatus payStatus;
	
	private BigDecimal finalPrice;// 使用积分后的价格

	private int integralNum;// 使用的积分个数

	private BigDecimal balance=BigDecimal.ZERO;// 使用了多少余额

	private CouponDto card;
	
	private PayType payType;// 0-支付宝，1-微信，2-现金,3-积分支付,4余额支付 5卡券支付

	private String userId;
	
	private String orderType;//打印订单类型
	public OrderPayStatusDto() {
		super();
	}

	public OrderPayStatusDto(BigDecimal finalPrice, int integralNum,
			BigDecimal balance, CouponDto card, PayType payType) {
		super();
		this.finalPrice = finalPrice;
		this.integralNum = integralNum;
		this.balance = balance;
		this.card = card;
		this.payType = payType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getIntegralNum() {
		return integralNum;
	}

	public void setIntegralNum(int integralNum) {
		this.integralNum = integralNum;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	 

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public PayStatus getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(PayStatus payStatus) {
		this.payStatus = payStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public CouponDto getCard() {
		return card;
	}

	public void setCard(CouponDto card) {
		this.card = card;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	 
	 
}
