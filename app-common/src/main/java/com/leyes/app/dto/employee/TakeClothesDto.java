package com.leyes.app.dto.employee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TakeClothesDto implements Serializable{
	private int washNumber;
	
	private String name;
	
	private String categoryId;

	private String unit;
	
	private BigDecimal price;//单价
	
	private int activity;
	
	private List<Amount> amounts;
	
	public int getNumber(int i){
		if(amounts!=null){
			 if(i<amounts.size()){
				return amounts.get(i).getNumber();
			 }else{
				 return 1;
			 }
		}
		return 1;
	}
	//获取对应单价
	public BigDecimal getPrice(int j){
		if(amounts!=null && amounts.size()>0){
			int num = amounts.get(j).getNumber();
			return price.multiply(new BigDecimal(num));
		}else{
			return price;
		}
	}
	public int getWashNumber() {
		return washNumber;
	}

	public void setWashNumber(int washNumber) {
		this.washNumber = washNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<Amount> getAmounts() {
		return amounts;
	}
	public void setAmounts(List<Amount> amounts) {
		this.amounts = amounts;
	}
	 
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	 
}
