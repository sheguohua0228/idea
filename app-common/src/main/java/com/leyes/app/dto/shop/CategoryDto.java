package com.leyes.app.dto.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryDto implements Serializable{

	private String categoryId;
	
	private String name;
	
	private String image;
	
	private List<SecondCategoryDto> categories;

	public CategoryDto(String categoryId, String name, String image) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.image = image;
	}

	public CategoryDto() {
		super();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

 
	public List<SecondCategoryDto> getCategories() {
		if(categories==null){
			categories=new LinkedList<SecondCategoryDto>();
		}
		return categories;
	}

	public void setCategories(List<SecondCategoryDto> categories) {
		this.categories = categories;
	}

	public void addData(SecondCategoryDto dto){
		if(categories==null){
			categories=new LinkedList<SecondCategoryDto>();
		}
		categories.add(dto);
	}
}
