package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.TicketNumber;

public interface TicketNumberMapper {

	int findTotalByTotal(int type);

	List<Map<String,Integer>> queryRecods();
	
	void save(TicketNumber ticket);
}
