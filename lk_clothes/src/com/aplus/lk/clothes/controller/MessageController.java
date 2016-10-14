package com.aplus.lk.clothes.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aplus.lk.clothes.bean.MessageDto;
import com.aplus.lk.clothes.bean.MessageRequest;
import com.aplus.lk.clothes.bean.MessageVo;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.AddressCenterPO;
import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.AdminService;
import com.aplus.lk.clothes.service.IMessageSerivce;

@Controller
@RequestMapping(value="message")
public class MessageController {

	@Autowired
	private AddressCenterService addressCenterService;
	@Autowired
	private IMessageSerivce messageSerivce;
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="add")
	public String add(HttpServletRequest request){
		List<AddressCenterPO> idAndName = addressCenterService.queryAllOfIdAndName();
		request.setAttribute("idAndName",idAndName);
		List<Map<String, Object>> memberInfo = messageSerivce.queryAllPhone();
		request.setAttribute("memberInfo", memberInfo);
		return "message_input";
	}
	@RequestMapping(value="factoryInput")
	public String factoryInput(HttpServletRequest request){
		return "message_factory_input";
	}
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String publishMessage(MessageVo param,HttpServletRequest request){
		
		 String title=request.getParameter("title");
		 String  previewImageUrl=request.getParameter("previewImageUrl");
		 String messageType=request.getParameter("messageType");
		 String communityId=request.getParameter("communityId");//社区
		 String  sendObject=request.getParameter("sendObject");
		 String mobile=request.getParameter("mobile");
		 String orderNumber = request.getParameter("orderNumber");
		 MessageRequest message = new MessageRequest();
		 message.setCommunityId(communityId);
		 message.setMessageType(messageType);
		 message.setMobile(mobile);
		 message.setPreviewImageUrl(previewImageUrl);
		 message.setOrderNumber(orderNumber);
		 message.setSendObject(sendObject);
		 message.setTitle(title);
		Admin admin = adminService.getLoginAdmin();
		message.setPublisher(admin.getName());
		message.setContents(param.getContents());
		messageSerivce.save(message);
		return "redirect:query";
	}
	@RequestMapping("factoryReplyWashOrder")
	public String factoryReplyWashOrder(MessageVo param,HttpServletRequest request){
		 String orderNumber = request.getParameter("orderNumber");
		 MessageRequest message = new MessageRequest();
		 message.setMessageType("1");
		 message.setOrderNumber(orderNumber);
		 message.setSendObject("4");
		Admin admin = adminService.getLoginAdmin();
		message.setPublisher(admin.getName());
		message.setContents(param.getContents());
		messageSerivce.save(message);
		return "redirect:query";
	}
	@RequestMapping("query")
	public String query(Pager pager,Integer msgType,HttpServletRequest request){
		pager=messageSerivce.query(pager, msgType);
		request.setAttribute("pager", pager);
		request.setAttribute("msgType", msgType);
		return "message_list";
	}
	
	@RequestMapping("view")
	public String view(long id,HttpServletRequest request){
		MessageDto dto = messageSerivce.messageDetail(id);
		request.setAttribute("message", dto);
		return "message_detail_view";
	}
	
	@RequestMapping("resend")
	public String resend(long id,HttpServletRequest request){
		MessageDto dto = messageSerivce.messageDetail(id);
		request.setAttribute("message", dto);
		List<AddressCenterPO> idAndName = addressCenterService.queryAllOfIdAndName();
		request.setAttribute("idAndName",idAndName);
		List<Map<String, Object>> memberInfo = messageSerivce.queryAllPhone();
		request.setAttribute("memberInfo", memberInfo);
		return "message_input";
	}
}
