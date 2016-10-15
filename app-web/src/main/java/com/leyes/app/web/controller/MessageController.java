package com.leyes.app.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyes.app.annotation.Security;
import com.leyes.app.dto.message.MessageDetailDto;
import com.leyes.app.dto.message.MessageDto;
import com.leyes.app.dto.message.MessageReadInfoDto;
import com.leyes.app.service.MessageService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.message.DeleteMessageRequest;
import com.leyes.app.web.request.message.QueryMessageDetailRequest;
import com.leyes.app.web.request.message.QueryMessageRequest;
import com.leyes.app.web.utils.ReturnResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//@Controller
@RequestMapping("message")
@Api(value = "message-api", description = "消息中心模块接口API", position = 1)
public class MessageController extends BaseController {
	
	private MessageService messageService = SpringContextUtils.getBean("messageService");
	
	/**
	 * 
	* @Title: queryMessage 
	* @Description: 查看消息中心列表
	* @param request
	* @return
	* @throws Exception
	* @return ReturnResult    
	* @throws
	 */
	@ApiOperation(value = "查看消息中心列表", notes = "查看消息中心列表", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryMessage", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryMessage(@RequestBody QueryMessageRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String communityId = sessionContextUtils.getCommunityId();
		List<MessageDto> message = messageService.queryMessage(userId, request.getMessageType(), communityId, request.getPage());
		return ReturnResult.SUCCESS(message);
	}
	
	/**
	 * 
	* @Title: queryMessageDetail 
	* @Description: 查看消息中心详细消息
	* @param request
	* @return
	* @throws Exception
	* @return ReturnResult    
	* @throws
	 */
	@ApiOperation(value = "查看消息中心详细消息", notes = "查看消息中心详细消息", response = ReturnResult.class, position = 2)
	@RequestMapping(value = "queryMessageDetail", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryMessageDetail(@RequestBody QueryMessageDetailRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		List<MessageDetailDto> messageDetail = messageService.queryMessageDetail(request.getMessageId(),userId);
		return ReturnResult.SUCCESS(messageDetail);
	}
	
	/**
	 * 
	* @Title: queryUnreadMessageNumber 
	* @Description: 查看未读消息数量
	* @param request
	* @return
	* @throws Exception
	* @return ReturnResult    
	* @throws
	 */
	@ApiOperation(value = "查看未读消息数量", notes = "查看未读消息数量", response = ReturnResult.class, position = 3)
	@RequestMapping(value = "queryUnreadMessageNumber", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryUnreadMessageNumber() throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		MessageReadInfoDto unreadNum = messageService.queryUnreadMessageNumber(userId);
		return ReturnResult.SUCCESS(unreadNum);
	}
	
	/**
	 * 
	* @Title: queryMessageDetail 
	* @Description: 删除消息
	* @param request
	* @return
	* @throws Exception
	* @return ReturnResult    
	* @throws
	 */
	@ApiOperation(value = "删除消息", notes = "删除消息", response = ReturnResult.class, position = 4)
	@RequestMapping(value = "deleteMessage", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deleteMessage(@RequestBody DeleteMessageRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		 messageService.deleteMessage(request.getMessageId(),userId); 
		return ReturnResult.SUCCESS();
	}
}
