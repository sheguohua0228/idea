package com.leyes.app.web.request.comsystem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class ClientCheckUpdateRequest {

	@ApiModelProperty(value="客户端版本号")
	private int versionCode;

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	
	
}
