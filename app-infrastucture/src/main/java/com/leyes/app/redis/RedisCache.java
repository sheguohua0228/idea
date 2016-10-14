package com.leyes.app.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.alibaba.fastjson.JSONObject;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.util.KeyValuePair;

/**
 * Redis缓存实现抽象类
 * 
 * @TypeName: RedisCache
 * @Description: TODO
 * @author：Jingpeng
 * @date 2016年7月14日 下午2:02:43
 * 
 * @param <T>
 */
public abstract class RedisCache<T> implements IRedisCache<RedisObject> {

	/**
	 * 默认域名
	 */
	protected String domainKey = "default_leyes_";
	/**
	 * 默认过期时间60分钟（以秒为单位）
	 */
	protected long defaultExpireTime = 60 * 60;

	@Autowired
	protected RedisTemplate<String, String> redisTemplate;

	@Override
	public void set(String token, RedisObject t) {
		set(token, t, defaultExpireTime);
	}

	@Override
	public void setNoExpire(String token, RedisObject t) {
		set(token, t, 0);
	}

	@Override
	public void set(final String token, final RedisObject t, final long seconds) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {

				String tStr = JSONObject.toJSONString(t);
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(domainKey + token);
				byte[] value = serializer.serialize(tStr);
				connection.set(key, value);
				if (seconds > 0) {
					connection.expire(key, seconds);
				}
				return true;
			}
		});

	}

	@SuppressWarnings("hiding")
	@Override
	public <T> void updateActiveTime(final String token, final Class<T> clazz) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisObject obj = (RedisObject) get(token, clazz);
				if (obj != null) {
					obj.setLastActiveDateTime(System.currentTimeMillis());
					set(token, obj);
				}
				return true;
			}
		});

	}

	@Override
	public void Remove(final String token) {
		redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(domainKey + token);
				if (connection.exists(key)) {
					connection.del(key);
				}
				return true;
			}
		});
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T get(final String token, final Class<T> clazz) {
		T t = redisTemplate.execute(new RedisCallback<T>() {
			public T doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(domainKey + token);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String json = serializer.deserialize(value);
					T obj = JSONObject.parseObject(json, clazz);
					return obj;
				}
				return null;
			}
		});
		return t;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<KeyValuePair<String, T>> get(final int pageIndex,
			final int pageSize, final Class<T> clazz) {
		List<KeyValuePair<String, T>> list = redisTemplate
				.execute(new RedisCallback<List<KeyValuePair<String, T>>>() {
					public List<KeyValuePair<String, T>> doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						List<KeyValuePair<String, T>> lists = getAll(clazz);
						int start = (pageIndex - 1) * pageSize;
						int toIndex = (start + pageSize) > lists.size() ? lists
								.size() : start + pageSize;
						List<KeyValuePair<String, T>> list = lists.subList(
								start, toIndex);
						return list;

					}
				});
		return list;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<KeyValuePair<String, T>> getAll(final Class<T> clazz) {
		List<KeyValuePair<String, T>> list = redisTemplate
				.execute(new RedisCallback<List<KeyValuePair<String, T>>>() {
					public List<KeyValuePair<String, T>> doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						List<KeyValuePair<String, T>> lists = new ArrayList<KeyValuePair<String, T>>();
						RedisSerializer<String> serializer = getRedisSerializer();
						byte[] pattern = serializer.serialize(domainKey + "*");
						Set<byte[]> sets = connection.keys(pattern);
						Iterator<byte[]> its = sets.iterator();
						while (its.hasNext()) {
							byte[] key = its.next();
							byte[] value = connection.get(key);
							String keyS = serializer.deserialize(key).replace(
									domainKey, "");
							String valueS = serializer.deserialize(value);
							// System.out.println("keyS:"+keyS);
							// System.out.println("valueS:"+valueS);
							T obj = JSONObject.parseObject(valueS, clazz);
							KeyValuePair<String, T> kv = new KeyValuePair<String, T>();
							kv.setKey(keyS);
							kv.setValue(obj);
							lists.add(kv);

						}
						return lists;

					}
				});
		return list;
	}

	@Override
	public <T> List<KeyValuePair<String, T>> get(final int pageIndex,
			final int pageSize, final RedisAuthType redisEnum,
			final Class<T> clazz) {
		List<KeyValuePair<String, T>> list = redisTemplate
				.execute(new RedisCallback<List<KeyValuePair<String, T>>>() {
					public List<KeyValuePair<String, T>> doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						List<KeyValuePair<String, T>> lists = getAll(clazz,
								redisEnum);
						int start = (pageIndex - 1) * pageSize;
						int toIndex = (start + pageSize) > lists.size() ? lists
								.size() : start + pageSize;
						List<KeyValuePair<String, T>> list = lists.subList(
								start, toIndex);
						return list;

					}
				});
		return list;
	}

	@Override
	public <T> List<KeyValuePair<String, T>> getAll(final Class<T> clazz,
			final RedisAuthType redisEnum) {
		List<KeyValuePair<String, T>> list = redisTemplate
				.execute(new RedisCallback<List<KeyValuePair<String, T>>>() {
					public List<KeyValuePair<String, T>> doInRedis(
							RedisConnection connection)
							throws DataAccessException {
						List<KeyValuePair<String, T>> lists = new ArrayList<KeyValuePair<String, T>>();
						RedisSerializer<String> serializer = getRedisSerializer();
						byte[] pattern = serializer.serialize(domainKey + "*");
						Set<byte[]> sets = connection.keys(pattern);
						Iterator<byte[]> its = sets.iterator();
						while (its.hasNext()) {
							byte[] key = its.next();
							byte[] value = connection.get(key);
							String keyS = serializer.deserialize(key).replace(
									domainKey, "");
							String valueS = serializer.deserialize(value);
							// System.out.println("keyS:"+keyS);
							// System.out.println("valueS:"+valueS);
							T obj = JSONObject.parseObject(valueS, clazz);
							if (obj instanceof RedisObject) {
								if (((RedisObject) obj).getRedisAuthTypeEnum()
										.equals(redisEnum)) {
									KeyValuePair<String, T> kv = new KeyValuePair<String, T>();
									kv.setKey(keyS);
									kv.setValue(obj);
									lists.add(kv);
								}
							}

						}
						return lists;

					}
				});
		return list;
	}

	@Override
	public RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}

}
