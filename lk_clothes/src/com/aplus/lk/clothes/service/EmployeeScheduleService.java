package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.EmployeeSchedule;

public interface EmployeeScheduleService {

	public Pager queryPager (Pager pager , String name ,String manager, Boolean isEnabled);

	public EmployeeSchedule findEmployeeScheduleByScheduleIdAndEmployeeId(
			String scheduleId, String employeeId, String centerId);

	public void saveEmployeeSchedule(EmployeeSchedule employeeSchedule);

	public void updateEmployeeSchedule(EmployeeSchedule employeeSchedule);

	public Pager queryByNamePager(Pager pager, String name);
	
}
