package com.leyes.app.dto.shop;

import java.io.Serializable;

public class GoodsInfoDto implements Serializable{

	private String goodsId;
	
	private int number;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

 
	
}
