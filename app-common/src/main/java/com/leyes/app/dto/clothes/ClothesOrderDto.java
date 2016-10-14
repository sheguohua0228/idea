package com.leyes.app.dto.clothes;

import java.io.Serializable;


public class ClothesOrderDto implements Serializable{

	private String orderId;//洗衣订单Id
    private String orderNumber;//订单编号
    private long orderDate;//下单时期
    private String price;//订单价格
    private int orderStatus;//订单状态 
    private int payStatus;//支付状态
    private String appraiseId;//评价Id
    private int isAppraise;
	public ClothesOrderDto() {
		super();
	}
	public ClothesOrderDto(String orderId, String orderNumber, long orderDate,
			String price, int orderStatus, int payStatus, String appraiseId) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.price = price;
		this.orderStatus = orderStatus;
		this.payStatus = payStatus;
		this.appraiseId = appraiseId;
	}
	 
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
	 
	public long getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(long orderDate) {
		this.orderDate = orderDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	 
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	 
	public int getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}
	public String getAppraiseId() {
		return appraiseId;
	}
	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}
	public int getIsAppraise() {
		return isAppraise;
	}
	public void setIsAppraise(int isAppraise) {
		this.isAppraise = isAppraise;
	}
	
}
