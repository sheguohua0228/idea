package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.WashClothes;
import com.aplus.lk.clothes.mapper.WashClothesMapper;
import com.aplus.lk.clothes.service.WashClothesService;

@Service
public class WashClothesServiceImpl implements WashClothesService{

	@Autowired
	private WashClothesMapper washClothesMapper;
	
	@Override
	public WashClothes queryByChildBarCode(String childBarCode) {
		return washClothesMapper.queryByChildBarCode(childBarCode);
	}

	@Override
	public boolean updateWashStatus(long clothesOrderId, String childBarCode,int clothesWashStatus) {
		Map<String,Object> parameterMap=new HashMap<String, Object>();
		parameterMap.put("washStatus", clothesWashStatus);
		parameterMap.put("clothesOrderId", clothesOrderId);
		parameterMap.put("childBarCode", childBarCode);
		int result = washClothesMapper.updateWashStatus(parameterMap);
		
		return result>0?true:false;
	}

	@Override
	public long queryWashedClothes(long clothesOrderId,int status) {
		Map<String,Object> parameterMap=new HashMap<String, Object>();
		parameterMap.put("washStatus", status);
		parameterMap.put("clothesOrderId", clothesOrderId);
		return washClothesMapper.queryWashedClothes(parameterMap);
	}

	@Override
	public long queryWashedClothesCount(long clothesOrderId) {
		return washClothesMapper.queryWashedClothesCount(clothesOrderId);
	}
	@Override
	public void updateClothesStatus(long clothesOrderId, int crrentStatus,
			int nextStatus) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("clothesOrderId", clothesOrderId);
		parameterMap.put("crrentStatus", crrentStatus);
		parameterMap.put("nextStatus", nextStatus);
		washClothesMapper.updateClothesStatus(parameterMap);
	}

	@Override
	public WashClothes queryClothesByPhoneBrand(String phone,String clothesName,String brand) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("phone", phone);
		parameterMap.put("brand", brand);
		parameterMap.put("clothesName", clothesName);
		return washClothesMapper.queryClothesByPhoneBrand(parameterMap);
	}

	@Override
	public Pager querySortingClothes(Pager pager,Long[] ids, String barCode,
			String userName,String phoneNumber, String[] washType) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("barCode", barCode);
		parameterMap.put("userName", userName);
		parameterMap.put("phoneNumber", phoneNumber);
		parameterMap.put("washTypes", washType);
		parameterMap.put("ids", ids);
		parameterMap.put("firstResult",
				(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());

		pager.setDataList(washClothesMapper.querySortingClothes(parameterMap));
		pager.setTotalRecords(washClothesMapper.querySortingClothesCount(parameterMap));

		return pager;
	}
	
}
