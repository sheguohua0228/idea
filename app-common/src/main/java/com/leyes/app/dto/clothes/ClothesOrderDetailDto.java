package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class ClothesOrderDetailDto implements Serializable{

	private int orderStatus;
	private String orderNumber;
	private String clothesAddressId;
	private String remarkVoiceUrl;
	private boolean isProcessedOfRemark;
	private int payStatus;
	private String price;
	private int clothesNumber;
	private String cardId;
	private long bespeakTime;
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getClothesAddressId() {
		return clothesAddressId;
	}

	public void setClothesAddressId(String clothesAddressId) {
		this.clothesAddressId = clothesAddressId;
	}

	public String getRemarkVoiceUrl() {
		return remarkVoiceUrl;
	}

	public void setRemarkVoiceUrl(String remarkVoiceUrl) {
		this.remarkVoiceUrl = remarkVoiceUrl;
	}

	public boolean isProcessedOfRemark() {
		return isProcessedOfRemark;
	}

	public void setProcessedOfRemark(boolean isProcessedOfRemark) {
		this.isProcessedOfRemark = isProcessedOfRemark;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

 

	public int getClothesNumber() {
		return clothesNumber;
	}

	public void setClothesNumber(int clothesNumber) {
		this.clothesNumber = clothesNumber;
	}

	public ClothesOrderDetailDto(int orderStatus, String orderNumber,
			String clothesAddressId, String remarkVoiceUrl,
			boolean isProcessedOfRemark, int payStatus, String price,int clothesNumber) {
		super();
		this.orderStatus = orderStatus;
		this.orderNumber = orderNumber;
		this.clothesAddressId = clothesAddressId;
		this.remarkVoiceUrl = remarkVoiceUrl;
		this.isProcessedOfRemark = isProcessedOfRemark;
		this.payStatus = payStatus;
		this.price = price;
		this.clothesNumber=clothesNumber;
	}

	public ClothesOrderDetailDto() {
		super();
	}

	public long getBespeakTime() {
		return bespeakTime;
	}

	public void setBespeakTime(long bespeakTime) {
		this.bespeakTime = bespeakTime;
	}

}
