package com.aplus.lk.clothes.bean;

import java.util.List;

public class ClothesPriceParams {
	
	private List<ModifyClothes> washClothesList;
	private Long orderId;
	
	public ClothesPriceParams() {

	}

	 

	public ClothesPriceParams(List<ModifyClothes> washClothesList, Long orderId) {
		super();
		this.washClothesList = washClothesList;
		this.orderId = orderId;
	}



	public List<ModifyClothes> getWashClothesList() {
		return washClothesList;
	}

	public void setWashClothesList(List<ModifyClothes> washClothesList) {
		this.washClothesList = washClothesList;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
