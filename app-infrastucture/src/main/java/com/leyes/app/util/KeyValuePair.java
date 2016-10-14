
package com.leyes.app.util;

/**
 * Key,Value类
* @TypeName: KeyValuePair 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月14日 下午2:02:18 
* 
* @param <T>
* @param <R>
 */
public class KeyValuePair<T extends Object,R extends Object> {
	 private T key;
	 private R value;
	public T getKey() {
		return key;
	}
	public void setKey(T key) {
		this.key = key;
	}
	public R getValue() {
		return value;
	}
	public void setValue(R value) {
		this.value = value;
	}
	 
	 
}
