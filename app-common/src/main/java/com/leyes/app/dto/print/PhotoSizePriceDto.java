package com.leyes.app.dto.print;

import java.io.Serializable;

public class PhotoSizePriceDto implements Serializable{

	private String name;
	
	private int size;
	
	private String price;

	private String infoId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	 
	 
}
