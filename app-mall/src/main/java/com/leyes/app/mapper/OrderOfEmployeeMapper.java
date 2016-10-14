package com.leyes.app.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.entity.GoodsOrder;

public interface OrderOfEmployeeMapper {

	public List<GoodsOrder> queryGoodsOrdersByCommunityId(
			@Param("list") List<String> communityIds,
			@Param("orderStatus") int orderStatus,
			@Param("payStatus") int payStatus, 
			@Param("status") int status,
			@Param("firstResult") int firstResult);
	public List<GoodsOrder> propertyQueryBespeakOrder(
			@Param("list") List<String> communityIds,
			@Param("orderStatus") int orderStatus,
			@Param("payStatus") int payStatus, 
			@Param("status") int status,
			@Param("firstResult") int firstResult);
	
	public List<GoodsOrder> queryDeliveryGoodsOrder(
			@Param("employeeId") String employeeId,
			@Param("orderStatus") int orderStatus,
			@Param("firstResult") int firstResult);

	public List<GoodsOrder> propertyQueryDeliveryGoodsOrder(
			@Param("employeeId") String employeeId,
			@Param("orderStatus") int orderStatus,
			@Param("firstResult") int firstResult);
	
	public List<GoodsOrder> queryFinishGoodsOrder(
			@Param("employeeId") String employeeId,
			@Param("orderStatus") int orderStatus,
			@Param("firstResult") int firstResult);

	public String queryDeliveryStatusOperator(@Param("orderId") String orderId,
			@Param("orderStatus") int orderStatus);

	public void updateDeliveryStatusOperator(@Param("orderId") String orderId,
			@Param("operator") String operator,
			@Param("finisher") String finisher,
			@Param("orderStatus") int orderStatus);

	public void updateGoodsOrderDeliveryTime(@Param("orderId") String orderId,
			@Param("deliveryTime") Date deliveryTime,
			@Param("remark") String remark);

	public String queryCommunityIdByOrderId(@Param("orderId") String orderId);

	public void updateGoodsOrderStatus(@Param("orderId") String orderId,
			@Param("orderStatus") int orderStatus);

	public Date queryGoodsOrderBackTime(@Param("orderId")String orderId);
}
