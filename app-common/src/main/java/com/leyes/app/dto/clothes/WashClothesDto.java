package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.math.BigDecimal;

public class WashClothesDto implements Serializable {

	private String clothesName;

	private String washStatus;// 洗涤状态 0 未洗涤 1洗涤中 2返洗中 3 已洗涤 4派送中5已完成

	private BigDecimal price;

	private int amount;// 尺寸的数量
	private String size;// 尺寸

	public WashClothesDto() {
		super();
	}

	public WashClothesDto(String clothesName, String washStatus,
			BigDecimal price) {
		super();
		this.clothesName = clothesName;
		this.washStatus = washStatus;
		this.price = price;
	}

	public String getClothesName() {
		return clothesName;
	}

	public void setClothesName(String clothesName) {
		this.clothesName = clothesName;
	}

	public String getWashStatus() {
		return washStatus;
	}

	public void setWashStatus(String washStatus) {
		this.washStatus = washStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
