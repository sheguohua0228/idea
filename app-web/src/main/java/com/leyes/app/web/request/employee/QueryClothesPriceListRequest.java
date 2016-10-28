package com.leyes.app.web.request.employee;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class QueryClothesPriceListRequest {

	@ApiModelProperty(value="卡券id,默认为-1")
	private String cardId;
	@ApiModelProperty(value="订单id")
	private String orderId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	 
	
}
