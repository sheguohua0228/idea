package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.entity.ClothesAddress;

public interface ClothesAddressService {
	
	public void save(ClothesAddress clothesAddress);
	
	public ClothesAddress queryClothesAddressByUserId(String userId);
	
	public void update(ClothesAddress clothesAddress, String employeeId, long clothesOrderId);
	
	public void updateAddress(ClothesAddress clothesAddress);
	
	public ClothesAddress queryClothesAddress(long clothesAddressId);
}
