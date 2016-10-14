package com.aplus.lk.clothes.bean;

import java.util.List;

import com.aplus.lk.clothes.entity.WashClothes;

public class WashClothesModle {

	public WashClothesModle() {
		
	}

	public WashClothesModle(List<WashClothes> washClothesList){
		this.washClothesList = washClothesList;
	}
	
	private List<WashClothes> washClothesList;

	public List<WashClothes> getWashClothesList() {
		return washClothesList;
	}

	public void setWashClothesList(List<WashClothes> washClothesList) {
		this.washClothesList = washClothesList;
	}

}
