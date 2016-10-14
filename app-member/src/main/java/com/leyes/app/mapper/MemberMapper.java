package com.leyes.app.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leyes.app.entity.AddressInfo;
import com.leyes.app.entity.CardPackage;
import com.leyes.app.entity.Member;
import com.leyes.app.entity.MemberRank;

public interface MemberMapper {

	public long queryPhoneCount(@Param("phone") String phone);

	public void createMember(Member member);

	public AddressInfo queryAddressById(@Param("addressId") String addressId);

	public void updateAccount(@Param("userId") String userId,
			@Param("integral") int integral,
			@Param("balance") BigDecimal balance);

	public Member queryAccount(@Param("userId") String userId);

	public Member queryUserInfo(@Param("userId") String userId);

	public List<Member> batchQueryMemberInfo(
			@Param("userIds") List<String> userIds);

	public List<CardPackage> queryCoupons(@Param("userId") String userId,
			@Param("time") Integer time);

	public void updateMemberHeadImage(@Param("userId") String userId,
			@Param("imageUrl") String imageUrl);

	public void updateMemberUserName(@Param("userId") String userId,
			@Param("userName") String userName);

	public void updateMemberCommunity(@Param("userId") String userId,
			@Param("communityId") String communityId);

	public void updateMemberPassword(@Param("userId") String userId,
			@Param("password") String password);

	public String queryCommunityId(@Param("userId") String userId);

	public String queryUserIdByPhone(@Param("phone") String phone);

	public String queryUserIdByPhone$password(@Param("phone") String phone,
			@Param("password") String password);

	public long queryAddressCount(@Param("userId") String userId,
			@Param("userName") String userName, @Param("phone") String phone,
			@Param("addressDetail") String addressDetail,
			@Param("communityId") String communityId);

	public void saveAddressInfo(AddressInfo addressInfo);

	public void updateAddress(AddressInfo addressInfo);

	public void updateAddressStatus(@Param("addressId") String addressId,
			@Param("status") int status);

	public void deleteAddress(@Param("addressId") String addressId);

	public List<AddressInfo> queryAddress(@Param("userId") String userId,
			@Param("firstResult") int firstResult, @Param("status") int status);

	public long queryHasOrderedAddressCount(@Param("addressId") String addressId);


	public void addCouponRecord(CardPackage cardPackage);

	public void updateMemberExp(@Param("userId") String userId,
			@Param("expValue") int expValue);

	public Member queryMemberInfo(@Param("userId") String userId);

	public MemberRank queryMemberRankInfo(@Param("userId") String userId);

	public String queryMemberMobileById(@Param("userId") String userId);

	public void updateAddressRemark(@Param("addressId") String addressId,
			@Param("remark") String remark);

	public Member queryMemberAccountInfo(@Param("phone") String phone);

	public String queryExpValueOfRank(@Param("userId") String userId);

	public void updateMemberRankId(@Param("userId") String userId,
			@Param("rankId") String rankId);

	public void useCoupon(@Param("userId") String userId,
			@Param("couponId") String couponId);

	public void updateMemberDeviceToken(@Param("userId") String userId,
			@Param("deviceToken") String deviceToken);
	
	public void deleteDeviceToken(@Param("deviceToken") String deviceToken);
}
