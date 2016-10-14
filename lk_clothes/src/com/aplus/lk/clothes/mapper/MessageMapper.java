package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Message;
import com.aplus.lk.clothes.entity.MessageContent;
import com.aplus.lk.clothes.entity.MessageReceiveInfo;

public interface MessageMapper {

	public int save(Message message);
	
	public void saveInfo(List<MessageReceiveInfo> info);
	
	public void saveContent(List<MessageContent> contents);
	
	public List<Map<String,Object>> queryAllPhone();
	
	public List<Map<String,Object>> queryRegistIdByMobile(String mobiles[]);
	
	public List<Message> query(Map<String,Object> params);
	
	public int queryCount(Map<String,Object> params);
	
	public List<Map<String,Object>> queryHouseholdOfCommunity(long communityId);
	
	public Message queryById(long messageId);
	
	public List<MessageContent> queryContentByMessageId(long messageId);
	
	public List<MessageReceiveInfo> queryReceiveByMessageId(long messageId);
	
	public int queryReceiveNumByMessageId(long messageId);
}
