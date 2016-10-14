package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Employee;

public interface EmployeeMapper {
	
	public Employee queryEmployeeByUsernamePassword(String username , String password);
	
	public void save(Employee employee);
	
	public void update(Employee employee);
	
	public Employee queryById(String id);
	
	public Employee queryByUsername(String username);
	
	public List<Employee> queryByLimit(Map<String, Object> params);
	
	public int queryCount(Map<String, Object> params);
	
	public void delete(String[] ids);
	
	public List<Employee> queryByAddressCenterId(long addressCenterId);
	
	public int queryCountByDepartmentId(String departmentId);

	public List<Employee> findAll();
	
	public String queryRegistIdByOrderId(long orderId);
}
