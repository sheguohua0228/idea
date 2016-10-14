package com.aplus.lk.clothes.service.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.entity.BrandWashMethod;
import com.aplus.lk.clothes.mapper.BrandWashMethodMapper;
import com.aplus.lk.clothes.service.IBrandWashService;
@Service
public class BrandWashServiceImpl implements IBrandWashService{

	@Autowired
	private BrandWashMethodMapper brandWashMethodMapper;
	@Override
	public List<BrandWashMethod> queryAllBrand(Integer type) {
		return brandWashMethodMapper.queryAllBrand(type);
	}

	@Override
	public BrandWashMethod queryByBrand(String brandName) {
		if(StringUtils.isNotBlank(brandName)){
			return brandWashMethodMapper.queryByBrand(brandName);
		}
		return null;
	}

	@Override
	public void add(BrandWashMethod brand) {
		if(StringUtils.isNotBlank(brand.getBrandName())){
			brandWashMethodMapper.add(brand);
		}
	}

	@Override
	public void delete(long id) {
		brandWashMethodMapper.delete(id);
	}

}
