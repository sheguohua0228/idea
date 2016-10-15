package com.leyes.app.web.response.clothes;

public class QueryClothesOrderResponse {

	private String id;//洗衣订单Id
    private String orderNumber;//订单编号
    private String orderDate;//下单时期
    private String price;//订单价格
    private int orderStatus;//订单状态 
    private int payStatus;//支付状态
    private String appraiseId;//评价Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
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
    
}
