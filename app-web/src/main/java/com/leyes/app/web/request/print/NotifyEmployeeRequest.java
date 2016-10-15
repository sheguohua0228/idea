package com.leyes.app.web.request.print;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class NotifyEmployeeRequest {

	@ApiModelProperty(value="订单id")
	private String orderId;
	
	@ApiModelProperty(value="社区id")
	private String communityId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	
	
}
