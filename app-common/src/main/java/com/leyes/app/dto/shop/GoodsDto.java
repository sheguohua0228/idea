package com.leyes.app.dto.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsDto implements Serializable{

	private String goodsId;
	
	private String image;
	
	private String name;
	
	private String price;
	
	private int salesVolume;
	
	private String introduce;//简介
	
	private String marketPrice;//市场价

	public GoodsDto(String goodsId, String image, String name, String price,
			int salesVolume) {
		super();
		this.goodsId = goodsId;
		this.image = image;
		this.name = name;
		this.price = price;
		this.salesVolume = salesVolume;
	}

	public GoodsDto() {
		super();
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
}
