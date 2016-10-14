package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 
 * @ClassName: WashStatus
 * @Description: TODO 实体 - 洗衣状态详情
 * @author w.gang wgang1130@163.com
 * @date 2015-7-16 下午1:59:55
 *
 */
public class WashStatus extends BaseEntity{
	
	/** 订单状态 */
	private Integer clothesOrderStatus;
	/** 订单ID */
	private long clothesOrderId;
	/** 洗衣图片 */
	private String clothesImageUrl;
	//外包图片
	private String outImageUrl;
	/** 描述 */
	private String description;
	/** 取回或送回时间 */
    private Date getOrBackTime;
    /** 操作者**/
    private String operator;
	public Integer getClothesOrderStatus() {
		return clothesOrderStatus;
	}

	public void setClothesOrderStatus(Integer clothesOrderStatus) {
		this.clothesOrderStatus = clothesOrderStatus;
	}

	public long getClothesOrderId() {
		return clothesOrderId;
	}

	public void setClothesOrderId(long clothesOrderId) {
		this.clothesOrderId = clothesOrderId;
	}

	public String getClothesImageUrl() {
		return clothesImageUrl;
	}

	public void setClothesImageUrl(String clothesImageUrl) {
		this.clothesImageUrl = clothesImageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getGetOrBackTime() {
		return getOrBackTime;
	}

	public void setGetOrBackTime(Date getOrBackTime) {
		this.getOrBackTime = getOrBackTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOutImageUrl() {
		return outImageUrl;
	}

	public void setOutImageUrl(String outImageUrl) {
		this.outImageUrl = outImageUrl;
	}
	
}
