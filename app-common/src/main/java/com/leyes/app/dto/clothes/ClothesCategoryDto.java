package com.leyes.app.dto.clothes;

import java.io.Serializable;

public class ClothesCategoryDto implements Serializable{

	private String categoryId;
	private String name;
	private String imageUrl;
	private String imageUrlChecked;
	private String remark;
	public ClothesCategoryDto() {
		super();
	}

 

	public ClothesCategoryDto(String categoryId, String name, String imageUrl,
			String imageUrlChecked, String remark) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.imageUrl = imageUrl;
		this.imageUrlChecked = imageUrlChecked;
		this.remark = remark;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrlChecked() {
		return imageUrlChecked;
	}

	public void setImageUrlChecked(String imageUrlChecked) {
		this.imageUrlChecked = imageUrlChecked;
	}

}
