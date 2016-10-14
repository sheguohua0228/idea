package com.aplus.lk.clothes.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.GroupManager;
import com.aplus.lk.clothes.mapper.GroupManagerMapper;
import com.aplus.lk.clothes.service.GroupManagerService;
import com.aplus.lk.clothes.utils.UUIDUtils;

@Service
public class GroupManagerServiceImpl implements GroupManagerService{

	@Autowired
	private GroupManagerMapper groupManagerMapper;
	
	@Override
	public int save(GroupManager groupManager) {
		groupManager.setId(UUIDUtils.getUUID());
		groupManager.setCreateDate(new Date());
		groupManager.setModifyDate(groupManager.getCreateDate());
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("groupId", groupManager.getId());
		groupManagerMapper.save(groupManager);
		// 保存Action
		for(String actionId : groupManager.getAuthorityList()){
			parameterMap.put("actionId", actionId);
			groupManagerMapper.saveGroupAction(parameterMap);
		}
		
		return 1;
	}

	@Override
	public int update(GroupManager groupManager) {
		groupManager.setModifyDate(new Date());
		List<String> persistentActionIdList = groupManagerMapper.queryAuthorityById(groupManager.getId());
		List<String> actionIdsList = groupManager.getAuthorityList();
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("groupId", groupManager.getId());
		for(String id : persistentActionIdList){ // 删除被取消的数据
			if(!actionIdsList.contains(id)){
				parameterMap.put("actionId", id);
				groupManagerMapper.deleteGroupAction(parameterMap);
			}
		}
		for(String id : actionIdsList){ // 添加被新选择的数据
			if(!persistentActionIdList.contains(id)){
				parameterMap.put("actionId", id);
				groupManagerMapper.saveGroupAction(parameterMap);
			}
		}
		return groupManagerMapper.update(groupManager);
	}

	@Override
	public int delete(String[] ids) {
		groupManagerMapper.deleteGroupActionsByGroupId(ids);
		return groupManagerMapper.delete(ids);
	}

	@Override
	public GroupManager queryGroupManagerByName(String name) {
		return groupManagerMapper.queryGroupManagerByName(name);
	}

	@Override
	public Pager queryPager(Pager pager, String groupName) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo() - 1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("groupName", groupName);
		pager.setDataList(groupManagerMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(groupManagerMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public boolean isExistByGroupName(String groupName) {
		GroupManager groupManager = groupManagerMapper.queryGroupManagerByName(groupName.trim());
		return groupManager != null;
	}

	@Override
	public GroupManager queryGroupManagerById(String id) {
		return groupManagerMapper.queryGroupManagerById(id);
	}

	@Override
	public int queryAdminCountByGroupManagerId(String groupManagerId) {
		return groupManagerMapper.queryAdminCountByGroupManagerId(groupManagerId);
	}

	@Override
	public List<String> queryAuthorityById(String id) {
		return groupManagerMapper.queryAuthorityById(id);
	}

	@Override
	public List<GroupManager> queryAll() {
		return groupManagerMapper.queryAll();
	}

}
