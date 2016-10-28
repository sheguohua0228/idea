package com.leyes.app.web.response.shop;

import java.io.Serializable;

public class QueryAppraiseResponse implements Serializable{

	String userName;
	String headImage;
	int star;
	String content;
	public QueryAppraiseResponse() {
		super();
	}
	public QueryAppraiseResponse(String userName, String headImage, int star,String content) {
		super();
		this.userName = userName;
		this.headImage = headImage;
		this.star = star;
		this.content=content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	 
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
	
}
