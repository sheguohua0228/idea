package com.aplus.lk.clothes.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Message;
import com.aplus.lk.clothes.mapper.CommunityMapper;
import com.aplus.lk.clothes.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityMapper communityMapper;

	@Override
	public int save(Message communityMessage) {
		Date date = new Date();
		communityMessage.setCreateDate(date);
		communityMessage.setModifyDate(date);
		return communityMapper.save(communityMessage);
	}

	@Override
	public Pager queryPager(Pager pager, String communityId,String messageName) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("communityId", communityId);
		parameterMap.put("messageName", messageName);
		parameterMap.put("firstResult",
				(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		pager.setDataList(communityMapper.queryByCommunityName(parameterMap));
		pager.setTotalRecords(communityMapper.queryCount(parameterMap));
		return pager;
	}

	@Override
	public Message findMessageById(String messageId) {
		return communityMapper.findMessageById(messageId);
	}
	
	@Override
	public void deleteMessage(long ids[]){
		communityMapper.deleteMessage(ids);
	}

	@Override
	public void updateMessage(Message communityMessage) {
		communityMapper.updateMessage(communityMessage);
	}
}
