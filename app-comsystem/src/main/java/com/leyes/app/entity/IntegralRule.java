package com.leyes.app.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.TradeType;

/**
 * 
* @TypeName: IntegralRule 
* @Description: 积分规则(积分基数为1)
* @author：Jingpeng 
* @date 2015年8月5日 下午4:36:01 
*
 */
public class IntegralRule {
	 
	private String id;

	private Date createTime;

	private Date updateTime;
	 
	private TradeType type; 
	 
	private IntegralSourceType sourceType;//0--注册 ,1--填写邀请码,2--消费, 3-兑换
	 
	private BigDecimal ratio;//比率
	 
	private Date startTime;//积分规则开始时间
	 
	private Date endTime;//积分规则结束时间
	
	public IntegralRule() {
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

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	 
	public IntegralSourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(IntegralSourceType sourceType) {
		this.sourceType = sourceType;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
 

	 
}
