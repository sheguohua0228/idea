package com.leyes.app.web.request.employee;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class SingleClothesRequest {

	@ApiModelProperty(value="订单Id")
	private String orderId;
	@ApiModelProperty(value="衣服id")
	private String clothesId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getClothesId() {
		return clothesId;
	}

	public void setClothesId(String clothesId) {
		this.clothesId = clothesId;
	}
}
