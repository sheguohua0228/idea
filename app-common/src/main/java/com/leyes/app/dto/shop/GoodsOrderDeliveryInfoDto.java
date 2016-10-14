package com.leyes.app.dto.shop;

import java.io.Serializable;

public class GoodsOrderDeliveryInfoDto implements Serializable{

	private long time;
	
	private String description;//此状态时衣服描述语言

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GoodsOrderDeliveryInfoDto(long time, String description) {
		super();
		this.time = time;
		this.description = description;
	}

	public GoodsOrderDeliveryInfoDto() {
		super();
	}
	
	
}
