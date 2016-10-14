package com.leyes.app.dto.comsystem;

import java.io.Serializable;
import java.math.BigDecimal;


public class IntegralRuleDto implements Serializable{

	private int type;// 积分类型（0--获取 1 - 使用）

	private int sourceType;// 0--注册 ,1--填写邀请码,2--消费, 3-兑换

	private BigDecimal ratio;// 比率

	public IntegralRuleDto(int type, int sourceType, BigDecimal ratio) {
		super();
		this.type = type;
		this.sourceType = sourceType;
		this.ratio = ratio;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	 

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public IntegralRuleDto() {
		super();
	}

}
