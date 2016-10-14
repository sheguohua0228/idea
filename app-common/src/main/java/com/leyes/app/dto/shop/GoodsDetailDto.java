package com.leyes.app.dto.shop;

import java.io.Serializable;

public class GoodsDetailDto implements Serializable{

	private String goodsId;
	
    private String name;
	
	private String introduce;
	
	private String price;
	
	private String marketPrice;
	
	private int saleVolume;
	
	private String specification;//规格

	private String brand;
	
	private String distribution;//配送

	private int stockNumber;//库存量
	
	private String categoryId;//商品分类
	
	private long endTime;
	public GoodsDetailDto() {
		super();
	}

	public GoodsDetailDto(String goodsId, String name, String introduce,
			String price, String marketPrice, int saleVolume,
			String specification, String brand) {
		super();
		this.goodsId = goodsId;
		this.name = name;
		this.introduce = introduce;
		this.price = price;
		this.marketPrice = marketPrice;
		this.saleVolume = saleVolume;
		this.specification = specification;
		this.brand = brand;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	 

	public int getSaleVolume() {
		return saleVolume;
	}

	public void setSaleVolume(int saleVolume) {
		this.saleVolume = saleVolume;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public int getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	
}
