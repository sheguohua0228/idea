package com.aplus.lk.clothes.entity;

import java.util.Date;

/**
 * 
 * @ClassName: Employee 实体 - 员工
 * @Description: TODO
 * @author w.gang wgang1130@163.com
 * @date 2015-7-25 下午4:16:31
 *
 */
public class Employee {
	
	private String id;
	private Date createTime;
	private Date updateTime;

	/** 用户名*/
	private String username;
	/** 密码 */
	private String password;
	/** 性别 */
	private int gender; // 0/男 ;1/女
	/** 真实姓名 */
	private String realName;
	/** 联系电话 */
	private String phone;
	/** 任职部门*/
	private String departmentId;
	/** 指定社区 */
	private long addressCenterId;
	/** 员工头像 **/
	private String headImageUrl;
	/** 推送注册ID **/
	private String registrationID;
	/** 优惠码 **/
	private String recommendedCode;
	
	public String getRecommendedCode() {
		return recommendedCode;
	}

	public void setRecommendedCode(String recommendedCode) {
		this.recommendedCode = recommendedCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public long getAddressCenterId() {
		return addressCenterId;
	}

	public void setAddressCenterId(long addressCenterId) {
		this.addressCenterId = addressCenterId;
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

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}
	
}
