package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 实体类 - 打印订单
 * 
 * @Title: PrintOrder.java
 * @Package com.aplus.lk.entity
 * @Description: TODO
 * @author w.gang
 * @date 2015-1-23 上午11:14:24
 * @version V1.0
 */
public class PrintOrder  {


	// 订单状态（未处理、已处理、已完成、已作废）
	public enum OrderStatus {
		unprocessed, processed, completed, invalid
	};

	// 付款状态（未支付、部分支付、已支付、部分退款、全额退款）
	public enum PaymentStatus {
		unpaid, partPayment, paid, partRefund, refunded
	};
	
	// 打印类型（本地打印、远端打印）
	public enum OrderType {
		local,telnet
	}
	
   // 提取状态（未提取、已提取）
	public enum extractStatus {
		unextract, extract
	};
	protected String id;// ID
	protected Date createDate;// 创建日期
	protected Date modifyDate;// 修改日期

	
	private String orderSn;// 订单编号
	private Integer orderStatus;// 订单状态
	private Integer paymentStatus;// 支付状态
	private BigDecimal totalPrice;// 总打印价格
	private BigDecimal paymentFee;// 支付费用
	private BigDecimal totalAmount;// 订单总额
	private BigDecimal paidAmount;// 已付金额
	private Integer totalQuantity;// 总打印数量
	
	private String memo;// 附言
	
//	private FetchCode fetchCode; //提取码
	private String fetchCodeId;
	private OrderType orderType;//打印类型:本地打印，远端打印；

	private String memberId;// 会员  (此处不用强引用 实体类)
	//private PaymentConfig paymentConfig;// 支付方式

	private String paymentConfigName;// 支付方式名称
	private Date payDate;//支付时间
	private Integer exStatus; //提取状态
	private String payOrderNumber; //支付流水号
	private String orderTypeCode; //订单类型Code 相框、打印订单...
	private String memberName;
	
	private BigDecimal finalPrice;//使用积分后的价格
	
	private Integer integralNum;//使用的积分个数
	/**
	 * 订单打印设备
	 */
	private String equipmentId;
//	/**
//	 * 订单项
//	 */
//	private Set<PrintOrderItem> orderItemSet;

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
 	public String getPayOrderNumber() {
		return payOrderNumber;
	}

	public void setPayOrderNumber(String payOrderNumber) {
		this.payOrderNumber = payOrderNumber;
	}

	public String getPaymentConfigName() {
		return paymentConfigName;
	}

	public void setPaymentConfigName(String paymentConfigName) {
		this.paymentConfigName = paymentConfigName;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getOrderTypeCode() {
		return orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public Integer getIntegralNum() {
		return integralNum;
	}

	public void setIntegralNum(Integer integralNum) {
		this.integralNum = integralNum;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(BigDecimal paymentFee) {
		this.paymentFee = paymentFee;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFetchCodeId() {
		return fetchCodeId;
	}

	public void setFetchCodeId(String fetchCodeId) {
		this.fetchCodeId = fetchCodeId;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getExStatus() {
		return exStatus;
	}

	public void setExStatus(Integer exStatus) {
		this.exStatus = exStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
