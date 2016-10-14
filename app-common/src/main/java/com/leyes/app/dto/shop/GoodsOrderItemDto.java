package com.leyes.app.dto.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsOrderItemDto implements Serializable {
	private static long serialVersionUID = 1L;

	private String orderId;

	private String goodsId;

	private String image;

	private int number;

	private String goodsName;

	private BigDecimal price;// 单价

	private String appraiseId;
	private int isAppraise;
	
	public int getIsAppraise() {
		return isAppraise;
	}

	public void setIsAppraise(int isAppraise) {
		this.isAppraise = isAppraise;
	}
	public String getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public GoodsOrderItemDto() {
		super();
	}

	public GoodsOrderItemDto(String orderId, String goodsId, String image,
			int number, String goodsName, BigDecimal price) {
		super();
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.image = image;
		this.number = number;
		this.goodsName = goodsName;
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

}
