package com.leyes.app.dto.comsystem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.leyes.app.enums.CardType;

public class CouponStatusDto implements Serializable{

	private String id;

	private boolean reuse;
	
	private CardType cardType;
	
	private BigDecimal price;
	
	private int time;

	private Date invalidTime;
	
	
	public CouponStatusDto(String id, boolean reuse, CardType cardType,
			BigDecimal price, int time, Date invalidTime) {
		super();
		this.id = id;
		this.reuse = reuse;
		this.cardType = cardType;
		this.price = price;
		this.time = time;
		this.invalidTime = invalidTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}



	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}



	public CouponStatusDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isReuse() {
		return reuse;
	}

	public void setReuse(boolean reuse) {
		this.reuse = reuse;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	 

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
}
