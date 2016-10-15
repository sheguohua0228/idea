package com.leyes.app.web.request.shop;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlaceGoodsOrderRequest {

	@ApiModelProperty(value="下单地址id")
	private String addressId;
	@ApiModelProperty(value="包含商品id和件数,[{\"goodsId\":\"\",\"number\":\"\"}]")
	private String goodsInfos;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getGoodsInfos() {
		return goodsInfos;
	}

	public void setGoodsInfos(String goodsInfos) {
		this.goodsInfos = goodsInfos;
	}

}
