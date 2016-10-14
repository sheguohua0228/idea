package com.leyes.app.dto.member;

import java.io.Serializable;
import java.math.BigDecimal;

public class CouponDto implements Serializable{

	private String cardId;
	
	private BigDecimal price;// 价格  (能抵扣的金额)
	
	private String name;//优惠卷名称
	
	private long invalidTime;// 失效时间
	
	private int clothesNumber;  //免费洗衣次数
	
	private int shoeNumber;//剩余洗鞋次数
	 
	private String remark;
	 
	private BigDecimal useCondition;// 支付优惠卡的使用条件 
	 
	private int useType; //使用场景： 0 通用 1打印 2洗衣 3管家
	
	private int useTimes;//可使用次数
	 
	private  int darnNum;//精工织补次数
	 
	private int visaPhotoNum;//免费打印签证照次数
 
	private int passportNum;//免费打印护照次数
	
	private int useStatus; 
	
	private int cardType;
	
	private double discountRatioOne;//一类衣物折扣率
	
	private double discountRatioTwo;//二类衣物折扣率
	
	public CouponDto() {
		super();
	}

	public CouponDto(String cardId,BigDecimal price, String name,  
			long invalidTime) {
		super();
		this.cardId=cardId;
		this.price = price;
		this.name = name;
		this.invalidTime = invalidTime;
	}



	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}



	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(long invalidTime) {
		this.invalidTime = invalidTime;
	}

	public int getClothesNumber() {
		return clothesNumber;
	}

	public void setClothesNumber(int clothesNumber) {
		this.clothesNumber = clothesNumber;
	}

	public int getShoeNumber() {
		return shoeNumber;
	}

	public void setShoeNumber(int shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	 

	public BigDecimal getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(BigDecimal useCondition) {
		this.useCondition = useCondition;
	}

	public int getUseType() {
		return useType;
	}

	public void setUseType(int useType) {
		this.useType = useType;
	}

	public int getUseTimes() {
		return useTimes;
	}

	public void setUseTimes(int useTimes) {
		this.useTimes = useTimes;
	}

	public int getDarnNum() {
		return darnNum;
	}

	public void setDarnNum(int darnNum) {
		this.darnNum = darnNum;
	}

	public int getVisaPhotoNum() {
		return visaPhotoNum;
	}

	public void setVisaPhotoNum(int visaPhotoNum) {
		this.visaPhotoNum = visaPhotoNum;
	}

	public int getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(int passportNum) {
		this.passportNum = passportNum;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	 

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public double getDiscountRatioOne() {
		return discountRatioOne;
	}

	public void setDiscountRatioOne(double discountRatioOne) {
		this.discountRatioOne = discountRatioOne;
	}

	public double getDiscountRatioTwo() {
		return discountRatioTwo;
	}

	public void setDiscountRatioTwo(double discountRatioTwo) {
		this.discountRatioTwo = discountRatioTwo;
	}
	
}
