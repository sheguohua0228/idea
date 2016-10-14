package com.leyes.app.service;

import java.util.List;

import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.comsystem.FileDto;
import com.leyes.app.dto.comsystem.ServiceChargeDto;
import com.leyes.app.dto.employee.DeliveryGoodsOrdersDto;
import com.leyes.app.dto.employee.FinishGoodsOrderDto;
import com.leyes.app.dto.employee.GoodsOrdersDto;
import com.leyes.app.dto.shop.AppraiseDto;
import com.leyes.app.dto.shop.BusinessDto;
import com.leyes.app.dto.shop.GoodsDetailDto;
import com.leyes.app.dto.shop.GoodsDto;
import com.leyes.app.dto.shop.GoodsInfoDto;
import com.leyes.app.dto.shop.GoodsLimitTimeDto;
import com.leyes.app.dto.shop.GoodsNameDto;
import com.leyes.app.dto.shop.GoodsOrderBaseInfoDto;
import com.leyes.app.dto.shop.GoodsOrderDeliveryInfoDto;
import com.leyes.app.dto.shop.GoodsOrderDetailDto;
import com.leyes.app.dto.shop.GoodsOrderDto;
import com.leyes.app.dto.shop.GoodsOrderInfoDto;
import com.leyes.app.dto.shop.GoodsOrderItemDto;
import com.leyes.app.enums.GoodsOrderSatatus;

public interface MallService {

	 
	/**
	 * 查看商品列表
	* @Title: queryGoods 
	* @Description:  
	* @param categoryId
	* @param sortCode
	* @param sort
	* @return
	* @return List<GoodsDto>    
	* @throws
	 */
	public List<GoodsDto> queryGoods(List<String> categoryId,int sortCode,String sort,int page)throws Exception;
	
	/**
	 * 查看限时特惠商品
	* @Title: queryLimitTimeGoods 
	* @Description:  
	* @return
	* @return List<GoodsLimitTimeDto>    
	* @throws
	 */
	public List<GoodsLimitTimeDto> queryLimitTimeGoods();
	 
	/**
	 * 查看最新上架商品列表
	* @Title: queryNewGoods 
	* @Description:  
	* @return
	* @return List<GoodsDto>    
	* @throws
	 */
	public List<GoodsDto> queryNewGoods();
	/**
	 * 查询猜你喜欢商品列表
	* @Title: queryFavoriteGoods 
	* @Description:  
	* @return
	* @throws Exception
	* @return List<GoodsDto>    
	* @throws
	 */
	public List<GoodsDto> queryFavoriteGoods()throws Exception;
	/**
	 * 查看商品的图片
	* @Title: queryGoodsImages 
	* @Description:  
	* @param goodsId
	* @return
	* @return List<String>    
	* @throws
	 */
	public List<FileDto> queryGoodsImages(String goodsId)throws Exception;
	/**
	 * 查看商品基础信息
	* @Title: queryGoodsInfo 
	* @Description:  
	* @param goodsId
	* @return
	* @throws Exception
	* @return GoodsDetailDto    
	* @throws
	 */
	public GoodsDetailDto queryGoodsInfo(String goodsId)throws Exception;
	/**
	 * 查询商品的描述
	* @Title: queryGoodsDescription 
	* @Description:   
	* @param goodsId
	* @return
	* @throws Exception
	* @return String    
	* @throws
	 */
	public String queryGoodsDescription(String goodsId)throws Exception;
	/**
	 * 查看商品的评价
	* @Title: queryAppraise 
	* @Description:  
	* @param goodsId
	* @param page
	* @return
	* @return List<AppraiseDto>    
	* @throws
	 */
	public List<AppraiseDto> queryAppraise(String goodsId,int page)throws Exception;
	/**
	 * 创建订单
	* @Title: placeGoodsOrder 
	* @Description:  
	* @param userId
	* @param addressId
	* @throws Exception
	* @return void    
	* @throws
	 */
	public GoodsOrderBaseInfoDto placeGoodsOrder(String userId,String addressId,String communityId,List<GoodsInfoDto> goods,ServiceChargeDto serviceCharge)throws Exception;

	/**
	 * 查询值得买订单列表
	* @Title: queryGoodsOrder 
	* @Description:  
	* @param userId
	* @param page 
	* @Param orderType 0、所有(默认) 1、待付款 2、未完成 3、待收货 4、退款
	* @return
	* @throws Exception
	* @return List<GoodsOrderInfoDto>    
	* @throws
	 */
	public List<GoodsOrderInfoDto> queryGoodsOrder(String userId,int orderType,int page)throws Exception;
	/**
	 * 查询订单商品子项
	* @Title: queryGoodsOrderItem 
	* @Description:  
	* @param orderId
	* @return
	* @throws Exception
	* @return List<GoodsOrderItemDto>    
	* @throws
	 */
	public List<GoodsOrderItemDto> queryGoodsOrderItem(String orderId)throws Exception;
	/**
	 * 查询商品订单信息
	* @Title: queryGoodsOrderDetail 
	* @Description:  
	* @param orderId
	* @return
	* @throws Exception
	* @return GoodsOrderDetailDto    
	* @throws
	 */
	public GoodsOrderDetailDto queryGoodsOrderDetail(String orderId)throws Exception;
	/**
	 * 查询商品物流信息
	* @Title: queryGoodsOrderDeliveryInfo 
	* @Description:  
	* @param orderId
	* @return
	* @throws Exception
	* @return List<GoodsOrderDeliveryInfoDto>    
	* @throws
	 */
	public List<GoodsOrderDeliveryInfoDto> queryGoodsOrderDeliveryInfo(String orderId)throws Exception;
	
	public GoodsOrderDto queryGoodsOrderPrice(String orderId)throws Exception;
	/**
	 * 评价值得买订单
	* @Title: appraise 
	* @Description:  
	* @param orderId
	* @param content
	* @param serviceStar
	* @param goodsId
	* @param userId
	* @throws Exception
	* @return void    
	* @throws
	 */
	public String appraise(String orderId,String content,int serviceStar,String goodsId,String userId)throws Exception;
	
	public void updateGoodsOrderPayStatus(OrderPayStatusDto orderPayStatus)throws Exception;
	
	public OrderPayStatusDto queryOrderStatusByOrderNumber(String orderNumber)throws Exception;
	
	public void applyRefund(String reason, String orderId)throws Exception;
	 
	public List<GoodsNameDto> queryGoodsLikeName(String keyWord)throws Exception;
	
	public void deleteOrder(String orderId)throws Exception;
	
	public String queryAddressIdByOrderNumber(String orderNumber)throws Exception;
	
	/***************周边商家相关   XXX************************************************************************************
	/**
	 * 查询周边商家
	* @Title: queryNearyByBusiness 
	* @Description:  
	* @param categoryId
	* @param latitude
	* @param longitude
	* @param page
	* @return
	* @throws Exception
	* @return List<BusinessDto>    
	* @throws
	 */
	public List<BusinessDto> queryNearyByBusiness(String categoryId,String communityId,String latitude,String longitude,int page)throws Exception;

	/**
	 * 查询商家信息
	* @Title: queryBusinessInfo 
	* @Description:  
	* @param businessId
	* @return
	* @return BusinessDto    
	* @throws
	 */
	public BusinessDto queryBusinessInfo(String businessId);
	/**
	 * 查询商家商品
	* @Title: queryGoodsByBusinessId 
	* @Description:  
	* @param businessId
	* @param page
	* @return
	* @return List<GoodsDto>    
	* @throws
	 */
	public List<GoodsDto> queryGoodsByBusinessId(String businessId,int page);
	
	//XXX *************************************小哥端***********************************************************/
	public List<GoodsOrdersDto> queryWorthBuyOrder(List<String> communityIds,int page)throws Exception;
	
	public List<GoodsOrdersDto> prpertyQueryGoodsOrder(List<String> communityIds,int page)throws Exception;
	
	public String queryDeliveryStatusOperator(String orderId,GoodsOrderSatatus orderStatus)throws Exception;
	
	public void updateDeliveryStatusOperator(String orderId,String employeeId,GoodsOrderSatatus orderStatus)throws Exception;
	
	public void updateDeliveryStatusFinisher(String orderId,String employeeId,GoodsOrderSatatus orderStatus)throws Exception;

	public void updateGoodsOrderDeliveryTime(String orderId,long time,String remark)throws Exception;

	public void addGoodsOrderDeliveryStatus(String orderId,String employeeId,GoodsOrderSatatus orderStatus,String orderStatusDesc)throws Exception;

	public List<DeliveryGoodsOrdersDto> queryDeliveryGoodsOrder(String employeeId,int page)throws Exception;
	
	public List<DeliveryGoodsOrdersDto> propertyQueryDeliveryGoodsOrder(String employeeId,int page)throws Exception;

	public List<FinishGoodsOrderDto> queryFinishGoodsOrder(String employeeId,int page)throws Exception;

	public String queryCommunityIdByOrderId(String orderId)throws Exception;
	
	public long queryGoodsOrderBackTime(String orderId)throws Exception;
}

