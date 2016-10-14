package com.aplus.lk.clothes.bean;

/**
 * 
 * @ClassName: AjaxResult
 * @Description: TODO JAVABean ajax请求返回对象
 * @author w.gang wgang1130@163.com
 * @date 2015-7-17 上午11:38:34
 * 
 */
public class AjaxResult {

	public enum Status {
		success, error
	}
	/** 状态 */
	private Status status;
	/** 消息 */
	private String message;
	/** 数据 */
	private Object data;
	
	public AjaxResult() {
	}
	
	public AjaxResult(Status status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public AjaxResult(Status status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}



	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
