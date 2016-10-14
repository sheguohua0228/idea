package com.leyes.app.enums;

public enum GoodsShelfState {
	/*待上架*/
	waitSell("待上架"),
	/*已下架*/
	offSell("已下架"),
	/*已上架*/
	onSell("已上架");
	private final String text;
	private GoodsShelfState(String text) {
		this.text=text;
	}
	public String getText() {
		return text;
	}
}
