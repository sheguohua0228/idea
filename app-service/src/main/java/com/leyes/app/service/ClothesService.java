package com.leyes.app.service;

import java.math.BigDecimal;
import java.util.List;

import com.leyes.app.dto.clothes.ClotheOrderExctraDto;
import com.leyes.app.dto.clothes.ClothesAppraiseDto;
import com.leyes.app.dto.clothes.ClothesCategoryDto;
import com.leyes.app.dto.clothes.ClothesOrderDetailDto;
import com.leyes.app.dto.clothes.ClothesOrderDto;
import com.leyes.app.dto.clothes.ClothesOrderPriceDto;
import com.leyes.app.dto.clothes.ClothesOrderUserIdDto;
import com.leyes.app.dto.clothes.ClothesPriceDto;
import com.leyes.app.dto.clothes.ClothesPriceOfCategoryDto;
import com.leyes.app.dto.clothes.DeliveryClothesOrderDto;
import com.leyes.app.dto.clothes.FinishClothesOrderDto;
import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.clothes.TakeClothesOrderDto;
import com.leyes.app.dto.clothes.WashClothesDto;
import com.leyes.app.dto.clothes.WashClothesStatusDto;
import com.leyes.app.dto.employee.ClothesDto;
import com.leyes.app.dto.employee.ClothesNumberDto;
import com.leyes.app.dto.employee.ClothesOrderTimeDto;
import com.leyes.app.dto.employee.ClothesOrdersDto;
import com.leyes.app.dto.employee.TakeClothesDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.enums.ClothesOrderStatus;

/**
 * 洗衣模块接口
* @TypeName: ClothesService 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年7月12日 上午10:54:50 
*
 */
public interface ClothesService {

	/**
	 * 查询衣服分类
	* @Title: queryClothesCategory 
	* @Description: TODO
	* @return
	* @return ClothesCategoryDto    
	* @throws
	 */
	public List<ClothesCategoryDto> queryClothesCategory(String communityId)throws Exception;
	
	 
	/**
	 * 按社区、分类查看衣服价格
	* @Title: queryClothesPrice 
	* @Description: TODO
	* @param communityId
	* @param categoryId
	* @return
	* @return ClothesPriceDto    
	* @throws
	 */
	public List<ClothesPriceDto> queryClothesPrice(String communityId,String categoryId)throws Exception;
	/**
	 * 立即下单
	* @Title: palceOrder 
	* @Description: TODO
	* @param userId
	* @param addressId
	* @return
	* @return boolean    
	* @throws
	 */
	public String placeOrder(String userId,String addressId,String communityId)throws Exception;
	/**
	 * 验证预约时间
	* @Title: checkReserveTime 
	* @Description: TODO
	* @param reserveTime
	* @return
	* @return boolean    
	* @throws
	 */
	public boolean checkReserveTime(long reserveTime)throws Exception;
	/**
	 * 下预约单
	* @Title: palceReserveOrder 
	* @Description: TODO
	* @param userId
	* @param addressId
	* @param reserveTime
	* @return
	* @return boolean    
	* @throws
	 */
	public String placeReserveOrder(String userId,String addressId,long reserveTime,String communityId)throws Exception;
	 /**
	  * 查询订单列表
	 * @Title: queryClothesOrder 
	 * @Description: TODO
	 * @param userId
	 * @param page
	 * @return
	 * @return List<ClothesOrderDto>    
	 * @throws
	  */
	public List<ClothesOrderDto> queryClothesOrder(String userId,int orderType, int page)throws Exception;
	  /**
	  * 查询衣服详情
	 * @Title: queryClothesOrderDetail 
	 * @Description: TODO
	 * @param userId
	 * @param orderId
	 * @return
	 * @return ClothesOrderDetailDto    
	 * @throws
	  */
	public ClothesOrderDetailDto queryClothesOrderDetail(String userId,String orderId)throws Exception;
	
	
	public List<WashClothesDto> queryClothesOrderWash(String orderId)throws Exception;
	
	public List<WashClothesStatusDto> queryClothesOrderWashStatus(String orderId)throws Exception;
	/**
	  * 评价订单
	 * @Title: appraise 
	 * @Description: TODO
	 * @param userId
	 * @param orderId
	 * @param content
	 * @param serviceStar
	 * @return
	 * @return boolean    
	 * @throws
	  */
	public String appraise(String userId,String orderId,String content,int serviceStar)throws Exception;
	 /**
	  * 查看评价
	 * @Title: queryAppraise 
	 * @Description: TODO
	 * @param userId
	 * @param appraiseId
	 * @return
	 * @return AppraiseDto    
	 * @throws
	  */
	public ClothesAppraiseDto queryAppraise(String appraiseId)throws Exception;
	 /**
	  * 删除订单
	 * @Title: deleteOrder 
	 * @Description: TODO
	 * @param orderId
	 * @return
	 * @return boolean    
	 * @throws
	  */
	public boolean deleteOrder(String orderId)throws Exception;
	 
	
	public ClotheOrderExctraDto queryClotheOrderExctra(String orderId)throws Exception;
	
	public ClothesOrderPriceDto queryClothesOrderPrice(String orderId)throws Exception;
	
	public void updateClothesOrderPayStatus(OrderPayStatusDto clothesOrderPayStatus)throws Exception;
	
	public OrderPayStatusDto queryOrderStatusByOrderNumber(String orderNumber)throws Exception;
	
	public String queryUnpaidClothesOrder(String userId)throws Exception;
	
	public void updateClothesOrderVoice(String orderId,String voiceUrl)throws Exception;
	/*******************************小哥端洗衣订单相关******************************************/
	/**
	 * 查询楼盘的预约订单列表
	* @Title: queryBespeakOrder 
	* @Description: TODO
	* @param communityId
	* @return
	* @return List    
	* @throws
	 */
	public List<ClothesOrdersDto> queryBespeakOrder(List<String> communityId,int page)throws Exception;
	
	public List<TakeClothesOrderDto> queryTakeClothesOrder(String employeeId,int page)throws Exception;
	
	public List<ClothesOrdersDto> queryWashingClothesOrder(String employeeId,int page)throws Exception;
	
	public List<DeliveryClothesOrderDto> queryDeliveryClothesOrder(String employeeId,int page)throws Exception;
	
	public List<FinishClothesOrderDto> queryFinishClothesOrder(String employeeId,int page)throws Exception;
	
	public String queryWashStatusFinisher(String orderId,ClothesOrderStatus orderStatus)throws Exception;
	
	public String queryWashStatusOperator(String orderId,ClothesOrderStatus orderStatus)throws Exception;
	
	public void updateWashFinisher(String orderId,String employeeId,ClothesOrderStatus orderStatus)throws Exception;

	public void updateWashOperator(String orderId,String employeeId,ClothesOrderStatus orderStatus)throws Exception;
	
	public void updateWashOperatorFinisher(String orderId,String employeeId,ClothesOrderStatus orderStatus)throws Exception;
	
	public void updateClothesOrderBespeakTime(String orderId,long time,String remark)throws Exception;

	public void updateClothesOrderBackTime(String orderId,long backTime,String remark)throws Exception;
	
	public String queryCommunityIdByOrderId(String orderId)throws Exception;
	
	public void updateClothesOrderBarCode(String orderId,String barCode,int clothesNumber,String cardId)throws Exception;

	public void addWashStatusDescription(String orderId,ClothesOrderStatus orderStatus,String operator,String finisher);

	public ClothesNumberDto queryClothesOrderBarCode(String orderId)throws Exception;
	
	public CouponDto confirmTakeClothes(String orderId,CouponDto card,String remark, String barCode,String employeeId,List<TakeClothesDto> clothesList)throws Exception;

	public void singleClothesCompleted(String orderId,String clothesId)throws Exception;
	
	public void singleClothesBackWash(String orderId,String clothesId)throws Exception;
	
	
	
	public OrderPayStatusDto queryPriceInfoByOrderId(String orderId)throws Exception;
	
	public OrderPayStatusDto handleClothesOrderPayPrice(BigDecimal price,BigDecimal balance,int integral,CouponDto card,BigDecimal ratio)throws Exception;

	public void updateClothesOrderStatus(String orderId,ClothesOrderStatus orderStatus,String employeeId)throws Exception;

	public ClothesOrderUserIdDto queryClothesOrderUserId(String orderId)throws Exception;
	
	public List<ClothesPriceOfCategoryDto> queryAllClothesPrice(String communityId,double discountRatioOne,double discountRatioTwo,boolean useCoupon)throws Exception;

	public String queryClothesOrderAddressId(String orderId)throws Exception;
	
	public ClothesOrderTimeDto queryClothesOrderTakeBackTime(String orderId)throws Exception;
	
	public List<ClothesDto> queryWashClothes(String orderId)throws Exception;
 
	public List<ClothesOrdersDto> propertyQueryTakeClothesOrder(List<String> communityIds,int page)throws Exception;
	
	public void updateClothesRemark(String orderId,String remark)throws Exception;
	
	public List<DeliveryClothesOrderDto> propertyQueryDeliveryClothesOrder(String employeeId,int page)throws Exception;
}
