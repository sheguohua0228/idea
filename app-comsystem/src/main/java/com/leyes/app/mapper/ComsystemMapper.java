package com.leyes.app.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.annotation.NotNull;
import com.leyes.app.entity.Banner;
import com.leyes.app.entity.Category;
import com.leyes.app.entity.ClientUpdateInfo;
import com.leyes.app.entity.Coupon;
import com.leyes.app.entity.RechargeCard;
import com.leyes.app.entity.RechargeOrder;
import com.leyes.app.entity.RecommendUseRecord;
import com.leyes.app.entity.ServiceCharge;
import com.leyes.app.entity.Topic;

public interface ComsystemMapper {

	public ClientUpdateInfo findByClientVersion(
			@Param("platform") String platform,
			@Param("versionCode") int versionCode,
			@Param("clientType") int clientType);

	public String queryMemberCommunityId(@Param("userId") String userId);

	public List<Banner> findBanners(@Param("status") int status);

	public List<Coupon> queryCoupons(@Param("list") List<String> list,
			@Param("price") BigDecimal price, @Param("useScene") int useScene,
			@Param("firstResult") int firstResult);

	public Coupon queryCouponInfo(@Param("couponId") String couponId);

	public Coupon queryCouponByPassword(@Param("password") String password);

	public RechargeCard queryRechargeCardByPassword(
			@Param("password") String password);

	public int queryRecommendUseRecord(@Param("userId") String userId,
			@Param("code") String code, @Param("type") int type);

	public void updateCouponStatus(@Param("couponId") String couponId);

	public void updateRechargeCardUseStatus(@Param("cardId") String cardId);
	
	public List<Category> queryCategoryByType(@Param("type") int type);

	public ServiceCharge queryServiceChargeByOrderType(
			@Param("orderType") int orderType);

	public List<Coupon> queryUserCoupons(@Param("userId") String userId,
			@Param("cardType") int cardType);

	public List<String> queryChildCategoryId(
			@Param("categoryId") String categoryId);

	public void saveRechargeOrder(RechargeOrder rechargeOrder);

	public RechargeOrder queryRechargeOrderPayStatus(
			@Param("orderNumber") String orderNumber);

	public void updateRechargeOrderPayStatus(@Param("orderId") String orderId,
			@Param("payStatus") int payStatus);

	public void updateCoupon(Coupon coupon);

	public List<Topic> queryTopics(@Param("status") int status);
	
	public void saveUseRecommendRecord(RecommendUseRecord record);
}
