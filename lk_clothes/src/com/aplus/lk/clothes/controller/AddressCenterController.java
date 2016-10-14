package com.aplus.lk.clothes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.EmployeeService;

@Controller
@RequestMapping("addressCenter")
public class AddressCenterController {
	
	@Autowired
	private AddressCenterService addressCenterService;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("add")
	public String add(){
		return "address_center_input";
	}
	
	@RequestMapping("save")
	public String save(AddressCenterPO addressCenterPO, MultipartFile imageUpload ,HttpServletRequest request){
		addressCenterService.save(addressCenterPO, imageUpload, request);
		return "redirect:query";
	}

	@RequestMapping("edit")
	public String edit(long id, HttpServletRequest request){
		request.setAttribute("addressCenter", addressCenterService.queryById(id));
		Pager pager=new Pager();
		pager.setPageSize(200);
		pager=employeeService.queryPager(pager, null);
		request.setAttribute("data", pager);
		return "address_center_input";
	}
	
	@RequestMapping("update")
	public String update(AddressCenterPO addressCenterPO, MultipartFile imageUpload ,HttpServletRequest request){
		addressCenterService.update(addressCenterPO,imageUpload,request);
		return "redirect:query";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(long[] ids){
		AjaxResult result = new AjaxResult();
		addressCenterService.delete(ids);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	
	@RequestMapping("view")
	public String view(long id, HttpServletRequest request){
		AddressCenterPO addressCenter = addressCenterService.queryById(id);
		String employeeId = addressCenter.getEmployeeId();
		String employeeName="空余";
		if(StringUtils.isNotBlank(employeeId)){
			Employee employee = employeeService.queryById(employeeId);
			if(employee!=null){
				employeeName=employee.getRealName();
			}
		}
		request.setAttribute("addressCenter", addressCenter);
		request.setAttribute("employeeName", employeeName);
		return "address_center_view";
	}
	
	@RequestMapping("query")
	public String query(Pager pager, String name ,HttpServletRequest request){
		request.setAttribute("pager", addressCenterService.queryPager(pager, name));
		request.setAttribute("name", name);
		return "address_center_list";
	}
	
	@RequestMapping("checkName")
	@ResponseBody
	public boolean checkName(String oldValue, String name){
		if(name.equalsIgnoreCase(oldValue)){
			return true;
		}
		return addressCenterService.queryByName(name) == null;
	}
	
	@RequestMapping("queryByCenterType")
	@ResponseBody
	public List<AddressCenterPO> queryByCenterType(int centerType){
		return addressCenterService.queryByCenterType(centerType);
	}
	
}
