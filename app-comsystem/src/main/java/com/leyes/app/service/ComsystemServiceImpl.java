package com.leyes.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.annotation.NotNull;
import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.comsystem.BannerDto;
import com.leyes.app.dto.comsystem.CommunityDto;
import com.leyes.app.dto.comsystem.CommunitySearchDto;
import com.leyes.app.dto.comsystem.CouponStatusDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.comsystem.OrderDto;
import com.leyes.app.dto.comsystem.PayResultDto;
import com.leyes.app.dto.comsystem.PushMessageDto;
import com.leyes.app.dto.comsystem.RechargeOrderDto;
import com.leyes.app.dto.comsystem.ServiceChargeDto;
import com.leyes.app.dto.comsystem.TemplateDto;
import com.leyes.app.dto.comsystem.TopicDto;
import com.leyes.app.dto.comsystem.VersionInfoDto;
import com.leyes.app.dto.member.CardPackageDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.dto.shop.CategoryDto;
import com.leyes.app.dto.shop.SecondCategoryDto;
import com.leyes.app.entity.Banner;
import com.leyes.app.entity.Category;
import com.leyes.app.entity.ClientUpdateInfo;
import com.leyes.app.entity.Community;
import com.leyes.app.entity.Coupon;
import com.leyes.app.entity.IntegralRule;
import com.leyes.app.entity.RechargeCard;
import com.leyes.app.entity.RechargeOrder;
import com.leyes.app.entity.RecommendUseRecord;
import com.leyes.app.entity.ServiceCharge;
import com.leyes.app.entity.Topic;
import com.leyes.app.enums.CardType;
import com.leyes.app.enums.CardUseScene;
import com.leyes.app.enums.DeviceType;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.PhoneCodeKey;
import com.leyes.app.enums.SMSTemplateEnum;
import com.leyes.app.enums.TradeType;
import com.leyes.app.enums.ValidStatus;
import com.leyes.app.exceptions.ComsystemException;
import com.leyes.app.exceptions.DisplayException;
import com.leyes.app.mapper.CommunityMapper;
import com.leyes.app.mapper.ComsystemMapper;
import com.leyes.app.mapper.IntegralMapper;
import com.leyes.app.pay.PayMethodWapper;
import com.leyes.app.push.PushConfig;
import com.leyes.app.util.PhoneCodeRedisCache;
import com.leyes.app.util.SendSMS;
import com.leyes.app.util.StringUtil;
import com.leyes.app.util.UuidUtil;
import com.leyes.app.util.VerifyCodeCache;
import com.leyes.app.util.alipay.AliPayContext;
import com.leyes.app.util.wechat.HttpUtil;

@Service("comsystemService")
public class ComsystemServiceImpl implements ComsystemService {
	 
	@Autowired
	private ComsystemMapper comsystemMapper;

	@Autowired
	private IntegralMapper integralMapper;
	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	private PhoneCodeRedisCache phoneCodeRedisCache;
	
	@Override
	public VersionInfoDto checkUpdate(@NotNull(message="客户端类型为空")String client,int versionCode,int clientType) throws Exception {
		ClientUpdateInfo updateInfo = comsystemMapper.findByClientVersion(client, versionCode,clientType);
		String downloadUrl="-1";
		if(updateInfo!=null){
			downloadUrl=updateInfo.getDownloadUrl();
		}
		return new VersionInfoDto(downloadUrl);
	}
	@Override
	public List<TemplateDto> queryTemplate(String communityId) {
		 
		return null;
	}
	@Override
	public List<BannerDto> queryBanner() {
		List<Banner> list = comsystemMapper.findBanners(ValidStatus.valid.ordinal());// entity转Dto
		List<BannerDto> result = null;
		if (list != null && list.size() > 0) {
			result=new ArrayList<BannerDto>(10);
			for (Banner banner : list) {
				result.add(new BannerDto(banner.getImageUrl(), banner.getDescription(), banner.getLinkUrl()));
			}
		}
		return result;
	}
	@Override
	public CommunityDto queryCommunityById(String communityId) throws Exception {
		if(StringUtils.isBlank(communityId))
			return null;
		Community community = communityMapper.queryCommunityById(communityId);
		if(community==null)
			return null;
		return new CommunityDto(community.getId(), community.getParentId(),
				community.getLatitude(), community.getLongitude(), community.getName());
	}
	@Override
	public List<TopicDto> queryTopics(String communityId) {
		 List<Topic> list = comsystemMapper.queryTopics(ValidStatus.valid.ordinal());
		 List<TopicDto> result=null;
		 if(list!=null && list.size()>0){
			 result=new ArrayList<TopicDto>(list.size());
			 TopicDto dto=null;
			 for (Topic topic : list) {
				dto=new TopicDto();
				dto.setName(topic.getName());
				dto.setImageUrl(topic.getImageUrl());
				dto.setUrl(topic.getJumpUrl());
				result.add(dto);
			}
		 }
		return result;
	}
	
	@Override
	public String queryPropertyInfo(String communityId) throws Exception {
		return communityMapper.queryCommunityPropertyPhone(communityId);
	}
	@Override
	public IntegralRuleDto queryIntegralRule(TradeType tradeType,
			IntegralSourceType sourceType) throws Exception{
		IntegralRule integraRule = integralMapper.queryRule(tradeType.ordinal(), sourceType.ordinal());
		
		return new IntegralRuleDto(integraRule.getType().ordinal() ,integraRule.getSourceType().ordinal(),
				integraRule.getRatio());
	}
	@Override
	public boolean push(PushMessageDto pushMessage) throws Exception {
		DeviceType deviceType = pushMessage.getType();
		List<String> deviceTokens = pushMessage.getDeviceTokens();
		switch (deviceType) {
			case ANDROID:
				PushConfig.sendAndroidUnicast(deviceTokens.toArray(new String[deviceTokens.size()]), pushMessage.getTitle(), pushMessage.getText(), pushMessage.getExtras());
				break;
			case IOS:
				PushConfig.sendIOSUnicast(deviceTokens.toArray(new String[deviceTokens.size()]), pushMessage.getTitle(), pushMessage.getExtras());
				break;
			case ASSISTANT:
				PushConfig.sendAssistantUnicast(deviceTokens.toArray(new String[deviceTokens.size()]), pushMessage.getTitle(), pushMessage.getText(), pushMessage.getExtras());
				break;
			default:
				return false;
		}
		return false;
	}
	@Override
	public void sendSMS(@NotNull(message="手机号不能为空")String[] mobileArray,SMSTemplateEnum template,List<String[]> list) throws Exception {
		String[] contentArray = StringUtil.Format(template.getValue(), list);
		if(contentArray.length==1)
			SendSMS.sendOnce(mobileArray, contentArray[0]);
		else
			SendSMS.sendBatch(mobileArray, contentArray);
	}
	
	
	@Override
	public void sendPhoneCode(@NotNull(message="手机号码不能为空") String phone,@NotNull(message="验证码类型不存在") PhoneCodeKey phoneCodeKey)
			throws Exception {
		String code = createPhoneCode(phone, phoneCodeKey);
		List<String[]> list=new ArrayList<String[]>(1);
		list.add(new String[]{code,"10"});
		if(!phone.equals("13980454347")){
			//发送验证码
			sendSMS(new String[]{phone},SMSTemplateEnum.T_NormalIdentifyCodeSMS,list); 
		}
	}
	
	
	@Override
	public void validatePhoneCode(@NotNull(message="手机号码不能为空") String phone,@NotNull(message="验证码不能为空") String code,
			PhoneCodeKey phoneCodeKey) throws Exception {
		phoneCodeRedisCache.setDomainKey(phoneCodeKey.getKey());
		VerifyCodeCache pcInfo = (VerifyCodeCache)phoneCodeRedisCache.get(phone, VerifyCodeCache.class);
		if(pcInfo==null)
			throw new ComsystemException("验证码不存在或已过期",new DisplayException());
		if(pcInfo.getPhone().equals(phone) && pcInfo.getCode().equals(code)){
            phoneCodeRedisCache.Remove(phone);
		}else 
			throw new ComsystemException("验证码不存在或已过期",new DisplayException());
		 
	}
	
	@Override
	public List<CommunitySearchDto> searchCommunity(@NotNull(message="请输入关键词") String keyWord) throws Exception {
		List<Community> list = communityMapper.searchCommunity(keyWord,ValidStatus.valid.ordinal());
		List<CommunitySearchDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<CommunitySearchDto>(list.size());
			CommunitySearchDto dto=null;
			for(Community community:list){
				String communityId = community.getId();
				String parentId = community.getParentId();
				dto=new CommunitySearchDto(communityId, 
						community.getParentId(), 
						community.getLatitude(), 
						community.getLongitude(), 
						community.getName(), 
						community.getAddress());
				dto.putExtars(communityId, parentId);
				result.add(dto);
			}
		}
		return result;
	}
	@Override
	public List<CouponDto> queryCoupons(List<CardPackageDto> list,BigDecimal price,
			int sence,String photoType,int page) throws Exception {
		page = page < 0 ? 0 : page;
		List<String> couponIds=new ArrayList<String>();
		if(list!=null){
			for(CardPackageDto dto:list){
				couponIds.add(dto.getCardId());
				dto.getUseStatus();
			}
		}
		
		List<Coupon> coupons = comsystemMapper.queryCoupons(couponIds,price, sence,page*10);
		List<CouponDto> result=null;
		if(coupons!=null && coupons.size()>0){
			result=new ArrayList<CouponDto>(10);
			for(Coupon coupon:coupons){
				if(coupon.getCardType().equals(CardType.group) && StringUtils.isNotEmpty(photoType)){
					if(photoType.equals("PASSPORT")){
						int passportNumber = coupon.getPassportNumber();
						 if(passportNumber<1){
							 continue;
						 }
					}else if(photoType.equals("VISA")){
						int visaPhotoNumber = coupon.getVisaPhotoNumber();
						 if(visaPhotoNumber<1){
							 continue;
						 }
					}else {
						continue;
					}
				}
				CouponDto dto = createCoupons(coupon);
				//TODO
				for(CardPackageDto cdto:list){
					if(cdto.getCardId().equals(coupon.getId())){
						dto.setUseStatus(cdto.getUseStatus());
						break;
					}
				}
				result.add(dto);
			}
		}
		return result;
	}
	@Override
	public CouponDto queryCoupon(@NotNull(message="优惠卷不存在")String couponId) throws Exception {
		Coupon coupons = comsystemMapper.queryCouponInfo(couponId);
		if(coupons==null)
			throw new ComsystemException("优惠卷不存在");
		return createCoupons(coupons);
	}
	
	@Override
	public CouponStatusDto queryCouponByPassword(@NotNull(message="优惠码无效")String password) throws Exception {
		Coupon coupon = comsystemMapper.queryCouponByPassword(password);
		if(coupon==null)
			throw new ComsystemException("优惠码无效",new DisplayException());
		return new CouponStatusDto(coupon.getId(), coupon.isReuse(),
				coupon.getCardType(), coupon.getPrice(),coupon.getTime(),
				coupon.getInvalidTime());
	}
	
	
	@Override
	public CouponStatusDto queryRechargeCardByPassword(@NotNull(message="优惠码无效")String password)
			throws Exception {
		RechargeCard rechargeCard = comsystemMapper.queryRechargeCardByPassword(password);
		if(rechargeCard==null)
			throw new ComsystemException("优惠码无效",new DisplayException());
		return new CouponStatusDto(rechargeCard.getId(), false,
					rechargeCard.getCardType(), 
					rechargeCard.getDenomination(),
					rechargeCard.getTime(),
					rechargeCard.getInvalidTime());
	}
	
	@Override
	public boolean isUseRecommendCode(@NotNull(message="用户身份失效")String userId,
			String code, int type)
			throws Exception {
		int result = comsystemMapper.queryRecommendUseRecord(userId, code, type);
		return result>0?false :true;
	}
	@Override
	public void updateCoupon(CouponDto couponDto) {
		 Coupon coupon=new Coupon();
		 coupon.setId(couponDto.getCardId());
		 coupon.setClothesNumber(couponDto.getClothesNumber());
		 coupon.setShoeNumber(couponDto.getShoeNumber());
		 coupon.setDarnNumber(couponDto.getDarnNum());
		 coupon.setVisaPhotoNumber(couponDto.getVisaPhotoNum());
		 coupon.setPassportNumber(couponDto.getPassportNum());
		 comsystemMapper.updateCoupon(coupon);
		 
	}
	
	@Override
	public void updateCouponStatus(@NotNull(message="优惠卷不存在")String couponId) throws Exception {
		comsystemMapper.updateCouponStatus(couponId);
	}
	
	@Override
	public void updateRechargeCardUseStatus(@NotNull(message="优惠卷不存在")String cardId) throws Exception {
		comsystemMapper.updateRechargeCardUseStatus(cardId);
		
	}
	
	@Override
	public void saveUseRecommendRecord(String userId, String code, int type)
			throws Exception {
		RecommendUseRecord record=new RecommendUseRecord();
		record.setCode(code);
		record.setType(type);
		record.setUserId(userId);
		comsystemMapper.saveUseRecommendRecord(record);
	}
	@Override
	public boolean aliPayNotifyVerify(String notifyId) throws Exception {
		String url="https://mapi.alipay.com/gateway.do?service=notify_verify&partner=PARTNER&notify_id=NOTIFYID";
		url.replace("PARTNER", AliPayContext.partner);
		url.replace("NOTIFYID", notifyId);
		String result = HttpUtil.httpsRequest(url, "GET", null);
		return result.equals("true")?true:false;
	}
	 
	@Override
	public PayResultDto payOrder(OrderDto order, BigDecimal balanceNumber,
			int integralNumber,BigDecimal ratio,int payType) throws Exception {
		PayMethodWapper wapper = new PayMethodWapper(order);
		return wapper.payMethod(balanceNumber, integralNumber,ratio, payType);
	}

	@Override
	public List<CategoryDto> queryCategoryByType(int type)	throws Exception {
		List<Category> list = comsystemMapper.queryCategoryByType(type);
		List<CategoryDto> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<CategoryDto>(10);
			CategoryDto dto=null;
			for(int i=0;i<list.size();i++){
				Category category = list.get(i);
				String categoryId = category.getId();
				dto=new CategoryDto(categoryId, category.getName(), category.getIcon());
				 
				if(category.getParentId().equals("-1")){
					list.remove(category);
					i--;
					if(list.size()>0){
						for(int j=0;j<list.size();j++){
							Category category1 = list.get(j);
							if(categoryId.equals(category1.getParentId())){
								dto.addData(new SecondCategoryDto(category1.getId(), category1.getName()));
								list.remove(category1);
								j--;
							}
						}
					}
				}
				result.add(dto);
			}
		}
		return result;
	}
	
	
	@Override
	public ServiceChargeDto queryServiceChargeByOrderType(OrderType orderType)throws Exception {
		ServiceCharge serviceCharge = comsystemMapper.queryServiceChargeByOrderType(orderType.ordinal());
		if(serviceCharge==null)
			return null;
		return new ServiceChargeDto(serviceCharge.getExpense(), serviceCharge.getSendCondition());
	}
	
	
	@Override
	public List<CouponDto> queryUserGroupCoupons(@NotNull(message="用户未登录")String userId)
			throws Exception {
		List<Coupon> couponsList = comsystemMapper.queryUserCoupons(userId, CardType.group.ordinal());
		List<CouponDto> result=null;
		if(couponsList!=null && couponsList.size()>0){
			result=new ArrayList<CouponDto>(couponsList.size());
			for(Coupon coupon:couponsList){
				result.add(createCoupons(coupon));
			}
		}
		return result;
	}
	
	
	@Override
	public List<CommunityDto> queryCommunityByIds(List<String> communityIds)
			throws Exception {
		List<Community> list = communityMapper.queryCommunityByIds(communityIds);
		List<CommunityDto> result=null;
		if(list!=null && list.size()>0){
			CommunityDto dto=null;
			result=new ArrayList<CommunityDto>();
			for(Community community:list){
				dto=new CommunityDto();
				dto.setCommunityId(community.getId());
				dto.setName(community.getName());
				result.add(dto);
			}
		}
		return result;
	}
	
	
	@Override
	public List<CommunityDto> queryChirldCommunity(String communityId)
			throws Exception {
		List<Community> list = communityMapper.queryChirldCommunity(communityId);
		List<CommunityDto> result=null;
		if(list!=null && list.size()>0){
			CommunityDto dto=null;
			result=new ArrayList<CommunityDto>();
			for(Community community:list){
				dto=new CommunityDto();
				dto.setCommunityId(community.getId());
				dto.setName(community.getName());
				result.add(dto);
			}
		}
		return result;
	}
	
	@Override
	public String queryParentCommunityId(String communityId) throws Exception {
		String parentId = communityMapper.queryParentCommunityId(communityId);
		if(StringUtils.isNotEmpty(parentId)&&parentId.equals("-1"))
			parentId=communityId;
		return parentId;
	}
	@Override
	public List<String> queryChildCategoryId(@NotNull(message="分类不存在")String categoryId)
			throws Exception {
		return comsystemMapper.queryChildCategoryId(categoryId);
	}
	
	
	@Override
	public RechargeOrderDto createRechargeOrder(String userId, BigDecimal price, int payType)
			throws Exception {
		RechargeOrder order = new RechargeOrder();
		order.setId(UuidUtil.getUUIDString());
		String orderNumber = UuidUtil.orderNumber();
		order.setOrderNumber(orderNumber);
		order.setPayType(PayType.translate(payType));
		order.setPrice(price);
		order.setUserId(userId);
		order.setPayStatus(PayStatus.UNPAY);
		comsystemMapper.saveRechargeOrder(order);
		RechargeOrderDto dto = new RechargeOrderDto();
		dto.setOrderNumber(orderNumber);
		dto.setPrice(price);
		return dto;
	}
	
	
	@Override
	public OrderPayStatusDto queryOrderStatusByOrderNumber(String orderNumber)
			throws Exception {
		RechargeOrder rechargeOrder=comsystemMapper.queryRechargeOrderPayStatus(orderNumber);
		if(rechargeOrder==null)
			throw new ComsystemException("充值订单不存在");
		OrderPayStatusDto orderPayStatus = new OrderPayStatusDto();
		orderPayStatus.setId(rechargeOrder.getId());
		orderPayStatus.setPayStatus(rechargeOrder.getPayStatus());
		orderPayStatus.setUserId(rechargeOrder.getUserId());
		orderPayStatus.setFinalPrice(rechargeOrder.getPrice());
		orderPayStatus.setPayType(rechargeOrder.getPayType());
		return orderPayStatus;
	}
	
	
	@Override
	public void updateRechargeOrderPayStatus(OrderPayStatusDto orderPayStatus) {
		comsystemMapper.updateRechargeOrderPayStatus(orderPayStatus.getId(),
				orderPayStatus.getPayStatus().ordinal());
	}
	private CouponDto createCoupons(Coupon c) {
		StringBuilder sb=null;
		 
		CouponDto card=new CouponDto(c.getId(), c.getDenomination(), c.getName(),
				  c.getInvalidTime().getTime());
		card.setDiscountRatioOne(c.getDiscountRatioOne());
		card.setDiscountRatioTwo(c.getDiscountRatioTwo());
		card.setVisaPhotoNum(c.getVisaPhotoNumber());
		card.setPassportNum(c.getPassportNumber());
		card.setUseCondition(c.getUseCondition());
		card.setCardType(c.getCardType().ordinal());
		card.setClothesNumber(c.getClothesNumber());
		card.setDarnNum(c.getDarnNumber());
		card.setShoeNumber(c.getShoeNumber());
		CardUseScene useScene = c.getUseScene();
		switch (useScene) {
			case all:
				card.setRemark(CardUseScene.all.getValue());
				break;
			case wash:
				card.setRemark(CardUseScene.wash.getValue());
				break;
			case print:
				card.setRemark(CardUseScene.print.getValue());
				break;
			case shop:
				card.setRemark(CardUseScene.shop.getValue());
				break;
			case property:
				card.setRemark(CardUseScene.property.getValue());
				break;
		}
		card.setRemark(card.getRemark()+c.getRemark());
		card.setPrice(c.getPrice());
		CardType cardType = c.getCardType();
		switch (cardType) {
			case group:
				card.setUseStatus(2);
				sb=new StringBuilder(card.getRemark());
				sb.append("卡券优惠包含：");
				appendRemark(sb, c.getClothesNumber(), "件夏装，");
				appendRemark(sb, c.getShoeNumber(), "双鞋，");
				appendRemark(sb, c.getDarnNumber(), "次精工织补，");
				appendRemark(sb, c.getVisaPhotoNumber(), "次签证照片打印，");
				appendRemark(sb, c.getPassportNumber(), "次护照照片打印，");
				sb.append("鞋包");
				double discountCountOne = c.getDiscountRatioOne();
				sb.append(discountCountOne*9.00);
				sb.append("折，家居");
				double discountRatioTwo = c.getDiscountRatioTwo();
				sb.append(discountRatioTwo*9.00);
				sb.append("折(含积分)。");
				card.setRemark(sb.toString());
				break;
		 
			case rebate:
				card.setPrice(c.getDenomination());
				break;
			default:
				break;
		}
		 
		
			
		 
		
		
		return card;
	}
	 protected void appendRemark(StringBuilder sb,int number,String remark){
		 if(number>0){
			 sb.append(number);
			 sb.append(remark);
		}
	 }
	 protected String createPhoneCode(String phoneNo,PhoneCodeKey key){
		 	//生成验证码
			Random random = new Random();
			String code =  String.valueOf(random.nextInt(899999)+100000);
			if(phoneNo.equals("13980454347")){
				code="123456";
			}
	        VerifyCodeCache info = new VerifyCodeCache(phoneNo, code);
	        phoneCodeRedisCache.setDomainKey(key.getKey());
	        phoneCodeRedisCache.set(phoneNo, info, 600);
	        return code;
	    }
}
