package com.leyes.app.web.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.annotation.Security;
import com.leyes.app.dto.clothes.ClothesOrderPriceDto;
import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.comsystem.BannerDto;
import com.leyes.app.dto.comsystem.CommunitySearchDto;
import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.comsystem.PayResultDto;
import com.leyes.app.dto.comsystem.PushMessageDto;
import com.leyes.app.dto.comsystem.RechargeOrderDto;
import com.leyes.app.dto.comsystem.TemplateDto;
import com.leyes.app.dto.comsystem.TopicDto;
import com.leyes.app.dto.comsystem.VersionInfoDto;
import com.leyes.app.dto.employee.EmployeeDto;
import com.leyes.app.dto.member.AccountDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.dto.print.PrintOrderDto;
import com.leyes.app.dto.shop.GoodsOrderDto;
import com.leyes.app.enums.CardType;
import com.leyes.app.enums.DeviceType;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.enums.SMSTemplateEnum;
import com.leyes.app.enums.TradeType;
import com.leyes.app.exceptions.ComsystemException;
import com.leyes.app.exceptions.DisplayException;
import com.leyes.app.message.IncomeOutDetailMessage;
import com.leyes.app.message.StatisticsOrderMessage;
import com.leyes.app.mq.QueueSender;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ClothesService;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.EmployeeService;
import com.leyes.app.service.MallService;
import com.leyes.app.service.MemberService;
import com.leyes.app.service.PrintService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.util.UuidUtil;
import com.leyes.app.util.XMLUtil;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.comsystem.KeyWordRequest;
import com.leyes.app.web.request.comsystem.PayOrderRequest;
import com.leyes.app.web.request.comsystem.RechargeRequest;
import com.leyes.app.web.response.comsystem.PayOrderResponse;
import com.leyes.app.web.response.comsystem.QueryPropertyInfoResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("comSystem")
@Api(value="comSystem-api",description="系统通用接口api",position=1)
public class ComSystemController extends BaseController{

	private ComsystemService comsystemService=SpringContextUtils.getBean("comsystemService");
	private ClothesService clothesService = null;
	private MemberService memberService=SpringContextUtils.getBean("memberService");
	private PrintService printService=null;
	private MallService mallService=SpringContextUtils.getBean("mallService");
	private EmployeeService employeeService=null;
	
	@ApiOperation(value = "检查更新", notes = "检查更新",response=ReturnResult.class, position = 1)
	@RequestMapping(value = "checkUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult checkUpdate()throws Exception {
		String client = sessionContextUtils.getClient();
		int versionCode=sessionContextUtils.getVersionCode();
		VersionInfoDto response = comsystemService.checkUpdate(client, versionCode,0);
		return ReturnResult.SUCCESS(response);
	}

	@ApiOperation(value = "获取主页配置模板", notes = "获取主页配置模板",response=ReturnResult.class, position = 2)
	@RequestMapping(value = "queryTemplate", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryTemplate() throws Exception {
		String communityId = sessionContextUtils.getCommunityId();
		List<TemplateDto> result = comsystemService.queryTemplate(communityId);
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "获取精选专题", notes = "获取精选专题",response=ReturnResult.class, position = 3)
	@RequestMapping(value = "queryTopics", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryTopics() throws Exception {
		String communityId = sessionContextUtils.getCommunityId();
		List<TopicDto> result = comsystemService.queryTopics(communityId);
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "获取物业信息", notes = "初期获取物业的电话号码",response=ReturnResult.class, position = 3)
	@RequestMapping(value = "queryPropertyInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryPropertyInfo() throws Exception {
		String communityId = sessionContextUtils.getCommunityId();
		String result = comsystemService.queryPropertyInfo(communityId);
		QueryPropertyInfoResponse response =new QueryPropertyInfoResponse();
		if(StringUtils.isBlank(result)){
			result="4008323325";
		}
		response.setPhone(result);
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "查询首页banner", notes = "查询首页banner",response=ReturnResult.class, position = 4)
	@RequestMapping(value = "queryBanner", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryBanner() throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId=null;
		if(userSession!=null)  userId = userSession.getUserId();
		List<BannerDto> response = comsystemService.queryBanner();
		
		// TODO 记录用户的打开app行为 
		QueueSender queueSender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
		StatisticsOrderMessage statisticsOrderMessage = new StatisticsOrderMessage();
		statisticsOrderMessage.setOrderType(4);
		statisticsOrderMessage.setUserId(userId);
		queueSender.send(statisticsOrderMessage);
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "模糊查询小区", notes = "模糊查询小区",response=ReturnResult.class, position = 4)
	@RequestMapping(value = "searchCommunity", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult searchCommunity(@RequestBody KeyWordRequest request) throws Exception {
		String keyWord = request.getKeyWord();
		List<CommunitySearchDto> result = comsystemService.searchCommunity(keyWord);
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "微信支付回调", notes = "使用优惠码",response=ReturnResult.class,position = 3)
	@RequestMapping(value="wechatNotify",method=RequestMethod.POST)
	@ResponseBody
	public String wechatNotify(HttpServletRequest request) {
		String result_code = "FAIL";
		String result_msg="pay error";
		try {
			InputStream inputStream = request.getInputStream();
			Map<String, String> params = XMLUtil.parseXml(inputStream);
			String return_code = params.get("return_code");
			String total_fee = params.get("total_fee");
			String out_trade_no = params.get("out_trade_no");
			String attach = params.get("attach");
		
			if(return_code.equals("SUCCESS")){
				int orderType = Integer.parseInt(attach);
				if(attach.equals("-1") ){
					OrderPayStatusDto order=printService.queryOrderStatusByOrderNumber(out_trade_no);
					if(!order.getPayStatus().equals(PayStatus.PAID)){
						order.setPayStatus(PayStatus.PAID);
						order.setPayType(PayType.WECHAT);
						printService.updatePrintOrderPayStatus(order);
					}
				}else{
					processOrderNotify(total_fee, out_trade_no, result_msg, result_code,orderType);
				}
			}
		} catch (Exception e) {
			result_code="FAIL";
		}
		String responseMsg = "<xml><return_code><![CDATA[" + result_code
				+ "]]></return_code><return_msg><![CDATA[" + result_msg
				+ "]]></return_msg></xml>";
		return responseMsg;
	}
	 
	
	@ApiOperation(value = "支付宝支付回调", notes = "支付宝支付回调",response=ReturnResult.class,position = 3)
	@RequestMapping(value="alipayNotify",method=RequestMethod.POST)
	@ResponseBody
	public String alipayNotify(HttpServletRequest request){
		String result_code="error";
		
		try {
			String tradeStatus =request.getParameter("trade_status");
			String out_trade_no =request.getParameter("out_trade_no");
			String subject = request.getParameter("subject");
			String total_fee = request.getParameter("total_fee");
			//验证是否是来自支付宝的回调
		//	boolean verifyResult = comsystemService.aliPayNotifyVerify(notifyId);
			//if(verifyResult){
				//判断支付状态
				if(tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED")){
					int orderType = Integer.parseInt(subject.split("_")[1]);
					processOrderNotify(total_fee, out_trade_no, null, result_code,orderType);
				}
		//	}
		} catch (Exception e) {
			result_code= "error";
		}
		
		return result_code;
	}
	 
	@ApiOperation(value = "支付订单", notes = "支付订单",response=ReturnResult.class,position = 3)
	@RequestMapping(value="payOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult payOrder(@RequestBody PayOrderRequest request) throws Exception{
		PayOrderResponse response=new PayOrderResponse();
		
		String orderId = request.getOrderId();
		int orderType = request.getOrderType();
		String couponId = request.getCouponId();
		int payType = request.getPayType();
		
		CouponDto coupon=null;
		int integralNumber=0;
		BigDecimal ratio =BigDecimal.ONE;
		if(StringUtils.isNotEmpty(couponId) && !couponId.equals("-1")){
			coupon = comsystemService.queryCoupon(couponId);
		}
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		
		AccountDto account = memberService.queryAccount(userId);
		boolean useBalance = request.isUseBalance();
		BigDecimal balanceNumber=useBalance? account.getBalance():BigDecimal.ZERO;
		boolean useIntegral = request.isUseIntegral();
		
		if(useIntegral){
			IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.payOut, IntegralSourceType.exchange);
			ratio = integralRule.getRatio();
			integralNumber=account.getScore();
		}
		
		switch (orderType) {
			//洗衣订单
			case 0:
				ClothesOrderPriceDto clothesOrder = clothesService.queryClothesOrderPrice(orderId);
				clothesOrder.setCoupon(coupon);
				PayResultDto clothesPayResult = comsystemService.payOrder(clothesOrder, balanceNumber,
						integralNumber,ratio, payType);
				clothesPayResult.setOrderNumber(clothesOrder.getOrderNumber());
				clothesPayResult.setOrderType(OrderType.clothesOrder);
				OrderPayStatusDto clothesOrderPayStatus = new OrderPayStatusDto(clothesPayResult.getFinalPrice(),
						clothesPayResult.getIntegralNum(), clothesPayResult.getBalance(), 
						coupon, clothesPayResult.getPayType());
				clothesOrderPayStatus.setId(orderId);
				processOrderPay(userId, clothesPayResult,clothesOrder.getOrderNumber(), clothesOrderPayStatus, response);
				
				//数据统计 TODO
				QueueSender clothesSender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
				StatisticsOrderMessage statisticsOrderMessage = new StatisticsOrderMessage();
				statisticsOrderMessage.setOrderType(5);
				statisticsOrderMessage.setOrderId(orderId);
				clothesSender.send(statisticsOrderMessage);
				
				break;
			//照片订单
			case 1:
				PrintOrderDto printOrder = printService.queryPrintOrderPrice(orderId);
				String photoType = printOrder.getPhotoType();
				if(coupon!=null && StringUtils.isNotEmpty(photoType)){
					if(photoType.equals("PASSPORT")){
						int passportNumber = coupon.getPassportNum();
						if(passportNumber<1){
							throw new ComsystemException("无法使用拼团卡",new DisplayException());
						}else{
							coupon.setPassportNum(passportNumber-1);
						}
					}else if(photoType.equals("VISA")){
						int visaPhotoNumber = coupon.getVisaPhotoNum();
						if(visaPhotoNumber<1){
							throw new ComsystemException("无法使用拼团卡",new DisplayException());
						}else{
							coupon.setVisaPhotoNum(visaPhotoNumber-1);
						}
					}
				}
				printOrder.setCoupon(coupon);
				
				PayResultDto printPayResult = comsystemService.payOrder(printOrder, balanceNumber,
						integralNumber,ratio, payType);
				printPayResult.setOrderType(OrderType.printOrder);
				printPayResult.setOrderNumber(printOrder.getOrderNumber());
				OrderPayStatusDto printOrderPayStatus = new OrderPayStatusDto(printPayResult.getFinalPrice(),
						printPayResult.getIntegralNum(), printPayResult.getBalance(),
						coupon, printPayResult.getPayType());
				printOrderPayStatus.setId(orderId);
				printOrderPayStatus.setOrderType(photoType);
				processOrderPay(userId, printPayResult,printOrder.getOrderNumber(), printOrderPayStatus, response);
				//数据统计 TODO
				QueueSender printSender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
				StatisticsOrderMessage statisticsPrintOrderMessage = new StatisticsOrderMessage();
				statisticsPrintOrderMessage.setOrderType(OrderType.printOrder.ordinal());
				statisticsPrintOrderMessage.setUserId(userId);
				statisticsPrintOrderMessage.setPrice(printPayResult.getFinalPrice());
				printSender.send(statisticsPrintOrderMessage);
				break;
			//值得买订单
			case 2:
				GoodsOrderDto goodsOrder = mallService.queryGoodsOrderPrice(orderId);
				goodsOrder.setCoupon(coupon);
				PayResultDto goodsPayResult = comsystemService.payOrder(goodsOrder, balanceNumber,
						integralNumber,ratio, payType);
				goodsPayResult.setOrderNumber(goodsOrder.getOrderNumber());
				goodsPayResult.setOrderType(OrderType.goodsOrder);
				OrderPayStatusDto goodsOrderPayStatus = new OrderPayStatusDto(goodsPayResult.getFinalPrice(),
						goodsPayResult.getIntegralNum(), goodsPayResult.getBalance(),
						coupon, goodsPayResult.getPayType());
				goodsOrderPayStatus.setId(orderId);
				processOrderPay(userId, goodsPayResult,goodsOrder.getOrderNumber(), goodsOrderPayStatus, response);
				
				//数据统计 TODO
				QueueSender shopSender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
				StatisticsOrderMessage statisticsShopOrderMessage = new StatisticsOrderMessage();
				statisticsShopOrderMessage.setOrderType(OrderType.goodsOrder.ordinal());
				statisticsShopOrderMessage.setOrderId(orderId);
				shopSender.send(statisticsShopOrderMessage);
				
				break;
			 
			default:
				return ReturnResult.FAILUER("订单类型错误");
		}
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "充值", notes = "充值",response=ReturnResult.class,position = 3)
	@RequestMapping(value="recharge",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult recharge(@RequestBody RechargeRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		int payType=request.getPayType();
		PayOrderResponse response=new PayOrderResponse();
		//创建一个充值订单
		RechargeOrderDto rechargeOrder = comsystemService.createRechargeOrder(userId, request.getPrice(), payType);
		//创建支付参数
		PayResultDto payResult = comsystemService.payOrder(rechargeOrder, BigDecimal.ZERO, 0,BigDecimal.ZERO, payType);
		response.setPayParams(payResult.getResult());
		return ReturnResult.SUCCESS(response);
	}
	
	private void processOrderNotify(String total_fee,String out_trade_no,
			String result_msg,String result_code,int orderType) throws Exception{
		//判断订单是否完成
		OrderPayStatusDto order =null;
		switch (orderType) {
			case 0:
				order=clothesService.queryOrderStatusByOrderNumber(out_trade_no);
				break;
			case 1:
				order=printService.queryOrderStatusByOrderNumber(out_trade_no);
				break;
			case 2:
				order=mallService.queryOrderStatusByOrderNumber(out_trade_no);
				break;
			case 4:
				order=comsystemService.queryOrderStatusByOrderNumber(out_trade_no);
				break;
		}
		if(!order.getPayStatus().equals(PayStatus.PAID)){
			//修改订单完成
			CouponDto card = order.getCard();
			OrderPayStatusDto orderPayStatus = new OrderPayStatusDto(order.getFinalPrice(),
					order.getIntegralNum(), order.getBalance(), card,
					order.getPayType());
			orderPayStatus.setId(order.getId());
			orderPayStatus.setPayStatus(PayStatus.PAID);
			
			switch (orderType) {
				case 0:
					clothesService.updateClothesOrderPayStatus(orderPayStatus);
					break;
				case 1:
					printService.updatePrintOrderPayStatus(orderPayStatus);
					String orderTypeCode = orderPayStatus.getOrderType();
					if(StringUtils.isNotEmpty(orderTypeCode) && orderTypeCode.equals("PASSPORT")){
						//赵欣彤			//胡静望			//田		//王宇浩
						String[] mobileArray={"18728458127","18108273626","13688450441","18615717856"};
						List<String[]> contents=new ArrayList<String[]>();
						String[] content={out_trade_no};
						contents.add(content);
						comsystemService.sendSMS(mobileArray, SMSTemplateEnum.t_notifyPassportOrder, contents);
					}
					break;
				case 2:
					mallService.updateGoodsOrderPayStatus(orderPayStatus);
					if(orderPayStatus.getPayStatus().equals(PayStatus.PAID)){
						//推送到后台和小哥
						try {
							String urlPost="http://api.leyes.me:8090/clothes-management/admin/newOrder";
							 URL url = new URL(urlPost);
					         URLConnection urlcon = url.openConnection();
					          urlcon.getInputStream();
						} catch (Exception e) {
						}
						String addressId = mallService.queryAddressIdByOrderNumber(out_trade_no);
						//查看地址对应的小区
						AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
						pushMessageToEmployee(addressInfo, orderPayStatus.getId());
					}
					break;
				case 4:
					comsystemService.updateRechargeOrderPayStatus(orderPayStatus);
					break;
			}
			
			PayResultDto payResult=new PayResultDto();
			payResult.setBalance(order.getBalance());
			payResult.setIntegralNum(order.getIntegralNum());
			payResult.setFinalPrice(order.getFinalPrice());
			payResult.setPayType(order.getPayType());
			payResult.setOrderType(OrderType.translate(orderType));
			payResult.setOrderNumber(out_trade_no); 
			
			updateMemberInfo(order.getUserId(), payResult);
			result_code="SUCCESS";
			result_msg="pay success";
		}
	}
	 
	 
	private void processOrderPay(String userId,PayResultDto payResult,String orderNumber,
			OrderPayStatusDto orderPayStatus,PayOrderResponse response) throws Exception{
		if(payResult.getPayStatus().equals(PayStatus.PAID)){
			//设置订单已支付
			orderPayStatus.setPayStatus(PayStatus.PAID);
			updateMemberInfo(userId, payResult);
			response.setPayStatus(PayStatus.PAID.ordinal());
			//判断是否使用卡券，如使用则将卡券改为已使用
			CouponDto card = orderPayStatus.getCard();
			if(card!=null){
				int cardType = card.getCardType();
				comsystemService.updateCoupon(card);
				if(cardType!=CardType.group.ordinal()){
					//修改为使用状态 TODO
					memberService.useCoupon(userId, card.getCardId());
				}
			}
			
		}else{
			//设置订单部分支付
			orderPayStatus.setPayStatus(PayStatus.PARTPAY);
			response.setPayParams(payResult.getResult());
		}
		switch (payResult.getOrderType()) {
			case clothesOrder:
				clothesService.updateClothesOrderPayStatus(orderPayStatus);
				break;
			case printOrder:
				printService.updatePrintOrderPayStatus(orderPayStatus);
				String orderTypeCode = orderPayStatus.getOrderType();
				if(StringUtils.isNotEmpty(orderTypeCode) && orderTypeCode.equals("PASSPORT")){
					//赵欣彤			//胡静望			//田		//王宇浩
					String[] mobileArray={"18728458127","18108273626","13688450441","18615717856"};
					List<String[]> contents=new ArrayList<String[]>();
					String[] content={orderPayStatus.getOrderNumber()};
					contents.add(content);
					comsystemService.sendSMS(mobileArray, SMSTemplateEnum.t_notifyPassportOrder, contents);
				}
				break;
			case goodsOrder:
				mallService.updateGoodsOrderPayStatus(orderPayStatus);
				if(orderPayStatus.getPayStatus().equals(PayStatus.PAID)){
					//推送到后台和小哥
					try {
						String urlPost="http://api.leyes.me:8090/clothes-management/admin/newOrder";
						 URL url = new URL(urlPost);
				         URLConnection urlcon = url.openConnection();
				          urlcon.getInputStream();
					} catch (Exception e) {
					}
					
					
					String addressId = mallService.queryAddressIdByOrderNumber(orderNumber);
					//查看地址对应的小区
					AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
					pushMessageToEmployee(addressInfo, orderPayStatus.getId());
				}
				break;
		}
		
	}
	private void updateMemberInfo(String userId,PayResultDto payResult) throws Exception{
		IncomeOutFlowDto dto =null;
		//修改用户的账户
		IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.income, IntegralSourceType.custom);
		BigDecimal cusumeBalance = payResult.getBalance();
		int comsumeIntegral = payResult.getIntegralNum();
		int getIntegral = payResult.getFinalPrice().add(cusumeBalance).divide(integralRule.getRatio()).setScale(0, BigDecimal.ROUND_HALF_DOWN).intValue();
		if(payResult.getOrderType().equals(OrderType.rechargeOrder)){
			 dto = new IncomeOutFlowDto(userId, 0, 0,BigDecimal.ZERO,payResult.getFinalPrice());
		}else{
			dto = new IncomeOutFlowDto(userId, comsumeIntegral, getIntegral, cusumeBalance, BigDecimal.ZERO);
		}
		memberService.updateMemberAccount(dto);
		// 记录交易日志
		sendIncomeOutMessage(userId, payResult, integralRule);
		 
		memberService.updateMemberExp(userId, getIntegral);
		
	}
	private void sendIncomeOutMessage(String userId,PayResultDto payResult,IntegralRuleDto integralRule){
		if(payResult.getPayStatus().equals(PayStatus.PAID)){
			BigDecimal cusumeBalance = payResult.getBalance();
			int comsumeIntegral = payResult.getIntegralNum();
			 
			QueueSender queueSender = new QueueSender(QueueManager.ACCOUNT_INCOMEOUT_DETAIL_QUEUE, QueueManager.HOST);
			int getIntegral = payResult.getFinalPrice().add(cusumeBalance).divide(integralRule.getRatio()).setScale(0, BigDecimal.ROUND_HALF_DOWN).intValue();
			//判断是否是充值订单，充值不送积分
			if(payResult.getOrderType().equals(OrderType.rechargeOrder)){
				IncomeOutDetailMessage incomeBalanceMessage = new IncomeOutDetailMessage();
				incomeBalanceMessage.setUserId(userId);
				incomeBalanceMessage.setMoney("+"+payResult.getFinalPrice());
				incomeBalanceMessage.setCurrencyType(payResult.getPayType());
				incomeBalanceMessage.setRemark(payResult.getOrderType().getValue()+"-"+payResult.getOrderNumber());
				queueSender.send(incomeBalanceMessage);
				getIntegral=0;
			}else{
				if(getIntegral!=0){
					IncomeOutDetailMessage incomeIntegralMessage = new IncomeOutDetailMessage();
					incomeIntegralMessage.setUserId(userId);
					incomeIntegralMessage.setMoney("+"+String.valueOf(getIntegral));
					incomeIntegralMessage.setCurrencyType(PayType.INTEGRAL);
					incomeIntegralMessage.setRemark(payResult.getOrderType().getValue());
					queueSender.send(incomeIntegralMessage);
				}
				if(comsumeIntegral!=0){
					IncomeOutDetailMessage outIntegralMessage = new IncomeOutDetailMessage();
					outIntegralMessage.setUserId(userId);
					outIntegralMessage.setMoney(String.valueOf(-comsumeIntegral));
					outIntegralMessage.setCurrencyType(PayType.INTEGRAL);
					outIntegralMessage.setRemark(payResult.getOrderType().getValue()+"-"+payResult.getOrderNumber());
					queueSender.send(outIntegralMessage);
				}
				if(cusumeBalance!=null && cusumeBalance.compareTo(BigDecimal.ZERO)!=0){
					IncomeOutDetailMessage outBalanceMessage = new IncomeOutDetailMessage();
					outBalanceMessage.setUserId(userId);
					outBalanceMessage.setMoney("-"+String.valueOf(cusumeBalance));
					outBalanceMessage.setCurrencyType(PayType.BALANCE);
					outBalanceMessage.setRemark(payResult.getOrderType().getValue()+"-"+payResult.getOrderNumber());
					queueSender.send(outBalanceMessage);
				}
				if(payResult.getFinalPrice().compareTo(BigDecimal.ZERO)!=0){
					IncomeOutDetailMessage outMoneyMessage = new IncomeOutDetailMessage();
					outMoneyMessage.setUserId(userId);
					outMoneyMessage.setMoney("-"+payResult.getFinalPrice().toString());
					outMoneyMessage.setCurrencyType(payResult.getPayType());
					outMoneyMessage.setRemark(payResult.getOrderType().getValue()+"-"+payResult.getOrderNumber());
					queueSender.send(outMoneyMessage);
				}
			}
			
		}
	}
			
	private void pushMessageToEmployee(AddressInfoDto addressInfo,
			String orderId) throws Exception {
		String communityId = addressInfo.getCommunityId();
		communityId=comsystemService.queryParentCommunityId(communityId);
		//推送给改小区的所有小哥
		List<EmployeeDto> employees = employeeService.queryEmployeeByCommunityId(communityId,RedisAuthType.employee.ordinal());
		if(employees!=null){
			PushMessageDto pushMessage = new PushMessageDto();
			for(EmployeeDto employee:employees){
				pushMessage.addDeviceToken(employee.getDeviceToken());
			}
			pushMessage.setMessageId(UuidUtil.getUUIDString());
			String jsonStr = "你好, "+addressInfo.getCommunityName()+"有新的值得买订单，请尽快处理！";
			pushMessage.setTitle("值得买订单");
			pushMessage.setText(jsonStr);
			pushMessage.setType(DeviceType.ASSISTANT);
			pushMessage.putExtras("notifyType", String.valueOf(OrderType.goodsOrder.ordinal()));
			pushMessage.putExtras("orderId", orderId);
			comsystemService.push(pushMessage);
			/*QueueSender queueSender=new QueueSender(QueueManager.PUSH_MESSAGE_QUEUE, QueueManager.HOST);
			queueSender.send(pushMessage);*/
		}
	}

}
