package com.aplus.lk.clothes.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Action;
import com.aplus.lk.clothes.mapper.ActionMapper;
import com.aplus.lk.clothes.service.ActionService;
import com.aplus.lk.clothes.utils.UUIDUtils;

@Service
public class ActionServiceImpl implements ActionService{

	@Autowired
	private ActionMapper actionMapper;
	
	@Override
	public int save(Action action) {
		action.setId(UUIDUtils.getUUID());
		return actionMapper.save(action);
	}

	@Override
	public int update(Action action) {
		action.setModifyDate(new Date());
		return actionMapper.update(action);
	}

	@Override
	public Action queryActionByActionName(String actionName) {
		return actionMapper.queryActionByActionName(actionName);
	}

	@Override
	public Action queryActionByRoleName(String roleName) {
		return actionMapper.queryActionByRoleName(roleName);
	}

	@Override
	public Pager queryPager(Pager pager, String actionName, String roleName) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo()-1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("actionName", actionName);
		parameterMap.put("roleName", roleName);
		pager.setDataList(actionMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(actionMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public boolean isExistByRoleName(String roleName) {
		return actionMapper.queryActionByRoleName(roleName) != null;
	}

	@Override
	public boolean isExistByActionName(String actionName) {
		return actionMapper.queryActionByActionName(actionName) != null;
	}

	@Override
	public Action queryActionById(String id) {
		return actionMapper.queryActionById(id);
	}

	@Override
	public boolean isExistByResource(String resource) {
		return actionMapper.queryActionByResource(resource) != null;
	}

	@Override
	public int queryGroupCountByActionId(String actionId) {
		return actionMapper.queryGroupCountByActionId(actionId);
	}

	@Override
	public int delete(String[] ids) {
		return actionMapper.delete(ids);
	}

	@Override
	public List<Action> queryActionsByMenuIndex(int[] menuIndexs) {
		return actionMapper.queryActionsByMenuIndex(menuIndexs);
	}

	@Override
	public List<Action> queryActionsByGroupId(String groupId) {
		return actionMapper.queryActionsByGroupId(groupId);
	}

	@Override
	public List<Action> queryAllActions() {
		return actionMapper.queryAllActions();
	}

}
