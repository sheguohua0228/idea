package com.leyes.app.web.request.business;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class QueryBusinessInfoRequest{

	@ApiModelProperty(value="商家ID")
	String businessId;

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	 
	
}
