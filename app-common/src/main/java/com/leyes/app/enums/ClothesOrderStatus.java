package com.leyes.app.enums;

public enum ClothesOrderStatus{

		      PLACE_ORDER("下单","订单已经预约，小哥会按时来收取您的待洗衣物"),
		     TAKE_CLOTHES("收衣","小哥已收取待洗物件，正在前往洗衣工厂中"),
		  CLOTHES_FACTORY("工厂","您的衣物已抵达工厂，正在分拣中"),
	         WASH_CLOTHES("洗涤","您的衣物已分拣完毕，正在洗涤中"),
         DELIVERY_CLOTHES("派送","您的衣物已打包完毕，正在派送中"),
   COMPLETE_CLOTHES_ORDER("完成","您的衣服已确认送回，祝你生活愉快");
	private String status;
	private String description;

	private ClothesOrderStatus(String status,String description) {
		this.status = status;
		this.description=description;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public int getIntValue() {
		return this.ordinal();
	}
}
