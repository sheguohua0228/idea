package com.aplus.lk.clothes.service;

import java.util.List;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Department;

public interface DepartmentService {
	
	public void save(Department department);
	
	public void update(Department department);
	
	public Department queryById(String id);
	
	public AjaxResult delete(String[] ids);
	
	public Pager queryPager (Pager pager , String name ,String manager, Boolean isEnabled);
	
	public Department queryByName(String name);
	
	public List<Department> queryAll();
	
}
