package com.leyes.app.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.annotation.Security;
import com.leyes.app.dto.clothes.ClotheOrderExctraDto;
import com.leyes.app.dto.comsystem.CommunityDto;
import com.leyes.app.dto.comsystem.CouponStatusDto;
import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.employee.EmployeeDto;
import com.leyes.app.dto.member.AccountDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.dto.member.CardPackageDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.dto.member.UserInfoDto;
import com.leyes.app.dto.print.PrintOrderDto;
import com.leyes.app.dto.query.AccountFlowDto;
import com.leyes.app.dto.shop.GoodsOrderDto;
import com.leyes.app.enums.CardType;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.PhoneCodeKey;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.enums.TradeType;
import com.leyes.app.message.IncomeOutDetailMessage;
import com.leyes.app.message.StatisticsOrderMessage;
import com.leyes.app.message.UpdateCommunityMessage;
import com.leyes.app.mq.QueueSender;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ClothesService;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.EmployeeService;
import com.leyes.app.service.MallService;
import com.leyes.app.service.MemberService;
import com.leyes.app.service.PrintService;
import com.leyes.app.service.QuerySystemService;
import com.leyes.app.service.StatisticsService;
import com.leyes.app.util.MD5Encrypt;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.comsystem.GetVerificationCodeRequest;
import com.leyes.app.web.request.comsystem.PageRequest;
import com.leyes.app.web.request.comsystem.RegistRequest;
import com.leyes.app.web.request.comsystem.UseCouponRequest;
import com.leyes.app.web.request.user.AddAddressRequest;
import com.leyes.app.web.request.user.DeleteAddressRequest;
import com.leyes.app.web.request.user.LoginRequest;
import com.leyes.app.web.request.user.ModifyAddressRequest;
import com.leyes.app.web.request.user.ModifyUserCommunityRequest;
import com.leyes.app.web.request.user.QueryCouponsRequest;
import com.leyes.app.web.request.user.UpdateHeadImageRequest;
import com.leyes.app.web.request.user.UpdatePasswordRequest;
import com.leyes.app.web.request.user.UpdateUserNameRequest;
import com.leyes.app.web.response.employee.EmployeeMobileResponse;
import com.leyes.app.web.response.user.LoginResponse;
import com.leyes.app.web.response.user.QueryAccountResponse;
import com.leyes.app.web.response.user.QueryBillResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.leyes.app.web.utils.SessionContextUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value="/member")
@Api(value = "member-api", description = "用户模块接口API", position = 1)
public class MemberController extends BaseController{
	 
	private Logger logger=Logger.getLogger(getClass());
	
	private MemberService memberService=SpringContextUtils.getBean("memberService");
	
	private ComsystemService comsystemService=SpringContextUtils.getBean("comsystemService");
	
	private ClothesService clothesService = null;
	
	private EmployeeService employeeService=null;
	 
	private PrintService printService=null;
	
	private MallService mallService=SpringContextUtils.getBean("mallService");
	
	private QuerySystemService querySystemService=null;
	
	private StatisticsService statisticsService = null;
	
	@ApiOperation(value = "获取验证码", notes = "获取验证码",response=ReturnResult.class, position = 6)
	@RequestMapping(value = "getVerificationCode", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult getVerificationCode(@RequestBody GetVerificationCodeRequest request) throws Exception {
		boolean result = memberService.memberExist(request.getUserName());
		int codeType = request.getCodeType();
		if(result && codeType==0){
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "用户已存在");
		}else if(! result && codeType!=0){
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "用户不存在");
		}
			
		comsystemService.sendPhoneCode(request.getUserName(),PhoneCodeKey.translate(request.getCodeType()));
		return ReturnResult.SUCCESS("短信下发成功，请注意查收");
	}
	
	@ApiOperation(value = "注册", notes = "注册",response=ReturnResult.class, position = 6)
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult regist(@RequestBody RegistRequest request)throws Exception {
		//判断用户是否存在
		boolean result = memberService.memberExist(request.getUserName());
		if(result) 
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "该手机号已被注册");
		//验证验证码是否正确
		comsystemService.validatePhoneCode(request.getUserName(), request.getCode(), PhoneCodeKey.COM_REGIST);
		String client = sessionContextUtils.getClient();
		//创建用户
		String password = request.getPassword();
		String userId = memberService.createMember(request.getUserName(),request.getDeviceToken(),client,MD5Encrypt.md5(password));
		//增加积分
		IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.income, IntegralSourceType.regist);
		int getIntegral = integralRule.getRatio().intValue();
		IncomeOutFlowDto dto=new IncomeOutFlowDto();
		dto.setUserId(userId);
		dto.setGetIntegral(getIntegral);
		memberService.updateMemberAccount(dto);
		//记录流水
		IncomeOutDetailMessage outMoneyMessage = new IncomeOutDetailMessage();
		outMoneyMessage.setUserId(userId);
		outMoneyMessage.setMoney("+"+getIntegral);
		outMoneyMessage.setCurrencyType(PayType.INTEGRAL);
		outMoneyMessage.setRemark("注册");
		QueueSender queueSender = new QueueSender(QueueManager.ACCOUNT_INCOMEOUT_DETAIL_QUEUE, QueueManager.HOST);
		queueSender.send(outMoneyMessage);
		
		//保存用户到缓存
		UserSession userSession=new UserSession();
		userSession.setUserId(userId);
		userSession.setRedisAuthTypeEnum(RedisAuthType.clientUser);
		String token = SessionContextUtils.getNewToken();
		sessionContextUtils.saveContextCurrentUserNoExpire(token, userSession);
		LoginResponse response=new LoginResponse();
		response.setToken(token);
		
		//统计数据 TODO
		QueueSender Sender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
		StatisticsOrderMessage statisticsOrderMessage = new StatisticsOrderMessage();
		statisticsOrderMessage.setOrderType(3);
		statisticsOrderMessage.setUserId(userId);
		Sender.send(statisticsOrderMessage);
		
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "登录", notes = "动态密码登录",response=ReturnResult.class,position = 1)
	@RequestMapping(value="login/dynamic",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResult loginDynamic(@RequestBody LoginRequest request) throws Exception{
		comsystemService.validatePhoneCode(request.getUserName(), request.getPassword(), PhoneCodeKey.COM_LOGIN);
		
		String userId = memberService.queryUserIdByPhone(request.getUserName());
		memberService.updateMemberDeviceToken(userId, request.getDeviceToken());
		//保存用户到缓存
		UserSession userSession=new UserSession();
		userSession.setUserId(userId);
		userSession.setRedisAuthTypeEnum(RedisAuthType.clientUser);
		String token = SessionContextUtils.getNewToken();
		sessionContextUtils.saveContextCurrentUserNoExpire(token, userSession);
		LoginResponse response=new LoginResponse();
		response.setToken(token);
		
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "登录", notes = "密码登录",response=ReturnResult.class,position = 1)
	@RequestMapping(value="login/normal",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResult loginNormal(@RequestBody LoginRequest request) throws Exception{
		String password = request.getPassword();
		
		String userId = memberService.login(request.getUserName(), MD5Encrypt.md5(password));
		memberService.updateMemberDeviceToken(userId, request.getDeviceToken());
		//保存用户到缓存
		UserSession userSession=new UserSession();
		userSession.setUserId(userId);
		userSession.setRedisAuthTypeEnum(RedisAuthType.clientUser);
		String token = SessionContextUtils.getNewToken();
		sessionContextUtils.saveContextCurrentUserNoExpire(token, userSession);
		LoginResponse response=new LoginResponse();
		response.setToken(token);
		return ReturnResult.SUCCESS(response);
	}
	 
	@ApiOperation(value = "退出登录", notes = "登出", response = ReturnResult.class, position = 5)
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@Security
	public @ResponseBody ReturnResult logout() {
		sessionContextUtils.removeContextCurrentUser();
		return ReturnResult.SUCCESS();
	}
	
	
	@ApiOperation(value = "修改头像", notes = "修改头像", response = ReturnResult.class, position = 5)
	@RequestMapping(value = "updateHeadImage", method = RequestMethod.POST)
	@Security
	public @ResponseBody ReturnResult updateHeadImage(@RequestBody UpdateHeadImageRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		memberService.updateMemberHeadImage(userId, request.getImageUrl());
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "修改昵称", notes = "修改昵称", response = ReturnResult.class, position = 5)
	@RequestMapping(value = "updateUserName", method = RequestMethod.POST)
	@Security
	public @ResponseBody ReturnResult updateUserName(@RequestBody UpdateUserNameRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		memberService.updateMemberUserName(userId, request.getUserName());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "修改密码", notes = "修改密码、忘记密码、设置密码", response = ReturnResult.class, position = 5)
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	public @ResponseBody ReturnResult updatePassword(@RequestBody UpdatePasswordRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String phone=request.getUserName();
		String password = request.getPassword();
		comsystemService.validatePhoneCode(phone, request.getCode(), PhoneCodeKey.COM_FORGET_PASSWORD);
		
		//忘记密码
		if(userSession==null){
			String userId = memberService.queryUserIdByPhone(phone);
			
			memberService.updateMemberPassword(userId, MD5Encrypt.md5(password));
			memberService.updateMemberDeviceToken(userId, request.getDeviceToken());
			//保存用户到缓存
			userSession=new UserSession();
			userSession.setUserId(userId);
			userSession.setRedisAuthTypeEnum(RedisAuthType.clientUser);
			String token = SessionContextUtils.getNewToken();
			sessionContextUtils.saveContextCurrentUserNoExpire(token, userSession);
			LoginResponse response=new LoginResponse();
			response.setToken(token);
			
			return ReturnResult.SUCCESS(response);
		}else{
			String userId = userSession.getUserId();
			String phoneUserId = memberService.queryUserIdByPhone(phone);
			if(!userId.equals(phoneUserId))
				return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "你输入的手机号码与当前帐号不符");
			memberService.updateMemberPassword(userId, MD5Encrypt.md5(password));
		}
		return ReturnResult.SUCCESS();
	}
	
	
	@ApiOperation(value = "添加地址", notes = "添加地址",response=ReturnResult.class,position = 1)
	@RequestMapping(value="addAddress",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult addAddress(@RequestBody AddAddressRequest request) throws Exception{
		AddressInfoDto addressInfoDto=new AddressInfoDto(request.getUserName(),
				request.getPhone(), request.getCommunityName(), request.getAddressDetail(),
				request.getCommunityId(), null,null);
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		memberService.addAddress(userId, addressInfoDto);
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "修改地址", notes = "修改地址",response=ReturnResult.class,position = 2)
	@RequestMapping(value="modifyAddress",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult modifyAddress(@RequestBody ModifyAddressRequest request) throws Exception{
		//判断地址是否有过下单
		boolean result = memberService.queryAddressHasOrdered(request.getAddressId());
		AddressInfoDto addressInfoDto=new AddressInfoDto(request.getUserName(),
				request.getPhone(), request.getCommunityName(), request.getAddressDetail(),
				request.getCommunityId(), request.getAddressId(),null);
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		
		if(result){
			memberService.deleteAddress(request.getAddressId());
			memberService.addAddress(userId, addressInfoDto);
		}else {
			memberService.updateAddress(addressInfoDto);
		}
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "删除地址", notes = "删除地址",response=ReturnResult.class,position = 3)
	@RequestMapping(value="deleteAddress",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deleteAddress(@RequestBody DeleteAddressRequest request) throws Exception{
		String addressId = request.getAddressId();
		memberService.deleteAddress(addressId);
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "获取用户地址列表", notes = "获取用户地址列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryAddress",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryAddress(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		List<AddressInfoDto> result = memberService.queryAddress(userId, request.getPage());
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "获取用户的社区信息", notes = "获取用户的社区信息",response=ReturnResult.class,position = 5)
	@RequestMapping(value="queryUserCommunity",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryUserCommunity() throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String communityId = memberService.queryUserCommunity(userId);
		CommunityDto community = comsystemService.queryCommunityById(communityId);
		List<EmployeeDto> employees = employeeService.queryEmployeeByCommunityId(communityId, RedisAuthType.employee.ordinal());
		String phone="4008323325";
		if(employees!=null){
			phone=employees.get(0).getPhone();
		}
		community.setPhone(phone);
		return ReturnResult.SUCCESS(community);
	}
	@ApiOperation(value = "修改用户的社区信息", notes = "修改用户的社区信息",response=ReturnResult.class,position = 6)
	@RequestMapping(value="modifyUserCommunity",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult modifyUserCommunity(@RequestBody ModifyUserCommunityRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String communityId = request.getCommunityId();
		memberService.updateUserCommunity(userId, request.getCommunityId());
		List<EmployeeDto> employees = employeeService.queryEmployeeByCommunityId(communityId, RedisAuthType.employee.ordinal());
		String phone="4008323325";
		if(employees!=null){
			phone=employees.get(0).getPhone();
		}
		//TODO 更新社区后将消息绑定到用户消息列表
		QueueSender queueSender=new QueueSender(QueueManager.UPDATE_COMMUNITY_BIND_MESSAGE_QUEUE, QueueManager.HOST);
		UpdateCommunityMessage message=new UpdateCommunityMessage(userId, communityId);
		queueSender.send(message);
		EmployeeMobileResponse response =new EmployeeMobileResponse();
		response.setPhone(phone);
		return ReturnResult.SUCCESS(response);
	}
	
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息",response=ReturnResult.class,position = 7)
	@RequestMapping(value="queryUserInfo",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryUserInfo() throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		UserInfoDto userInfo = memberService.queryUserInfo(userId);
		return ReturnResult.SUCCESS(userInfo);
	}
	@ApiOperation(value = "获取账户信息", notes = "获取账户信息",response=ReturnResult.class,position = 8)
	@RequestMapping(value="queryAccount",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryAccount() throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		//获取用户的账户
		AccountDto account = memberService.queryAccount(userId);
		IntegralRuleDto rule = comsystemService.queryIntegralRule(TradeType.payOut, IntegralSourceType.exchange);
		QueryAccountResponse response = new QueryAccountResponse();
		response.setRatio(rule.getRatio().toString());
		response.setBalance(account.getBalance().toString());
		response.setScore(account.getScore());
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "使用优惠码", notes = "使用优惠码",response=ReturnResult.class,position = 3)
	@RequestMapping(value="useCoupon",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult useCoupon(@ApiParam(required=true) @RequestBody UseCouponRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String recommendCode = request.getCouponCode();
		
		//boolean employeeExistCode = employeeService.queryRecommendCodeExist(recommendCode);
		int length = recommendCode.length();
		QueueSender queueSender = new QueueSender(QueueManager.ACCOUNT_INCOMEOUT_DETAIL_QUEUE, QueueManager.HOST);
		switch (length) {
		//判断是否是小哥优惠码
		case 5:
			//判断用户是否使用过小哥优惠码
			boolean isUsed = comsystemService.isUseRecommendCode(userId, "", 1);
			if(!isUsed)
				return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "帐号已使用乐E小哥优惠码");
			IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.income, IntegralSourceType.fillCode);
			int integral = integralRule.getRatio().intValue();
			IncomeOutFlowDto dto = new IncomeOutFlowDto(userId, 0, integral, BigDecimal.ZERO, BigDecimal.ZERO);
			memberService.updateMemberAccount(dto);
			comsystemService.saveUseRecommendRecord(userId, recommendCode, 1);
			//TODO
			
			IncomeOutDetailMessage incomeMessage = new IncomeOutDetailMessage();
			incomeMessage.setUserId(userId);
			incomeMessage.setMoney(String.valueOf(integral));
			incomeMessage.setCurrencyType(PayType.INTEGRAL);
			incomeMessage.setRemark("使用小哥推荐码");
			queueSender.send(incomeMessage);
			break;
		//优惠卷
		case 8:
			//查询该优惠码是否被使用
			CouponStatusDto coupon = comsystemService.queryCouponByPassword(recommendCode);
			//查看用户是否拥该次的卡券
			List<CardPackageDto> cardIds= memberService.queryCoupons(userId,coupon.getTime());
			if(cardIds!=null && !coupon.isReuse()){
				return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY,"您已使用过该类型优惠码");
			}
				memberService.addCoupon(userId, coupon.getId(),coupon.getTime(),coupon.getInvalidTime());
				comsystemService.updateCouponStatus(coupon.getId());
			break;
		case 7:
			CouponStatusDto rechargeCard = comsystemService.queryRechargeCardByPassword(recommendCode);
			boolean isUse=comsystemService.isUseRecommendCode(userId, recommendCode, rechargeCard.getTime());
			if(!isUse)
				return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "您已使用过当前活动的优惠码");
			BigDecimal price = rechargeCard.getPrice();
			CardType cardType = rechargeCard.getCardType();
			IncomeOutFlowDto incomeOutFlowDto=new  IncomeOutFlowDto();
			IncomeOutDetailMessage inComeMessage = new IncomeOutDetailMessage();
			incomeOutFlowDto.setUserId(userId);
			if(cardType.equals(CardType.coin)){
				incomeOutFlowDto.setGetIntegral(price.intValue());
				inComeMessage.setCurrencyType(PayType.INTEGRAL);
			}else if(cardType.equals(CardType.balance)){
				incomeOutFlowDto.setGetBalance(price);
				inComeMessage.setCurrencyType(PayType.BALANCE);
			}
			memberService.updateMemberAccount(incomeOutFlowDto);
			comsystemService.updateRechargeCardUseStatus(rechargeCard.getId());
			comsystemService.saveUseRecommendRecord(userId, recommendCode, rechargeCard.getTime());
			
			inComeMessage.setUserId(userId);
			inComeMessage.setMoney("+"+String.valueOf(price));
			inComeMessage.setRemark("使用充值卡");
			queueSender.send(inComeMessage);
			break;
		default:
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY,"优惠码无效");
		}
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "获取卡券列表", notes = "获取卡券列表",response=ReturnResult.class,position = 8)
	@RequestMapping(value="queryCoupons",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryCoupons(@RequestBody QueryCouponsRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		int orderType = request.getOrderType();
		String orderId = request.getOrderId();
		String cardId=null;
		BigDecimal price=BigDecimal.ZERO;
		List<CouponDto> response=null;
		String photoType=null;
		if(orderType!=-1){
			switch (orderType) {
				//洗衣订单
				case 0:
					ClotheOrderExctraDto clothesOrderDto = clothesService.queryClotheOrderExctra(orderId);
					cardId=clothesOrderDto.getCardId();
					price=clothesOrderDto.getPrice();
				break;
				case 1:
					PrintOrderDto printOrderDto = printService.queryPrintOrderPrice(orderId);
					price=printOrderDto.getPrice();
					photoType=printOrderDto.getPhotoType();
				break;
				case 2:
					GoodsOrderDto goodsOrder = mallService.queryGoodsOrderPrice(orderId);
					price=goodsOrder.getPrice();
					break;
					
			}
		}
		if(StringUtils.isNotEmpty(cardId)){
			CouponDto couponsDto = comsystemService.queryCoupon(cardId);
			response=new ArrayList<CouponDto>();
			response.add(couponsDto);
		}else{
			List<CardPackageDto> list = memberService.queryCoupons(userId,null);
			if(list!=null &&list.size()>0){
				response=comsystemService.queryCoupons(list,
					price, orderType,photoType, request.getPage());
			}
		}
		return ReturnResult.SUCCESS(response);
	}
	
	@ApiOperation(value = "获取账单", notes = "获取账单",response=ReturnResult.class,position = 8)
	@RequestMapping(value="queryBill",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryBill(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		Map<String, List<AccountFlowDto>> data = querySystemService.queryAccountFlow(userId, request.getPage());
		List<QueryBillResponse> list =null;
		QueryBillResponse response=null;
		if(data!=null){
			list=new ArrayList<QueryBillResponse>();
			Set<String> keySet = data.keySet();
			Iterator<String> iters = keySet.iterator();
			while(iters.hasNext()){
				String key=iters.next();
				response=new QueryBillResponse();
				response.setDate(key);
				response.setList(data.get(key));
				list.add(response);
			}
		}
		return ReturnResult.SUCCESS(list);
	}
	
}
 