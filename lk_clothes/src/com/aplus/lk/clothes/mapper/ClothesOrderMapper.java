package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.ClothesOrder;
import com.aplus.lk.clothes.entity.OutFactoryClothes;
import com.aplus.lk.clothes.entity.ReassignmentRecord;

public interface ClothesOrderMapper {

	public List<ClothesOrder> queryAll(Map<String, Object> parameterMap);
	
	public List<ClothesOrder> queryByLimit(Map<String, Object> parameterMap);
	/**
	 * @Title: queryCountByLimit
	 * @Description: TODO 获取记录数
	 * @param parameterMap
	 * @param 
	 * @return int 返回记录数
	 * @throws
	 */ 
	public int queryCount(Map<String, Object> parameterMap);
	
	public ClothesOrder queryClothesOrderById(long id);
	
	public ClothesOrder queryClothesOrderByBarCode(String barCode);
	
	public int updateOrderStatus(Map<String, Object> parameterMap);
	
	public void updateStatusById(Map<String, Object> parameterMap);
	
	public void updateIsProcessOfRemarkById(Map<String, Object> parameterMap);
	
	public ClothesOrder queryClothesOrderByChildBarCode(String childBarCode);
	
	public void updateEmployeeIdById(Map<String, Object> parameterMap);
	
	public void createTelOrder(ClothesOrder order) ;
	
	public void createRecord(ReassignmentRecord record);
	
	public List<ClothesOrder> queryBackWashList(Map<String, Object> parameterMap);
	
	public int queryBackWashListCount(Map<String, Object> parameterMap);
	//通过订单号查询用户的极光推送id和id,小工手机号
	public List<Map<String,Object>> queryInfoByOrderNo(String orderNumber);
	
	public List<ClothesOrder> queryHistoryOrder(Map<String, Object> parameterMap);
	
	public int queryHistoryOrderCount(Map<String, Object> parameterMap);

	
	public List<OutFactoryClothes> queryBarCode(String barCode);

	
	public void modifyOrderPrice(Map<String,Object> parameter);
    
	public List<OutFactoryClothes> queryWashList(Map<String,Object> parameter);
	
	public List<OutFactoryClothes> queryClothesDetail(String orderNumber);
	
	public int updateOrderPrintStatus(Long orderId);
	
	public int queryOrderCountByPhoneNum(String phoneNumber);
	
	public List<Integer> queryCollectOrderCount();
	
	public List<Integer> queryCommunityOrderCount();
	
	public int queryClothesCount(int orderId);
	
	public int querySortCount(Map<String,Object> parameter);
	
	public int updateFactoryRemark(Map<String,Object> parameter);
    
	public int updateFactoryMatter(Map<String,Object> parameter);
	
	public int finishOrder(ClothesOrder order);
}
