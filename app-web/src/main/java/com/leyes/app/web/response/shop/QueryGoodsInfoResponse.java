package com.leyes.app.web.response.shop;

import java.io.Serializable;
import java.util.List;

import com.leyes.app.dto.comsystem.FileDto;

public class QueryGoodsInfoResponse implements Serializable{

	private String goodsId;
	
	private List<FileDto> images;
	
	private String name;
	
	private String introduce;
	
	private String price;
	
	private String marketPrice;
	
	private int saleVolume;
	
	private String specification;//规格
	
	private String distribution;//配送

	private int limitNumber=-1;//限购数量 
	
	private String express;//快递
	
	private long endTime;
	
	public List<FileDto> getImages() {
		return images;
	}

	public void setImages(List<FileDto> images) {
		this.images = images;
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

	public int getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
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
	
}
