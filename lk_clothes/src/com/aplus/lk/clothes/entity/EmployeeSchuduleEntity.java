package com.aplus.lk.clothes.entity;

public class EmployeeSchuduleEntity {

	private long communityId;
	
	private String communityName;
	
	private String employeeName;
	
	private String employeeId;
	
	private Shedule shedule;

	public long getCommunityId() {
		return communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public Shedule getShedule() {
		return shedule;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setShedule(Shedule shedule) {
		this.shedule = shedule;
	}
	
}
