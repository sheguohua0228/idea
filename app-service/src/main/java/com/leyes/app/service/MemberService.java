package com.leyes.app.service;

import java.util.Date;
import java.util.List;

import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.member.AccountDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.dto.member.CardPackageDto;
import com.leyes.app.dto.member.MemberDto;
import com.leyes.app.dto.member.MemberInfoDto;
import com.leyes.app.dto.member.MemberRankDto;
import com.leyes.app.dto.member.UserInfoDto;

public interface MemberService {

	/**
	 * 查看用户是否存在
	* @Title: memberExist 
	* @Description:  
	* @param phone
	* @return
	* @throws Exception
	* @return boolean    
	* @throws
	 */
	public boolean memberExist(String phone)throws Exception;
	/**
	 * 创建用户
	* @Title: createMember 
	* @Description:  
	* @param phone
	* @param deviceToken
	* @param client
	* @param password
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public String createMember(String phone,String deviceToken,String client,String password)throws Exception;
	/**
	 * 修改用户友盟推送token
	* @Title: updateMemberDeviceToken 
	* @Description: TODO
	* @param userId
	* @param deviceToken
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public void updateMemberDeviceToken(String userId,String deviceToken)throws Exception;
	/**
	 * 通过手机号码查看用户ID
	* @Title: queryUserIdByPhone 
	* @Description:  
	* @param phone
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public String queryUserIdByPhone(String phone)throws Exception;
	/**
	 * 登录
	* @Title: login 
	* @Description:  
	* @param phone
	* @param password
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public String login(String phone,String password)throws Exception;
	/**
	 * 修改头像
	* @Title: updateMemberHeadImage 
	* @Description:  
	* @param userId
	* @param imageUrl
	* @throws Exception
	* @return void    
	* @throws
	 */
	public void updateMemberHeadImage(String userId,String imageUrl)throws Exception;
	/**
	 * 修改用户名
	* @Title: updateMemberUserName 
	* @Description:  
	* @param userId
	* @param userName
	* @throws Exception
	* @return void    
	* @throws
	 */
	public void updateMemberUserName(String userId,String userName)throws Exception;
	/**
	 * 修改密码
	* @Title: updateMemberPassword 
	* @Description: 
	* @param userId
	* @param password
	* @throws Exception
	* @return void    
	* @throws
	 */
	public void updateMemberPassword(String userId,String password)throws Exception;
	/**
	 * 添加地址
	 * 
	 * @Title: addAddress
	 * @Description:  
	 * @param userName 用户名
	 * @param phone 手机号
	 * @param address 地址（社区名）
	 * @param addressDetail 详细地址
	 * @param communityId 社区ID
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public void addAddress(String userId,AddressInfoDto addressInfoDto) throws Exception;
	/**
	 * 判断该地址是否下过订单
	* @Title: queryHasOrderedAddressCount 
	* @Description:  
	* @param addressId
	* @return
	* @throws
	 */
	public boolean queryAddressHasOrdered(String addressId)throws Exception;
	/**
	 * 修改地址
	 * 
	 * @Title: modifyAddress
	 * @Description:  
	 * @param userName
	 * @param phone
	 * @param communityName
	 * @param addressDetail
	 * @param communityId
	 * @param addressId
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateAddress(AddressInfoDto addressInfoDto)throws Exception;

	/**
	 * 删除地址
	 * 
	 * @Title: deleteAddress
	 * @Description:  
	 * @param addressId
	 * @return
	 * @throws Exception
	 * @return ReturnResult
	 * @throws
	 */
	public void deleteAddress(String addressId) throws Exception;

	/**
	 * 获取用户地址列表
	 * 
	 * @Title: queryAddress
	 * @Description:  
	 * @param userId
	 * @param page
	 * @return
	 * @throws Exception
	 * @return List<AddressInfoDto>
	 * @throws
	 */
	public List<AddressInfoDto> queryAddress(String userId, int page)
			throws Exception;

	/**
	 * 查看地址详情
	* @Title: queryAddressById 
	* @Description:  
	* @param userId
	* @param addressId
	* @return
	* @throws Exception
	* @return AddressInfoDto    
	* @throws
	 */
	public AddressInfoDto queryAddressById(String addressId)throws Exception;
	
	/**
	 * 获取用户的社区id
	* @Title: queryUserCommunity 
	* @Description:  
	* @param userId
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public String queryUserCommunity(String userId) throws Exception;

	/**
	 * 修改用户的社区信息 
	* @Title: modifyUserCommunity 
	* @Description:  
	* @param userId
	* @param communityId
	* @throws Exception
	* @return void    
	* @throws
	 */
	public void updateUserCommunity(String userId, String communityId)
			throws Exception;

	/**
	 * 获取用户信息
	* @Title: queryUserInfo 
	* @Description:  
	* @param userId
	* @return
	* @throws Exception
	* @return UserInfoDto    
	* @throws
	 */
	public UserInfoDto queryUserInfo(String userId) throws Exception;

	/**
	 * 获取账户信息 
	* @Title: queryAccount 
	* @Description:  
	* @param userId
	* @param page
	* @return
	* @throws Exception
	* @return AccountDto    
	* @throws
	 */
	public AccountDto queryAccount(String userId) throws Exception;

	/**
	 * 获取卡券列表
	* @Title: queryCoupons 
	* @Description:  
	* @param userId
	* @throws Exception
	* @return void    
	* @throws
	 */
	public List<CardPackageDto> queryCoupons(String userId,Integer time) throws Exception;
	 
	/**
	 * 修改用户积分
	* @Title: updateMemberIntegral 
	* @Description:  
	* @param userId
	* @param integral 
	* @throws Exception
	* @return void    
	* @throws
	 */
	public void updateMemberAccount(IncomeOutFlowDto incomeOutFlowDto)throws Exception;

	public void updateMemberExp(String userId,int expValue)throws Exception;
	
	public void addCoupon(String userId,String couponId,int time,Date invalidTime)throws Exception; 
	
	public void useCoupon(String userId,String couponId)throws Exception;
	
	public List<UserInfoDto> batchQueryMemberInfo(List<String> userIds)throws Exception;
	
	public MemberDto queryMemberInfo(String userId)throws Exception;
	
	public MemberRankDto queryMemberRankInfo(String userId)throws Exception;
	
	public String queryMemberMobileById(String userId)throws Exception;
	
	public void updateAddressRemark(String addressId,String remark)throws Exception;

	public MemberInfoDto queryMemberAccountInfo(String phone)throws Exception;
}
