package com.aplus.lk.clothes.bean;

public class ModifyAdjustment {

	private String addressCenterId; //社区ID
	
	private String employeeId;//员工ID
	
	private String status;//请假--调休--换班
	
	private String scheduleId;//排班Id
	
	private String centerId;//待换班社区

	public String getAddressCenterId() {
		return addressCenterId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setAddressCenterId(String addressCenterId) {
		this.addressCenterId = addressCenterId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
}
