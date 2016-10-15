package com.leyes.app.web.request.user;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class ModifyAddressRequest {

	@ApiModelProperty(value = "用户名", required = true)
	private String userName;
	@ApiModelProperty(value = "电话号码", required = true)
	private String phone;
	@ApiModelProperty(value = "社区名字", required = true)
	private String communityName;
	@ApiModelProperty(value = "详细地址", required = true)
	private String addressDetail;
	@ApiModelProperty(value = "社区id", required = true)
	private String communityId;
	@ApiModelProperty(value = "地址Id", required = true)
	private String addressId;
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
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	 
}
