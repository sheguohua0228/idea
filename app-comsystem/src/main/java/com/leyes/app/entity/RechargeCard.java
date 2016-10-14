package com.leyes.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.leyes.app.enums.CardType;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.ValidStatus;

public class RechargeCard {

	private String id;

	private Date createTime;

	private Date updateTime;
	
	private BigDecimal denomination;//面额
	
	private BigDecimal price;//价格
	
	private Date invalidTime;// 失效时间
	
	private String password;
	
	private PayType payType;
	
	private ValidStatus useStatus;
	
	private CardType cardType;
	
	private int time;//第几次充值

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

	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public ValidStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(ValidStatus useStatus) {
		this.useStatus = useStatus;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	
}
