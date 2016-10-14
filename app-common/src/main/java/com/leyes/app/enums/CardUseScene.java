package com.leyes.app.enums;

public enum CardUseScene {

	 all("全场通用；"),
	 wash("仅洗衣订单可用；"),
	 print("仅打印订单可用；"),
	 shop("仅值得买订单可用；"),
	 property("物业报修订单可用；");
	 
	 private String value;
	 
	 private CardUseScene(String value){
		 this.value=value;
	 }
	 public String getValue(){
		 return this.value;
	 }
}
