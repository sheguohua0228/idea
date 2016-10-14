package com.leyes.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.leyes.app.enums.GoodsShelfState;

/**
 * 商品
* @TypeName: Goods 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月26日 下午2:02:20 
*
 */
public class Goods {

	private String id;

	private Date createTime;
	
	private Date updateTime;
	
	private String previewImage;//预览图
	
	private String name;
	
	private String businessId;//提供商id
	
	private boolean selfSupport;//是否自营
	
	private BigDecimal price;
	
	private GoodsShelfState shelfState;//商品上海状态
	
	private BigDecimal marketPrice;//市场价
	
	private int saleVolume;//销量
	
	private int stockNumber;//库存量
	
	private String introduce;//简介
	
	private String detail;//详情

	private String brand;
	
	private String categoryId;//商品分类
	
	private boolean isTop;//是否置顶
	
	private String specification;//规格
	
	private String distribution;//配送
	
	private Date endTime;
	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public boolean isTop() {
		return isTop;
	}


	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}


	public int getStockNumber() {
		return stockNumber;
	}


	public void setStockNumber(int stockNumber) {
		this.stockNumber = stockNumber;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getPreviewImage() {
		return previewImage;
	}


	public void setPreviewImage(String previewImage) {
		this.previewImage = previewImage;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public GoodsShelfState getShelfState() {
		return shelfState;
	}


	public void setShelfState(GoodsShelfState shelfState) {
		this.shelfState = shelfState;
	}


	public BigDecimal getMarketPrice() {
		return marketPrice;
	}


	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}


	public int getSaleVolume() {
		return saleVolume;
	}


	public void setSaleVolume(int saleVolume) {
		this.saleVolume = saleVolume;
	}


	public String getIntroduce() {
		return introduce;
	}


	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getSpecification() {
		return specification;
	}


	public void setSpecification(String specification) {
		this.specification = specification;
	}


	public String getBusinessId() {
		return businessId;
	}


	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}


	public boolean isSelfSupport() {
		return selfSupport;
	}


	public void setSelfSupport(boolean selfSupport) {
		this.selfSupport = selfSupport;
	}


	public String getDistribution() {
		return distribution;
	}


	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
