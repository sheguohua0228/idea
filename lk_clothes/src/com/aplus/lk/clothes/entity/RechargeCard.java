package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @TypeName: RechargeCard
 * @Description: 充值卡 （即时充值时，充值钱先创建充值卡并消费，活动充值卡，可预先生成一定量的充值卡）
 * @author：Jingpeng
 * @date 2016年1月21日 上午10:14:10
 * 
 */
public class RechargeCard {

	private long id;

	private Date createTime;

	private Date updateTime;
	 
	private BigDecimal denomination;// 面额
	 
	private BigDecimal price;// 价格
	 
	private BigDecimal useCondition;// 支付优惠卡的使用条件
	
	private BigDecimal discountPrice;// 支付优惠卡的优惠金额
	
	private Date startTime;// 生效时间
	 
	private Date invalidTime;// 失效时间
	 
	private String password;
 
	private String remark;
	
	private int useStatus;// 使用状态 0未使用 1已使用
	
	private int rechargeType;//充值类型 0积分 1余额
	
	private int num;
	
	private int time;
	
	private int useType;
	
	private  int darnNum;//精工织补次数
	
	private int visaPhotoNum;//免费打印签证照次数
 
	private int passportNum;//免费打印护照次数
	public RechargeCard(Date createTime, Date updateTime,
			BigDecimal denomination, BigDecimal price, Date startTime,
			Date invalidTime, String remark, int rechargeType,int time,BigDecimal usecondition,
			BigDecimal discountprice ) {
		super();
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.denomination = denomination;
		this.price = price;
		this.startTime = startTime;
		this.invalidTime = invalidTime;
		this.remark = remark;
		this.rechargeType = rechargeType;
		this.time=time;
		this.useCondition=usecondition;
		this.discountPrice=discountprice;
	}

	public RechargeCard() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}

	public int getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public BigDecimal getUseCondition() {
		return useCondition;
	}

	public void setUseCondition(BigDecimal useCondition) {
		this.useCondition = useCondition;
	}

	 

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getUseType() {
		return useType;
	}

	public void setUseType(int useType) {
		this.useType = useType;
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

	 
	 
}
