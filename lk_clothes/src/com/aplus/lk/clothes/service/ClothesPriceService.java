package com.aplus.lk.clothes.service;

import java.util.List;

import com.aplus.lk.clothes.entity.ClothesPrice;

public interface ClothesPriceService {

	public List<ClothesPrice> query(String name);

	public int save(ClothesPrice clothesPrice);

	public int update(ClothesPrice clothesPrice);

	public int delete(long id);
	
	public ClothesPrice queryById(long id);

}
