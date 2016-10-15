package com.leyes.app.web.response.shop;

import java.util.List;

import com.leyes.app.dto.member.AddressDto;
import com.leyes.app.dto.shop.GoodsOrderDeliveryInfoDto;
import com.leyes.app.dto.shop.GoodsOrderItemDto;

public class QueryGoodsOrderDetailResponse {

	private String orderNumber;
	
	private long placeOrderTime;
	
	private AddressDto address;
	
	private List<GoodsOrderDeliveryInfoDto> deliveries;
	
	private List<GoodsOrderItemDto> goodsList;
	
	private String price;
	
	private String balance;
	
	private int integral;
	
	private int payStatus;
	
	private int orderStatus;

	private int freight;
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getPlaceOrderTime() {
		return placeOrderTime;
	}

	public void setPlaceOrderTime(long placeOrderTime) {
		this.placeOrderTime = placeOrderTime;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public List<GoodsOrderDeliveryInfoDto> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<GoodsOrderDeliveryInfoDto> deliveries) {
		this.deliveries = deliveries;
	}

	public List<GoodsOrderItemDto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsOrderItemDto> goodsList) {
		this.goodsList = goodsList;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getFreight() {
		return freight;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}
	
	
}
