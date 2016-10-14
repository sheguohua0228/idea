package com.leyes.app.dto.comsystem;

import java.io.Serializable;
import java.math.BigDecimal;

import com.leyes.app.dto.member.CouponDto;


public abstract class OrderDto implements Serializable{

	private BigDecimal price;//订单总价

	private String orderNumber;

	private CouponDto coupon;
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public CouponDto getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponDto coupon) {
		this.coupon = coupon;
	}

	 
}

