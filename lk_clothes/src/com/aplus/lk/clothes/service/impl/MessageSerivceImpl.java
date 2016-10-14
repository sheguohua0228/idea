package com.aplus.lk.clothes.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.aplus.lk.clothes.bean.MessageContentVo;
import com.aplus.lk.clothes.bean.MessageDto;
import com.aplus.lk.clothes.bean.MessageRequest;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Message;
import com.aplus.lk.clothes.entity.MessageContent;
import com.aplus.lk.clothes.entity.MessageReceiveInfo;
import com.aplus.lk.clothes.mapper.ClothesOrderMapper;
import com.aplus.lk.clothes.mapper.CommunityMapper;
import com.aplus.lk.clothes.mapper.MessageMapper;
import com.aplus.lk.clothes.service.IMessageSerivce;
import com.aplus.lk.clothes.utils.JPushUtils;
import com.aplus.lk.clothes.utils.SendMessageMOMingUtil;

@Service
public class MessageSerivceImpl implements IMessageSerivce {

	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	private ClothesOrderMapper clothesOrderMapper;

	private Logger logger = Logger.getLogger(getClass());
	
	Asny t =null;
	
	@Override
	public void save(MessageRequest messageRequest) {
		Message message = new Message();
		Date date = new Date();
		message.setCreateDate(date);
		message.setModifyDate(date);
		message.setPublisher(messageRequest.getPublisher());
		message.setMessageStatus(0);
		message.setMessageType(Integer.parseInt(messageRequest.getMessageType()));
		message.setPreviewImageUrl(messageRequest.getPreviewImageUrl());
		String content=messageRequest.getTitle();
		 if(messageRequest.getSendObject().equals("4")){
			 content="订单号为："+messageRequest.getOrderNumber()+"的乐E洗衣订单回复";
		 }
		message.setTitle(content);
		messageMapper.save(message);
		 
		long id = message.getId();
		System.out.println("插入后消息id为"+id);
		 
		if (id != 0) {
			//创建消息内容
			MessageContent msgContent = null;
			List<MessageContent> lists=null;
			MessageContentVo[] contents = messageRequest.getContents();
			if(contents!=null){
				lists=new ArrayList<MessageContent>();
				for(MessageContentVo conts:contents){
					if(conts==null){
						break;
					}
					String contentsVo=conts.getContent();
					if(StringUtils.isNotBlank(contentsVo)){
					msgContent =new MessageContent();
					msgContent.setCreateDate(date);
					msgContent.setModifyDate(date);
					
					
					msgContent.setContent(contentsVo);
					int type=conts.getType();
					msgContent.setContentType(type);
					if(type==0){
						content+=contentsVo;
					}
					msgContent.setMessageId(id);
					msgContent.setSort(conts.getOrder());
					lists.add(msgContent);
 
				}
				}
			}
			try {
				messageMapper.saveContent(lists);
			} catch (Exception e) {
				logger.error("保存消息内容失败:{}"+e.getMessage());
			}
			String mobiles = messageRequest.getMobile();
			String sendObject = messageRequest.getSendObject();
			String communityId = messageRequest.getCommunityId();
			if(StringUtils.isNotBlank(sendObject)){
			String orderNumber =null;
			List<Map<String, Object>> memberIdAndRegisterIds = messageRequest.getMemberIdAndRegisterId();
			if(sendObject.equals("1")&&StringUtils.isBlank(mobiles)){//查找全体用户
				memberIdAndRegisterIds=queryAllPhone();
				saveReceiveInfo(id,null,memberIdAndRegisterIds);
			}else if(sendObject.equals("3")||StringUtils.isNotBlank(mobiles)){//填写的所有手机号的用户
				String[] mobile = mobiles.split(";");
				memberIdAndRegisterIds=messageMapper.queryRegistIdByMobile(mobile);
				saveReceiveInfo(id,null, memberIdAndRegisterIds);
			}else if(sendObject.equals("4")){//订单消息
				orderNumber = messageRequest.getOrderNumber();
				if(StringUtils.isNotBlank(orderNumber)){
				   memberIdAndRegisterIds =clothesOrderMapper.queryInfoByOrderNo(orderNumber);
				   saveReceiveInfo(id,orderNumber, memberIdAndRegisterIds);
				 }
			}else if(sendObject.equals("2") && StringUtils.isNotBlank(communityId) ){//社区消息
				memberIdAndRegisterIds =messageMapper.queryHouseholdOfCommunity(Long.parseLong(communityId));
				saveCommunityRecevieInfo(id, communityId);
			}
			 	ExecutorService executor = Executors.newCachedThreadPool();
				// 异步推送用户消息
				Asny asny  =new Asny(message,memberIdAndRegisterIds);
				executor.execute(asny);
				executor.shutdown();
			}
			
		}

	}

	@Override
	public List<Map<String, Object>> queryAllPhone() {
		return messageMapper.queryAllPhone();
	}

	@Override
	public Pager query(Pager pager,Integer msgType) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo()-1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("messageType", msgType);
		pager.setDataList(messageMapper.query(parameterMap));
		pager.setTotalRecords(messageMapper.queryCount(parameterMap));
		return pager;
	}
	
	/**
	 * 保存社区接收信息
	* @Title: saveCommunityRecevieInfo 
	* @Description: TODO
	* @param messageId
	* @param communityId
	* @return void    
	* @throws
	 */
	private void saveCommunityRecevieInfo(long messageId,String communityId){
		List<MessageReceiveInfo> list =new ArrayList<MessageReceiveInfo>();
		if(StringUtils.isNotBlank(communityId)){
			MessageReceiveInfo info = null;
			info = new MessageReceiveInfo();
			info.setMessageId(messageId);
			info.setRead(true);
			info.setStatus(0);
			if(StringUtils.isNotBlank(communityId)){
				info.setCommunityId(Long.parseLong(communityId));
			}
			list.add(info);
			messageMapper.saveInfo(list);
			
		}
	}
	/**
	 * 保存消息接收信息
	* @Title: saveReceiveInfo 
	* @Description: TODO
	* @param messageId
	* @param memberIdAndRegisterId
	* @return void    
	* @throws
	 */
	private void saveReceiveInfo(long messageId,String orderNumber,List<Map<String, Object>> memberIdAndRegisterId){
		List<MessageReceiveInfo> list =null;
		if (!CollectionUtils.isEmpty(memberIdAndRegisterId)) {
			list = new ArrayList<MessageReceiveInfo>();
			MessageReceiveInfo info = null;
			for (Map<String, Object> data : memberIdAndRegisterId) {
				String memberId = String.valueOf(data.get("id"));
				info = new MessageReceiveInfo();
				info.setMemberId(memberId);
				info.setMessageId(messageId);
				info.setRead(false);
				if(StringUtils.isNotBlank(orderNumber)){
					info.setOrderNumber(orderNumber);
				}
				list.add(info);
			}
			messageMapper.saveInfo(list);
		}
	}
	
	protected class Asny extends Thread {

		private Message message;
		private List<Map<String, Object>> memberIdAndRegisterId;
		 
		public Asny(Message message,List<Map<String, Object>> memberIdAndRegisterId) {
			super();
			this.message = message;
			this.memberIdAndRegisterId = memberIdAndRegisterId;
		}

		public Asny() {
			super();
		}

		@Override
		public void run() {
			long messageId = message.getId();
			String title = message.getTitle();
			Map<String, String> extras = new HashMap<String, String>();
			extras.put("type", "1");
			extras.put("messageId", String.valueOf(messageId));
		 
			if (!CollectionUtils.isEmpty(memberIdAndRegisterId)) {
				for (Map<String, Object> data : memberIdAndRegisterId) {
					String registrationId =(String)(data.get("registrationID"));
					if(StringUtils.isNotBlank(registrationId)){
						JPushUtils.sendPushToClient("乐一下",title, null, registrationId, extras);
					}
				}
			}
		}
	}

	@Override
	public MessageDto messageDetail(long messageId) {
		MessageDto dto = new MessageDto();
		Message message = messageMapper.queryById(messageId);
		dto.setCreateDate(message.getCreateDate());
		dto.setTitle(message.getTitle());
		dto.setPreviewImageUrl(message.getPreviewImageUrl());
		dto.setPublisher(message.getPublisher());
		dto.setMessageType(message.getMessageType());
		dto.setMessageStatus(message.getMessageStatus());
		dto.setContents(messageMapper.queryContentByMessageId(messageId));
		dto.setReceiverNum(messageMapper.queryReceiveNumByMessageId(messageId));
		return dto;
	}
	 
}
