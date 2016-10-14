package com.aplus.lk.clothes.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.AjaxResult.Status;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.entity.Message;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.CommunityService;

@Controller
@RequestMapping("community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;
	@Autowired
	private AddressCenterService addressCenterService;
	
	@RequestMapping("add")
	public String add(HttpServletRequest request){
		List<AddressCenterPO> idAndName = addressCenterService.queryAllOfIdAndName();
		request.setAttribute("idAndName",idAndName);
		return "community_message_input";
	}
	
	@RequestMapping("save")
	public String save(@ModelAttribute Message message, HttpServletRequest request){
		if(message!=null){
			communityService.save(message);
		}
		return "redirect:query";
	}
	
	@RequestMapping("query")
	public String query(Pager pager,String communityId,String messageName,HttpServletRequest request){
		pager=communityService.queryPager(pager, communityId,messageName);
		request.setAttribute("pager", pager);
		request.setAttribute("communityId", communityId);
		List<AddressCenterPO> idAndName = addressCenterService.queryAllOfIdAndName();
		request.setAttribute("idAndName",idAndName);
		if(communityId!=null&& communityId!=""){
			request.setAttribute("communityId", communityId);
		}
		if(messageName!=null&&messageName!=""){
			request.setAttribute("messageName", messageName);
		} 
		return "community_message_list";
	}
	@RequestMapping("edit")
	public String edit(String messageId,HttpServletRequest request){
		Message message = communityService.findMessageById(messageId);
		request.setAttribute("communityMessage", message);
		List<AddressCenterPO> idAndName = addressCenterService.queryAllOfIdAndName();
		request.setAttribute("idAndName",idAndName);
		request.setAttribute("messageId", messageId);
		return "community_message_input";
	}
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResult delete(long ids[]){
		AjaxResult result = new AjaxResult();
		communityService.deleteMessage(ids);
		result.setStatus(Status.success);
		result.setMessage("删除成功");
		return result;
	}
	@RequestMapping("update")
	public String update(@ModelAttribute Message communityMessage, HttpServletRequest request){
		if(communityMessage!=null){
			communityMessage.setModifyDate(new Date());
			communityService.updateMessage(communityMessage);
		}
		return "redirect:query";
	}
}
