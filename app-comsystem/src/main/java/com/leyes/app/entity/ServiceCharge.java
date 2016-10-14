package com.leyes.app.entity;

import java.util.Date;

import com.leyes.app.enums.OrderType;

/**
 * 服务费
* @TypeName: ServiceCharge 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年8月8日 上午11:30:33 
*
 */
public class ServiceCharge {

	private String id;

	private Date createTime;

	private Date updateTime;
	
	private OrderType orderType;
	
	private int expense;//费用
	
	private int sendCondition;//(如为洗衣单则表示件数，值得买则表示订单总价格)

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

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public int getSendCondition() {
		return sendCondition;
	}

	public void setSendCondition(int sendCondition) {
		this.sendCondition = sendCondition;
	}

}
