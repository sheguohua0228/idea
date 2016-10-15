package com.leyes.app.web.request.user;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryCouponsRequest {

	@ApiModelProperty(value = "当前页，0开始")
	private int page;
	@ApiModelProperty(value = "订单id")
	private String orderId;
	@ApiModelProperty(value = "支付类型,如无 填-1; 0 洗衣 1照片 2值得买3物业")
	private int orderType;
	 
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

 

}
