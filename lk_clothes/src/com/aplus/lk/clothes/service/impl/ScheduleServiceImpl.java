package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.mapper.SheduleMapper;
import com.aplus.lk.clothes.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SheduleMapper sheduleMapper;
	
	@Override
	public Pager queryPager(Pager pager, String name, Integer status,
			Integer type) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("name", name);
		parameterMap.put("status", status);
		parameterMap.put("type", type);
		parameterMap.put("firstResult", pager.getFirstResult());
		parameterMap.put("maxResult", pager.getPageSize());
		pager.setDataList(sheduleMapper.queryByLimit(parameterMap));
//		pager.setTotalRecords(sheduleMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public void delete(long[] ids, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}
