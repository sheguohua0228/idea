package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Message;

public interface CommunityMapper {

	public int save(Message communityMessage);
	
	public List<Message> queryByCommunityName(Map<String,Object> params);
	
	public int queryCount(Map<String,Object> params);
	
	/**
	 * @Title: deleteByAddressCenterId
	 * @Description: TODO 根据社区删除社区告示
	 * @param @param addressCenterId 社区ID
	 * @return void
	 * @throws
	 */
	public void deleteByAddressCenterIds(long[] addressCenterIds);
	
	public void deleteMessage(long ids[]);
	
	public Message findMessageById(String messageId);
	
	public void updateMessage(Message communityMessage);
	
	public List<Map<String,Object>> queryHouseholdOfCommunity(long communityId);
}
