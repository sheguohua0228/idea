package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Banner;

public interface BannerMapper {
	
	public void save(Banner banner);
	
	public void update(Banner banner);
	
	public Banner queryById(long id);
	
	public List<Banner> queryByLimit(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public void delete(long[] ids);
	
	public void deleteById(long id);
	
	public String queryImageUrlById(long id);
	
}
