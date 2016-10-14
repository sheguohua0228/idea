package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Shedule;

public interface SheduleMapper {

	public int save(Shedule shedule);

	public List<Shedule> queryByLimit(Map<String, Object> parameterMap);
	
}
