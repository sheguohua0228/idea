package com.leyes.app.web.request.clothes;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class CheckReserveTimeRequest {

	@ApiModelProperty(value="预约时间")
	private long reserveTime;

	public long getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(long reserveTime) {
		this.reserveTime = reserveTime;
	}
}
