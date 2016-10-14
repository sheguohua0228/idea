package com.leyes.app.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.leyes.app.enums.GoodsOrderSatatus;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.ValidStatus;

public class GoodsOrder {

	private String id;

	private Date createTime;
	
	private Date updateTime;
	
	private String userId;
	
	private String addressId;
	
	private GoodsOrderSatatus orderStatus;//订单状态
	
	private ValidStatus status; 
	
	private String communityId;
	
	private BigDecimal price;//订单总价

	private BigDecimal finalPrice;//实际支付金额

	private int integralNumber;// 使用的积分个数

	private BigDecimal balance;// 使用了多少余额

	private String orderNumber;

	private PayStatus payStatus; // 支付状态（0-未支付，1-支付成功 ,2-支付失败）

	private Date payTime;

	private PayType payType;// 0-支付宝，1-微信，2-现金,3-积分支付,4余额支付 5卡券支付
	 
	private String cardId;// 优惠卡id

	private boolean appraise;//是否评价
	
	private int freight;//运费
	
	private Date deliveryTime;//送回时间
	
	private String remark;//小哥的备注
	
	/**/
	private List<GoodsOrderItem> goodsItems;
	
	private String operator;
	
	private String finisher;
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public GoodsOrderSatatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(GoodsOrderSatatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ValidStatus getStatus() {
		return status;
	}

	public void setStatus(ValidStatus status) {
		this.status = status;
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

 
	public int getIntegralNumber() {
		return integralNumber;
	}

	public void setIntegralNumber(int integralNumber) {
		this.integralNumber = integralNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public PayStatus getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(PayStatus payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public boolean isAppraise() {
		return appraise;
	}

	public void setAppraise(boolean appraise) {
		this.appraise = appraise;
	}

	public List<GoodsOrderItem> getGoodsItems() {
		return goodsItems;
	}

	public void setGoodsItems(List<GoodsOrderItem> goodsItems) {
		this.goodsItems = goodsItems;
	}

	public int getFreight() {
		return freight;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFinisher() {
		return finisher;
	}

	public void setFinisher(String finisher) {
		this.finisher = finisher;
	}
	
	
}
