package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClotheOrderExctraDto implements Serializable{

	private String cardId;
	
	private BigDecimal price;

	public ClotheOrderExctraDto() {
		super();
	}

	public ClotheOrderExctraDto(String cardId, BigDecimal price) {
		super();
		this.cardId = cardId;
		this.price = price;
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
