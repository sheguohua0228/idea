package com.aplus.lk.clothes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.DepartmentService;
import com.aplus.lk.clothes.service.EmployeeService;
import com.aplus.lk.clothes.utils.StringUtils;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AddressCenterService addressCenterService;
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("add")
	public String add(HttpServletRequest request){
		request.setAttribute("addressCenterList", addressCenterService.queryAllOfIdAndName());
		request.setAttribute("departmentList", departmentService.queryAll());
		return "employee_input";
	}
	
	@RequestMapping("save")
	public String save(Employee employee, MultipartFile imageUpload, HttpServletRequest request){
		employeeService.save(employee,imageUpload,request);
		return "redirect:query";
	}
	
	@RequestMapping("edit")
	public String edit(String id,HttpServletRequest request){
		Employee employee = employeeService.queryById(id);
		request.setAttribute("employee", employee);
		request.setAttribute("addressCenterList", addressCenterService.queryAllOfIdAndName());
		request.setAttribute("departmentList", departmentService.queryAll());
		return "employee_input";
	}
	
	@RequestMapping("update")
	public String update(Employee employee, MultipartFile imageUpload, HttpServletRequest request){
		employeeService.update(employee,imageUpload,request);
		return "redirect:query";
	}
	
	@RequestMapping("query")
	public String query(Pager pager, String name ,HttpServletRequest request){
		request.setAttribute("pager", employeeService.queryPager(pager, name));
		request.setAttribute("name", name);
		request.setAttribute("addressCenterList", addressCenterService.queryAllOfIdAndName());
		request.setAttribute("departmentList", departmentService.queryAll());
		return "employee_list";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(String[] ids){
		AjaxResult result = new AjaxResult(Status.success,"删除成功",null);
		employeeService.delete(ids);
		return result;
	}
	
	@RequestMapping("checkUsername")
	@ResponseBody
	public boolean checkUsername(String username){
		return employeeService.queryByUsername(username) == null;
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(String username, String password, String validCode, HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		if(StringUtils.isEmpty(username)){
			ajaxResult.setStatus(Status.error);
			ajaxResult.setMessage("请输入账户");
			return ajaxResult;
		}
		if(StringUtils.isEmpty(password)){
			ajaxResult.setStatus(Status.error);
			ajaxResult.setMessage("请输入密码");
			return ajaxResult;
		}
		if(StringUtils.isEmpty(validCode)){
			ajaxResult.setStatus(Status.error);
			ajaxResult.setMessage("请输入验证码");
			return ajaxResult;
		}
		HttpSession session = request.getSession();
		String sessionValidCode = (String) session.getAttribute("validCode");
		if(!validCode.equalsIgnoreCase(sessionValidCode)){
			ajaxResult.setStatus(Status.error);
			ajaxResult.setMessage("验证码错误，请重新输入");
		}
		Employee employee = employeeService.queryEmployeeByUsernamePassword(username, DigestUtils.md5Hex(password));
		if(employee == null){
			ajaxResult.setStatus(Status.error);
			ajaxResult.setMessage("用户名或密码错误，请重新输入");
		}
		session.setAttribute("loginEmployee", employee);
		ajaxResult.setStatus(Status.success);
		ajaxResult.setMessage("登录成功");
		session.removeAttribute("validCode");
		return ajaxResult;
	}
	
	@RequestMapping("queryByAddressCenterId")
	@ResponseBody
	public List<Employee> queryByAddressCenterId(long addressCenterId){
		return employeeService.queryByAddressCenterId(addressCenterId);
	}
	
}
