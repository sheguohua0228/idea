package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.AddressCenterPO;

public interface AddressCenterMapper {
	
	public void save(AddressCenterPO addressCenterPO);
	
	public void update(AddressCenterPO addressCenterPO);
	
	public void delete(long[] ids);
	
	public AddressCenterPO queryById(long id);
	
	public AddressCenterPO queryByName(String name);
	
	public List<AddressCenterPO> queryByLimit(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public List<AddressCenterPO> queryAllOfIdAndName();
	
	public String queryNameById(String id);
	
	public List<AddressCenterPO> queryAll();
	
	public List<AddressCenterPO> queryByCenterType(int centerType);
	
}
