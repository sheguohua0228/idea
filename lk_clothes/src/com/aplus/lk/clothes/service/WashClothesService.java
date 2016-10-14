package com.aplus.lk.clothes.service;

import java.util.List;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.WashClothes;

public interface WashClothesService {
	
	public WashClothes queryByChildBarCode(String childBarCode);
	
	public boolean updateWashStatus(long clothesOrderId,String childBarCode,int clothesWashStatus);
	
	public long queryWashedClothes(long clothesOrderId,int status);
	
	public long queryWashedClothesCount(long clothesOrderId);
	
	public void updateClothesStatus(long clothesOrderId,int crrentStatus,int nextStatus);
	
	public WashClothes queryClothesByPhoneBrand(String phone,String clothesName,String brand);
	
	public Pager querySortingClothes(Pager pager,Long[] ids, String barCode, String userName,
			String phoneNumber, String[] washType);
}
