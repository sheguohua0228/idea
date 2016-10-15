package com.leyes.app.web.response.clothes;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.dto.clothes.WashClothesDto;
import com.leyes.app.dto.clothes.WashClothesStatusDto;
import com.leyes.app.dto.member.AddressDto;

public class QueryClothesOrderDetailResponse {

	private String orderId;
	private int orderStatus;
	private String orderNumber;
	private AddressDto address; 
	private String voiceMessage;//语音留言
	private String totalPrice;//总价
	private int quantity;//件数
	private int payStatus;
	private String cardId;
	private long bespeakTime;
	private List<WashClothesDto> clothesDetails;//洗涤详情
	private List<WashClothesStatusDto> washStates;//洗涤状态
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
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	public String getVoiceMessage() {
		return voiceMessage;
	}
	public void setVoiceMessage(String voiceMessage) {
		this.voiceMessage = voiceMessage;
	}
	 
	public List<WashClothesDto> getClothesDetails() {
		return clothesDetails==null?new ArrayList<WashClothesDto>(1):clothesDetails;
	}
	public void setClothesDetails(List<WashClothesDto> clothesDetails) {
		this.clothesDetails = clothesDetails;
	}
	public List<WashClothesStatusDto> getWashStates() {
		return washStates==null?new ArrayList<WashClothesStatusDto>(1):washStates;
	}
	public void setWashStates(List<WashClothesStatusDto> washStates) {
		this.washStates = washStates;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCardId() {
		return StringUtils.isBlank(cardId)?"-1":cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public long getBespeakTime() {
		return bespeakTime;
	}
	public void setBespeakTime(long bespeakTime) {
		this.bespeakTime = bespeakTime;
	}
	
}
