package com.leyes.app.web.request.shop;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryAppraiseRequest {

	@ApiModelProperty(value="商品id")
	private String goodsId;
	@ApiModelProperty(value="当前页，默认0")
	private int page;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}
