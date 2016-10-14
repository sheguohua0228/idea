package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.AppraisePO;

public interface AppraiseMapper {

	public List<AppraisePO> query(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
}
