package com.leyes.app.web.request.business;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class QueryBusinessRequest {

	@ApiModelProperty(value="分类ID")
	private String categoryId;
	@ApiModelProperty(value="经度")
	private String latitude;
	@ApiModelProperty(value="纬度")
	private String longitude;
	@ApiModelProperty(value="当前页0")
	private int page;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getPage() {
		return page < 0 ? 0 : page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
