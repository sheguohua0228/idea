package com.leyes.app.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.leyes.app.annotation.Security;
import com.leyes.app.dto.clothes.ClothesCategoryDto;
import com.leyes.app.dto.clothes.ClothesOrderDetailDto;
import com.leyes.app.dto.clothes.ClothesOrderDto;
import com.leyes.app.dto.clothes.ClothesPriceDto;
import com.leyes.app.dto.clothes.WashClothesDto;
import com.leyes.app.dto.clothes.WashClothesStatusDto;
import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.comsystem.PushMessageDto;
import com.leyes.app.dto.employee.EmployeeDto;
import com.leyes.app.dto.member.AddressDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.enums.DeviceType;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.enums.TradeType;
import com.leyes.app.message.IncomeOutDetailMessage;
import com.leyes.app.mq.QueueSender;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ClothesService;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.EmployeeService;
import com.leyes.app.service.MemberService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.util.UuidUtil;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.clothes.AppraiseRequest;
import com.leyes.app.web.request.clothes.CheckReserveTimeRequest;
import com.leyes.app.web.request.clothes.PlaceOrderRequest;
import com.leyes.app.web.request.clothes.PlaceReserveOrderRequest;
import com.leyes.app.web.request.clothes.QueryClothesPriceRequest;
import com.leyes.app.web.request.clothes.UploadVoiceMailRequest;
import com.leyes.app.web.request.employee.OrderIdRequest;
import com.leyes.app.web.request.print.QueryOrderRequest;
import com.leyes.app.web.response.clothes.ClothesOrderIdResponse;
import com.leyes.app.web.response.clothes.PlaceClothesOrderResponse;
import com.leyes.app.web.response.clothes.QueryClothesOrderDetailResponse;
import com.leyes.app.web.response.comsystem.AppraiseResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("clothes")
@Api(value = "clothes-api", description = "洗衣模块接口API", position = 1)
public class ClothesController  extends BaseController{
	@Autowired
	private ClothesService clothesService ;
	@Autowired
	private ComsystemService comsystemService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private MemberService memberService;
	
	@ApiOperation(value = "查询衣服分类", notes = "app端查看衣服的分类,请将communityId放在header中传到服务器",response=ReturnResult.class,position = 1)
	@RequestMapping(value="queryClothesCategory",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryClothesCategory()throws Exception{
		String communityId = sessionContextUtils.getCommunityId();
		
		List<ClothesCategoryDto> result = clothesService.queryClothesCategory(communityId);
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value="查询衣服价格列表",notes="按衣服分类查询衣服价格列表,请将communityId放在header中传到服务器",response=ReturnResult.class,position=2)
	@RequestMapping(value="queryClothesPrice",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryClothesPrice(@RequestBody QueryClothesPriceRequest request) throws Exception{
		String communityId = sessionContextUtils.getCommunityId();
		List<ClothesPriceDto> result = clothesService.queryClothesPrice(communityId, request.getCategoryId());
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value="下洗衣单",notes="下洗衣单",response=ReturnResult.class,position=3)
	@RequestMapping(value="placeOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ReturnResult placeOrder(@RequestBody PlaceOrderRequest request) throws Exception{
		 
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		//查看地址对应的小区
		AddressInfoDto addressInfo = memberService.queryAddressById(request.getAddressId());
		//创建订单
		String orderId = clothesService.placeOrder(userId, request.getAddressId(),addressInfo.getCommunityId());
		
		//推送给该小区的所有小哥
		// 发送给消息队列去推送
		pushMessageToEmployee(addressInfo, orderId);
		PlaceClothesOrderResponse response =new PlaceClothesOrderResponse();
		response.setMessage("在您居住小区内的驻场服务人员将于2分钟内与您联系，请稍等...");
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value="校验预约下单时间",notes="校验预约下单时间",response=ReturnResult.class,position=4)
	@RequestMapping(value="checkReserveTime",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult checkReserveTime(@RequestBody CheckReserveTimeRequest request) throws Exception{
		clothesService.checkReserveTime(request.getReserveTime());
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value="预约下单",notes="预约下单",response=ReturnResult.class,position=5)
	@RequestMapping(value="placeReserveOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ReturnResult placeReserveOrder(@RequestBody PlaceReserveOrderRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		//查看地址对应的小区
		AddressInfoDto addressInfo = memberService.queryAddressById(request.getAddressId());
		//保存订单
		String orderId = clothesService.placeReserveOrder(userId, request.getAddressId(),
				request.getReserveTime(),addressInfo.getCommunityId());
		
		pushMessageToEmployee(addressInfo, orderId);
		PlaceClothesOrderResponse response =new PlaceClothesOrderResponse();
		response.setMessage("预约成功，在您居住小区内的驻场人员将在您预约的时间准时到达。");
		return ReturnResult.SUCCESS(response);
	}

	
	@ApiOperation(value="查看洗衣订单列表",notes="查看洗衣订单列表",response=ReturnResult.class,position=6)
	@RequestMapping(value="queryClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryClothesOrder(@RequestBody QueryOrderRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		List<ClothesOrderDto> result = clothesService.queryClothesOrder(userId,request.getOrderType(), request.getPage());
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value="查看洗衣订单详情",notes="查看洗衣订单详情",response=ReturnResult.class,position=7)
	@RequestMapping(value="queryClothesOrderDetail",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryClothesOrderDetail(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String orderId = request.getOrderId();
		ClothesOrderDetailDto clothesOrderDetail = clothesService.queryClothesOrderDetail(userId, orderId);
		QueryClothesOrderDetailResponse response=new QueryClothesOrderDetailResponse();
		response.setOrderNumber(clothesOrderDetail.getOrderNumber());
		response.setOrderStatus(clothesOrderDetail.getOrderStatus());
		response.setVoiceMessage(clothesOrderDetail.getRemarkVoiceUrl());
		response.setQuantity(clothesOrderDetail.getClothesNumber());
		response.setTotalPrice(clothesOrderDetail.getPrice());
		response.setPayStatus(clothesOrderDetail.getPayStatus());
		response.setOrderId(orderId);
		response.setCardId(clothesOrderDetail.getCardId());
		AddressInfoDto address = memberService.queryAddressById(clothesOrderDetail.getClothesAddressId());
		response.setAddress(new AddressDto(address.getUserName(), address.getPhone(), address.getCommunityName(), address.getAddressDetail()));
		List<WashClothesDto> clothesDetails = clothesService.queryClothesOrderWash(orderId);
		response.setClothesDetails(clothesDetails);
		List<WashClothesStatusDto> status = clothesService.queryClothesOrderWashStatus(orderId);
		response.setWashStates(status);
		response.setBespeakTime(clothesOrderDetail.getBespeakTime());
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value="评价",notes="评价洗衣订单",response=ReturnResult.class,position=8)
	@RequestMapping(value="appraise",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult appraise(@RequestBody AppraiseRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		//评价
		String appraiseId=clothesService.appraise(userId,request.getOrderId(), request.getContent(), request.getServiceStar());
		IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.income, IntegralSourceType.appraise);
		int integral = integralRule.getRatio().intValue();
		//送积分
		IncomeOutFlowDto dto = new IncomeOutFlowDto(userId, 0, integral, BigDecimal.ZERO, BigDecimal.ZERO );
		memberService.updateMemberAccount(dto);
		
		QueueSender queueSender = new QueueSender(QueueManager.ACCOUNT_INCOMEOUT_DETAIL_QUEUE, QueueManager.HOST);
		 
		IncomeOutDetailMessage incomeMessage = new IncomeOutDetailMessage();
		incomeMessage.setUserId(userId);
		incomeMessage.setMoney(String.valueOf(integral));
		incomeMessage.setCurrencyType(PayType.INTEGRAL);
		incomeMessage.setRemark(OrderType.clothesOrder.getValue() +"-评价");
		queueSender.send(incomeMessage);
		AppraiseResponse response=new AppraiseResponse();
		response.setAppraiseId(appraiseId);
		return ReturnResult.SUCCESS(response);
	}
	/*@ApiOperation(value="查看洗衣订单评价",notes="查看洗衣订单评价",response=ReturnResult.class,position=8)
	@RequestMapping(value="queryAppraise",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryAppraise(@RequestBody QueryAppraiseRequest request) throws Exception{
		ClothesAppraiseDto response = clothesService.queryAppraise(request.getAppraiseId());
		return ReturnResult.SUCCESS(response);
	}*/
	@ApiOperation(value="删除洗衣订单",notes="删除洗衣订单",response=ReturnResult.class,position=9)
	@RequestMapping(value="deleteOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deleteOrder(@RequestBody OrderIdRequest request) throws Exception{
		clothesService.deleteOrder(request.getOrderId());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value="查看已完成未付款洗衣订单id",notes="查看已完成未付款洗衣订单id",response=ReturnResult.class,position=9)
	@RequestMapping(value="queryUnpaidClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryUnpaidClothesOrder() throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String orderId = clothesService.queryUnpaidClothesOrder(userId);
		if(StringUtils.isBlank(orderId)){
			return ReturnResult.SUCCESS(null);
		}else{
			ClothesOrderIdResponse response=new ClothesOrderIdResponse();
			response.setOrderId(orderId);
			return ReturnResult.SUCCESS(response);
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
			String jsonStr = "你好, "+addressInfo.getCommunityName()+"有新的洗衣订单，请尽快处理！";
			pushMessage.setTitle("洗衣订单");
			pushMessage.setText(jsonStr);
			pushMessage.setType(DeviceType.ASSISTANT);
			pushMessage.putExtras("notifyType", String.valueOf(OrderType.clothesOrder.ordinal()));
			pushMessage.putExtras("orderId", orderId);
			comsystemService.push(pushMessage);
			/*QueueSender queueSender=new QueueSender(QueueManager.PUSH_MESSAGE_QUEUE, QueueManager.HOST);
			queueSender.send(pushMessage);*/
		}
	}
	@ApiOperation(value="上传语音留言",notes="上传语音留言",response=ReturnResult.class,position=9)
	@RequestMapping(value="uploadVoiceMail",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult uploadVoiceMail(@RequestBody UploadVoiceMailRequest request) throws Exception{
		clothesService.updateClothesOrderVoice(request.getOrderId(),request.getVoiceUrl());
		return ReturnResult.SUCCESS();
	}
	
}
