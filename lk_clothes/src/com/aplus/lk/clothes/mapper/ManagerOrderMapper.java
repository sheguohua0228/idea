package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.ManagerOrder;
import com.aplus.lk.clothes.entity.ReassignmentRecord;

public interface ManagerOrderMapper  {
	public List<ManagerOrder> queryByLimit(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public ManagerOrder queryManagerOrderById(long id);
	
	public Employee queryEmployessById(String  employeeId);
	
	public void updateStatusById(Map<String, Object> parameterMap);
	
	public void updateEmployeeIdById(Map<String, Object> parameterMap);
	
	public void createRecord(ReassignmentRecord  reassignmentRecord);
}
