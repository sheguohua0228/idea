package com.leyes.app.dto.employee;

import java.io.Serializable;

public class GoodsItemDto implements Serializable{

	private int number;

	private String goodsName;

	private String imageUrl;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public GoodsItemDto() {
		super();
	}

	public GoodsItemDto(int number, String goodsName,String imageUrl) {
		super();
		this.number = number;
		this.goodsName = goodsName;
		this.imageUrl=imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
