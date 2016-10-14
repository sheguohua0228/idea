package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClothesOrderPriceInfoDto implements Serializable{

	private String userId;
	
	private String cardId;
	
	private BigDecimal price;

	public ClothesOrderPriceInfoDto(String userId, String cardId,
			BigDecimal price) {
		super();
		this.userId = userId;
		this.cardId = cardId;
		this.price = price;
	}

	public ClothesOrderPriceInfoDto() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
