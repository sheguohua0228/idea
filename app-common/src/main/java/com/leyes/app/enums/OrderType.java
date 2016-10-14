package com.leyes.app.enums;

public enum OrderType {
	 
	clothesOrder("乐E洗衣"),
	 
	printOrder("照片打印"),
	 
	goodsOrder("值得买"),
	 
	repairOrder("物业报修"),
	
	rechargeOrder("充值");
	
	private String value;
	
	private OrderType (String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}
	
	public static OrderType translate(int code){
		switch (code) {
			case 0:
				return OrderType.clothesOrder;
			case 1:
				return OrderType.printOrder;
			case 2:
				return OrderType.goodsOrder;
			case 3:
				return OrderType.repairOrder;
			case 4:
				return OrderType.rechargeOrder;
		}
		return null;
	}
}
