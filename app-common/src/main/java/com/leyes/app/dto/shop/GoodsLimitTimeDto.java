package com.leyes.app.dto.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsLimitTimeDto implements Serializable{

	private String goodsId;
	
	private long endTime;
	
	private BigDecimal price;
	
	private String	marketPrice;
	
	private String image;

	public GoodsLimitTimeDto() {
		super();
	}

	public GoodsLimitTimeDto(String goodsId,  long endTime,
			BigDecimal price, String marketPrice, String image) {
		super();
		this.goodsId = goodsId;
		this.endTime = endTime;
		this.price = price;
		this.marketPrice = marketPrice;
		this.image = image;
	}
	 
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	 

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
