package com.leyes.app.dto.shop;

import java.io.Serializable;

public class GoodsNameDto implements Serializable{

	private String goodsId;
	
	private String name;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GoodsNameDto(String goodsId, String name) {
		super();
		this.goodsId = goodsId;
		this.name = name;
	}

	public GoodsNameDto() {
		super();
	}
	
	
}
