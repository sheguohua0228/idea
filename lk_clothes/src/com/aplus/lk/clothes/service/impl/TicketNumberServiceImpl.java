package com.aplus.lk.clothes.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.entity.TicketNumber;
import com.aplus.lk.clothes.mapper.TicketNumberMapper;
import com.aplus.lk.clothes.service.TicketNumberService;

@Service
public class TicketNumberServiceImpl implements TicketNumberService{

	@Autowired
	private TicketNumberMapper ticketNumberMapper;
	
	@Override
	public int findTotalByType(int type) {
		return ticketNumberMapper.findTotalByTotal(type);
	}

	@Override
	public List<Map<String, Integer>> queryRecods() {
		return ticketNumberMapper.queryRecods();
	}

	@Override
	public void save(TicketNumber ticket) {
		ticket.setUpdateTime(ticket.getCreateTime());
		ticketNumberMapper.save(ticket);
	}

	
}
