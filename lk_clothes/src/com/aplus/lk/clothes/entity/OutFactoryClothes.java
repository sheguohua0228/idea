package com.aplus.lk.clothes.entity;

import java.util.List;

public class OutFactoryClothes {
    
	//订单编号
	private String orderNumber;
	//子条码
	private String childBarCode;
	//衣服名称
	private String clothesName;
	//下单时间 
	private String createTime;
	//用户姓名
	private String userName;
	//用户手机
	private String phoneNumber;
	//小哥姓名
	private String realName;
	//小哥电话
	private String phone;
	//品牌
	private String brand;
	//缩略图
	private String imgUrl;
	//是否外包
	private String outSourcing;
	//配件
	private String partsDesc;
	//小哥备注
	private String desc;
	//工厂备注
	private String factoryRemark;
	//衣物列表
	private List<OutFactoryClothes> clothesList;
	//订单id
	private long clothesOrderId;
	//外包图
	private String outImageUrl;
	//送回地址
	private String sendAddress;
	//出厂备注
	private String outDesc;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getChildBarCode() {
		return childBarCode;
	}
	public void setChildBarCode(String childBarCode) {
		this.childBarCode = childBarCode;
	}
	public String getClothesName() {
		return clothesName;
	}
	public void setClothesName(String clothesName) {
		this.clothesName = clothesName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getClothesOrderId() {
		return clothesOrderId;
	}
	public void setClothesOrderId(long clothesOrderId) {
		this.clothesOrderId = clothesOrderId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getOutSourcing() {
		return outSourcing;
	}
	public void setOutSourcing(String outSourcing) {
		this.outSourcing = outSourcing;
	}
	public String getPartsDesc() {
		return partsDesc;
	}
	public void setPartsDesc(String partsDesc) {
		this.partsDesc = partsDesc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String description) {
		this.desc = description;
	}
	public List<OutFactoryClothes> getClothesList() {
		return clothesList;
	}
	public void setClothesList(List<OutFactoryClothes> clothesList) {
		this.clothesList = clothesList;
	}
	public String getOutImageUrl() {
		return outImageUrl;
	}
	public void setOutImageUrl(String outImageUrl) {
		this.outImageUrl = outImageUrl;
	}
	public String getSendAddress() {
		return sendAddress;
	}
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	public String getFactoryRemark() {
		return factoryRemark;
	}
	public void setFactoryRemark(String factoryRemark) {
		this.factoryRemark = factoryRemark;
	}
	public String getOutDesc() {
		return outDesc;
	}
	public void setOutDesc(String outDesc) {
		this.outDesc = outDesc;
	}
	
}
