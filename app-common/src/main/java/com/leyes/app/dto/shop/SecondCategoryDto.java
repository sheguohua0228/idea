package com.leyes.app.dto.shop;

import java.io.Serializable;

public class SecondCategoryDto implements Serializable{

	private String c_categoryId;
	
	private String c_name;
	
 

	public String getC_categoryId() {
		return c_categoryId;
	}

	public void setC_categoryId(String c_categoryId) {
		this.c_categoryId = c_categoryId;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	 
	public SecondCategoryDto() {
		super();
	}

	public SecondCategoryDto(String c_categoryId, String c_name) {
		super();
		this.c_categoryId = c_categoryId;
		this.c_name = c_name;
	}

	 
	
}
