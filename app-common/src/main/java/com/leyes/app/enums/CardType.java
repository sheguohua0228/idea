package com.leyes.app.enums;
/**
 * 卡券类型
* @TypeName: CardType 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月20日 下午4:23:51 
*
 */
public enum CardType {
	 
	coin("积分"),
	balance("余额"),
	discount("折扣"),
	rebate("返利"),
	group("拼团");
	
	
	private String value;
	
	private CardType(String value){
		this.value=value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public int getIntValue() {
		return this.ordinal();
	}
	
	public static CardType translate(int code){
		CardType cardType=null;
		switch (code) {
		case 0:
			cardType= coin;
			 break;
		case 1:
			cardType= balance;
			 break;
		case 2:
			cardType= discount;
			 break;
		case 3:
			cardType= rebate;
			 break;
		case 4:
			cardType= group;
			 break;
		}
		return cardType;
	}
}
