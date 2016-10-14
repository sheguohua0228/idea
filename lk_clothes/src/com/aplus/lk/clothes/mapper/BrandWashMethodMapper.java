package com.aplus.lk.clothes.mapper;


import java.util.List;

import com.aplus.lk.clothes.entity.BrandWashMethod;


public interface BrandWashMethodMapper {
	
	public List<BrandWashMethod> queryAllBrand(Integer brandType);  
	
	public BrandWashMethod queryByBrand(String brandName);
	
	public void add(BrandWashMethod brand);
	
	public void delete(long id);
}
