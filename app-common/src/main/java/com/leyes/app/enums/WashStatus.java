package com.leyes.app.enums;

public enum WashStatus {

	 notWashed("未洗涤"),
	 
	 washing("洗涤中"),
	 
	 backWash("返洗中"),
	 
	 washed("已洗涤"),
	 
	 sending("派送中"),
	 
	 finish("已完成");
	 
	 private String value;
	 
	 private WashStatus(String value){
		 this.value=value;
	 }

	public String getValue() {
		return value;
	}
	 
	 
}
