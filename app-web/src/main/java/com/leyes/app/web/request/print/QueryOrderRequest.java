package com.leyes.app.web.request.print;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryOrderRequest {

	@ApiModelProperty(value="当前页0")
	private int page;
	@ApiModelProperty(value="0、所有 1、待付款 2、未完成 3、待收货 4、退款")
	private int orderType;
	
	public int getPage() {
		return page < 0 ? 0 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	
}
