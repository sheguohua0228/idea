package com.leyes.app.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.leyes.app.redis.RedisCache;


@SuppressWarnings("rawtypes")
@Component
@Qualifier("phoneCodeRedisCache")
public class PhoneCodeRedisCache extends RedisCache {

	@SuppressWarnings("unchecked")
	public void setDomainKey(String domainKey){
		this.domainKey = domainKey;
	}
	
}
