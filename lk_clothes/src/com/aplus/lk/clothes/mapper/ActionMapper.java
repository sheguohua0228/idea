package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Action;

public interface ActionMapper {
	
	public int save(Action action);
	
	public int update(Action action);
	
	public Action queryActionByActionName(String actionName);
	
	public Action queryActionByRoleName(String roleName);
	
	public Action queryActionByResource(String resource);
	
	public List<Action> queryByLimit(Map<String, Object> parameterMap);
	
	public int queryCount(Map<String, Object> parameterMap);
	
	public Action queryActionById(String id);
	
	public int queryGroupCountByActionId(String actionId);
	
	public int delete(String[] ids);
	
	public List<Action> queryActionsByMenuIndex(int[] menuIndexs);
	
	public List<Action> queryActionsByGroupId(String groupId);
	
	public List<Action> queryAllActions();
	
}
