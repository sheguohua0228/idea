package com.aplus.lk.clothes.service;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.TicketNumber;

public interface TicketNumberService {

	int findTotalByType(int type);

	List<Map<String,Integer>> queryRecods();
	
	void save(TicketNumber ticket);
}
