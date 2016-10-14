package com.aplus.lk.clothes.utils;

/**
 * Bean类 - 极光推送结果
 * 
 * @Title: PushResult.java
 * @Package com.aplus.lk.bean
 * @Description: TODO
 * @author w.gang
 * @date 2015-1-14 下午3:14:45
 * @version V1.0
 */
public class PushResult {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	public static final int CODE_1011 = 1011;//没有满足条件的推送目标
	public static final int CODE_1005 = 1005;//推送内容超过推送限制000个字符
	public static final int CODE_1030 = 1030;//服务器请求超时
	
	/**
	 * 发送状态：成功 、 失败
	 */
	private String status;
	/**
	 * 状态码
	 */
	private int code;
	
	/**
	 * 返回消息
	 */
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
