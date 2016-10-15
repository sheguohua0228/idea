package com.leyes.app.web.request.user;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class AddAddressRequest {
	@ApiModelProperty(value="用户名称")
	private String userName;
	@ApiModelProperty(value="电话号码")
	private String phone;
	@ApiModelProperty(value="社区名，即地址")
	private String communityName;
	@ApiModelProperty(value="地址详情")
	private String addressDetail;
	@ApiModelProperty(value="社区id")
	private String communityId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	 
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

}
