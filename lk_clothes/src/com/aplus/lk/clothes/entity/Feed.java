package com.aplus.lk.clothes.entity;

import java.util.Date;

public class Feed {

	private long id;
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private long questionId;// 问题id
	private String answer;// 问题答案
	private boolean idHandle;// 是否处理（默认未处理）
	private String userId;// 用户编号
	private String orderNum;// 订单id
	private String questionDetail; //用户问题描述
	private String phoneNo;
	private Integer feedType;//反馈来源 0 app 1 电话
	private Member member;
	private Question question;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public boolean getIdHandle() {
		return idHandle;
	}

	public void setIdHandle(boolean idHandle) {
		this.idHandle = idHandle;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getQuestionDetail() {
		return questionDetail;
	}

	public void setQuestionDetail(String questionDetail) {
		this.questionDetail = questionDetail;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getFeedType() {
		return feedType;
	}

	public void setFeedType(Integer feedType) {
		this.feedType = feedType;
	}

	 

}
