package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.GroupManager;

public interface GroupManagerMapper {
	
	public int save(GroupManager groupManager);
	
	public int update(GroupManager groupManager);
	
	public int delete(String[] ids);
	
	public List<GroupManager> queryByLimit(Map<String, Object> parameterMap);
	
	public GroupManager queryGroupManagerByName(String name);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public GroupManager queryGroupManagerById(String id);
	
	public int queryAdminCountByGroupManagerId(String groupManagerId);
	
	public int saveGroupAction(Map<String, String> parameterMap);
	
	public void deleteGroupAction(Map<String, String> parameterMap);
	
	public GroupManager queryGroupManagerWithActionById(String id);
	
	public List<String> queryAuthorityById(String id);
	
	public List<GroupManager> queryAll();
	
	public void deleteGroupActionsByGroupId(String[] groupIds);
	
}
