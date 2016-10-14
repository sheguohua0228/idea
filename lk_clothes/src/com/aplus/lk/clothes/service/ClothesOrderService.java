package com.aplus.lk.clothes.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.ClothesPriceParams;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.WashClothesModle;
import com.aplus.lk.clothes.entity.ClothesOrder;
import com.aplus.lk.clothes.entity.ClothesOrder.OrderStatus;
import com.aplus.lk.clothes.entity.Member;
import com.aplus.lk.clothes.entity.OutFactoryClothes;
import com.aplus.lk.clothes.entity.ReassignmentRecord;
import com.aplus.lk.clothes.entity.WashClothes;

public interface ClothesOrderService {
	
	/**
	 * @Title: queryAll
	 * @Description: TODO 分页查询洗衣订单
	 * @param barCode 条形码
	 * @param orderNumber 订单编号
	 * @param orderStatus 订单状态
	 * @param payStatus 支付状态
	 * @param firstResult 第一条记录
	 * @param maxResult 最大记录
	 * @return List<ClothesOrder>
	 * @throws
	 */
	public List<ClothesOrder> queryAll(String barCode, String orderNumber, Integer status ,Integer orderStatus, Integer payStatus, int firstResult, int maxResult);
	
	public Pager queryPager(Pager pager, String barCode, String orderNumber,Integer status , Integer orderStatus, Integer payStatus, String phoneNumber);
	
	public ClothesOrder queryClothesOrderById(long id);
	
	public ClothesOrder queryClothesOrderAndClothesStatusAddressById(long id);
	
	public ClothesOrder queryClothesOrderByBarCode(String barCode, HttpServletRequest request);
	
	public boolean collectToFactory(WashClothesModle washClothesModle ,MultipartFile imageUpload,
			long clothesOrderId, Boolean isProccessed, HttpServletRequest request);
	
	public boolean updateOrderStatus(long orderId, OrderStatus oldOrderStatus, 
			OrderStatus newOrderStatus,String description,
			MultipartFile imageUpload,String operator,HttpServletRequest request);
	
	public int updateOrderStatus(long orderId, int newOrderStatus, Boolean isProcessOfRemark,String factoryRemark);
	
	public void updateStatusById(long orderId, int newStatus);
	
	public void updateIsProcessOfRemarkById(long orderId,Boolean isProcessedOfRemark);
	
	public ClothesOrder queryClothesOrderByChildBarCode(String childBarCode);

	public boolean collectToFactory(WashClothesModle clothesModle,
			String savePath, long clothesOrderId, boolean isProccessed,String factoryRemark,
			String operator,String outImageUrl,HttpServletRequest request);
	
	public void createTelOrder(ClothesOrder order);
	
	public void createRecord(ReassignmentRecord record);
	
	public void changeOrderEmployee(long orderId,String employeeId);
	
	public ClothesOrder queryWashClothesByChildBarCode(String childBarCode);
	
	public Pager queryBackWashList(Pager pager,String phoneNumber);

	public Pager queryHistoryOrder(Pager pager,Long orderId);

	public List<OutFactoryClothes> queryBarCode(String barcode);
	

	public List<WashClothes> queryClothesByOrderId(Long orderId);
	
	public void updateClothesPrice(ClothesPriceParams clothesPriceParams);
    
	public void queryWaitFactoryList(HttpServletRequest request,String phoneNumber,String barCode);
	
	public boolean updateOrderPrintStatus(Long id);
	
	public int queryOrderCountByPhoneNum(String phoneNumber);
	
	public int queryCollectCount();
	
	public Map<String,Object> queryCommunityCount();
	
	public int updateOrderFactoryRemark(String factoryRemark,String orderNumber);
	
	public int updateOrderFactoryMatter(String orderNumber,String childBarCode,String factoryMatter);
	
	public boolean withhod(int method, ClothesOrder order,Member member);
}
