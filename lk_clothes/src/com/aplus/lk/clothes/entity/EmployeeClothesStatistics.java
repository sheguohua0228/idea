package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 小工洗衣量统计
* @TypeName: EmployeeClothesStatistics 
* @Description: TODO
* @author：Jingpeng 
* @date 2015年12月16日 下午8:37:37 
*
 */
public class EmployeeClothesStatistics {

	private Date createTime;
	
	private BigDecimal price;
	
	private String real_name;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	
}
