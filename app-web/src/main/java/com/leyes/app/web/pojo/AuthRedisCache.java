package com.leyes.app.web.pojo;


import org.springframework.stereotype.Component;

import com.leyes.app.redis.RedisCache;




@SuppressWarnings("rawtypes")
@Component 
public class AuthRedisCache extends RedisCache {
   
	@SuppressWarnings("unchecked")
	public AuthRedisCache() { 
		super();
		this.domainKey="com.leyes.auth_";  
	} 
}

