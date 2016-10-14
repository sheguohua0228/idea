package com.aplus.lk.clothes.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Action;
import com.aplus.lk.clothes.entity.GroupManager;
import com.aplus.lk.clothes.service.ActionService;

@Controller
@RequestMapping("action")
public class ActionController {
	
	@Autowired
	private ActionService actionService;
	
	@RequestMapping("checkActionName")
	@ResponseBody
	public boolean checkActionName(String oldValue, String actionName){
		if(actionName.equalsIgnoreCase(oldValue)){
			return true;
		}
		return !actionService.isExistByActionName(actionName);
	}
	
	@RequestMapping("checkRoleName")
	@ResponseBody
	public boolean checkRoleName(String oldValue, String roleName){
		if(roleName.equalsIgnoreCase(oldValue)){
			return true;
		}
		return !actionService.isExistByRoleName(roleName);
	}
	
	@RequestMapping("checkResource")
	@ResponseBody
	public boolean checkResource(String oldValue, String resource){
		if(resource.equalsIgnoreCase(oldValue)){
			return true;
		}
		return !actionService.isExistByResource(resource);
	}
	
	
	
	@RequestMapping("query")
	public String query(Pager pager, String actionName, String roleName ,HttpServletRequest request){
		request.setAttribute("pager", actionService.queryPager(pager, actionName, roleName));
		if(actionName != null){
			request.setAttribute("actionName", actionName);
		}
		return "action_list";
	}
	
	@RequestMapping("add")
	public String add(){
		return "action_input";
	}
	
	@RequestMapping("save")
	public String save(Action action, HttpServletRequest request){
		action.setCreateDate(new Date());
		action.setModifyDate(action.getCreateDate());
		actionService.save(action);
		return "redirect:query";
	}
	
	@RequestMapping("edit")
	public String edit(String id, HttpServletRequest request){
		request.setAttribute("action", actionService.queryActionById(id));
		return "action_input";
	}
	
	@RequestMapping("update")
	public String update(Action action, HttpServletRequest request){
		action.setModifyDate(new Date());
		actionService.update(action);
		return "redirect:query";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(String[] ids, HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		Action action = null;
		for (String id : ids) {
			action = actionService.queryActionById(id);
			if(GroupManager.ROLE_BASE.equalsIgnoreCase(action.getRoleName())){
				result.setStatus(Status.error);
				result.setMessage("基础权限不能被删除");
				return result;
			}
			if(actionService.queryGroupCountByActionId(id) > 0){
				result.setStatus(Status.error);
				result.setMessage("权限["+action.getActionName()+"]正在被使用，删除失败");
				return result;
			}
		}
		actionService.delete(ids);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	
	
	
}
