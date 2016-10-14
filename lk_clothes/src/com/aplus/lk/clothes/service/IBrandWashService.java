package com.aplus.lk.clothes.service;


import java.util.List;

import com.aplus.lk.clothes.entity.BrandWashMethod;

public interface IBrandWashService {

	public List<BrandWashMethod> queryAllBrand(Integer type);  
	
	public BrandWashMethod queryByBrand(String brandName);
	
	public void add(BrandWashMethod brand);
	
	public void delete(long id);
}
