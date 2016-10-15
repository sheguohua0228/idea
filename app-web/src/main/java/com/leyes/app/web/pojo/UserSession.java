package com.leyes.app.web.pojo;

import com.leyes.app.redis.RedisObject;


public class UserSession extends RedisObject{

	// 用户ID
	String userId;
	 
	String employeeId;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	 
	
}
