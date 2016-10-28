package com.leyes.app.web.request.comsystem;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PageRequest {

	@ApiModelProperty(value="当前页0")
	private int page;

	public int getPage() {
		return page < 0 ? 0 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
