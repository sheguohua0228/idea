package com.leyes.app.web.request.shop;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryGoodsRequest {
	
	@ApiModelProperty(value="分类父ID")
	private String categoryId;
	@ApiModelProperty(value="子分类ID，默认-1")
	private String c_categoryId;
	@ApiModelProperty(value="筛选类型 0:价格 ,1:销量  (默认-1)")
	private int sortCode;
	@ApiModelProperty(value="排序，顺序： ASC。倒序：DESC（默认） ")
	private String sort;//ASC DESC
	@ApiModelProperty(value="页数，默认为0")
	private int page;
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getC_categoryId() {
		return c_categoryId;
	}

	public void setC_categoryId(String c_categoryId) {
		this.c_categoryId = c_categoryId;
	}

	public int getSortCode() {
		return sortCode;
	}

	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getPage() {
		return page < 0 ? 0 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}
