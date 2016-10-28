package com.leyes.app.web.utils;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Rest接口返回数据的包装器，主要用于通知是否操作（调用）成功与否（包含错误信息或Code）
 * 
 * @TypeName: ReturnResult
 * @Description: TODO
 * @author：Jingpeng
 * @date 2016年7月7日 上午10:27:28
 * 
 */
@ApiModel
public class ReturnResult implements Serializable {

	/** 变量 serialVersionUID(long) */
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS = 200;
	public static final int FAILURE = 300;
	public static final int FAULURE_INTERNAL_ERROR = 301;
	public static final int FAULURE_PARAMTER_NULL_ERROR=302;
	public static final int FAULURE_USER_NOT_LOGIN = 303;
	public static final int FAULURE_PARAMTER_REQUIRED=304;
	public static final int FAULURE_NEED_DISPLAY=305;

	@ApiModelProperty(value="响应状态码")
	private int code;
	@ApiModelProperty(value="响应描述")
	private String status;
	@ApiModelProperty(value="数据对象")
	private Object data;

	public Object getData() {
		return this.data;
	}

	/**
	 * @param obj
	 *            无用的参数，防止在JSON序列化时调用此方法，调用时请置为NULL.
	 * @return
	 */
	public Object getData(Object obj) {
		return this.data;
	}

	public void setData(Object obj) {
		this.data = obj;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	 

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static ReturnResult SUCCESS() {
		return SUCCESS("SUCCESS");
	}

	public static ReturnResult SUCCESS(Object Data) {
		return SUCCESS("SUCCESS", Data);
	}

	/**
	 * @param msg
	 *            成功信息
	 * @return
	 */
	public static ReturnResult SUCCESS(String msg) {
		ReturnResult rp = new ReturnResult();
		rp.setCode(SUCCESS);
		rp.status = msg;
		return rp;
	}

	/**
	 * @param msg
	 *            成功信息
	 * @return
	 */
	public static ReturnResult SUCCESS(int code, Object data) {
		ReturnResult rp = new ReturnResult();
		rp.setCode(code);
		rp.status = "SUCCESS";
		rp.setData(data);
		return rp;
	}

	/**
	 * @param msg
	 *            成功信息
	 * @param data
	 *            返回数据
	 * @return
	 */
	public static ReturnResult SUCCESS(String msg, Object data) {
		ReturnResult rp = new ReturnResult();
		rp.setCode(SUCCESS);
		rp.status = msg;
		rp.setData(data);
		return rp;
	}

	/**
	 * @param msg
	 *            错误消息
	 * @return
	 */
	public static ReturnResult FAILUER(String msg) {
		ReturnResult rp = new ReturnResult();
		rp.setCode(FAILURE);
		rp.status = msg;
		return rp;
	}

	/**
	 * @param failureCode
	 *            错误编码
	 * @param msg
	 *            错误消息
	 * @return
	 */
	public static ReturnResult FAILUER(int failureCode, String msg) {
		ReturnResult rp = FAILUER(msg);
		rp.code = failureCode;
		return rp;
	}
}
