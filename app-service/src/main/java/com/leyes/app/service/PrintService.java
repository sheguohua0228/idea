package com.leyes.app.service;

import java.util.List;

import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.print.PhotoSizePriceDto;
import com.leyes.app.dto.print.PrintOrderBaseInfoDto;
import com.leyes.app.dto.print.PrintOrderDto;
import com.leyes.app.dto.print.QueryPrintFetchCodeDto;
import com.leyes.app.dto.print.QueryPrintOrderDetailDto;
import com.leyes.app.dto.print.VisaPhotoRequireInfoDto;

public interface PrintService {
	/**
	 * 获取照片价格列表
	 * 
	 * @Title: queryPhotoSizePrice
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return List<PhotoSizePriceDto>
	 * @throws
	 */
	public List<PhotoSizePriceDto> queryPhotoSizePrice() throws Exception;

	/**
	 * 获取护照打印要求列表
	 * 
	 * @Title: queryVisaPhotoInfo
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return List<VisaPhotoRequireInfoDto>
	 * @throws
	 */
	public List<VisaPhotoRequireInfoDto> queryVisaPhotoInfo() throws Exception;

	/**
	 * 删除打印订单
	 * 
	 * @Title: deletePrintOrder
	 * @Description: TODO
	 * @param orderId
	 * @return
	 * @throws Exception
	 * @return int
	 * @throws
	 */
	public void deletePrintOrder(String orderId) throws Exception;

	public PrintOrderBaseInfoDto placePrintOrder(String userId,String jsonPhotoInfos)throws Exception;

	/**
	 *  查看打印订单列表
	* @Title: queryPrintOrder 
	* @Description: TODO
	* @param userId
	* @param page
	* @param orderType 0、所有(默认) 1、待付款 2、未完成 3、待收货 4、退款
	* @return
	* @throws Exception
	* @return List<QueryPrintFetchCodeDto>    
	* @throws
	 */
	public List<QueryPrintFetchCodeDto> queryPrintOrder(String userId,
			int page,int orderType) throws Exception;

	public String queryFetchCode(String orderId) throws Exception;
	/**
	 * 查看打印订单详情
	 * 
	 * @Title: queryPrintOrderDetail
	 * @Description: TODO
	 * @param orderNumber
	 * @param userId
	 * @return
	 * @throws Exception
	 * @return QueryPrintOrderDetailDto
	 * @throws
	 */
	public QueryPrintOrderDetailDto queryPrintOrderDetail(String orderNumber) throws Exception;

	/**
	 * 申请照片订单退款
	 * 
	 * @Title: applyRefund
	 * @Description: TODO
	 * @param reason
	 * @param orderNumber
	 * @return
	 * @throws Exception
	 * @return boolean
	 * @throws
	 */
	public void applyRefund(String reason, String orderId)throws Exception;

	
	public String appraise(String userId,String orderId,String content,int serviceStar)throws Exception;
	
	public void updateVisaPhotoNoticeStatus(String orderId)throws Exception;
	
	public PrintOrderDto queryPrintOrderPrice(String orderId)throws Exception;
	
	public void updatePrintOrderPayStatus(OrderPayStatusDto printOrderPayStatus)throws Exception;
	
	public OrderPayStatusDto queryOrderStatusByOrderNumber(String orderNumber)throws Exception;

	public void updatePrintOrderPhotoUrl(String orderId,String photoUrl)throws Exception;
}
