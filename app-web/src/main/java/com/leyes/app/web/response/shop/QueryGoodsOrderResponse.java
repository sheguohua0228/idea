package com.leyes.app.web.response.shop;

import java.io.Serializable;
import java.util.List;

import com.leyes.app.dto.shop.GoodsOrderItemDto;

public class QueryGoodsOrderResponse implements Serializable{

	private int orderStatus;
	
	private String orderId;
	
	private int number;//商品数量
	
	private String price;
	
	private int payStatus;
	
	private int freight;

	private String orderNumber;
	
	private List<GoodsOrderItemDto> goodsList;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<GoodsOrderItemDto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsOrderItemDto> goodsList) {
		this.goodsList = goodsList;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public int getFreight() {
		return freight;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}
 
}
