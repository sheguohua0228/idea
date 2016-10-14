package com.leyes.app.sms.bean;

public class SendResultBean {

	private int result;
	private String errMsg;

	public SendResultBean() {
		super();
	}

	public SendResultBean(int result, String errMsg) {
		super();
		this.result = result;
		this.errMsg = errMsg;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String toString(){
		return "发送短信响应状态码为："+result+"\n 响应结果描述为:"+errMsg;
	}
}
