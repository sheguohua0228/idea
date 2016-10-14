package com.aplus.lk.clothes.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.ScheduleEmployeeModle;
import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.entity.EmployeeSchedule;
import com.aplus.lk.clothes.entity.EmployeeSchuduleEntity;
import com.aplus.lk.clothes.entity.Shedule;
import com.aplus.lk.clothes.mapper.AddressCenterMapper;
import com.aplus.lk.clothes.mapper.EmployeeScheduleMapper;
import com.aplus.lk.clothes.mapper.SheduleMapper;
import com.aplus.lk.clothes.service.EmployeeScheduleService;

@Service
public class EmployeeScheduleServiceImpl implements EmployeeScheduleService{

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private EmployeeScheduleMapper employeeScheduleMapper;
	
	@Autowired
	private AddressCenterMapper addressCenterMapper;
	
	@Autowired
	private SheduleMapper sheduleMapper;
	
	@Override
	public Pager queryPager(Pager pager, String name, String manager,
			Boolean isEnabled) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		List<ScheduleEmployeeModle> lists = new ArrayList<ScheduleEmployeeModle>();
		List<Long> ids = makeScheduleIds(sheduleMapper.queryByLimit(null));
		List<EmployeeSchuduleEntity> employees = employeeScheduleMapper.queryByLimit(ids);
		List<AddressCenterPO> centers = addressCenterMapper.queryByLimit(parameterMap);
		if(employees != null && employees.size() > 0){
			Map<Long, List<EmployeeSchuduleEntity>> map = makeEmployeeAddressCenter(employees, centers);
			ScheduleEmployeeModle scheduleEmployeeModle = null;
			String[] nameStrings = null;
			for (Entry<Long, List<EmployeeSchuduleEntity>> entry : map.entrySet()) {
				nameStrings = new String[]{"","","","","","",""};
				scheduleEmployeeModle = new ScheduleEmployeeModle();
				for(EmployeeSchuduleEntity entity1 : entry.getValue()) {
					nameStrings = makeNames(entity1, nameStrings);
					makeScheduleEmployeeModle(entity1, scheduleEmployeeModle, nameStrings);
				}
				scheduleEmployeeModle.setName(map.get(entry.getKey()).get(0).getCommunityName());
				scheduleEmployeeModle.setCommunityId(entry.getKey());
				lists.add(scheduleEmployeeModle);
			}
			pager.setDataList(lists);
			pager.setTotalRecords(addressCenterMapper.queryCount(parameterMap));
		} else{
			logger.info("定时任务出错或者异常");
		}
		return pager;
	}

	/**
	 * 组装每周对应的排班名字,已","分开
	 * @param entity1
	 * @param nameStrings
	 * @return
	 */
	private String[] makeNames(EmployeeSchuduleEntity entity,
			String[] names) {
		if (StringUtils.isNotBlank(entity.getShedule().getMonday())) {
			if(names[0] == ""){
				names[0] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[0], entity.getEmployeeName())){
				names[0] = names[0] + "," + entity.getEmployeeName();
			}
		} else if (StringUtils.isNotBlank(entity.getShedule().getTuesday())) {
			if(names[1] == ""){
				names[1] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[1], entity.getEmployeeName())){
				names[1] = names[1] + "," + entity.getEmployeeName();
			}
		} else if (StringUtils.isNotBlank(entity.getShedule().getWednesday())) {
			if(names[2] == ""){
				names[2] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[2], entity.getEmployeeName())){
				names[2] = names[2] + "," + entity.getEmployeeName();
			}
		} else if (StringUtils.isNotBlank(entity.getShedule().getThursday())) {
			if(names[3] == ""){
				names[3] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[3], entity.getEmployeeName())){
				names[3] = names[3] + "," + entity.getEmployeeName();
			}
		} else if (StringUtils.isNotBlank(entity.getShedule().getFriday())) {
			if(names[4] == ""){
				names[4] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[4], entity.getEmployeeName())){
				names[4] = names[4] + "," + entity.getEmployeeName();
			}
		} else if (StringUtils.isNotBlank(entity.getShedule().getSaturday())) {
			if(names[5] == ""){
				names[5] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[5], entity.getEmployeeName())){
				names[5] = names[5] + "," + entity.getEmployeeName();
			}
		} else if (StringUtils.isNotBlank(entity.getShedule().getSunday())) {
			if(names[6] == ""){
				names[6] = entity.getEmployeeName();
			}
			if(!StringUtils.equals(names[6], entity.getEmployeeName())){
				names[6] = names[6] + "," + entity.getEmployeeName();
			}
		}
		return names;
	}

	/**
	 * key : 社区Id ； value: 员工
	 * @param emList
	 * @param addressCenterPOs
	 * @return
	 */
	private Map<Long, List<EmployeeSchuduleEntity>> makeEmployeeAddressCenter(
			List<EmployeeSchuduleEntity> emList, List<AddressCenterPO> addressCenterPOs) {
		Map<Long, List<EmployeeSchuduleEntity>> map = new HashMap<Long, List<EmployeeSchuduleEntity>>();
		if(emList != null && emList.size() > 0){
			for(EmployeeSchuduleEntity employee : emList){
				map.put(employee.getCommunityId(), new ArrayList<EmployeeSchuduleEntity>());
			}
			for(EmployeeSchuduleEntity employee : emList){
				if(map.containsKey(employee.getCommunityId())){
					map.get(employee.getCommunityId()).add(employee);
				} 
			}
		}
		return map;
	}
	
	/**
	 * 获取排班的Ids
	 * @param queryByLimit
	 * @return
	 */
	private List<Long> makeScheduleIds(List<Shedule> shedules) {
		List<Long> idsList =  new ArrayList<Long>();
		if(shedules != null && shedules.size() > 0){
			for (Shedule shedule : shedules) {
				idsList.add(shedule.getId());
			}
		}
		return idsList;
	}

	/**
	 * 组装模板数据
	 * @param entity
	 * @return
	 */
	private void makeScheduleEmployeeModle(
			EmployeeSchuduleEntity entity, 
			ScheduleEmployeeModle scheduleEmployeeModle, 
			String[] nameStrings) {
		if (StringUtils.isNotBlank(entity.getShedule().getMonday())) {
			scheduleEmployeeModle.setMonday("星期一");
			scheduleEmployeeModle.setMondayTime(entity.getShedule()
					.getMondayTime().substring(0, 10));
			scheduleEmployeeModle.setMondayEmployyeeName(nameStrings[0]);
		} else if (StringUtils.isNotBlank(entity.getShedule().getTuesday())) {
			scheduleEmployeeModle.setTuesday("星期二");
			scheduleEmployeeModle.setTuesdayTime(entity.getShedule()
					.getTuesdayTime().substring(0, 10));
			scheduleEmployeeModle.setTuesdayEmployyeeName(nameStrings[1]);
		} else if (StringUtils.isNotBlank(entity.getShedule().getWednesday())) {
			scheduleEmployeeModle.setWednesday("星期三");
			scheduleEmployeeModle.setWednesdayTime(entity.getShedule()
					.getWednesdayTime().substring(0, 10));
			scheduleEmployeeModle.setWednesdayEmployyeeName(nameStrings[2]);
		} else if (StringUtils.isNotBlank(entity.getShedule().getThursday())) {
			scheduleEmployeeModle.setThursday("星期四");
			scheduleEmployeeModle.setThursdayTime(entity.getShedule()
					.getThursdayTime().substring(0, 10));
			scheduleEmployeeModle.setThursdayEmployyeeName(nameStrings[3]);
		} else if (StringUtils.isNotBlank(entity.getShedule().getFriday())) {
			scheduleEmployeeModle.setFriday("星期五");
			scheduleEmployeeModle.setFridayTime(entity.getShedule()
					.getFridayTime().substring(0, 10));
			scheduleEmployeeModle.setFridayEmployyeeName(nameStrings[4]);
		} else if (StringUtils.isNotBlank(entity.getShedule().getSaturday())) {
			scheduleEmployeeModle.setSaturday("星期六");
			scheduleEmployeeModle.setSaturdayTime(entity.getShedule()
					.getSaturdayTime().substring(0, 10));
			scheduleEmployeeModle.setSaturdayEmployyeeName(nameStrings[5]);
		} else if (StringUtils.isNotBlank(entity.getShedule().getSunday())) {
			scheduleEmployeeModle.setSunday("星期天");
			scheduleEmployeeModle.setSundayTime(entity.getShedule()
					.getSundayTime().substring(0, 10));
			scheduleEmployeeModle.setSundayEmployyeeName(nameStrings[6]);
		}
	}

	@Override
	public EmployeeSchedule findEmployeeScheduleByScheduleIdAndEmployeeId(
			String scheduleId, String employeeId, String centerId) {
		EmployeeSchedule employeeSchedule = 
				employeeScheduleMapper.queryByScheduleIdAndEmployeeId(scheduleId, employeeId, centerId);
		return employeeSchedule != null ? employeeSchedule : null;
	}

	@Override
	public void saveEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		employeeScheduleMapper.save(employeeSchedule);
	}

	@Override
	public void updateEmployeeSchedule(EmployeeSchedule employeeSchedule) {
		employeeScheduleMapper.update(employeeSchedule);
		
	}

	@Override
	public Pager queryByNamePager(Pager pager, String name) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		List<ScheduleEmployeeModle> lists = new ArrayList<ScheduleEmployeeModle>();
		List<EmployeeSchuduleEntity> employees = employeeScheduleMapper.queryByNameLimit(name);
		List<AddressCenterPO> centers = addressCenterMapper.queryByLimit(parameterMap);
		if(employees != null && employees.size() > 0){
			Map<Long, List<EmployeeSchuduleEntity>> map = makeEmployeeAddressCenter(employees, centers);
			ScheduleEmployeeModle scheduleEmployeeModle = null;
			String[] nameStrings = null;
			for (Entry<Long, List<EmployeeSchuduleEntity>> entry : map.entrySet()) {
				nameStrings = new String[]{"","","","","","",""};
				scheduleEmployeeModle = new ScheduleEmployeeModle();
				for(EmployeeSchuduleEntity entity1 : entry.getValue()) {
					nameStrings = makeNames(entity1, nameStrings);
					makeScheduleEmployeeModle(entity1, scheduleEmployeeModle, nameStrings);
				}
				scheduleEmployeeModle.setName(map.get(entry.getKey()).get(0).getCommunityName());
				scheduleEmployeeModle.setCommunityId(entry.getKey());
				lists.add(scheduleEmployeeModle);
			}
			pager.setDataList(lists);
			pager.setTotalRecords(addressCenterMapper.queryCount(parameterMap));
		} else{
			logger.info("定时任务出错或者异常");
		}
		return pager;
	}

}
