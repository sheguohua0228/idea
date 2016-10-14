package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.RechargeCard;

public interface RechargeCardMapper {

	public List<RechargeCard> query(Map<String,Object> params);
	
	public int queryCount();
	
	public void saveAll(List<RechargeCard> list);
}
