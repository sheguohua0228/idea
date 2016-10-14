package com.leyes.app.enums;

public enum GoodsOrderSatatus {

	create("你提交了订单，请等待系统确认"), 
	
	readyOurDepot("已收到您的订单，准备出库"), 
	
	outDepot("您的订单已拣货完成，准备配送"), 
	
	delivery("乐E小哥【%s】已出发，联系电话【%s】，感谢您的耐心等待"), 
	
	finish("感谢您在乐E下购物，欢迎下次光临");

	private String value;

	private GoodsOrderSatatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
