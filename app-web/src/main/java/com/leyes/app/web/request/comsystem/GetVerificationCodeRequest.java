package com.leyes.app.web.request.comsystem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetVerificationCodeRequest {

	@ApiModelProperty(value="手机号码")
	private String userName;
	
	@ApiModelProperty(value="验证码类型,默认为注册验证码 （0）,登录验证码（1）,修改密码（2）")
	private int codeType;//发送验证码类型 （默认为注册）

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCodeType() {
		return codeType;
	}

	public void setCodeType(int codeType) {
		this.codeType = codeType;
	}
	 
}
