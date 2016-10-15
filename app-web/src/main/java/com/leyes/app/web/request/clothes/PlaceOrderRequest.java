package com.leyes.app.web.request.clothes;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlaceOrderRequest {
	
	@ApiModelProperty(value="地址ID")
	private String addressId;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	
}
