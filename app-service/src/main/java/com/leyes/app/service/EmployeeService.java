package com.leyes.app.service;

import java.util.List;

import com.leyes.app.dto.employee.EmployeeDto;

public interface EmployeeService {

	public List<EmployeeDto> queryEmployeeByCommunityId(String communityId,int authType)throws Exception;
	
	public boolean queryRecommendCodeExist(String recommendCode) throws Exception;
	
	public EmployeeDto login(String userName,String password,String deviceToken)throws Exception;

	public List<String> queryCommunityOfEmployee(String employeeId)throws Exception;
	
	public EmployeeDto queryEmployeeById(String employeeId)throws Exception;
	
}