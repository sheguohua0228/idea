package com.leyes.app.web.response.business;

import java.util.List;

import com.leyes.app.dto.shop.BusinessDto;
import com.leyes.app.dto.shop.GoodsDto;


public class QueryBusinessInfoResponse{

	 private BusinessDto businessInfo;
	 
	 private List<GoodsDto> goodsList;

	public BusinessDto getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessDto businessInfo) {
		this.businessInfo = businessInfo;
	}

	public List<GoodsDto> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsDto> goodsList) {
		this.goodsList = goodsList;
	}
	 
	 
}
