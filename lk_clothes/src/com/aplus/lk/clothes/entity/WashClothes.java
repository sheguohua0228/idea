package com.aplus.lk.clothes.entity;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: WashClothes
 * @Description: TODO 实体类 - 实际清洗的衣服信息
 * @author w.gang wgang1130@163.com
 * @date 2015-7-16 下午12:08:00
 * 
 */
public class WashClothes extends BaseEntity {

	/** 子条码 */
	private String childBarCode;
	/** 衣服数量 */
	private int number;
	/** 描述 */
	private String description;
	/** 洗衣订单ID */
	private long clothesOrderId;
	/** 类型名 */
	private String clothesName;

	private String imageUrl; // 条形码

	private String unit;// 单位

	private int washStatus;// 洗涤状态 0 未洗涤 1洗涤中 2返洗中 3 已洗涤 4派送中5已完成
	
	private int amount;// 尺寸的数量
	
	private String size;// 尺寸
	
	private BigDecimal totalPrice;
	
	private String brand;
	
	private String color;
	
	private String defective;
	
	private String custom;//自定义
	/**
	 *洗涤方式 
	 */
	private String washType;
	/**
	 * 配件说明
	 */
	private String partsDesc;
	/**
	 * 分拣说明
	 */
	private String sortingDesc;
	/**
	 * 洗涤说明
	 */
	private String washDesc;
	/**
	 * 烘干说明
	 */
	private String dryDesc;
	/**
	 * 熨烫说明
	 */
	private String ironingDesc;
	/**
	 * 整理说明
	 */
	private String arrangeDesc;
	/**
	 * 质检说明
	 */
	private String qcDesc;
	
	/*
	 * 临时属性 为了查询用户
	 * */
	private String username;
	
	private String phoneNumber;
	
	private String isOut;
	
	public String getChildBarCode() {
		return childBarCode;
	}

	public void setChildBarCode(String childBarCode) {
		this.childBarCode = childBarCode;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDefective() {
		return defective;
	}

	public void setDefective(String defective) {
		this.defective = defective;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClothesName() {
		return clothesName;
	}

	public void setClothesName(String clothesName) {
		this.clothesName = clothesName;
	}

	public long getClothesOrderId() {
		return clothesOrderId;
	}

	public void setClothesOrderId(long clothesOrderId) {
		this.clothesOrderId = clothesOrderId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getWashStatus() {
		return washStatus;
	}

	public void setWashStatus(int washStatus) {
		this.washStatus = washStatus;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPartsDesc() {
		return partsDesc;
	}

	public void setPartsDesc(String partsDesc) {
		this.partsDesc = partsDesc;
	}

	public String getSortingDesc() {
		return sortingDesc;
	}

	public void setSortingDesc(String sortingDesc) {
		this.sortingDesc = sortingDesc;
	}

	public String getWashDesc() {
		return washDesc;
	}

	public void setWashDesc(String washDesc) {
		this.washDesc = washDesc;
	}

	public String getDryDesc() {
		return dryDesc;
	}

	public void setDryDesc(String dryDesc) {
		this.dryDesc = dryDesc;
	}

	public String getIroningDesc() {
		return ironingDesc;
	}

	public void setIroningDesc(String ironingDesc) {
		this.ironingDesc = ironingDesc;
	}

	public String getArrangeDesc() {
		return arrangeDesc;
	}

	public void setArrangeDesc(String arrangeDesc) {
		this.arrangeDesc = arrangeDesc;
	}

	public String getQcDesc() {
		return qcDesc;
	}

	public void setQcDesc(String qcDesc) {
		this.qcDesc = qcDesc;
	}

	public String getWashType() {
		return washType;
	}

	public void setWashType(String washType) {
		this.washType = washType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIsOut() {
		return isOut;
	}

	public void setIsOut(String isOut) {
		this.isOut = isOut;
	}

}
