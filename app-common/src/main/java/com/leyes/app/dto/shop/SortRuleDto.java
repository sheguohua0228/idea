package com.leyes.app.dto.shop;

import java.io.Serializable;

public class SortRuleDto implements Serializable{

	private String displayName;
	
	private int sortCode;//
	
	private String sort;// asc desc

	public SortRuleDto() {
		super();
	}

	public SortRuleDto(String displayName, int sortCode, String sort) {
		super();
		this.displayName = displayName;
		this.sortCode = sortCode;
		this.sort = sort;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	
	
	
}
