package com.leyes.app.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.dto.shop.GoodsInfoDto;
import com.leyes.app.dto.shop.SortRuleDto;
import com.leyes.app.entity.Goods;
import com.leyes.app.entity.GoodsAppraise;
import com.leyes.app.entity.GoodsOrder;
import com.leyes.app.entity.GoodsOrderDeliveryInfo;
import com.leyes.app.entity.GoodsOrderItem;
import com.leyes.app.entity.GoodsOrderRefund;

public interface MallMapper {

	public List<SortRuleDto> querySortRule() throws Exception;

	public List<Goods> queryGoods(
			@Param("categoryIds") List<String> categoryIds,
			@Param("shelfState") int shelfState, @Param("sort") String sort,
			@Param("firstResult") int firstResult);

	public List<Goods> queryLimitTimeGoods(@Param("shelfState") int shelfState);

	public List<Goods> queryNewGoods(@Param("shelfState") int shelfState);

	public List<Goods> queryFavoriteGoods(@Param("shelfState") int shelfState);

	public List<String> queryGoodsImages(@Param("goodsId") String goodsId);

	public Goods queryGoodsInfo(@Param("goodsId") String goodsId)
			throws Exception;

	public String queryGoodsDescription(@Param("goodsId") String goodsId);

	public List<GoodsAppraise> queryAppraise(@Param("goodsId") String goodsId,
			@Param("firstResult") int firstResult);

	public void placeGoodsOrder(String userId, String addressId,
			List<GoodsInfoDto> goods);

	public List<GoodsOrder> queryGoodsOrder(@Param("userId") String userId,
			@Param("payStatus") int payStatus,
			@Param("orderStatus") int orderStatus, @Param("status") int status,
			@Param("firstResult") int firstResult);

	public List<GoodsOrder> queryRefundGoodsOrder(
			@Param("userId") String userId, @Param("status") int status,
			@Param("firstResult") int firstResult);

	public List<GoodsOrderItem> queryGoodsOrderItem(
			@Param("orderId") String orderId);

	public GoodsOrder queryGoodsOrderDetail(@Param("orderId") String orderId);

	public List<GoodsOrderDeliveryInfo> queryGoodsOrderDeliveryInfo(
			@Param("orderId") String orderId);

	public void createAppraise(GoodsAppraise goodsAppraise);

	public BigDecimal queryGoodsPrice(@Param("goodsId") String goodsId);

	public void createGoodsOrder(GoodsOrder goodsOrder);

	public Goods queryGoodsBaseInfo(@Param("goodsId") String goodsId);

	public void createGoodsOrderItem(List<GoodsOrderItem> goodsItems);

	public void createGoodsOrderDeliveryInfo(GoodsOrderDeliveryInfo deliveryInfo);

	public void deductGoodsStockNumber(@Param("goodsList") List<Goods> goodsList);

	public List<Goods> queryGoodsByBusinessId(
			@Param("businessId") String businessId,
			@Param("shelfState") int shelfState,
			@Param("firstResult") int firstResult);

	public void saveApplyRefund(GoodsOrderRefund goodsOrderRefund);

	public GoodsOrder queryGoodsOrderPrice(@Param("orderId") String orderId);

	public List<Goods> queryGoodsLikeName(@Param("keyWord") String keyWord);

	public GoodsOrder queryOrderStatusByOrderNumber(
			@Param("orderNumber") String orderNumber);

	public void updateGoodsOrderPayStatus(GoodsOrder goodsOrder);

	public GoodsOrder queryGoodsOrderStatus(@Param("orderId") String orderId);

	public void deleteGoodsOrder(@Param("orderId") String orderId,
			@Param("status") int status);

	public void updateGoodsOrderAppraiseId(@Param("goodsId") String goodsId,
			@Param("orderId") String orderId,
			@Param("appraiseId") String appraiseId);

	public String queryAddressIdByOrderNumber(
			@Param("orderNumber") String orderNumber);

	public void updateGoodsOrderStatus(@Param("orderId") String orderId,
			@Param("orderStatus") int orderStatus);
}
