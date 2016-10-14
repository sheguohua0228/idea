package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;

public class ClothesPrice extends BaseEntity {
	
	// 上衣 、下装 、大衣、裙装配件、居家用品、鞋包皮具
	public enum Type{
		coat,bottoms,overcoat,accessories,housewares,leather
	}
	
	/** 衣服类型名 */
	private String name;
	/** 父类型 */
	private Integer type;
	/** 原价 */
	private BigDecimal originalPrice;
	/** 折后价 */
	private BigDecimal discountPrice;
	/** 衣服示例图 */
	private String imageUrl;
	/** 描述 */
	private String desc;
	/** 别名 **/
	private String alias;
	
	private Double discountRatio;
	
	private String unit;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Double getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(Double discountRatio) {
		this.discountRatio = discountRatio;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
