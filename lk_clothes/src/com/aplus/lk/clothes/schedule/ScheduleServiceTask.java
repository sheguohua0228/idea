package com.aplus.lk.clothes.schedule;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.EmployeeSchedule;
import com.aplus.lk.clothes.entity.Shedule;
import com.aplus.lk.clothes.mapper.AddressCenterMapper;
import com.aplus.lk.clothes.mapper.EmployeeMapper;
import com.aplus.lk.clothes.mapper.EmployeeScheduleMapper;
import com.aplus.lk.clothes.mapper.SheduleMapper;


@Component
@Lazy(false)
public class ScheduleServiceTask implements ScheduleService{

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SheduleMapper sheduleMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeScheduleMapper employeeScheduleMapper;
	
	@Autowired
	private AddressCenterMapper addressCenterMapper;
	
	@Transactional
	@Scheduled(cron = "0 0 1 ? * MON")//  0/5 * * * * ?  每5秒执行一次0 0 1 ? * MON 每周一凌晨1点执行一次
	@Override
	public void performWeekly() {
		Map<Integer, String> weeks = weekdays();
		try {
			if (weeks != null && weeks.size() > 0) {
				Shedule shedule = null;
				Map<Long, List<Employee>> empMap = makeEmployeeAddressCenter(employeeMapper
						.findAll(), addressCenterMapper.queryAll());
				for (int i = 1; i <= 7; i++) {
					shedule = new Shedule();
					sheduleMapper.save(makeShedule(weeks, shedule, i));
					for (Entry<Long, List<Employee>> entry : empMap.entrySet()) {
						for (Employee employee : entry.getValue()) {
							employeeScheduleMapper
									.save(makeEmplopyeeScheduleData(employee,shedule));
						}
					}
					System.out.println("test:" + i);
				}
			}
		} catch (Exception e) {
			logger.error("定时任务失败，{}", e.getMessage());
		}
	}

	/**
	 * 
	 * @param employee
	 * @param shedule
	 * @return
	 */
	private EmployeeSchedule makeEmplopyeeScheduleData(Employee employee,
			Shedule shedule) {
		EmployeeSchedule employeeSchedule = new EmployeeSchedule();
		employeeSchedule.setCreateTime(new Date());
		employeeSchedule.setUpdateTime(new Date());
		employeeSchedule.setEmployeeId(employee.getId());
		employeeSchedule.setScheduleId(shedule.getId());
		employeeSchedule.setStatus(0);//默认有效
		employeeSchedule.setTemporaryCommunityId(employee.getAddressCenterId());
		
		return employeeSchedule;
	}

	/**
	 * 组装key:社区-Id, value:对应的人员
	 * @param emList
	 * @return
	 */
	private Map<Long, List<Employee>> makeEmployeeAddressCenter(
			List<Employee> emList, List<AddressCenterPO> addressCenterPOs) {
		Map<Long, List<Employee>> map = new HashMap<Long, List<Employee>>();
		if(emList != null && emList.size() > 0){
			for(AddressCenterPO addressCenterPO : addressCenterPOs){
				map.put(addressCenterPO.getId(), new ArrayList<Employee>());
			}
			for(Employee employee : emList){
				if(map.containsKey(employee.getAddressCenterId())){
					map.get(employee.getAddressCenterId()).add(employee);
				} 
			}
		}
		return map;
	}

	/**
	 * 组装shedule定时任务数据，即排班数据
	 * @param weeks
	 * @param i
	 * @return
	 * @throws ParseException
	 */
	private Shedule makeShedule(Map<Integer, String> weeks, Shedule shedule,int i) throws ParseException {
		shedule.setCreateTime(new Date());
		shedule.setUpdateTime(new Date());
		if (i == 1) {
			shedule.setMonday(i + "");
			shedule.setMondayTime(weeks.get(i));
		} else if (i == 2) {
			shedule.setTuesday(i + "");
			shedule.setTuesdayTime(weeks.get(i));
		} else if (i == 3) {
			shedule.setWednesday(i + "");
			shedule.setWednesdayTime(weeks.get(i));
		} else if (i == 4) {
			shedule.setThursday(i + "");
			shedule.setThursdayTime(weeks.get(i));
		} else if (i == 5) {
			shedule.setFriday(i + "");
			shedule.setFridayTime(weeks.get(i));
		} else if (i == 6) {
			shedule.setSaturday(i + "");
			shedule.setSaturdayTime(weeks.get(i));
		} else if (i == 7) {
			shedule.setSunday(i + "");
			shedule.setSundayTime(weeks.get(i));
		}
		shedule.setBeginTime(getDayTime(7, 30));
		shedule.setEndTime(getDayTime(22, 00));
		
		return shedule;
	}

	/**
	 * 根据日期取得星期几
	 * @param date
	 * @return
	 */
	public String getWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(date);
		return week;
	}

	/**
	 * 将星期转换为对应的系数 星期日：1，星期一：2，星期二：3，星期三：4，星期四：5，星期五：6，星期六：7
	 * @param strWeek
	 * @return
	 */
	public Integer getWeekNum(String strWeek) {
		Integer number = 1;// 默认为星期日
		if ("星期日".equals(strWeek)) {
			number = 1;
		} else if ("星期一".equals(strWeek)) {
			number = 2;
		} else if ("星期二".equals(strWeek)) {
			number = 3;
		} else if ("星期三".equals(strWeek)) {
			number = 4;
		} else if ("星期四".equals(strWeek)) {
			number = 5;
		} else if ("星期五".equals(strWeek)) {
			number = 6;
		} else if ("星期六".equals(strWeek)) {
			number = 7;
		} else {
			number = 1;
		}
		return number;
	}
	
	/**
	 * 一周对应的时间
	 */
	private Map<Integer, String> weekdays() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 今天是一周中的第几天
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		if (c.getFirstDayOfWeek() == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		// 计算一周开始的日期
		c.add(Calendar.DAY_OF_MONTH, -dayOfWeek);

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 1; i <= 7; i++) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			map.put(i, sdf.format(c.getTime()));
		}
		return map;
	}
	
	/**
	 * 获取当天beginTime:endTime的时间戳
	 * @return
	 * @throws ParseException 
	 */
	 private Date getDayTime(Integer beginTime, Integer endTime) throws ParseException {
		 SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.HOUR_OF_DAY, beginTime);
		  cal.set(Calendar.SECOND, 0);
		  cal.set(Calendar.MINUTE, endTime);
		  cal.set(Calendar.MILLISECOND, 001);
		  long time = new Timestamp(cal.getTimeInMillis()).getTime();
		  String d = format.format(time);
		  Date date = format.parse(d);
		  return date;
	}
	 
}
