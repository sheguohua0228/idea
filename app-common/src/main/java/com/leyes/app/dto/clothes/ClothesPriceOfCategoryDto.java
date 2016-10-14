package com.leyes.app.dto.clothes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClothesPriceOfCategoryDto implements Serializable{

	private String category;
	/**
	 * 普通图标
	 */
	private String urlGrey;
	/**
	 * 被选择时的图标
	 */
	private String urlChecked;
	
	
	private List<ClothesPriceDetailDto> clothesPrices;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrlGrey() {
		return urlGrey;
	}

	public void setUrlGrey(String urlGrey) {
		this.urlGrey = urlGrey;
	}
 

	public String getUrlChecked() {
		return urlChecked;
	}

	public void setUrlChecked(String urlChecked) {
		this.urlChecked = urlChecked;
	}

	public List<ClothesPriceDetailDto> getClothesPrices() {
		return clothesPrices;
	}

	public void setClothesPrices(List<ClothesPriceDetailDto> clothesPrices) {
		this.clothesPrices = clothesPrices;
	}
	public void addData(ClothesPriceDetailDto clothesPriceDetailDto){
		if(clothesPrices==null) 
			clothesPrices=new ArrayList<ClothesPriceDetailDto>();
		clothesPrices.add(clothesPriceDetailDto);
	}
	
}
