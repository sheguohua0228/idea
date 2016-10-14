package com.aplus.lk.clothes.bean;

public class MessageContentVo {

	private String content;
	
	private int type;

	private int order;
	
	public MessageContentVo(String content, int type, int order) {
		super();
		this.content = content;
		this.type = type;
		this.order = order;
	}

	public MessageContentVo() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
