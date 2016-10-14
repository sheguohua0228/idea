package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ManagerOrder extends BaseEntity {
	
	
	public enum Status {
		unprocessed, processed, completed, invalid
	}

	/**
	 *  0-预约订单， 1-进行中 ，  3-已完成
	 */
	public enum OrderStatus {
		appointments, processing, factory ,washing, send, completed
	}
	
	// 付款状态（未支付、支付成功、支付失败）
	public enum PayStatus {
		unpaid,  paid, partError
	};
	
	/** 订单状态 */
	private Integer orderStatus;
	
	
	/** 订单价格 */
	private BigDecimal price;
	/** 最终价格 */
	private BigDecimal finalPrice;
	/** 用户id*/
	private String userId;
	/** 用户名*/
	private String userName;
	/**订单编号*/
	private String orderNumber;
	/** 订单内容 */
	private String content;
	/** 客户描述 */
	private String desc;
	/** 用户订单联系电话 （暂且忽略）*/
	private String phoneNumber;
	/** 地址查询ID*/
	private Integer addressId; 
	/** 处理员工 */
	private String employeeId;
	/** */
	private Integer progressId;
	/** 订单图片（忽略） */
	private String prictureImageUrl;
	/** 订单评价ID */
	private String appraiseId;
	/** 支付状态 */
	private Integer payStatus;
	/** 下单时间 */
    private Date time;
    /** 处理状态 */
	private Integer status;
	/** 支付时间*/
	private Date payTime;
	/***/
	private String employeeMemo;
	/***/
	private Integer integralNum;
	/** 支付方式 0-支付宝，1-微信，2-现金,3-积分支付**/
	private String payType;
	/***/
	private String remarkVoiceUrl;
	/***/
	private Integer isProcessedOfRemark;
	/** 截止时间 */
	private Date backTime;
	/***/
	private String takeAddress;
	/***/
	private String sendAddress;
	/**送回地址*/
	private String backAddress;
	
	private ManagerAddress managerAddress;
	
	

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Integer getAddressId(){
		return addressId;
	}
	
	public void setAddressId(Integer addressId){
		this.addressId=addressId;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public Integer getProgressId() {
		return progressId;
	}

	public void setProgressId(Integer progressId) {
		this.progressId = progressId;
	}
	
	public String getPrictureImageUrl() {
		return prictureImageUrl;
	}

	public void setPrictureImageUrl(String prictureImageUrl) {
		this.prictureImageUrl = prictureImageUrl;
	}
	
	public String getAppraiseId() {
		return appraiseId;
	}

	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}
	
	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
	public Date getPayTime() {
		return payTime;
	}

	public void setpayTime(Date payTime) {
		this.payTime = payTime;
	}
	
    public String getEmployeeMemo(){
		return employeeMemo;
	}
    
	public void setEmployeeMemo(String employeeMemo){
		this.employeeMemo=employeeMemo;
	}
	
	public Integer getIntegralNum(){
		return integralNum;
	}
    
	public void setIntegralNum(Integer integralNum){
		this.integralNum=integralNum;
	}
	
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getRemarkVoiceUrl() {
		return remarkVoiceUrl;
	}

	public void setRemarkVoiceUrl(String remarkVoiceUrl) {
		this.remarkVoiceUrl = remarkVoiceUrl;
	}
	
	public Integer getIsProcessedOfRemark(){
		return isProcessedOfRemark;
	}
	
	public void setIsProcessedOfRemark(Integer isProcessedOfRemark ){
		this.isProcessedOfRemark=isProcessedOfRemark;
	}
	
	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public String getTakeAddress() {
		return takeAddress;
	}

	public void setTakeAddress(String takeAddress) {
		this.takeAddress = takeAddress;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setsendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	
	public String getBackAddress() {
		return backAddress;
	}

	public void setBackAddress(String backAddress) {
		this.backAddress = backAddress;
	}
	
	public ManagerAddress getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress(ManagerAddress managerAddress) {
		this.managerAddress = managerAddress;
	}
}
