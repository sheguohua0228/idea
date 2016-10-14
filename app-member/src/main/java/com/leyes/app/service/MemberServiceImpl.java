package com.leyes.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.annotation.NotNull;
import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.member.AccountDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.dto.member.CardPackageDto;
import com.leyes.app.dto.member.MemberDto;
import com.leyes.app.dto.member.MemberInfoDto;
import com.leyes.app.dto.member.MemberRankDto;
import com.leyes.app.dto.member.UserInfoDto;
import com.leyes.app.entity.AddressInfo;
import com.leyes.app.entity.CardPackage;
import com.leyes.app.entity.Member;
import com.leyes.app.entity.MemberRank;
import com.leyes.app.enums.AccountStatus;
import com.leyes.app.enums.DeviceType;
import com.leyes.app.enums.ValidStatus;
import com.leyes.app.exceptions.DisplayException;
import com.leyes.app.exceptions.MemberException;
import com.leyes.app.mapper.MemberMapper;
import com.leyes.app.util.UuidUtil;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean memberExist(@NotNull(message="手机号不能为空")String phone)throws Exception {
		isPhone(phone);
		long result = memberMapper.queryPhoneCount(phone);
		return result>0 ? true:false;
	}
	@Override
	public String createMember(@NotNull(message="手机号不能为空")String phone,
			String deviceToken,String client,@NotNull(message="密码不能为空")String password)throws Exception {
		isPhone(phone);
		Member member=new Member();
		String id = UuidUtil.getUUIDString();
		member.setId(id);
		member.setAccountStatus(AccountStatus.normal);
		member.setUserName(phone);
		member.setDeviceToken(deviceToken);
		member.setRankId("0731dcsoft2010031200000000000010");
		member.setDeviceType(DeviceType.valueOf(client.toUpperCase()));
		member.setMobile(phone);
		member.setPassword(password);
		memberMapper.createMember(member);
		/*//发送注册信息
		QueueSender sender=new QueueSender(QueueManager.REGIST, QueueManager.HOST);
		MemberRegistMessage message=new MemberRegistMessage();
		message.setUserId(id);
		message.setDeviceType(client);
		sender.send(message);*/
		return id;
	}
	
	
	@Override
	public void updateMemberDeviceToken(@NotNull(message="用户不存在")String userId, String deviceToken)
			throws Exception {
		if(StringUtils.isNotEmpty(deviceToken)){
			memberMapper.deleteDeviceToken(deviceToken);
			memberMapper.updateMemberDeviceToken(userId,deviceToken);
		}
	}
	@Override
	public String queryUserIdByPhone(@NotNull(message="手机号不能为空")String phone)throws Exception {
		isPhone(phone);
		 String userId = memberMapper.queryUserIdByPhone(phone);
		 if(StringUtils.isBlank(userId))
			 throw new MemberException("用户不存在",new DisplayException());
		return userId;
	}
	@Override
	public String login(@NotNull(message="手机号不能为空")String phone,@NotNull(message="密码不能为空") String password)throws Exception {
		isPhone(phone);
		String userId = memberMapper.queryUserIdByPhone$password(phone, password);
		if(StringUtils.isBlank(userId))
			throw new MemberException("用户名或密码错误",new DisplayException());
		return userId;
	}
	@Override
	public void addAddress(@NotNull(message="用户不存在")String userId,AddressInfoDto addressInfoDto) throws Exception {
		String phone = addressInfoDto.getPhone();
		isPhone(phone);
		long result = memberMapper.queryAddressCount(userId, addressInfoDto.getUserName(),
				phone, addressInfoDto.getAddressDetail(),
				addressInfoDto.getCommunityId());
		if(result>0) throw new MemberException("该地址已存在！");
		String id = UuidUtil.getUUIDString();
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setId(id);
		addressInfo.setAddress(addressInfoDto.getCommunityName());
		addressInfo.setAddressDetail(addressInfoDto.getAddressDetail());
		addressInfo.setCommunityId(addressInfoDto.getCommunityId());
		addressInfo.setPhoneNumber(phone);
		addressInfo.setUserName(addressInfoDto.getUserName());
		addressInfo.setUserId(userId);
		addressInfo.setStatus(ValidStatus.valid);
		memberMapper.saveAddressInfo(addressInfo);
		
	}
	@Override
	public boolean queryAddressHasOrdered(@NotNull(message="地址不存在")String addressId) throws Exception {
		 long result = memberMapper.queryHasOrderedAddressCount(addressId);
		 return result>0?true:false;
	}
	@Override
	public void updateAddress(AddressInfoDto addressInfoDto) throws Exception {
		String phone = addressInfoDto.getPhone();
		isPhone(phone);
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setAddress(addressInfoDto.getCommunityName());
		addressInfo.setUserName(addressInfoDto.getUserName());
		addressInfo.setPhoneNumber(phone);
		addressInfo.setAddressDetail(addressInfoDto.getAddressDetail());
		addressInfo.setCommunityId(addressInfoDto.getCommunityId());
		addressInfo.setId(addressInfoDto.getAddressId());
		memberMapper.updateAddress(addressInfo);
	}
	@Override
	public void deleteAddress(@NotNull(message="地址不存在")String addressId) throws Exception {
		 long result = memberMapper.queryHasOrderedAddressCount(addressId);
		if(result>0){
			memberMapper.updateAddressStatus(addressId, ValidStatus.delete.ordinal());
		}else{
			memberMapper.deleteAddress(addressId);
		}
	}
	@Override
	public List<AddressInfoDto> queryAddress(@NotNull(message="用户不存在")String userId, int page)throws Exception {
		page = page < 0 ? 0 : page;
		List<AddressInfo> list = memberMapper.queryAddress(userId, page*10,
				ValidStatus.valid.ordinal());
		List<AddressInfoDto> result = null;
		if (list != null && list.size() > 0) {
			result = new ArrayList<AddressInfoDto>(10);
			for (AddressInfo addressInfo : list) {
				result.add(new AddressInfoDto(addressInfo.getUserName(),
						addressInfo.getPhoneNumber(), addressInfo.getAddress(),
						addressInfo.getAddressDetail(), addressInfo
								.getCommunityId(), addressInfo.getId(),null));
			}
		}
		return result;
	}
	@Override
	public AddressInfoDto queryAddressById(@NotNull(message="地址不存在")String addressId)
			throws Exception {
		AddressInfo addressInfo = memberMapper.queryAddressById(addressId);
		if(addressInfo==null)
			throw new MemberException("地址信息不存在");
		return new AddressInfoDto(addressInfo.getUserName(), addressInfo.getPhoneNumber(), 
				addressInfo.getAddress(), addressInfo.getAddressDetail(),
				addressInfo.getCommunityId(), addressInfo.getId(),addressInfo.getRemarkAddress());
	}
	@Override
	public String queryUserCommunity(@NotNull(message="用户不存在")String userId) throws Exception {
		return memberMapper.queryCommunityId(userId);
	}
	@Override
	public void updateUserCommunity(@NotNull(message="用户不存在")String userId,@NotNull(message="社区不存在") String communityId)
			throws Exception {
		memberMapper.updateMemberCommunity(userId, communityId);
	}
	@Override
	public UserInfoDto queryUserInfo(@NotNull(message="用户不存在")String userId) throws Exception {
		Member member = memberMapper.queryUserInfo(userId);
		if(member==null)
			throw new MemberException("用户身份失效");
		return new UserInfoDto(member.getHeadIcon(), 
				member.getUserName(), member.getRankId());
	}
	@Override
	public AccountDto queryAccount(@NotNull(message="用户不存在")String userId) throws Exception {
		Member member = memberMapper.queryAccount(userId);
		if(member==null)
			throw new MemberException("用户身份失效");
		return new AccountDto(member.getDeposit(),member.getScore());
	}
	
	@Override
	public void updateMemberAccount(IncomeOutFlowDto incomeOutFlowDto) throws Exception  {
		String userId = incomeOutFlowDto.getUserId();
		BigDecimal incomeBalance = incomeOutFlowDto.getGetBalance();
		int incomeIntegral = incomeOutFlowDto.getGetIntegral();
		BigDecimal outBalance = incomeOutFlowDto.getUseBalance();
		int outIntegral = incomeOutFlowDto.getUseIntegral();
		
		memberMapper.updateAccount(userId, incomeIntegral-outIntegral, incomeBalance.subtract(outBalance));
	}
	@Override
	public void updateMemberHeadImage(@NotNull(message="用户不存在")String userId,@NotNull(message="头像不能为空") String imageUrl) throws Exception {
		memberMapper.updateMemberHeadImage(userId, imageUrl);
	}
	@Override
	public void updateMemberUserName(@NotNull(message="用户不存在")String userId,@NotNull(message="昵称不能为空") String userName) throws Exception {
		memberMapper.updateMemberUserName(userId, userName);
	}
	
	@Override
	public void updateMemberPassword(@NotNull(message="用户不存在")String userId, @NotNull(message="密码不能为空")String password)throws Exception {
		memberMapper.updateMemberPassword(userId, password);
	}
	
	@Override
	public List<UserInfoDto> batchQueryMemberInfo(List<String> userIds)
			throws Exception {
		List<Member> list = memberMapper.batchQueryMemberInfo(userIds);
		List<UserInfoDto> result=null;
		UserInfoDto dto=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<UserInfoDto>();
			for(Member member:list){
				dto=new UserInfoDto();
				dto.setHeadImage(member.getHeadIcon());
				dto.setUserName(cashNumber(member.getUserName()));
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public void addCoupon(@NotNull(message="用户不存在")String userId,
			String couponId,int time,Date invalidTime) throws Exception {
		CardPackage cardPackage=new CardPackage();
		cardPackage.setId(UuidUtil.getUUIDString());
		cardPackage.setUserId(userId);
		cardPackage.setCardId(couponId);
		cardPackage.setTime(time);
		cardPackage.setInvalidTime(invalidTime);
		memberMapper.addCouponRecord(cardPackage);
	}
	
	
	@Override
	public void useCoupon(String userId, String couponId) throws Exception {
		memberMapper.useCoupon(userId, couponId);
	}
	@Override
	public void updateMemberExp(@NotNull(message="用户不存在")String userId, int exppValue) throws Exception {
		memberMapper.updateMemberExp(userId, exppValue);
		String rankId=memberMapper.queryExpValueOfRank(userId);
		memberMapper.updateMemberRankId(userId,rankId);
	}
	@Override
	public List<CardPackageDto> queryCoupons(@NotNull(message="用户不存在")String userId,Integer time) throws Exception {
		List<CardPackage> list = memberMapper.queryCoupons(userId,time);
		List<CardPackageDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<CardPackageDto>();
			CardPackageDto dto=null;
			for (CardPackage cardPackage : list) {
				dto=new CardPackageDto();
				dto.setCardId(cardPackage.getCardId());
				dto.setUseStatus(cardPackage.getUseStatus());
				result.add(dto);
			}
		}
		return result;
	}
	@Override
	public MemberDto queryMemberInfo(@NotNull(message="用户不存在")String userId) throws Exception {
		Member member = memberMapper.queryMemberInfo(userId);
		if(member==null)
			throw new MemberException("用户身份失效");
		return new MemberDto(member.getDeposit(), member.getScore(), member.getUserName(), member.getDeviceToken(), member.getDeviceType());
	} 
	
	@Override
	public MemberRankDto queryMemberRankInfo(@NotNull(message="用户不存在")String userId)throws Exception {
		MemberRank memberRank = memberMapper.queryMemberRankInfo(userId);
		if(memberRank==null)
			throw new MemberException("未查询到该会员等级信息");
		return new MemberRankDto(memberRank.getName(), memberRank.getPreferentialScale(), memberRank.getScore(), memberRank.getLv(), memberRank.getDiscount());
	}
	
	
	@Override
	public String queryMemberMobileById(@NotNull(message="用户不存在") String userId) throws Exception {
		return memberMapper.queryMemberMobileById(userId);
	}
	
	
	
	@Override
	public void updateAddressRemark(String addressId, String remark)
			throws Exception {
		memberMapper.updateAddressRemark(addressId,remark);
	}
	 
	@Override
	public MemberInfoDto queryMemberAccountInfo(@NotNull(message="手机号码不能为空") String phone) throws Exception {
		isPhone(phone);
		Member member = memberMapper.queryMemberAccountInfo(phone);
		if(member==null)
			throw new MemberException("用户信息不存在",new DisplayException());
		return new MemberInfoDto(member.getRankId(),String.valueOf(member.getScore()),
				member.getDeposit().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}
	protected void isPhone(String phone) throws MemberException{
		Pattern p = Pattern.compile("^1\\d{10}$");  
		Matcher m = p.matcher(phone);  
		  if(!m.matches()){
			  throw new MemberException("手机号码不正确");
		  }
	}
	protected static String cashNumber (String str){
		String ss = null;
		if(str.length()>7){
			ss = str.substring(0,str.length()-(str.substring(3)).length())+"****"+str.substring(7);
		}
		return ss;
	}
}
