package com.aplus.lk.clothes.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.entity.GroupManager;
import com.aplus.lk.clothes.service.ActionService;
import com.aplus.lk.clothes.service.GroupManagerService;

@Controller
@RequestMapping("groupManager")
public class GroupManagerController {
	
	@Autowired
	private GroupManagerService groupManagerService;
	@Autowired
	private ActionService actionService;
	
	@RequestMapping("add")
	public String add(HttpServletRequest request){
		request.setAttribute("actionList", actionService.queryAllActions());
		return "group_manager_input";
	}
	
	@RequestMapping("edit")
	public String edit(String id, HttpServletRequest request){
		GroupManager manager = groupManagerService.queryGroupManagerById(id);
		manager.setAuthorityList(groupManagerService.queryAuthorityById(id));
		request.setAttribute("groupManager", manager);
		request.setAttribute("actionList", actionService.queryAllActions());
		return "group_manager_input";
	}
	
	@RequestMapping("save")
	public String save(GroupManager groupManager,HttpServletRequest request){
		groupManagerService.save(groupManager);
		return "redirect:query";
	}
	
	@RequestMapping("update")
	public String update(GroupManager groupManager){
		groupManagerService.update(groupManager);
		return "redirect:query";
	}
	
	@RequestMapping("query")
	public String query(Pager pager , String groupName, HttpServletRequest request){
		request.setAttribute("pager", groupManagerService.queryPager(pager, groupName));
		request.setAttribute("groupName", groupName);
		return "group_manager_list";
	}
	
	/**
	 * @Title: delete
	 * @Description: TODO 放入回收站
	 * @param @param ids
	 * @param @return
	 * @return AjaxResult
	 * @throws
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(String[] ids){
		AjaxResult result = new AjaxResult();
		GroupManager manager = null;
		for(String id : ids){
			manager = groupManagerService.queryGroupManagerById(id);
			if(groupManagerService.queryAdminCountByGroupManagerId(id) > 0){
				result.setStatus(Status.error);
				result.setMessage("分组["+manager.getGroupName()+"]下存在管理员，删除失败");
				return result;
			}
		}
		groupManagerService.delete(ids);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	
	@RequestMapping("checkGroupName")
	@ResponseBody
	public boolean checkGroupName(String oldValue, String groupName){
		if(groupName.equalsIgnoreCase(oldValue)){
			return true;
		}
		return !groupManagerService.isExistByGroupName(groupName);
	}
}
