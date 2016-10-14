package com.aplus.lk.clothes.mapper;

import com.aplus.lk.clothes.entity.ClothesAddress;

public interface ClothesAddressMapper {
	
	public ClothesAddress queryClothesAddressById(long id);
	
	public void save(ClothesAddress clothesAddress);
	
	public ClothesAddress queryClothesAddressByUserId(String userId);
	
	public void update(ClothesAddress clothesAddress);

}
