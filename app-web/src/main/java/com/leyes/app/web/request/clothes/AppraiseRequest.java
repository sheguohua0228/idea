package com.leyes.app.web.request.clothes;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class AppraiseRequest {

	@ApiModelProperty(value="订单ID")
	private String orderId;
	@ApiModelProperty(value="评价内容")
	private String content;
	@ApiModelProperty(value="服务星级")
	private int serviceStar;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getServiceStar() {
		return serviceStar;
	}
	public void setServiceStar(int serviceStar) {
		this.serviceStar = serviceStar;
	}
	
}
