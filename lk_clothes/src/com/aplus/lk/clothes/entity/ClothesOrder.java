package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ClothesOrder extends BaseEntity{
	
	/**
	 * 订单处理状态（未处理、已处理、已完成、已作废）
	 */
	public enum Status {
		unprocessed, processed, completed, invalid
	}

	/**
	 *  0-用户已下单， 1-收衣 ， 2- 进入工厂， 3-洗涤 ， 4-送回 , 5-已完成
	 */
	public enum OrderStatus {
		appointments, collect, factory ,washing, send, completed
	}
	
	// 付款状态（未支付、支付成功、支付失败）
	public enum PayStatus {
		unpaid,  paid, partError
	};
	
	/** 订单编号 */
	private String orderNumber;
	/** 订单状态 */
	private Integer orderStatus;
	/** 支付状态 */
	private Integer payStatus;
	/** 处理状态 */
	private Integer status;
	/** 订单价格 */
	private BigDecimal price;
	/** 条形码 */
	private String barCode;
	/** 订单内容 */
	private String content;
	/** 客户描述 */
	private String desc;
	/** 订单图片（忽略） */
	private String prictureImageUrl;
	/** 用户订单联系电话 （暂且忽略）*/
	/** 取衣时间 */
	private String phoneNumber;
	private Date time;
	/** 送回时间 */
	private Date backTime;
	/** 支付方式 0-支付宝，1-微信，2-现金,3-积分支付**/
	private String payType;
	
	/** 处理员工 */
	private String employeeId;
	private Employee employee;
	/** 进度跟踪ID */
	private String progressId;
	/** 订单评价ID */
	private String appraiseId;
	/** 用户ID */
	private String userId;
	/** 用户 */
	private Member member;
	/** 取衣地址 */
	private long clothesAddressId; 
	private ClothesAddress clothesAddress;
	
	private String takeAddress;//取衣地址
	
	 
	private String backAddress;//送回地址
	
	private List<WashClothes> washClothesSet; // 衣服信息
	private List<WashStatus> washStatusSet; // 状态详情

	private WashClothes washClothes;//通过子条码查出的单个衣服
	private WashStatus washStatus;//通过子条码查出的单个衣服洗涤状态
	/** 客户留言音频路径 **/
	private String remarkVoiceUrl; 
	/** 客户留言是否已经处理 **/
	private boolean isProcessedOfRemark;
	private boolean isBackWash;//是否返洗  0 否 1是
	private String factoryRemark;//工厂的备注
	private String cardId;
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPrictureImageUrl() {
		return prictureImageUrl;
	}

	public void setPrictureImageUrl(String prictureImageUrl) {
		this.prictureImageUrl = prictureImageUrl;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ClothesAddress getClothesAddress() {
		return clothesAddress;
	}

	public void setClothesAddress(ClothesAddress clothesAddress) {
		this.clothesAddress = clothesAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public long getClothesAddressId() {
		return clothesAddressId;
	}

	public void setClothesAddressId(long clothesAddressId) {
		this.clothesAddressId = clothesAddressId;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
 

	public List<WashClothes> getWashClothesSet() {
		return washClothesSet;
	}

	public void setWashClothesSet(List<WashClothes> washClothesSet) {
		this.washClothesSet = washClothesSet;
	}

	public List<WashStatus> getWashStatusSet() {
		return washStatusSet;
	}

	public void setWashStatusSet(List<WashStatus> washStatusSet) {
		this.washStatusSet = washStatusSet;
	}

	public String getRemarkVoiceUrl() {
		return remarkVoiceUrl;
	}

	public void setRemarkVoiceUrl(String remarkVoiceUrl) {
		this.remarkVoiceUrl = remarkVoiceUrl;
	}

	public boolean getIsProcessedOfRemark() {
		return isProcessedOfRemark;
	}

	public void setIsProcessedOfRemark(boolean isProcessedOfRemark) {
		this.isProcessedOfRemark = isProcessedOfRemark;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public WashClothes getWashClothes() {
		return washClothes;
	}

	public void setWashClothes(WashClothes washClothes) {
		this.washClothes = washClothes;
	}

	public WashStatus getWashStatus() {
		return washStatus;
	}

	public void setWashStatus(WashStatus washStatus) {
		this.washStatus = washStatus;
	}

	public void setProcessedOfRemark(boolean isProcessedOfRemark) {
		this.isProcessedOfRemark = isProcessedOfRemark;
	}

	public boolean isBackWash() {
		return isBackWash;
	}

	public void setBackWash(boolean isBackWash) {
		this.isBackWash = isBackWash;
	}

	public String getTakeAddress() {
		return takeAddress;
	}

	public void setTakeAddress(String takeAddress) {
		this.takeAddress = takeAddress;
	}

	public String getBackAddress() {
		return backAddress;
	}

	public void setBackAddress(String backAddress) {
		this.backAddress = backAddress;
	}

	public String getFactoryRemark() {
		return factoryRemark;
	}

	public void setFactoryRemark(String factoryRemark) {
		this.factoryRemark = factoryRemark;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

 
}
