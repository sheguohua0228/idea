package com.leyes.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.annotation.NotNull;
import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.comsystem.FileDto;
import com.leyes.app.dto.comsystem.ServiceChargeDto;
import com.leyes.app.dto.employee.DeliveryGoodsOrdersDto;
import com.leyes.app.dto.employee.FinishGoodsOrderDto;
import com.leyes.app.dto.employee.GoodsItemDto;
import com.leyes.app.dto.employee.GoodsOrdersDto;
import com.leyes.app.dto.member.CouponDto;
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
import com.leyes.app.entity.Business;
import com.leyes.app.entity.Goods;
import com.leyes.app.entity.GoodsAppraise;
import com.leyes.app.entity.GoodsOrder;
import com.leyes.app.entity.GoodsOrderDeliveryInfo;
import com.leyes.app.entity.GoodsOrderItem;
import com.leyes.app.entity.GoodsOrderRefund;
import com.leyes.app.enums.DealStatus;
import com.leyes.app.enums.FileType;
import com.leyes.app.enums.GoodsOrderSatatus;
import com.leyes.app.enums.GoodsShelfState;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.ValidStatus;
import com.leyes.app.exceptions.ClothesException;
import com.leyes.app.exceptions.MallException;
import com.leyes.app.mapper.BusinessMapper;
import com.leyes.app.mapper.MallMapper;
import com.leyes.app.mapper.OrderOfEmployeeMapper;
import com.leyes.app.util.UuidUtil;

@Service("mallService")
public class MallServiceImpl implements MallService{

	@Autowired
	private MallMapper mallMapper;
	
	@Autowired
	private BusinessMapper businessMapper;
	 
	@Autowired
	private OrderOfEmployeeMapper orderOfEmployeeMapper;
	
	@Override
	public List<GoodsDto> queryGoods(@NotNull(message="商品分类不存在")List<String> categoryIds, int sortCode,
			String sort,int page) throws Exception {
		String order ="create_time ";
		switch(sortCode){
			case 0:
				order="price ";
				break;
			case 1:
				order="sale_volume ";
				break;
		}
		page = page < 0 ? 0 : page;
		List<Goods> list = mallMapper.queryGoods(categoryIds, GoodsShelfState.onSell.ordinal(), order+sort, page*10);
		List<GoodsDto> result = null;
		if (list != null && list.size() > 0) {
			result = new ArrayList<GoodsDto>(10);
			GoodsDto goodsDto = null;
			for (Goods goods : list) {
				goodsDto = new GoodsDto(goods.getId(), goods.getPreviewImage(),
						goods.getName(), goods.getPrice().toString(), goods.getSaleVolume());
				BigDecimal marketPrice = goods.getMarketPrice();
				String market=marketPrice.compareTo(BigDecimal.ZERO)==0?"":marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				goodsDto.setMarketPrice(market);
				
				result.add(goodsDto);
			}
		}
		return result;
	}
	
	
	@Override
	public List<GoodsLimitTimeDto> queryLimitTimeGoods() {
		List<Goods> list = mallMapper.queryLimitTimeGoods(GoodsShelfState.onSell.ordinal());
		List<GoodsLimitTimeDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<GoodsLimitTimeDto>();
			GoodsLimitTimeDto dto=null;
			for (Goods goods:list) {
				 BigDecimal marketPrice = goods.getMarketPrice();
				String market=marketPrice.compareTo(BigDecimal.ZERO)==0?"":marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				 dto=new GoodsLimitTimeDto(goods.getId(),  goods.getEndTime().getTime(),
						 goods.getPrice(), market,
						 goods.getPreviewImage());
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public List<GoodsDto> queryNewGoods() {
		List<Goods> list = mallMapper.queryNewGoods(GoodsShelfState.onSell.ordinal());
		List<GoodsDto> result = null;
		if (list != null && list.size() > 0) {
			result = new ArrayList<GoodsDto>(list.size());
			GoodsDto goodsDto = null;
			for (Goods goods : list) {
				goodsDto = new GoodsDto(goods.getId(), goods.getPreviewImage(),
						goods.getName(), goods.getPrice().toString(), goods.getSaleVolume());
				goodsDto.setIntroduce(goods.getIntroduce());
				BigDecimal marketPrice = goods.getMarketPrice();
				String market=marketPrice.compareTo(BigDecimal.ZERO)==0?"":marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				goodsDto.setMarketPrice(market);
				result.add(goodsDto);
			}
		}
		return result;
	}
	
	
	@Override
	public List<GoodsDto> queryFavoriteGoods() throws Exception {
		List<Goods> list = mallMapper.queryFavoriteGoods(GoodsShelfState.onSell.ordinal());
		List<GoodsDto> result = null;
		if (list != null && list.size() > 0) {
			result = new ArrayList<GoodsDto>(10);
			GoodsDto goodsDto = null;
			for (Goods goods : list) {
				goodsDto = new GoodsDto(goods.getId(), goods.getPreviewImage(),
						goods.getName(), goods.getPrice().toString(), goods.getSaleVolume());
				BigDecimal marketPrice = goods.getMarketPrice();
				String market=marketPrice.compareTo(BigDecimal.ZERO)==0?"":marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				goodsDto.setMarketPrice(market);
				result.add(goodsDto);
			}
		}
		return result;
	}


	@Override
	public List<FileDto> queryGoodsImages(@NotNull(message="商品不存在") String goodsId)throws Exception{
		List<String> list = mallMapper.queryGoodsImages(goodsId);
		List<FileDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<FileDto>();
			FileDto file=null;
			for (String path:list) {
				file=new FileDto(path, FileType.image.ordinal());
				result.add(file);
			}
		}
		return result;
	}
	@Override
	public GoodsDetailDto queryGoodsInfo(@NotNull(message="商品不存在")String goodsId) throws Exception {
		Goods goods = mallMapper.queryGoodsInfo(goodsId);
		if(goods==null)
			throw new MallException("商品不存在或已下架");
		BigDecimal marketPrice = goods.getMarketPrice();
		String market=marketPrice.compareTo(BigDecimal.ZERO)==0?"":marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		GoodsDetailDto goodsDto=new GoodsDetailDto(goods.getId(), goods.getName(),
				goods.getIntroduce(), goods.getPrice().toString(),
				market, goods.getSaleVolume(),
				goods.getSpecification(), goods.getBrand());
		goodsDto.setCategoryId(goods.getCategoryId());
		goodsDto.setStockNumber(goods.getStockNumber());
		goodsDto.setDistribution(goods.getDistribution());
	 
		Date endTime=goods.getEndTime();
		goodsDto.setEndTime(endTime==null? -1:endTime.getTime());
		return goodsDto;
	}
	@Override
	public String queryGoodsDescription(@NotNull(message="商品不存在")String goodsId) throws Exception {
		return mallMapper.queryGoodsDescription(goodsId);
	}
	@Override
	public List<AppraiseDto> queryAppraise(@NotNull(message="商品不存在")String goodsId, int page)throws Exception {
		page = page < 0 ? 0 : page;
		List<GoodsAppraise> list = mallMapper.queryAppraise(goodsId, page*10);
		List<AppraiseDto> result=null;
		AppraiseDto dto=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<AppraiseDto>(10);
			for(GoodsAppraise goodsAppraise:list){
				dto=new AppraiseDto(goodsAppraise.getServiceStar(), goodsAppraise.getUserId());
				dto.setContent(goodsAppraise.getContent());
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public GoodsOrderBaseInfoDto placeGoodsOrder(@NotNull(message="用户未登录") String userId,
			@NotNull(message="下单地址不存在") String addressId,@NotNull(message="") String communityId,
			List<GoodsInfoDto> goodsList,ServiceChargeDto serviceCharge) throws Exception {
		if(goodsList==null || goodsList.size()==0)
			throw new MallException("请选择商品");
		BigDecimal totalPrice=BigDecimal.ZERO;
		GoodsOrder order = makeGoodsOrderParam(userId, addressId);
		order.setCommunityId(communityId);
		List<GoodsOrderItem> goodsItems=new ArrayList<GoodsOrderItem>();
		GoodsOrderItem goodsItem=null;
		List<Goods> updateGoodsList=new ArrayList<Goods>(goodsList.size());
		for(GoodsInfoDto goodDto:goodsList){
			String goodsId = goodDto.getGoodsId();
			int number = goodDto.getNumber();
			BigDecimal price =BigDecimal.ZERO;
			Goods goods = mallMapper.queryGoodsBaseInfo(goodsId);
			int stockNumber = goods.getStockNumber();
			//查看库存是否足够
			if(stockNumber<number) 
				throw new MallException("部分商品库存不足");
			if(price.compareTo(BigDecimal.ZERO)==0){
				price = goods.getPrice();
			}
			totalPrice=totalPrice.add(price.multiply(new BigDecimal(number)));
			goodsItem=new GoodsOrderItem();
			goodsItem.setId(UuidUtil.getUUIDString());
			goodsItem.setImage(goods.getPreviewImage());
			goodsItem.setNumber(number);
			goodsItem.setPrice(price);
			goodsItem.setOrderId(order.getId());
			goodsItem.setGoodsId(goodsId);
			goodsItem.setGoodsName(goods.getName());
			goodsItems.add(goodsItem);
			
			goods.setStockNumber(stockNumber-number);
			goods.setSaleVolume(number);
			updateGoodsList.add(goods);
		}
		int freight=0;
		if(totalPrice.compareTo(new BigDecimal(serviceCharge.getCondition()))==-1){
			freight=serviceCharge.getExpense();
			totalPrice=totalPrice.add(new BigDecimal(freight));
		} 
		order.setFreight(freight);
		order.setPrice(totalPrice);
		order.setFinalPrice(totalPrice);
		mallMapper.createGoodsOrder(order);
		mallMapper.createGoodsOrderItem(goodsItems);
		//减少库存量
		mallMapper.deductGoodsStockNumber(updateGoodsList);
		GoodsOrderDeliveryInfo deliveryInfo=new GoodsOrderDeliveryInfo();
		deliveryInfo.setId(UuidUtil.getUUIDString());
		deliveryInfo.setOrderId(order.getId());
		deliveryInfo.setOrderStatus(GoodsOrderSatatus.create);
		deliveryInfo.setDescription(GoodsOrderSatatus.create.getValue());
		deliveryInfo.setOperator("系统");
		mallMapper.createGoodsOrderDeliveryInfo(deliveryInfo);
		return new GoodsOrderBaseInfoDto(order.getId(), order.getOrderNumber(), totalPrice);
	}
	@Override
	public List<GoodsOrderInfoDto> queryGoodsOrder(@NotNull(message="用户未登录")String userId,
			int orderType,int page) throws Exception {
		page = page < 0 ? 0 : page;
		List<GoodsOrder> list =null;
		// 0、所有(默认) 1、待付款 2、未完成 3、待收货 4、退款
		switch (orderType) {
		case 1:
			list = mallMapper.queryGoodsOrder(userId,
					PayStatus.UNPAY.ordinal(),GoodsOrderSatatus.create.ordinal(),
					ValidStatus.valid.ordinal(),page*10);
			break;
		case 3:
			list = mallMapper.queryGoodsOrder(userId,PayStatus.PAID.ordinal(),
						GoodsOrderSatatus.finish.ordinal(),
						ValidStatus.valid.ordinal(),page*10);
			break;
		case 4:
			list =mallMapper.queryRefundGoodsOrder(userId, ValidStatus.valid.ordinal(), page*10);
			break;
		default:
			 list = mallMapper.queryGoodsOrder(userId,-1,-1,ValidStatus.valid.ordinal(),page*10);
			break;
		}
		List<GoodsOrderInfoDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<GoodsOrderInfoDto>();
			GoodsOrderInfoDto dto=null;
			 for (GoodsOrder goodsOrder : list) {
				dto=new GoodsOrderInfoDto(goodsOrder.getOrderStatus().ordinal(),
						 goodsOrder.getId(), goodsOrder.getPrice().toString());
				dto.setPayStatus(goodsOrder.getPayStatus().ordinal());
				dto.setFreight(goodsOrder.getFreight());
				dto.setOrderNumber(goodsOrder.getOrderNumber());
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public List<GoodsOrderItemDto> queryGoodsOrderItem(@NotNull(message="订单不存在")String orderId) throws Exception {
		List<GoodsOrderItem> list = mallMapper.queryGoodsOrderItem(orderId);
		List<GoodsOrderItemDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<GoodsOrderItemDto>();
			GoodsOrderItemDto dto=null;
			 for (GoodsOrderItem item : list) {
				dto=new GoodsOrderItemDto(item.getOrderId(), item.getGoodsId(),
						item.getImage(), item.getNumber(), item.getGoodsName(), item.getPrice());
				String appraiseId = item.getAppraiseId();
				dto.setAppraiseId(item.getAppraiseId());
				dto.setIsAppraise(StringUtils.isNotEmpty(appraiseId)?1:0);
				result.add(dto);
			}
		}
		return result;
	}
	@Override
	public GoodsOrderDetailDto queryGoodsOrderDetail(@NotNull(message="订单不存在")String orderId)
			throws Exception {
		GoodsOrder goodsOrder = mallMapper.queryGoodsOrderDetail(orderId);
		GoodsOrderDetailDto dto = new GoodsOrderDetailDto(goodsOrder.getOrderNumber(), 
				goodsOrder.getCreateTime().getTime(),goodsOrder.getPrice().toString(),  
				goodsOrder.getBalance().toString(), goodsOrder.getIntegralNumber());
		dto.setAddressId(goodsOrder.getAddressId());
		dto.setOrderStatus(goodsOrder.getOrderStatus().ordinal());
		dto.setPayStatus(goodsOrder.getPayStatus().ordinal());
		dto.setFreight(goodsOrder.getFreight());
		return dto;
	}
	@Override
	public List<GoodsOrderDeliveryInfoDto> queryGoodsOrderDeliveryInfo(
			@NotNull(message="订单不存在")String orderId) throws Exception {
		List<GoodsOrderDeliveryInfo> goodsDeliveryInfo = mallMapper.queryGoodsOrderDeliveryInfo(orderId);
		List<GoodsOrderDeliveryInfoDto> result=null;
		if(goodsDeliveryInfo!=null && goodsDeliveryInfo.size()>0){
			result=new ArrayList<GoodsOrderDeliveryInfoDto>(goodsDeliveryInfo.size());
			GoodsOrderDeliveryInfoDto dto=null;
			for (GoodsOrderDeliveryInfo goodsOrderDeliveryInfo : goodsDeliveryInfo) {
				dto=new GoodsOrderDeliveryInfoDto(goodsOrderDeliveryInfo.getCreateTime().getTime(),goodsOrderDeliveryInfo.getDescription());
				result.add(dto);
			}
		}
		return result;
	}
	
	
	@Override
	public GoodsOrderDto queryGoodsOrderPrice(@NotNull(message="订单不存在")String orderId) throws Exception {
		GoodsOrder goodsOrder = mallMapper.queryGoodsOrderPrice(orderId);
		if(goodsOrder==null)
			throw new MallException("订单不存在");
		GoodsOrderDto dto = new GoodsOrderDto();
		dto.setOrderNumber(goodsOrder.getOrderNumber());
		dto.setPrice(goodsOrder.getPrice());
		return dto;
	}


	@Override
	public String appraise(@NotNull(message="订单不存在")String orderId,@NotNull(message="评价内容不能为空")  String content, int serviceStar,
			@NotNull(message="商品不存在") String goodsId, String userId) throws Exception {
		GoodsAppraise appraise = new GoodsAppraise();
		String appraiseId = UuidUtil.getUUIDString();
		appraise.setId(UuidUtil.getUUIDString());
		appraise.setGoodsId(goodsId);
		appraise.setOrderId(orderId);
		appraise.setUserId(userId);
		appraise.setServiceStar(serviceStar);
		appraise.setContent(content);
		mallMapper.createAppraise(appraise);
		mallMapper.updateGoodsOrderAppraiseId(goodsId, orderId, appraiseId);
		return appraiseId;
	}
	
	@Override
	public List<GoodsNameDto> queryGoodsLikeName(@NotNull(message="搜索关键不能为空") String keyWord) {
		List<Goods> list = mallMapper.queryGoodsLikeName(keyWord);
		List<GoodsNameDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<GoodsNameDto>();
			GoodsNameDto dto=null;
			for (Goods goods : list) {
				dto=new GoodsNameDto(goods.getId(), goods.getName());
				result.add(dto);
			}
		}
		return result;
	}


	@Override
	public OrderPayStatusDto queryOrderStatusByOrderNumber(String orderNumber)
			throws Exception {
		GoodsOrder goodsOrder=mallMapper.queryOrderStatusByOrderNumber(orderNumber);
		 
		if (goodsOrder==null)
			throw new ClothesException("订单不存在");
		OrderPayStatusDto orderPayStatus = new OrderPayStatusDto();
		orderPayStatus.setId(goodsOrder.getId());
		orderPayStatus.setBalance(goodsOrder.getBalance());
		orderPayStatus.setIntegralNum(goodsOrder.getIntegralNumber());
		orderPayStatus.setPayStatus(goodsOrder.getPayStatus());
		orderPayStatus.setUserId(goodsOrder.getUserId());
		String cardId = goodsOrder.getCardId();
		CouponDto card=null;
		if(StringUtils.isNotEmpty(cardId)){
			card=new CouponDto();
			card.setCardId(cardId);
		}
		orderPayStatus.setCard(card);
		return orderPayStatus;
	}


	@Override
	public void updateGoodsOrderPayStatus(OrderPayStatusDto orderPayStatus)
			throws Exception {
		String orderId = orderPayStatus.getId();
		//更新订单支付状态
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setId(orderId);
		goodsOrder.setPayStatus(orderPayStatus.getPayStatus());
		goodsOrder.setFinalPrice(orderPayStatus.getFinalPrice());
		goodsOrder.setIntegralNumber(orderPayStatus.getIntegralNum());
		goodsOrder.setBalance(orderPayStatus.getBalance());
		CouponDto card = orderPayStatus.getCard();
		String cardId=null;
		if(card!=null){
			cardId=card.getCardId();
		}
		goodsOrder.setCardId(cardId);
		goodsOrder.setPayType(orderPayStatus.getPayType());
		mallMapper.updateGoodsOrderPayStatus(goodsOrder);
		if(orderPayStatus.getPayStatus().equals(PayStatus.PAID)){
			//将订单流程走到准备出库状态
			GoodsOrderDeliveryInfo deliveryInfo=new GoodsOrderDeliveryInfo();
			deliveryInfo.setId(UuidUtil.getUUIDString());
			deliveryInfo.setOrderId(orderId);
			deliveryInfo.setOrderStatus(GoodsOrderSatatus.readyOurDepot);
			deliveryInfo.setDescription(GoodsOrderSatatus.readyOurDepot.getValue());
			mallMapper.createGoodsOrderDeliveryInfo(deliveryInfo);
			mallMapper.updateGoodsOrderStatus(orderId,GoodsOrderSatatus.readyOurDepot.ordinal());
		}
	}


	@Override
	public void deleteOrder(@NotNull(message="订单不存在") String orderId) throws Exception {
		GoodsOrder goodsOrder = mallMapper.queryGoodsOrderStatus(orderId);
		GoodsOrderSatatus orderStatus = goodsOrder.getOrderStatus();
		PayStatus payStatus = goodsOrder.getPayStatus();
		if((orderStatus.equals(GoodsOrderSatatus.create) && !payStatus.equals(PayStatus.PAID))
				||(orderStatus.equals(GoodsOrderSatatus.finish) && payStatus.equals(PayStatus.PAID))
				){
			mallMapper.deleteGoodsOrder(orderId,ValidStatus.delete.ordinal());
		}else
			throw new MallException("无法删除该订单");
		 
	}


	@Override
	public String queryAddressIdByOrderNumber(@NotNull(message="订单编号不存在") String orderNumber) throws Exception {
		return mallMapper.queryAddressIdByOrderNumber(orderNumber);
	}


	//XXX *******************************周边商家******************************************/
	@Override
	public List<BusinessDto> queryNearyByBusiness(String categoryId,String communityId,
			String latitude,String longitude,int page) {
		if(StringUtils.isBlank(longitude) || StringUtils.isBlank(latitude)){
			latitude="-1";
			longitude="-1";
		}
		page = page < 0 ? 0 : page;
		List<Business> list = businessMapper.queryNearyByBusiness(categoryId,communityId, latitude,
				longitude, page*10);
		List<BusinessDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<BusinessDto>();
			BusinessDto dto=null;
			for(Business business:list){
				dto=new BusinessDto(business.getId(), business.getName(),
						business.getPreviewImage(), business.getAddress(), 
						business.getTelephone(), business.getServerTime(), 
						business.getDistance());
				result.add(dto);
			}
		}
		
		return result;
	}
	
	
	@Override
	public BusinessDto queryBusinessInfo(String businessId) {
		Business business = businessMapper.queryBusinessInfo(businessId);
		return new BusinessDto(business.getId(), business.getName(),
				business.getPreviewImage(), business.getAddress(), 
				business.getTelephone(), business.getServerTime(), 
				business.getDescription());
	}


	@Override
	public List<GoodsDto> queryGoodsByBusinessId(String businessId,int page) {
		page = page < 0 ? 0 : page;
		List<Goods> list = mallMapper.queryGoodsByBusinessId(businessId, GoodsShelfState.onSell.ordinal(), page*10);
		List<GoodsDto> result = null;
		if (list != null && list.size() > 0) {
			result = new ArrayList<GoodsDto>(10);
			GoodsDto goodsDto = null;
			for (Goods goods : list) {
				goodsDto = new GoodsDto(goods.getId(), goods.getPreviewImage(),
						goods.getName(), goods.getPrice().toString(), goods.getSaleVolume());
				BigDecimal marketPrice = goods.getMarketPrice();
				String market=marketPrice.compareTo(BigDecimal.ZERO)==0?"":marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				goodsDto.setMarketPrice(market);
				result.add(goodsDto);
			}
		}
		return result;
	}


	@Override
	public void applyRefund(String reason, String orderId) throws Exception {
		GoodsOrder goodsOrder = mallMapper.queryGoodsOrderStatus(orderId);
		GoodsOrderSatatus orderStatus = goodsOrder.getOrderStatus();
		PayStatus payStatus = goodsOrder.getPayStatus();
		if(!payStatus.equals(PayStatus.PAID))
			throw new MallException("当前状态不能申请退款");
		if(!orderStatus.equals(GoodsOrderSatatus.create)) 
			throw new MallException("当前状态不能申请退款");
		 
		GoodsOrderRefund applyRefund = new GoodsOrderRefund();
		applyRefund.setId(UuidUtil.getUUIDString());
		applyRefund.setOrderId(orderId);
		applyRefund.setReason(reason);
		applyRefund.setDealStatus(DealStatus.UNPROCESSED.ordinal());
		applyRefund.setDealResult(DealStatus.UNPROCESSED.getValue());
		mallMapper.saveApplyRefund(applyRefund);
	}


	//XXX **************************************小哥端************************************************/
	@Override
	public List<GoodsOrdersDto> queryWorthBuyOrder(List<String> communityIds, int page)
			throws Exception {
		List<GoodsOrder> list = orderOfEmployeeMapper.queryGoodsOrdersByCommunityId(communityIds, 
				GoodsOrderSatatus.readyOurDepot.ordinal(),PayStatus.PAID.ordinal(),ValidStatus.valid.ordinal(),page*10);
		List<GoodsOrdersDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<GoodsOrdersDto>();
			GoodsOrdersDto dto=null;
			for (GoodsOrder goodsOrder : list) {
				dto=new GoodsOrdersDto(goodsOrder.getId(), 
						goodsOrder.getCreateTime().getTime(),
						goodsOrder.getOrderNumber(), 
						goodsOrder.getUserId(), 
						goodsOrder.getAddressId(), 
						goodsOrder.getOperator());
				dto.setRemark(goodsOrder.getRemark());
				dto.setFinisher(goodsOrder.getFinisher());
				Date deliveryTime = goodsOrder.getDeliveryTime() ;
				dto.setDeliveryTime(deliveryTime==null?-1L:deliveryTime.getTime());
				 List<GoodsOrderItem> goodsItems = goodsOrder.getGoodsItems();
				 if(goodsItems!=null && goodsItems.size()>0){
					 for (GoodsOrderItem goodsOrderItem : goodsItems) {
						 GoodsItemDto item=new GoodsItemDto(goodsOrderItem.getNumber(),
								 goodsOrderItem.getGoodsName(),
								 goodsOrderItem.getImage());
						 dto.addGoodsItem(item);
					 }
				 }
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public String queryDeliveryStatusOperator(@NotNull(message="订单不存在")String orderId,
			GoodsOrderSatatus orderStatus) throws Exception {
		return orderOfEmployeeMapper.queryDeliveryStatusOperator(orderId, orderStatus.ordinal());
	}
	
	@Override
	public void updateDeliveryStatusOperator(@NotNull(message="订单不存在")String orderId, String employeeId,
			GoodsOrderSatatus orderStatus) throws Exception {
		orderOfEmployeeMapper.updateDeliveryStatusOperator(orderId, employeeId, null, orderStatus.ordinal());
	}
	
	
	@Override
	public void updateDeliveryStatusFinisher(String orderId, String employeeId,
			GoodsOrderSatatus orderStatus) throws Exception {
		orderOfEmployeeMapper.updateDeliveryStatusOperator(orderId,null, employeeId, orderStatus.ordinal());
	}


	@Override
	public void updateGoodsOrderDeliveryTime(@NotNull(message="订单不存在") String orderId, long time,String remark)
			throws Exception {
		orderOfEmployeeMapper.updateGoodsOrderDeliveryTime(orderId, new Date(time),remark);
	}
	  
	@Override
	public void addGoodsOrderDeliveryStatus(@NotNull(message="订单不存在")String orderId,
			@NotNull(message="小哥不存在")String employeeId,
			GoodsOrderSatatus orderStatus,String orderStatusDesc) throws Exception {
		GoodsOrderDeliveryInfo deliveryInfo=new GoodsOrderDeliveryInfo();
		deliveryInfo.setId(UuidUtil.getUUIDString());
		deliveryInfo.setOrderId(orderId);
		deliveryInfo.setOrderStatus(orderStatus);
		deliveryInfo.setDescription(orderStatusDesc);
		deliveryInfo.setOperator(employeeId);
		mallMapper.createGoodsOrderDeliveryInfo(deliveryInfo);
		orderOfEmployeeMapper.updateGoodsOrderStatus(orderId,orderStatus.ordinal());
	}


	@Override
	public List<DeliveryGoodsOrdersDto> queryDeliveryGoodsOrder(String employeeId,
			int page) throws Exception {
		List<GoodsOrder> list = orderOfEmployeeMapper.queryDeliveryGoodsOrder(employeeId, GoodsOrderSatatus.delivery.ordinal(), page*10);
		List<DeliveryGoodsOrdersDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<DeliveryGoodsOrdersDto>();
			DeliveryGoodsOrdersDto dto=null;
			for (GoodsOrder goodsOrder : list) {
				dto=new DeliveryGoodsOrdersDto(goodsOrder.getId(), 
						goodsOrder.getCreateTime().getTime(),
						goodsOrder.getOrderNumber(), 
						goodsOrder.getUserId(), 
						goodsOrder.getAddressId(), 
						goodsOrder.getRemark());
				dto.setFinisher(goodsOrder.getFinisher());
				Date deliveryTime = goodsOrder.getDeliveryTime();
				dto.setDeliveryTime(deliveryTime==null?-1L:deliveryTime.getTime());
				 List<GoodsOrderItem> goodsItems = goodsOrder.getGoodsItems();
				 if(goodsItems!=null && goodsItems.size()>0){
					 for (GoodsOrderItem goodsOrderItem : goodsItems) {
						 GoodsItemDto item=new GoodsItemDto(goodsOrderItem.getNumber(),
								 goodsOrderItem.getGoodsName(),goodsOrderItem.getImage());
						 dto.addGoodsItem(item);
					 }
				 }
				result.add(dto);
			}
		}
		return result;
	}


	@Override
	public List<GoodsOrdersDto> prpertyQueryGoodsOrder(List<String> communityIds,
			int page) throws Exception {
		List<GoodsOrder> list = orderOfEmployeeMapper.propertyQueryBespeakOrder(communityIds, 
				GoodsOrderSatatus.outDepot.ordinal(), PayStatus.PAID.ordinal(),
				ValidStatus.valid.ordinal(), page*10);
		List<GoodsOrdersDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<GoodsOrdersDto>();
			GoodsOrdersDto dto=null;
			for (GoodsOrder goodsOrder : list) {
				dto=new GoodsOrdersDto(goodsOrder.getId(), 
						goodsOrder.getCreateTime().getTime(),
						goodsOrder.getOrderNumber(), 
						goodsOrder.getUserId(), 
						goodsOrder.getAddressId(), 
						goodsOrder.getOperator());
				dto.setRemark(goodsOrder.getRemark());
				dto.setFinisher(goodsOrder.getFinisher());
				Date deliveryTime = goodsOrder.getDeliveryTime() ;
				dto.setDeliveryTime(deliveryTime==null?-1L:deliveryTime.getTime());
				 List<GoodsOrderItem> goodsItems = goodsOrder.getGoodsItems();
				 if(goodsItems!=null && goodsItems.size()>0){
					 for (GoodsOrderItem goodsOrderItem : goodsItems) {
						 GoodsItemDto item=new GoodsItemDto(goodsOrderItem.getNumber(),
								 goodsOrderItem.getGoodsName(),
								 goodsOrderItem.getImage());
						 dto.addGoodsItem(item);
					 }
				 }
				result.add(dto);
			}
		}
		return result;
	}


	@Override
	public List<DeliveryGoodsOrdersDto> propertyQueryDeliveryGoodsOrder(
			String employeeId, int page) throws Exception {
		List<GoodsOrder> list = orderOfEmployeeMapper.propertyQueryDeliveryGoodsOrder(employeeId, GoodsOrderSatatus.delivery.ordinal(), page*10);
		List<DeliveryGoodsOrdersDto> result=null;
		if(list!=null && list.size()>0){
			
			result=new ArrayList<DeliveryGoodsOrdersDto>();
			DeliveryGoodsOrdersDto dto=null;
			for (GoodsOrder goodsOrder : list) {
				String finisher = goodsOrder.getFinisher();
				if(StringUtils.isBlank(finisher)){
				dto=new DeliveryGoodsOrdersDto(goodsOrder.getId(), 
						goodsOrder.getCreateTime().getTime(),
						goodsOrder.getOrderNumber(), 
						goodsOrder.getUserId(), 
						goodsOrder.getAddressId(), 
						goodsOrder.getRemark());
				dto.setFinisher(finisher);
				Date deliveryTime = goodsOrder.getDeliveryTime();
				dto.setDeliveryTime(deliveryTime==null?-1L:deliveryTime.getTime());
				 List<GoodsOrderItem> goodsItems = goodsOrder.getGoodsItems();
				 if(goodsItems!=null && goodsItems.size()>0){
					 for (GoodsOrderItem goodsOrderItem : goodsItems) {
						 GoodsItemDto item=new GoodsItemDto(goodsOrderItem.getNumber(),
								 goodsOrderItem.getGoodsName(),goodsOrderItem.getImage());
						 dto.addGoodsItem(item);
					 }
				 }
				result.add(dto);
			}
			}
		}
		return result;
	}


	@Override
	public List<FinishGoodsOrderDto> queryFinishGoodsOrder(String employeeId,
			int page) throws Exception {
		List<GoodsOrder> list = orderOfEmployeeMapper.queryFinishGoodsOrder(employeeId, GoodsOrderSatatus.finish.ordinal(), page*10);
		List<FinishGoodsOrderDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<FinishGoodsOrderDto>();
			FinishGoodsOrderDto dto=null;
			for (GoodsOrder goodsOrder : list) {
				dto=new FinishGoodsOrderDto(goodsOrder.getId(),
						goodsOrder.getCreateTime().getTime(),
						goodsOrder.getOrderNumber(),
						goodsOrder.getUserId(),
						goodsOrder.getAddressId(),
						goodsOrder.getRemark());
				List<GoodsOrderItem> goodsItems = goodsOrder.getGoodsItems();
				 if(goodsItems!=null && goodsItems.size()>0){
					 for (GoodsOrderItem goodsOrderItem : goodsItems) {
						 GoodsItemDto item=new GoodsItemDto(goodsOrderItem.getNumber(),
								 goodsOrderItem.getGoodsName(),goodsOrderItem.getImage());
						 dto.addGoodsItem(item);
					 }
				 }
				result.add(dto);
			}
		}
		return result;
	}


	@Override
	public String queryCommunityIdByOrderId(@NotNull(message="订单不存在") String orderId) throws Exception {
		return orderOfEmployeeMapper.queryCommunityIdByOrderId(orderId);
	}


	@Override
	public long queryGoodsOrderBackTime(String orderId) throws Exception {
		Date backTime = orderOfEmployeeMapper.queryGoodsOrderBackTime(orderId);
		return backTime==null?0:backTime.getTime();
	}


	protected GoodsOrder makeGoodsOrderParam(String userId,String addressId){
		GoodsOrder order=new GoodsOrder();
		String orderId=UuidUtil.getUUIDString();
		order.setId(orderId);
		order.setUserId(userId);
		order.setAddressId(addressId);
		order.setOrderStatus(GoodsOrderSatatus.create);
		order.setStatus(ValidStatus.valid);
		order.setIntegralNumber(0);
		order.setBalance(BigDecimal.ZERO);
		order.setOrderNumber(UuidUtil.orderNumber());
		order.setPayStatus(PayStatus.UNPAY);
		return order;
	}



}
