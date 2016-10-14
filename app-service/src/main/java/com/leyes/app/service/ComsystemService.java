package com.leyes.app.service;

 
import java.math.BigDecimal;
import java.util.List;

import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.comsystem.BannerDto;
import com.leyes.app.dto.comsystem.CommunityDto;
import com.leyes.app.dto.comsystem.CommunitySearchDto;
import com.leyes.app.dto.comsystem.CouponStatusDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.comsystem.OrderDto;
import com.leyes.app.dto.comsystem.PayResultDto;
import com.leyes.app.dto.comsystem.PushMessageDto;
import com.leyes.app.dto.comsystem.RechargeOrderDto;
import com.leyes.app.dto.comsystem.ServiceChargeDto;
import com.leyes.app.dto.comsystem.TemplateDto;
import com.leyes.app.dto.comsystem.TopicDto;
import com.leyes.app.dto.comsystem.VersionInfoDto;
import com.leyes.app.dto.member.CardPackageDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.dto.shop.CategoryDto;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PhoneCodeKey;
import com.leyes.app.enums.SMSTemplateEnum;
import com.leyes.app.enums.TradeType;

public interface ComsystemService {

	/**
	 * @throws Exception
	 *             检查客户端版本是否有更新
	 * @Title: checkUpdate
	 * @Description: TODO
	 * @param client
	 * @param versionCode
	 * @return
	 * @return VersionInfoDto
	 * @throws
	 */
	
	public VersionInfoDto checkUpdate(String client,int versionCode,int clientType) throws Exception;
    /**
     * 获取主页配置模版
    * @Title: queryTemplate 
    * @Description: TODO
    * @param comunityId
    * @return
    * @throws Exception
    * @return TemplatesDto    
    * @throws
     */
	 
	public List<TemplateDto> queryTemplate(String communityId) throws Exception;
	/**
	 *  获取banner
	* @Title: queryBanner 
	* @Description: TODO
	* @param versionCode
	* @param client
	* @param channel
	* @return
	* @throws Exception
	* @return List<BannerDto>    
	* @throws
	 */
	public List<BannerDto> queryBanner() throws Exception;
 
	 /**
	  * 通过id查看社区
	 * @Title: queryCommunityById 
	 * @Description: TODO
	 * @param communityId
	 * @return
	 * @throws Exception
	 * @return CommunityDto    
	 * @throws
	  */
	public CommunityDto queryCommunityById(String communityId) throws Exception;
	
	/**
	 *  检查客户端主页模板
	* @Title: queryTopics 
	* @Description: TODO
	* @param communityId
	* @return
	* @throws Exception
	* @return TopicsDto    
	* @throws
	 */
	public List<TopicDto> queryTopics(String communityId) throws Exception;
	
	public String queryPropertyInfo(String communityId)throws Exception;
	 /**
	  * 按类型查看积分规则
	 * @Title: queryIntegralRule 
	 * @Description: TODO
	 * @param type
	 * @param sourceType
	 * @return
	 * @return IntegralRuleDto    
	 * @throws
	  */
	public IntegralRuleDto queryIntegralRule(TradeType type,IntegralSourceType sourceType)throws Exception;
	 
    /**
     * 推送
    * @Title: push 
    * @Description: TODO
    * @param pushMessage
    * @return
    * @throws Exception
    * @return boolean    
    * @throws
     */
	public boolean push(PushMessageDto pushMessage) throws Exception;

 
	public void sendSMS(String[] phone,SMSTemplateEnum template,List<String[]> params)throws Exception;
	/**
	 * 获取卡券列表
	* @Title: queryCoupons 
	* @Description:  
	* @param userId
	* @throws Exception
	* @return void    
	* @throws
	 */
	public List<CouponDto> queryCoupons(List<CardPackageDto> list,BigDecimal price,
			int sence,String photoType, int page) throws Exception;
	/**
	 * 根据id查看卡券
	* @Title: queryCoupon 
	* @Description:  
	* @param couponId
	* @return
	* @throws Exception
	* @return CouponsDto    
	* @throws
	 */
	public CouponDto queryCoupon(String couponId) throws Exception;
	/**
	 * 通过卡券的密码查看未被使用的卡券
	* @Title: queryCouponByPassword 
	* @Description: TODO
	* @param password
	* @return
	* @throws Exception
	* @return CouponStatusDto    
	* @throws
	 */
	public CouponStatusDto queryCouponByPassword(String password)throws Exception;
	
	public CouponStatusDto queryRechargeCardByPassword(String password)throws Exception;
	
	public boolean isUseRecommendCode(String userId,String code,int type)throws Exception;
	/**
	 * 使用卡券
	* @Title: useCoupon 
	* @Description: TODO
	* @param coupon
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public void updateCoupon(CouponDto couponDto)throws Exception;
	/**
	 * 修改卡券的状态（将卡券修改为已绑定用户）
	* @Title: updateCouponStatus 
	* @Description: TODO
	* @param couponId
	* @throws Exception
	* @return void    
	* @throws
	 */
	public void updateCouponStatus(String couponId)throws Exception;
	
	public void updateRechargeCardUseStatus(String cardId)throws Exception;
	
	public void saveUseRecommendRecord(String userId,String code,int type)throws Exception;
	/**
	 * 订单支付
	* @Title: payOrder 
	* @Description: TODO
	* @param order
	* @param balanceNumber 使用余额量
	* @param integralNumber 使用几分量
	* @param ratio 积分使用比例
	* @param payType 支付方式（微信、支付宝）
	* @return
	* @throws Exception
	* @return PayResultDto    
	* @throws
	 */
	public PayResultDto payOrder(OrderDto order,BigDecimal balanceNumber,
			int integralNumber,BigDecimal ratio,int payType) throws Exception;

	public boolean aliPayNotifyVerify(String notifyId)throws Exception;
	/**
	 * 
	* @Title: queryCategoryByType 
	* @Description: TODO
	* @param communityId
	* @param type 商家的分类还是商品的分类
	* @return
	* @return List<CategoryDto>    
	* @throws
	 */
	public List<CategoryDto> queryCategoryByType(int type) throws Exception;
	
	public ServiceChargeDto queryServiceChargeByOrderType(OrderType orderType)throws Exception;
	
	public List<CouponDto> queryUserGroupCoupons(String userId)throws Exception;

	public List<CommunityDto> queryCommunityByIds(List<String> communityIds)throws Exception;
	
	public List<CommunityDto> queryChirldCommunity(String communityId)throws Exception;
	
	public String queryParentCommunityId(String communityId)throws Exception;
	
	public List<String> queryChildCategoryId(String categoryId)throws Exception;
	
	public RechargeOrderDto createRechargeOrder(String userId,BigDecimal price,int payType)throws Exception;

	public OrderPayStatusDto queryOrderStatusByOrderNumber(String orderNumber)throws Exception;
	
	public void updateRechargeOrderPayStatus(OrderPayStatusDto orderPayStatus);
	
	public void sendPhoneCode(String phone,PhoneCodeKey phoneCodeKey)throws Exception;
	
	public void validatePhoneCode(String phone,String code,PhoneCodeKey phoneCodeKey)throws Exception;

	public List<CommunitySearchDto> searchCommunity(String keyWord)throws Exception;
}