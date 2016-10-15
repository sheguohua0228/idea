package com.leyes.app.web.request.user;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginRequest {

	@ApiModelProperty(value = "用户名")
	private String userName;
	@ApiModelProperty(value = "密码")
	private String password;
	@ApiModelProperty(value="友盟推送设备编号")
	private String deviceToken;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	
}
