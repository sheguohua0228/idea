package com.aplus.lk.clothes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.entity.ClothesPrice;
import com.aplus.lk.clothes.mapper.ClothesPriceMapper;
import com.aplus.lk.clothes.service.ClothesPriceService;

@Service
public class ClothesPriceServiceImpl implements ClothesPriceService{

	@Autowired
	private ClothesPriceMapper clothesPriceMapper;
	
	@Override
	public List<ClothesPrice> query(String name) {
		return clothesPriceMapper.query(name);
	}

	@Override
	public int save(ClothesPrice clothesPrice) {
		return clothesPriceMapper.save(clothesPrice);
	}

	@Override
	public int update(ClothesPrice clothesPrice) {
		return clothesPriceMapper.update(clothesPrice);
	}

	@Override
	public int delete(long id) {
		return clothesPriceMapper.delete(id);
	}

	@Override
	public ClothesPrice queryById(long id) {
		return clothesPriceMapper.queryById(id);
	}

}
