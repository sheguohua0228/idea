package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Department;

public interface DepartmentMapper {
	
	public void save(Department department);
	
	public void update(Department department);
	
	public List<Department> queryByLimit(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public Department queryById(String id);
	
	public void delete(String[] ids);
	
	public Department queryByName(String name);
	
	public List<Department> queryAll();
	
}
