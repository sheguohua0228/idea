package com.aplus.lk.clothes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Department;
import com.aplus.lk.clothes.service.DepartmentService;

@Controller
@RequestMapping("department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("add")
	public String add(){
		return "department_input";
	}
	
	@RequestMapping("save")
	public String save(Department department){
		departmentService.save(department);
		return "redirect:query";
	}
	
	@RequestMapping("edit")
	public String edit(String id, HttpServletRequest request){
		request.setAttribute("department", departmentService.queryById(id));
		return "department_input";
	}
	
	@RequestMapping("update")
	public String update(Department department, HttpServletRequest request){
		departmentService.update(department);
		return "redirect:query";
	}
	
	@RequestMapping("query")
	public String query(Pager pager, String name , String manager , Boolean isEnabled, HttpServletRequest request){
		request.setAttribute("pager", departmentService.queryPager(pager, name, manager, isEnabled));
		request.setAttribute("name", name);
		request.setAttribute("manager", manager);
		request.setAttribute("isEnabled", isEnabled);
		return "department_list";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(String[] ids){
		return departmentService.delete(ids);
	}
	
	@RequestMapping("checkName")
	@ResponseBody
	public boolean checkName(String oldValue, String name){
		if(name.equals(oldValue)){
			return true;
		}
		return departmentService.queryByName(name) == null;
	}
	
	@RequestMapping("queryAll")
	@ResponseBody
	public List<Department> queryAll(){
		return departmentService.queryAll();
	}
	
}
