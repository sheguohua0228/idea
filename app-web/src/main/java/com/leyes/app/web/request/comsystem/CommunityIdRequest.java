package com.leyes.app.web.request.comsystem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class CommunityIdRequest{

	@ApiModelProperty(value="社区id,默认-1")
	private String communityId;

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	 
	
}
