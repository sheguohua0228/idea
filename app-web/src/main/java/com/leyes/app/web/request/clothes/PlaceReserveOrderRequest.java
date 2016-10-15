package com.leyes.app.web.request.clothes;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlaceReserveOrderRequest {
	
	@ApiModelProperty(value="地址Id")
	private String addressId;
	@ApiModelProperty(value="预约时间")
	private long reserveTime;
 

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public long getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(long reserveTime) {
		this.reserveTime = reserveTime;
	}
	
}
