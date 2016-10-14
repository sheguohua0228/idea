package com.aplus.lk.clothes.service;

import java.util.List;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.GroupManager;

public interface GroupManagerService {
	
	public int save(GroupManager groupManager);

	public int update(GroupManager groupManager);

	public int delete(String[] ids);

	public GroupManager queryGroupManagerByName(String name);
	
	public Pager queryPager(Pager pager, String groupName);
	
	public boolean isExistByGroupName(String groupName);
	
	public GroupManager queryGroupManagerById(String id);
	
	public int queryAdminCountByGroupManagerId(String groupManagerId);
	
	public List<String> queryAuthorityById(String id);
	
	public List<GroupManager> queryAll();
	
}
