package com.leyes.app.redis;

import java.util.List;

import org.springframework.data.redis.serializer.RedisSerializer;

import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.util.KeyValuePair;
/**
 * Redis缓存接口
* @TypeName: IRedisCache 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月14日 下午1:54:55 
* 
* @param <T>
 */
public interface IRedisCache<T extends RedisObject> {
	
	/**
	 * 设置缓存默认60分钟删除
	* @Title: set 
	* @Description: TODO
	* @param token
	* @param t
	* @return void    
	* @throws
	 */
	void set(String token,T t);
	
	/**
	 * 设置缓存 无过期时间
	* @Title: setNoExpire 
	* @Description: TODO
	* @param token
	* @param t
	* @return void    
	* @throws
	 */
	void setNoExpire(String token,T t);
	/**
	 * 设置缓存
	* @Title: set 
	* @Description: TODO
	* @param token
	* @param t
	* @param seconds 为0永不过期
	* @return void    
	* @throws
	 */
	void set(String token,T t,long seconds);
	
	/**
	 * 更新缓存信息
	* @Title: updateActiveTime 
	* @Description: TODO
	* @param token
	* @param t
	* @return void    
	* @throws
	 */
	@SuppressWarnings("hiding")
	<T> void updateActiveTime(String token,Class<T> clazz);
	 
	/**
	 * 从缓存中去除
	* @Title: Remove 
	* @Description: TODO
	* @param token
	* @return void    
	* @throws
	 */
	void Remove(String token);
	 
	 
	/**
	 * 获取缓存
	* @Title: get 
	* @Description: TODO
	* @param token
	* @return
	* @return T    
	* @throws
	 */
	@SuppressWarnings("hiding")
	<T> T get(String token,Class<T> clazz);
	/**
	 * 分页获取所有缓存对象
	* @Title: get 
	* @Description: TODO
	* @param pageIndex
	* @param pageSize
	* @param clazz
	* @return
	* @return List<KeyValuePair<String,T>>    
	* @throws
	 */
	@SuppressWarnings("hiding")
	<T> List<KeyValuePair<String,T>> get(int pageIndex,int pageSize,Class<T> clazz);
	
	/**
	 * 根据类型获取分页所有缓存对象
	* @Title: get 
	* @Description: TODO
	* @param pageIndex
	* @param pageSize
	* @param redisEnum
	* @param clazz
	* @return
	* @return List<KeyValuePair<String,T>>    
	* @throws
	 */
	@SuppressWarnings("hiding")
	<T> List<KeyValuePair<String,T>> get(int pageIndex,int pageSize,RedisAuthType redisEnum,Class<T> clazz);
	
	/**
	 * 得到所有缓存对象
	* @Title: getAll 
	* @Description: TODO
	* @param clazz
	* @return
	* @return List<KeyValuePair<String,T>>    
	* @throws
	 */
	@SuppressWarnings("hiding")
	<T> List<KeyValuePair<String,T>> getAll(Class<T> clazz);
	
	/**
	 * 根据类型得到所有缓存对象
	* @Title: getAll 
	* @Description: TODO
	* @param clazz
	* @param redisEnum
	* @return
	* @return List<KeyValuePair<String,T>>    
	* @throws
	 */
	@SuppressWarnings("hiding")
	<T> List<KeyValuePair<String,T>> getAll(Class<T> clazz,RedisAuthType redisEnum);
	
	/**
	 * 获取 RedisSerializer 
	* @Title: getRedisSerializer 
	* @Description: TODO
	* @return
	* @return RedisSerializer<String>    
	* @throws
	 */
	RedisSerializer<String> getRedisSerializer();
}
