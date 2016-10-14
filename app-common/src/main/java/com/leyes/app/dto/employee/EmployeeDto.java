package com.leyes.app.dto.employee;

import java.io.Serializable;


public class EmployeeDto implements Serializable{

	private String employeeId;
	/** 真实姓名 */
	private String realName;
	/** 联系电话 */
	private String phone;
	/** 员工头像 **/
	private String headImageUrl;
	/** 推送Token **/
	private String deviceToken;
	
	private int group;
	
	private String recommendCode;
	public EmployeeDto() {
		super();
	}
	public EmployeeDto(String employeeId, String realName, String phone,
			String headImageUrl, String deviceToken) {
		super();
		this.employeeId = employeeId;
		this.realName = realName;
		this.phone = phone;
		this.headImageUrl = headImageUrl;
		this.deviceToken = deviceToken;
	}
	 
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public String getHeadImageUrl() {
		return headImageUrl;
	}
	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getRecommendCode() {
		return recommendCode;
	}
	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}
	 
	
}
