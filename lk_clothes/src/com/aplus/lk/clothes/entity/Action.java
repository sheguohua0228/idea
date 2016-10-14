package com.aplus.lk.clothes.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 实体类 - 权限
 */
public class Action {
	
	private String id;
	private Date createDate;
	private Date modifyDate;
	
	private String actionName; //权限名称
	private String resource; //资源URL
	private String roleName;  //角色名称
	private Integer menuIndex; //菜单索引
	
	private Set<GroupManager> groupSet=new HashSet<GroupManager>(); //分组

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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}

	public Set<GroupManager> getGroupSet() {
		return groupSet;
	}

	public void setGroupSet(Set<GroupManager> groupSet) {
		this.groupSet = groupSet;
	}
	
	
	
}
