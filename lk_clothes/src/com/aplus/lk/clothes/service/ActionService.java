package com.aplus.lk.clothes.service;

import java.util.List;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Action;

public interface ActionService {

	public int save(Action action);

	public int update(Action action);

	public Action queryActionByActionName(String actionName);

	public Action queryActionByRoleName(String roleName);

	public Pager queryPager(Pager pager, String actionName, String roleName);
	
	public boolean isExistByRoleName(String roleName);
	
	public boolean isExistByActionName(String actionName);
	
	public boolean isExistByResource(String resource);
	
	public Action queryActionById(String id);
	
	public int queryGroupCountByActionId(String actionId);
	
	public int delete(String[] ids);
	
	public List<Action> queryActionsByMenuIndex(int[] menuIndexs);
	
	public List<Action> queryActionsByGroupId(String groupId);
	
	public List<Action> queryAllActions();
	
}
