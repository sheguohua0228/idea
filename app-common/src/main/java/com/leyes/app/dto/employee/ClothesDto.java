package com.leyes.app.dto.employee;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClothesDto implements Serializable{

	private String clothesId;
	
	private String clothesName;
	
	private int washStatus;

	private BigDecimal price;
	
	public String getClothesName() {
		return clothesName;
	}

	public void setClothesName(String clothesName) {
		this.clothesName = clothesName;
	}

	public int getWashStatus() {
		return washStatus;
	}

	public void setWashStatus(int washStatus) {
		this.washStatus = washStatus;
	}

	public ClothesDto() {
		super();
	}

	public String getClothesId() {
		return clothesId;
	}

	public void setClothesId(String clothesId) {
		this.clothesId = clothesId;
	}

	public ClothesDto(String clothesId,String clothesName, int washStatus) {
		super();
		this.clothesId=clothesId;
		this.clothesName = clothesName;
		this.washStatus = washStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
