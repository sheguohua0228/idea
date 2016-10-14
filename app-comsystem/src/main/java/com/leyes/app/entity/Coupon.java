package com.leyes.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.leyes.app.enums.CardType;
import com.leyes.app.enums.CardUseScene;

/**
 * 优惠卡
 * 
 * @TypeName: Coupon
 * @Description: TODO
 * @author：Jingpeng
 * @date 2016年7月18日 下午4:30:04
 * 
 */
public class Coupon {

	private String id;

	private Date createTime;

	private Date updateTime;

	private BigDecimal denomination;// 能抵扣的金额

	private BigDecimal price;//

	private String name;// 优惠卷名称

	private Date invalidTime;// 失效时间

	private String remark;

	private CardType cardType;// 卡券类型 2折扣卡 3团购卡

	private BigDecimal useCondition;// 支付优惠卡的使用条件

	private CardUseScene useScene; // 使用场景： 通用0; 1 洗衣 2照片 3值得买4物业

	private boolean reuse;// 是否可以重复使用 0否 1
	
	private int clothesNumber;// 免费洗衣次数

	private int shoeNumber;// 剩余洗鞋次数
	
	private int darnNumber;// 精工织补次数

	private int visaPhotoNumber;// 免费打印签证照次数

	private int passportNumber;// 免费打印护照次数

	private String password;
	
	private boolean bindUser;//是否已被用户绑定（被领取后即被视为绑定）
	
	private int time;//第几次发布的卡券 每次递增1
	
	private double discountRatioOne;//一类衣物折扣率
	
	private double discountRatioTwo;//二类衣物折扣率
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getDenomination() {
		return denomination;
	}

	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
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

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
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
		return remark==null?"":remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

 
	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public BigDecimal getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(BigDecimal useCondition) {
		this.useCondition = useCondition;
	}

	public CardUseScene getUseScene() {
		return useScene;
	}

	public void setUseScene(CardUseScene useScene) {
		this.useScene = useScene;
	}

	public boolean isReuse() {
		return reuse;
	}

	public void setReuse(boolean reuse) {
		this.reuse = reuse;
	}

	public int getDarnNumber() {
		return darnNumber;
	}

	public void setDarnNumber(int darnNumber) {
		this.darnNumber = darnNumber;
	}

	public int getVisaPhotoNumber() {
		return visaPhotoNumber;
	}

	public void setVisaPhotoNumber(int visaPhotoNumber) {
		this.visaPhotoNumber = visaPhotoNumber;
	}

	public int getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBindUser() {
		return bindUser;
	}

	public void setBindUser(boolean bindUser) {
		this.bindUser = bindUser;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
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
