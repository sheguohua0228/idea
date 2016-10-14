package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Message;

public interface CommunityService {

	public int save(Message communityMessage);
	
	public Pager queryPager(Pager pager, String communityId,String messageName);
	
	public Message findMessageById(String messageId);
	
	public void deleteMessage(long ids[]);
	
	public void updateMessage(Message communityMessage);
}
