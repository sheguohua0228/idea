package com.leyes.app.web.request.shop;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryLimitTimeGoodsInfoRequest {

	@ApiModelProperty(value="限时团购活动id")
	private String activityId;

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	
}
