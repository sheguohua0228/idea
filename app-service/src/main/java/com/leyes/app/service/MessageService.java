package com.leyes.app.service;

import java.util.List;

import com.leyes.app.dto.message.MessageDetailDto;
import com.leyes.app.dto.message.MessageDto;
import com.leyes.app.dto.message.MessageInfoDto;
import com.leyes.app.dto.message.MessageReadInfoDto;

/**
 * 消息中心接口
 * 
 * @TypeName: MessageService
 * @Description: TODO
 * @author：Administrator
 * @date 2016年7月21日 下午6:58:42
 * 
 */
public interface MessageService {

	/**
	 * 查看消息中心消息列表
	 * 
	 * @Title: queryMessage
	 * @Description: TODO
	 * @param userId
	 * @param messageType
	 * @param page
	 * @param comunityId
	 * @return
	 * @throws Exception
	 * @return List<MessageDto>
	 * @throws
	 */
	public List<MessageDto> queryMessage(String userId, int messageType,
			String communityId, int page) throws Exception;

	/**
	 * 查看详细消息
	 * 
	 * @Title: queryMessageDetail
	 * @Description: TODO
	 * @param messageId
	 * @return
	 * @throws Exception
	 * @return MessageDetailDto
	 * @throws
	 */
	public List<MessageDetailDto> queryMessageDetail(String messageId,
			String userId) throws Exception;

	/**
	 * 查看未读消息数量
	 * 
	 * @Title: queryUnreadMessageNumber
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @throws Exception
	 * @return MessageReadInfoDto
	 * @throws
	 */
	public MessageReadInfoDto queryUnreadMessageNumber(String userId)
			throws Exception;

	public void createMessageRecevieInfo(String userId, String communityId,
			List<String> messageIds) throws Exception;

	public List<String> queryCommunityMessageId(String communityId)
			throws Exception;

	/**
	 * 删除消息
	 * 
	 * @Title: deleteMessage
	 * @Description: TODO
	 * @param messageId
	 * @param userId
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteMessage(String messageId, String userId) throws Exception;

	
	public void saveMessage(MessageInfoDto messageInfoDto)throws Exception;
}
