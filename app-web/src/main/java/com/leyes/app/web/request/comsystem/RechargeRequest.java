package com.leyes.app.web.request.comsystem;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class RechargeRequest {

	@ApiModelProperty(value="充值类型,0支付宝 1微信")
	private int payType;
	
	@ApiModelProperty(value="充值金额")
	private BigDecimal price;

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
