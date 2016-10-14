package com.aplus.lk.clothes.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aplus.lk.clothes.bean.ModifyAdjustment;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.ScheduleEmployeeModle;
import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.entity.EmployeeSchedule;
import com.aplus.lk.clothes.entity.Shedule;
import com.aplus.lk.clothes.mapper.SheduleMapper;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.EmployeeScheduleService;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

	@Autowired
	private EmployeeScheduleService employeeScheduleService;
	
	@Autowired
	private AddressCenterService addressCenterService;
	
	@Autowired
	private SheduleMapper scheduleMapper;
	
	@SuppressWarnings("unchecked")
	@RequestMapping("query")
	public String query(Pager pager, String name, Integer status, Integer type , HttpServletRequest request){
		pager = employeeScheduleService.queryPager(pager, null, null, null);
		List<ScheduleEmployeeModle> lists = (List<ScheduleEmployeeModle>) pager.getDataList();
		request.setAttribute("pager", pager);
		request.setAttribute("mondayTime", lists.get(0).getMondayTime());
		request.setAttribute("tuesdayTime", lists.get(0).getTuesdayTime());
		request.setAttribute("wednesdayTime", lists.get(0).getWednesdayTime());
		request.setAttribute("thursdayTime", lists.get(0).getThursdayTime());
		request.setAttribute("fridayTime", lists.get(0).getFridayTime());
		request.setAttribute("saturday", lists.get(0).getSaturdayTime());
		request.setAttribute("sundayTime", lists.get(0).getSundayTime());
		request.setAttribute("status", status);
		request.setAttribute("type", type);
		return "schedule_list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("queryByName")
	public String queryByName(Pager pager, String name, Integer status, Integer type , HttpServletRequest request){
		pager = employeeScheduleService.queryByNamePager(pager, name);
		List<ScheduleEmployeeModle> lists = (List<ScheduleEmployeeModle>) pager.getDataList();
		request.setAttribute("pager", pager);
		if(lists != null && lists.size() > 0){
			request.setAttribute("mondayTime", lists.get(0).getMondayTime());
			request.setAttribute("tuesdayTime", lists.get(0).getTuesdayTime());
			request.setAttribute("wednesdayTime", lists.get(0).getWednesdayTime());
			request.setAttribute("thursdayTime", lists.get(0).getThursdayTime());
			request.setAttribute("fridayTime", lists.get(0).getFridayTime());
			request.setAttribute("saturday", lists.get(0).getSaturdayTime());
			request.setAttribute("sundayTime", lists.get(0).getSundayTime());
		}
		request.setAttribute("status", status);
		request.setAttribute("type", type);
		return "schedule_list";
	}
	
	
	@RequestMapping("modify")
	public String modify(HttpServletRequest request){
		List<AddressCenterPO> list = addressCenterService.queryAll();
		List<Shedule> scheduleList = scheduleMapper.queryByLimit(null);
		if(list != null && list.size() > 0){
			request.setAttribute("addressCenterList", list);
		}
		if(scheduleList != null && scheduleList.size() > 0){
			for(Shedule shedule : scheduleList){
				if(StringUtils.isNotBlank(shedule.getMonday())){
					shedule.setMondayTime(shedule.getMondayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getTuesdayTime())) {
					shedule.setTuesdayTime(shedule.getTuesdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getWednesday())) {
					shedule.setWednesdayTime(shedule.getWednesdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getThursday())) {
					shedule.setThursdayTime(shedule.getThursdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getFriday())) {
					shedule.setFridayTime(shedule.getFridayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getSaturday())) {
					shedule.setSaturdayTime(shedule.getSaturdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getSunday())) {
					shedule.setSundayTime(shedule.getSundayTime().substring(0, 10));
				}
			}
			request.setAttribute("scheduleList", scheduleList);
		}
		return "schedule_input";
	}
	
	@RequestMapping("modifyAdjustment")
	public String modifyAdjustment(
			@ModelAttribute ModifyAdjustment modifyAdjustment,
			HttpServletRequest request) {
		EmployeeSchedule employeeSchedule = 
				employeeScheduleService.findEmployeeScheduleByScheduleIdAndEmployeeId(modifyAdjustment.getScheduleId(), modifyAdjustment.getEmployeeId(),modifyAdjustment.getAddressCenterId());
		if(employeeSchedule != null){
			employeeSchedule.setUpdateTime(new Date());
			employeeSchedule.setStatus(Integer.valueOf(modifyAdjustment.getStatus()));
			employeeScheduleService.updateEmployeeSchedule(employeeSchedule);
		}
		return "redirect:query";
	}
	
	@RequestMapping("changeShift")
	public String changeShift(
			@ModelAttribute ModifyAdjustment modifyAdjustment,
			HttpServletRequest request) {
		EmployeeSchedule employeeSchedule = 
				employeeScheduleService
				.findEmployeeScheduleByScheduleIdAndEmployeeId(modifyAdjustment.getScheduleId(), modifyAdjustment.getEmployeeId(),modifyAdjustment.getAddressCenterId());
		if(employeeSchedule != null){
			employeeSchedule.setUpdateTime(new Date());
			employeeSchedule.setStatus(Integer.valueOf(modifyAdjustment.getStatus()));
			employeeScheduleService.updateEmployeeSchedule(employeeSchedule);
		}
		EmployeeSchedule newSchdedule = new  EmployeeSchedule();
		newSchdedule.setCreateTime(new Date());
		newSchdedule.setUpdateTime(new Date());
		newSchdedule.setEmployeeId(modifyAdjustment.getEmployeeId());
		newSchdedule.setScheduleId(Long.valueOf(modifyAdjustment.getScheduleId()));
		newSchdedule.setTemporaryCommunityId(Long.valueOf(modifyAdjustment.getCenterId()));
		newSchdedule.setStatus(Integer.valueOf(modifyAdjustment.getStatus()));
		employeeScheduleService.saveEmployeeSchedule(newSchdedule);
		
		return "redirect:query";
	}
	
	@RequestMapping("change")
	public String change(HttpServletRequest request){
		List<AddressCenterPO> list = addressCenterService.queryAll();
		List<Shedule> scheduleList = scheduleMapper.queryByLimit(null);
		if(list != null && list.size() > 0){
			request.setAttribute("addressCenterList", list);
		}
		if(scheduleList != null && scheduleList.size() > 0){
			for(Shedule shedule : scheduleList){
				if(StringUtils.isNotBlank(shedule.getMonday())){
					shedule.setMondayTime(shedule.getMondayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getTuesdayTime())) {
					shedule.setTuesdayTime(shedule.getTuesdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getWednesday())) {
					shedule.setWednesdayTime(shedule.getWednesdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getThursday())) {
					shedule.setThursdayTime(shedule.getThursdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getFriday())) {
					shedule.setFridayTime(shedule.getFridayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getSaturday())) {
					shedule.setSaturdayTime(shedule.getSaturdayTime().substring(0, 10));
				}else if (StringUtils.isNotBlank(shedule.getSunday())) {
					shedule.setSundayTime(shedule.getSundayTime().substring(0, 10));
				}
			}
			request.setAttribute("scheduleList", scheduleList);
		}
		return "schedule_change_input";
	}
	
}
