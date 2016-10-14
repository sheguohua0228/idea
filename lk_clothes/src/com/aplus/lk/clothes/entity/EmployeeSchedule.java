package com.aplus.lk.clothes.entity;

import java.util.Date;

public class EmployeeSchedule extends BaseEntity{

	private String employeeId;//员工Id
	
	private long scheduleId;//排班表Id
	
	private Integer status; //0-有效，1-请假，2-调休（默认0）

	private Date leaveOffTime; //调休或者请假时间
	
	private Long temporaryCommunityId;//社区ID

	public String getEmployeeId() {
		return employeeId;
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getLeaveOffTime() {
		return leaveOffTime;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setLeaveOffTime(Date leaveOffTime) {
		this.leaveOffTime = leaveOffTime;
	}

	public Long getTemporaryCommunityId() {
		return temporaryCommunityId;
	}

	public void setTemporaryCommunityId(Long temporaryCommunityId) {
		this.temporaryCommunityId = temporaryCommunityId;
	}
}
