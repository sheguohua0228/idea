package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.EmployeeSchedule;
import com.aplus.lk.clothes.entity.EmployeeSchuduleEntity;

public interface EmployeeScheduleMapper {

	public void save(EmployeeSchedule employeeSchedule);

	public List<EmployeeSchuduleEntity> queryByLimit(List<Long> ids);

	public int queryCount(Map<String, Object> parameterMap);

	public EmployeeSchedule queryByScheduleIdAndEmployeeId(String scheduleId, String employeeId, String centerId);

	public void update(EmployeeSchedule employeeSchedule);

	public List<EmployeeSchuduleEntity> queryByNameLimit(String name);
	
}
