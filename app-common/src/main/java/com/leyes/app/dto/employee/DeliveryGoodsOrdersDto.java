package com.leyes.app.dto.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeliveryGoodsOrdersDto implements Serializable{

	private String orderId;
	private long orderTime;
	private String orderNumber;
	private String userId;
	private String addressId;
	private String remark;
	private long deliveryTime;
	private List<GoodsItemDto> goodsItems;
	private String finisher;
	
	public DeliveryGoodsOrdersDto(String orderId, long orderTime, String orderNumber,
			String userId, String addressId, String remark) {
		super();
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.addressId = addressId;
		this.remark = remark;
	}
	public DeliveryGoodsOrdersDto() {
		super();
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	 
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<GoodsItemDto> getGoodsItems() {
		return goodsItems;
	}
	public void setGoodsItems(List<GoodsItemDto> goodsItems) {
		this.goodsItems = goodsItems;
	}
	public void addGoodsItem(GoodsItemDto goodsItem){
		if(goodsItems==null)
			goodsItems=new ArrayList<GoodsItemDto>();
		goodsItems.add(goodsItem);
	}
	public long getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getFinisher() {
		return finisher;
	}
	public void setFinisher(String finisher) {
		this.finisher = finisher;
	}
	
}
