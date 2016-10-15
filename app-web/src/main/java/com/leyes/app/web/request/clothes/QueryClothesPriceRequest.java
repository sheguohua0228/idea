package com.leyes.app.web.request.clothes;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class QueryClothesPriceRequest {

	@ApiModelProperty(value="分类ID")
	String categoryId;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	 
	 
	 
}
