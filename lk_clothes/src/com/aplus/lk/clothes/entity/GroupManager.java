package com.aplus.lk.clothes.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 实体类 - 分组
 */
public class GroupManager {

	public static final String ROLE_BASE = "ROLE_BASE";// 基础管理权限
	
	private String id;
	private Date createDate;
	private Date modifyDate;
	
	private String groupName;  //分组名称
	private String groupInfo;  //分组信息
	private String masterName; //创建人
	private String masterId;   //创建人ID
	private List<String> authorityList; //权限集合
	private Integer status;//分组状态(0代表回收，1代表恢复)
	
	private Set<Admin> adminSet = new HashSet<Admin>();// 管理员
	private Set<Action> actionSet= new HashSet<Action>();//权限

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}

	
	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	
	public Set<Admin> getAdminSet() {
		return adminSet;
	}

	public void setAdminSet(Set<Admin> adminSet) {
		this.adminSet = adminSet;
	}

	public Set<Action> getActionSet() {
		return actionSet;
	}

	public void setActionSet(Set<Action> actionSet) {
		this.actionSet = actionSet;
	}

	public List<String> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}

	
	
}
