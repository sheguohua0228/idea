package com.leyes.app.dto.print;


public class PlaceOrderDto {

	private String imageUrl;
	
	private String infoId;
	
	private int number;
	
	//护照打印是否本地户口 默认为-1，本地户口 1，非本地户口0
	private int isNative;

	//是否为护照
	private int photoType;//1 普通照片 2护照 3签证照

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	 

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getIsNative() {
		return isNative;
	}

	public void setIsNative(int isNative) {
		this.isNative = isNative;
	}

	public int getPhotoType() {
		return photoType;
	}

	public void setPhotoType(int photoType) {
		this.photoType = photoType;
	}

	 
	
	
	 
}
