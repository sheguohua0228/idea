package com.leyes.app.redis;

import java.io.Serializable;

import com.leyes.app.enums.RedisAuthType;


public class RedisObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 认证信息类型
	 */
	private RedisAuthType redisAuthTypeEnum;
	
	/**
	 * 最近活动时间
	 */
	private long lastActiveDateTime;

	public long getLastActiveDateTime() {
		return lastActiveDateTime;
	}

	public void setLastActiveDateTime(long lastActiveDateTime) {
		this.lastActiveDateTime = lastActiveDateTime;
	}

	public RedisAuthType getRedisAuthTypeEnum() {
		return redisAuthTypeEnum;
	}

	public void setRedisAuthTypeEnum(RedisAuthType redisAuthTypeEnum) {
		this.redisAuthTypeEnum = redisAuthTypeEnum;
	}

 
	
	
	

}
