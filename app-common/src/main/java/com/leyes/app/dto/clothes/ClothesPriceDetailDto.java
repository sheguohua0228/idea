package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClothesPriceDetailDto implements Serializable,Cloneable{

	private String categoryId;
	private String name;
	private BigDecimal price;
	private String imageUrl;
	private String unit;
	private double discountRatio;
	private int activity;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	 
	public double getDiscountRatio() {
		return discountRatio;
	}
	public void setDiscountRatio(double discountRatio) {
		this.discountRatio = discountRatio;
	}
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	public ClothesPriceDetailDto() {
		super();
	}
	public ClothesPriceDetailDto(String categoryId, String name, BigDecimal price,
			String imageUrl, String unit, double discountRatio, int activity) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
		this.unit = unit;
		this.discountRatio = discountRatio;
		this.activity = activity;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		ClothesPriceDetailDto	o = (ClothesPriceDetailDto) super.clone();
		return o;
	}
}
