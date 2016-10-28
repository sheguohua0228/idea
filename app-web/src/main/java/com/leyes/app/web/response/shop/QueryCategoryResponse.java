package com.leyes.app.web.response.shop;

import java.util.List;

import com.leyes.app.dto.shop.CategoryDto;

public class QueryCategoryResponse {
	
	private String categoryId;

	private String name;

	private String image;

	List<CategoryDto> categories;

	public QueryCategoryResponse() {
		super();
	}

	public QueryCategoryResponse(String categoryId, String name, String image) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.image = image;
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

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

}
