package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.bean.ModifyClothes;
import com.aplus.lk.clothes.entity.WashClothes;

public interface WashClothesMapper {
	
	public List<WashClothes> queryWashClothesByOrderId(long orderId);
	
	public int update(WashClothes washClothes);
	
	public WashClothes queryByChildBarCode(String childBarCode);
	
	public int updateWashStatus(Map<String,Object> parameterMap);
	
	public long queryWashedClothes(Map<String,Object> parameterMap);
	
	public long queryWashedClothesCount(long clothesOrderId);
	
	public void updateClothesStatus(Map<String, Object> parameterMap);
	
	public WashClothes queryClothesByPhoneBrand(Map<String, Object> parameterMap);

	public List<WashClothes> querySortingClothes(Map<String, Object> parameterMap);
	
	public int querySortingClothesCount(Map<String, Object> parameterMap);
	
	public void modifyClothesPrice(List<ModifyClothes> washClothesList);
	
	public double queryClothesTotalPrice(Long orderId);
}

