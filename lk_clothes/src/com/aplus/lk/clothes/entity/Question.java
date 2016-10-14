package com.aplus.lk.clothes.entity;

import java.util.Date;

public class Question {

	private long id;
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private String title;// 问题标题
	private Integer questionType;// 问题类型（0洗衣、1打印,2门票。3其他）
	private String description;// 问题描述

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	 

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
