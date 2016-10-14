package com.leyes.app.util;

import com.leyes.app.redis.RedisObject;

public class VerifyCodeCache extends RedisObject{

	private String phone;
	
	private String code;

	public VerifyCodeCache(String phone, String code) {
		super();
		this.phone = phone;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public VerifyCodeCache(String code) {
		super();
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public VerifyCodeCache() {}
	
	
}
