package com.leyes.app.web.request.shop;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
@ApiModel
public class GoodsOrderAppraiseRequest {

	@ApiModelProperty(value="订单ID")
	private String orderId;
	@ApiModelProperty(value="评价内容")
	private String content;
	@ApiModelProperty(value="服务星级")
	private int serviceStar;
	@ApiModelProperty(value="商品id")
	private String goodsId;
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
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
