package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.service.AdminService;
import com.aplus.lk.clothes.utils.StringUtils;

@Controller
@RequestMapping("adminProfile")
public class AdminProfileController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping("checkCurrentPassword")
	@ResponseBody
	public boolean checkCurrentPassword(String currentPassword){
		Admin admin = adminService.queryAdminById(adminService.getLoginAdmin().getId());
		if(StringUtils.isNotEmpty(currentPassword) && DigestUtils.md5Hex(currentPassword).equals(admin.getPassword())){
			return true;
		}
		return false;
	}
	
	@RequestMapping("edit")
	public String edit(HttpServletRequest request){
		request.setAttribute("admin", adminService.queryAdminById(adminService.getLoginAdmin().getId()));
		return "admin_profile_input";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Admin admin,String currentPassword){
		AjaxResult result = new AjaxResult(Status.success, "修改成功", null);
		Admin persistentAdmin = adminService.queryAdminById(adminService.getLoginAdmin().getId());
		if(StringUtils.isNotEmpty(currentPassword) && StringUtils.isNotEmpty(admin.getPassword())){
			if(!DigestUtils.md5Hex(currentPassword).equals(persistentAdmin.getPassword())){
				result.setStatus(Status.error);
				result.setMessage("当前密码输入错误");
				return result;
			}
		}
		admin.setId(persistentAdmin.getId());
		adminService.updatePasswordAndEmail(admin);
		return result;
	}
	
}
