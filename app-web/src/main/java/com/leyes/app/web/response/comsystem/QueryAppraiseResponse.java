package com.leyes.app.web.response.comsystem;

public class QueryAppraiseResponse {

	private String appraiseId;

	private String content;
	private int serviceStar;
	private long appraiseTime;
	public String getAppraiseId() {
		return appraiseId;
	}
	public void setAppraiseId(String appraiseId) {
		this.appraiseId = appraiseId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getServiceStar() {
		return serviceStar;
	}
	public void setServiceStar(int serviceStar) {
		this.serviceStar = serviceStar;
	}
	public long getAppraiseTime() {
		return appraiseTime;
	}
	public void setAppraiseTime(long appraiseTime) {
		this.appraiseTime = appraiseTime;
	}
	
}
