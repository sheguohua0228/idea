package com.aplus.lk.clothes.entity;

import java.util.Date;
/**
 * 服务的社区地址
 * @author Ethan
 *
 */
public class AddressCenterPO {

	// 社区类型  ： 主城区、机动
	public enum CenterType{
		main, maneuver
	}
	
	private long id;

	private Date createTime;

	private Date updateTime;

	private String longitude;

	private String latitude;

	private String address;

	private Integer serviceRange;

	private Boolean flag;

	private String icon;//社区图片
	/**
	 * 社区名
	 */
	private String name;
	
	private Integer centerType;
	
	private String employeeId;
	public AddressCenterPO(long id, String address, String icon, String name) {
		super();
		this.id = id;
		this.address = address;
		this.icon = icon;
		this.name = name;
	}

	public AddressCenterPO() {
		super();
	}
	
	public Integer getServiceRange() {
		return serviceRange;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setServiceRange(Integer serviceRange) {
		this.serviceRange = serviceRange;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public long getId() {
		return id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCenterType() {
		return centerType;
	}

	public void setCenterType(Integer centerType) {
		this.centerType = centerType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
