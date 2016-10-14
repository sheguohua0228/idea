package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.mapper.AppraiseMapper;
import com.aplus.lk.clothes.service.IAppraiseSerivce;

@Service
public class AppraiseSerivceImpl implements IAppraiseSerivce {

	@Autowired
	private AppraiseMapper appraiseMapper;

	@Override
	public Pager query(Pager pager, String phoneNumber, String orderNumber) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("phoneNumber", phoneNumber);
		parameterMap.put("orderNumber", orderNumber);
		parameterMap.put("firstResult",
				(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		pager.setDataList(appraiseMapper.query(parameterMap));
		pager.setTotalRecords(appraiseMapper.queryCount(parameterMap));
		return pager;
	}

}
