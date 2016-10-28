package com.leyes.app.web.response.business;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryCategoryResponse {

	@ApiModelProperty(value = "")
	private String categoryId;
	@ApiModelProperty(value = "")
	private String name;
	@ApiModelProperty(value = "")
	private String icon;
	@ApiModelProperty(value = "")
	private int type;

	public QueryCategoryResponse(String categoryId, String name, String icon,
			int type) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.icon = icon;
		this.type = type;
	}

	public QueryCategoryResponse() {
		super();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
