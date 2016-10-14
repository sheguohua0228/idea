package com.aplus.lk.clothes.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.comet4j.core.CometAppListener;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.CometServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.service.AdminService;
import com.aplus.lk.clothes.service.GroupManagerService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private GroupManagerService groupManagerService;
	
	@RequestMapping("query")
	public String query(Pager pager, String username, String name, HttpServletRequest request){
		request.setAttribute("pager", adminService.queryPager(pager, username, name));
		request.setAttribute("username", username);
		request.setAttribute("name", name);
		return "admin_list";
	}
	
	@RequestMapping("add")
	public String add(HttpServletRequest request){
		request.setAttribute("groupList", groupManagerService.queryAll());
		return "admin_input";
	}
	
	@RequestMapping("edit")
	public String edit(String id, HttpServletRequest request){
		request.setAttribute("admin", adminService.queryAdminWithActionIdById(id));
		request.setAttribute("groupList", groupManagerService.queryAll());
		return "admin_input";
	}
	
	@RequestMapping("save")
	public String save(Admin admin, HttpServletRequest request){
		adminService.save(admin);
		return "redirect:query";
	}
	
	@RequestMapping("update")
	public String update(Admin admin, HttpServletRequest request){
		adminService.update(admin);
		return "redirect:query";
	}
	
	@RequestMapping("checkUsername")
	@ResponseBody
	public boolean checkUsername(String username){
		return adminService.queryAdminByUsername(username) == null;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(String[] ids){
		AjaxResult result = new AjaxResult(Status.success, "删除成功", null);
		adminService.delete(ids);
		return result;
	}
	
	@RequestMapping("getCallInfo")
	@ResponseBody
	public String getCallInfo(String customerNumber,String cno,HttpServletRequest request){
		final String phone=customerNumber;
		
		CometContext cc = CometContext.getInstance();
		 
		try {
			cc.registChannel(cno);
		} catch (Exception e) {
		}
		
       /* Thread helloAppModule = new Thread(new Runnable() {
			
			@Override
			public void run() {
				  CometEngine engine = CometContext.getInstance().getEngine();  
                  engine.sendToAll(CHANNEL, phone);  
			}
		}, "Sender App Module");  
        helloAppModule.setDaemon(true);  
        helloAppModule.start();  */
		CometEngine engine = CometContext.getInstance().getEngine();  
		
		engine.sendToAll(cno, phone);  
		//engine.sendTo(CHANNEL , engine.getConnection(connId),"Hi,呼入手机号为："+phone);  
		request.getSession().setAttribute("mobiePhone", phone);
		logger.info("来电手机号码为："+customerNumber);
		return "{'reuslt':'success'}";
	}
	 
	 
	 


}
