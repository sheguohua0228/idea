package com.leyes.app.web.response.employee;

import java.util.List;

import com.leyes.app.dto.employee.GoodsItemDto;

public class QueryFinishGoodsOrderResponse {

	private String orderId;
	private String orderNumber;
	private long orderTime;
	private String userName;
	private String phone;
	private String address;
	private String remark;
	private List<GoodsItemDto> goodsItems;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
	
}
