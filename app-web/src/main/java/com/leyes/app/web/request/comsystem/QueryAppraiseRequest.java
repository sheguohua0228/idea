package com.leyes.app.web.request.comsystem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryAppraiseRequest {

	@ApiModelProperty(value="评价id")
	private String appraiseId;

	public String getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}
	
}
