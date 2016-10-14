package com.leyes.app.entity;

import java.util.Date;

/**
 * 商品评价
* @TypeName: GoodsAppraise 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月26日 下午5:00:03 
*
 */
public class GoodsAppraise {

	private String id;

	private Date createTime;
	
	private Date updateTime;
	
	private String orderId;
	
	private String goodsId;
	
	private String userId;
	
	private int serviceStar;
	
	private String content;

	private boolean isShow;//是否显示
	
	private boolean isHot;//是否热评
	
	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getServiceStar() {
		return serviceStar;
	}

	public void setServiceStar(int serviceStar) {
		this.serviceStar = serviceStar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
