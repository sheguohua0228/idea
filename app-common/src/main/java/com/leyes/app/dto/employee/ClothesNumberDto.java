package com.leyes.app.dto.employee;

import java.io.Serializable;

public class ClothesNumberDto implements Serializable{

	private String barCode;
	
	private int clothesNumber;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getClothesNumber() {
		return clothesNumber;
	}

	public void setClothesNumber(int clothesNumber) {
		this.clothesNumber = clothesNumber;
	}

	public ClothesNumberDto(String barCode, int clothesNumber) {
		super();
		this.barCode = barCode;
		this.clothesNumber = clothesNumber;
	}

	public ClothesNumberDto() {
		super();
	}
	
	
}
