package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class ClothesPriceDto implements Serializable{

	private String categoryId;
	private String name;
	private String price;
	private String imageUrl;
	private String unit;
	public ClothesPriceDto() {
		super();
	}
	 
	public ClothesPriceDto(String categoryId, String name, String price,
			String imageUrl, String unit) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.imageUrl = imageUrl;
		this.unit = unit;
	}

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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
	
}
