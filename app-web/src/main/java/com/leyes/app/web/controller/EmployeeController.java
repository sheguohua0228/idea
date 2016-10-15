package com.leyes.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.leyes.app.annotation.Security;
import com.leyes.app.dto.clothes.ClothesOrderUserIdDto;
import com.leyes.app.dto.clothes.ClothesPriceOfCategoryDto;
import com.leyes.app.dto.clothes.DeliveryClothesOrderDto;
import com.leyes.app.dto.clothes.FinishClothesOrderDto;
import com.leyes.app.dto.clothes.OrderPayStatusDto;
import com.leyes.app.dto.clothes.TakeClothesOrderDto;
import com.leyes.app.dto.comsystem.CommunityDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.comsystem.PushMessageDto;
import com.leyes.app.dto.comsystem.VersionInfoDto;
import com.leyes.app.dto.employee.ClothesDto;
import com.leyes.app.dto.employee.ClothesOrderTimeDto;
import com.leyes.app.dto.employee.ClothesOrdersDto;
import com.leyes.app.dto.employee.DeliveryGoodsOrdersDto;
import com.leyes.app.dto.employee.EmployeeDto;
import com.leyes.app.dto.employee.FinishGoodsOrderDto;
import com.leyes.app.dto.employee.GoodsOrdersDto;
import com.leyes.app.dto.employee.TakeClothesDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.dto.member.CouponDto;
import com.leyes.app.dto.member.MemberDto;
import com.leyes.app.dto.member.MemberInfoDto;
import com.leyes.app.dto.member.MemberRankDto;
import com.leyes.app.dto.message.MessageContentDto;
import com.leyes.app.dto.message.MessageInfoDto;
import com.leyes.app.dto.message.MessageReceiveInfoDto;
import com.leyes.app.enums.ClothesOrderStatus;
import com.leyes.app.enums.DeviceType;
import com.leyes.app.enums.FileType;
import com.leyes.app.enums.GoodsOrderSatatus;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.MessageType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayStatus;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.enums.TradeType;
import com.leyes.app.message.StatisticsOrderMessage;
import com.leyes.app.mq.QueueSender;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ClothesService;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.EmployeeService;
import com.leyes.app.service.MallService;
import com.leyes.app.service.MemberService;
import com.leyes.app.service.MessageService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.util.UuidUtil;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.comsystem.PageRequest;
import com.leyes.app.web.request.employee.ConfirmTakeClothesRequest;
import com.leyes.app.web.request.employee.ConfirmTimeRequest;
import com.leyes.app.web.request.employee.EmployeeFinishTakeClothesRequest;
import com.leyes.app.web.request.employee.OrderIdRequest;
import com.leyes.app.web.request.employee.QueryClothesPriceListRequest;
import com.leyes.app.web.request.employee.QueryUserInfoRequest;
import com.leyes.app.web.request.employee.SingleClothesRequest;
import com.leyes.app.web.request.user.LoginRequest;
import com.leyes.app.web.response.employee.PropertyQuerySendClothesOrder;
import com.leyes.app.web.response.employee.PropertyQueryTakeClothesOrderResponse;
import com.leyes.app.web.response.employee.QueryBespeakOrderResponse;
import com.leyes.app.web.response.employee.QueryChargeCommunityResponse;
import com.leyes.app.web.response.employee.QueryDeliveryGoodsOrderResponse;
import com.leyes.app.web.response.employee.QueryFinishClothesOrderResponse;
import com.leyes.app.web.response.employee.QueryFinishGoodsOrderResponse;
import com.leyes.app.web.response.employee.QuerySendClothesOrderResponse;
import com.leyes.app.web.response.employee.QueryTakeClothesOrderResponse;
import com.leyes.app.web.response.employee.QueryWashingClothesOrderResponse;
import com.leyes.app.web.response.employee.QueryWorthBuyOrderResponse;
import com.leyes.app.web.response.user.LoginResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.leyes.app.web.utils.SessionContextUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("employee")
@Api(value="employee-api",description="小哥端接口api",position=1)
public class EmployeeController extends BaseController{

	private ClothesService clothesService = SpringContextUtils.getBean("clothesService");
	
	private EmployeeService employeeService=SpringContextUtils.getBean("employeeService");
	
	private MemberService memberService=SpringContextUtils.getBean("memberService");
	
	private ComsystemService comsystemService=SpringContextUtils.getBean("comsystemService");
	
	private MessageService messageService = SpringContextUtils.getBean("messageService");
	
	private MallService mallService=SpringContextUtils.getBean("mallService");
	
	
	@ApiOperation(value = "登录", notes = "密码登录",response=ReturnResult.class,position = 1)
	@RequestMapping(value="login/normal",method=RequestMethod.POST)
	@ResponseBody
	public ReturnResult loginNormal(@RequestBody LoginRequest request) throws Exception{
		EmployeeDto dto = employeeService.login(request.getUserName(), request.getPassword(),request.getDeviceToken());
		//保存用户到缓存
		UserSession userSession=new UserSession();
		userSession.setEmployeeId(dto.getEmployeeId());
		userSession.setRedisAuthTypeEnum(RedisAuthType.employee);
		String token = SessionContextUtils.getNewToken();
		sessionContextUtils.saveContextCurrentUserNoExpire(token, userSession);
		LoginResponse response=new LoginResponse();
		response.setToken(token);
		response.setRole(dto.getGroup());
		response.setHeadImage(dto.getHeadImageUrl());
		response.setPhone(dto.getPhone());
		response.setRealName(dto.getRealName());
		response.setRecommendCode(dto.getRecommendCode());
		return ReturnResult.SUCCESS(response);
	}
	
	@ApiOperation(value = "退出登录", notes = "登出", response = ReturnResult.class, position = 5)
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@Security
	public @ResponseBody ReturnResult logout() {
		sessionContextUtils.removeContextCurrentUser();
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "检查更新", notes = "检查更新",response=ReturnResult.class, position = 1)
	@RequestMapping(value = "checkUpdate", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult checkUpdate()throws Exception {
		String client = sessionContextUtils.getClient();
		int versionCode=sessionContextUtils.getVersionCode();
		VersionInfoDto response = comsystemService.checkUpdate(client, versionCode,1);
		return ReturnResult.SUCCESS(response);
	}
	
	
	@ApiOperation(value = "小哥查看自己负责的楼盘", notes = "小哥查看自己负责的楼盘",response=ReturnResult.class,position = 3)
	@RequestMapping(value="queryChargeCommunity",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryChargeCommunity() throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<String> communityIds = employeeService.queryCommunityOfEmployee(employeeId);
		
		List<QueryChargeCommunityResponse> response=null;
		if(communityIds!=null && communityIds.size()>0){
			response=new ArrayList<QueryChargeCommunityResponse>();
			List<CommunityDto> communityList = comsystemService.queryCommunityByIds(communityIds);
			for(CommunityDto dto:communityList){
				response.add(new QueryChargeCommunityResponse(dto.getCommunityId(), dto.getName()));
			}
		}
		return ReturnResult.SUCCESS(response);
	}
	
	@ApiOperation(value = "小哥领取预约订单", notes = "小哥领取预约订单",response=ReturnResult.class,position = 3)
	@RequestMapping(value="receiveBespeakOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult receiveBespeakOrder(@RequestBody OrderIdRequest request) throws Exception{
		//领取成功或显示该订单已被某某领取
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		ClothesOrderStatus orderStatus = ClothesOrderStatus.PLACE_ORDER;
		String orderId = request.getOrderId();
		String operator = clothesService.queryWashStatusOperator(orderId, orderStatus);
		if(StringUtils.isBlank(operator)){
			clothesService.updateWashOperator(request.getOrderId(), employeeId,orderStatus);
			return ReturnResult.SUCCESS();
		}else{
			EmployeeDto dto = employeeService.queryEmployeeById(employeeId);
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY,"该洗衣订单已被[ "+dto.getRealName()+" ]领取");
		}
	}
	 
	@ApiOperation(value = "确认预约收衣时间", notes = "确认预约收衣时间",response=ReturnResult.class,position = 6)
	@RequestMapping(value="confirmBespeakTime",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult confirmBespeakTime(@RequestBody ConfirmTimeRequest request) throws Exception{
		//如用户未选择预约时间则打电话预约收衣时间
		String orderId = request.getOrderId();
		clothesService.updateClothesOrderBespeakTime(orderId,
				request.getTime(),request.getRemark());
		String address = request.getTakeAddress();
		if(StringUtils.isNotEmpty(address)){
			String addressId = clothesService.queryClothesOrderAddressId(orderId);
			memberService.updateAddressRemark(addressId, request.getTakeAddress());
		}
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "小哥自主收衣", notes = "小哥自主收衣",response=ReturnResult.class,position = 6)
	@RequestMapping(value="selfTakeClothes",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult selfTakeClothes(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		//判断小哥是否预约时间
		ClothesOrderTimeDto orderTime = clothesService.queryClothesOrderTakeBackTime(orderId);
		if(orderTime.getTakeTime()==0)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "请先预约取衣时间");
		clothesService.updateWashFinisher(request.getOrderId(), employeeId, ClothesOrderStatus.PLACE_ORDER);
		
		
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "指派订单给众包人员去取衣", notes = "指派订单给众包人员",response=ReturnResult.class,position = 7)
	@RequestMapping(value="assignTakeOrderToProperty",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult assignTakeOrderToProperty(@RequestBody OrderIdRequest request) throws Exception{
		String orderId = request.getOrderId();
		//判断小哥是否预约时间
		ClothesOrderTimeDto orderTime = clothesService.queryClothesOrderTakeBackTime(orderId);
		if(orderTime.getTakeTime()==0)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "请先预约取衣时间");
		//查询该楼盘的众包人员
		String communityId = clothesService.queryCommunityIdByOrderId(orderId);
		//标记预约订单的完成为 0表示 小哥已分派订单，当物业人员领取订单后改为物业的id
		clothesService.updateWashFinisher(orderId, "0", ClothesOrderStatus.PLACE_ORDER);
		
		//推送给物业人员
		List<EmployeeDto> employees = employeeService.queryEmployeeByCommunityId(communityId,RedisAuthType.property.ordinal());
		PushMessageDto pushMessage = new PushMessageDto();
		for(EmployeeDto employee:employees){
			pushMessage.addDeviceToken(employee.getDeviceToken());
		}
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 有预约洗衣订单，请查看！";
		pushMessage.setTitle("洗衣订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.clothesOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	
	 
	@ApiOperation(value = "众包人员确认收到订单", notes = "众包人员确认收到订单",response=ReturnResult.class,position = 7)
	@RequestMapping(value="propertyReceiveOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyReceiveOrder(@RequestBody OrderIdRequest request) throws Exception{
		//订单绑定众包人员
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		String result = clothesService.queryWashStatusFinisher(orderId, ClothesOrderStatus.PLACE_ORDER);
		if(StringUtils.isNotEmpty(result) && result.equals("0")){
			clothesService.updateWashFinisher(orderId, employeeId, ClothesOrderStatus.PLACE_ORDER);
			//推送给小哥
			PushMessageDto pushMessage = new PushMessageDto();
			String BEmployeeId = clothesService.queryWashStatusOperator(orderId, ClothesOrderStatus.PLACE_ORDER);
			EmployeeDto dto = employeeService.queryEmployeeById(BEmployeeId);
			pushMessage.addDeviceToken(dto.getDeviceToken());
			 
			pushMessage.setMessageId(UuidUtil.getUUIDString());
			String jsonStr = "你好, 物业人员已【接收】洗衣订单，请查看！";
			pushMessage.setTitle("洗衣订单");
			pushMessage.setText(jsonStr);
			pushMessage.setType(DeviceType.ASSISTANT);
			pushMessage.putExtras("type",String.valueOf(OrderType.clothesOrder.ordinal()));
			pushMessage.putExtras("orderId", orderId);
			
			comsystemService.push(pushMessage);
			return ReturnResult.SUCCESS();
		}else{
			EmployeeDto dto = employeeService.queryEmployeeById(employeeId);
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY,"该订单已被[ "+dto.getRealName()+" ]领取");
		}	
	}
	
	
	@ApiOperation(value = "众包人员完成取件", notes = "众包人员，扫码、填写件数，表示该人员已经将衣物从客户处取走",response=ReturnResult.class,position = 8)
	@RequestMapping(value="propertyFinishTakeClothes",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyFinishTakeClothes(@RequestBody EmployeeFinishTakeClothesRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		//订单绑定条码
		String orderId = request.getOrderId();
		clothesService.updateClothesOrderBarCode(request.getOrderId(), request.getBarCode(), request.getClothesCount(),null);
		clothesService.updateWashOperator(orderId, employeeId, ClothesOrderStatus.TAKE_CLOTHES);
		
		clothesService.updateClothesRemark(orderId, request.getRemark());
		
		clothesService.addWashStatusDescription(orderId, ClothesOrderStatus.TAKE_CLOTHES, employeeId,null);
		
		//推送给该订单的小哥
		PushMessageDto pushMessage = new PushMessageDto();
		String BEmployeeId = clothesService.queryWashStatusOperator(orderId, ClothesOrderStatus.PLACE_ORDER);
		EmployeeDto dto = employeeService.queryEmployeeById(BEmployeeId);
		pushMessage.addDeviceToken(dto.getDeviceToken());
		 
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 物业人员已【取衣】洗衣订单，请查看！";
		pushMessage.setTitle("洗衣订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.clothesOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "确认收衣", notes = "1、小哥自取衣物，确认收衣。2、众包人员取衣，小哥完善所收衣服",response=ReturnResult.class,position = 9)
	@RequestMapping(value="confirmTakeClothes",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult confirmTakeClothes(@RequestBody ConfirmTakeClothesRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		String cardId = request.getCardId();
		String clothesJson = request.getClothes();
		List<TakeClothesDto> clothesList = JSONObject.parseArray(clothesJson, TakeClothesDto.class);
		CouponDto card=null;
		//查询使用的卡券
		if(StringUtils.isNotEmpty(cardId) && !cardId.equals("-1")){
			card = comsystemService.queryCoupon(cardId);
		}
		//查看订单是否已有条码
		  
		//若无条码则验证条码是否重复
		String barCode = request.getBarCode();
		card=clothesService.confirmTakeClothes(orderId,card,request.getRemark(),barCode,employeeId,clothesList);
		if(card!=null){
			comsystemService.updateCoupon(card);
		}
		//订单统计数据 TODO
		QueueSender queueSender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
		StatisticsOrderMessage statisticsOrderMessage = new StatisticsOrderMessage();
		statisticsOrderMessage.setOrderType(OrderType.clothesOrder.ordinal());
		statisticsOrderMessage.setOrderStatus(ClothesOrderStatus.TAKE_CLOTHES.ordinal());
		statisticsOrderMessage.setOrderId(orderId);
		queueSender.send(statisticsOrderMessage);
		return ReturnResult.SUCCESS();
	}
	 
	
	@ApiOperation(value = "小哥自主送回订单", notes = "小哥自主送回订单",response=ReturnResult.class,position = 4)
	@RequestMapping(value="deliveryClothesBackOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deliveryClothesBackOrder(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		//判断小哥是否预约时间
		ClothesOrderTimeDto orderTime = clothesService.queryClothesOrderTakeBackTime(orderId);
		if(orderTime.getBackTime()==0)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "请先预约送回时间");
		clothesService.updateWashFinisher(request.getOrderId(), employeeId, ClothesOrderStatus.DELIVERY_CLOTHES);
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "小哥指派给物业去送回", notes = "小哥指派给物业去送回",response=ReturnResult.class,position = 4)
	@RequestMapping(value="assignBackOrderToProperty",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult assignBackOrderToProperty(@RequestBody OrderIdRequest request) throws Exception{
		String orderId = request.getOrderId();
		//判断小哥是否预约时间
		ClothesOrderTimeDto orderTime = clothesService.queryClothesOrderTakeBackTime(orderId);
		if(orderTime.getBackTime()==0)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "请先预约送回时间");
		
		//查询该订单取衣步奏第一个操作者
		String employeeId = clothesService.queryWashStatusOperator(orderId, ClothesOrderStatus.TAKE_CLOTHES);
		//标记订单已指派给物业人员
		clothesService.updateWashFinisher(orderId, "0", ClothesOrderStatus.DELIVERY_CLOTHES);
		
		//查询该楼盘的众包人员
		EmployeeDto dto = employeeService.queryEmployeeById(employeeId);
		PushMessageDto pushMessage = new PushMessageDto();
		pushMessage.addDeviceToken(dto.getDeviceToken());
		 
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 有送回洗衣订单，请查看！";
		pushMessage.setTitle("洗衣订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.clothesOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "单件衣服完成", notes = "单件衣服完成",response=ReturnResult.class,position = 10)
	@RequestMapping(value="singleClothesCompleted",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult singleClothesCompleted(@RequestBody SingleClothesRequest request) throws Exception{
		clothesService.singleClothesCompleted(request.getOrderId(), request.getClothesId());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "单件衣服返洗", notes = "单件衣服返洗",response=ReturnResult.class,position = 11)
	@RequestMapping(value="singleClothesBackWash",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult singleClothesBackWash(@RequestBody SingleClothesRequest request) throws Exception{
		clothesService.singleClothesBackWash(request.getOrderId(), request.getClothesId());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "确认送回时间", notes = "确认送回时间",response=ReturnResult.class,position = 6)
	@RequestMapping(value="confirmBackTime",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult confirmBackTime(@RequestBody ConfirmTimeRequest request) throws Exception{
		//记录送回时间
		clothesService.updateClothesOrderBackTime(request.getOrderId(), request.getTime(),request.getRemark());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "物业人员标记订单已送达", notes = "物业人员标记订单已送达",response=ReturnResult.class,position = 6)
	@RequestMapping(value="confirmClothesArrived",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult confirmClotheArrived(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		clothesService.updateWashFinisher(request.getOrderId(), employeeId, ClothesOrderStatus.DELIVERY_CLOTHES);
		//推送给小哥
		//推送给该订单的小哥
		PushMessageDto pushMessage = new PushMessageDto();
		String BEmployeeId = clothesService.queryWashStatusOperator(orderId, ClothesOrderStatus.PLACE_ORDER);
		EmployeeDto dto = employeeService.queryEmployeeById(BEmployeeId);
		pushMessage.addDeviceToken(dto.getDeviceToken());
		 
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 物业人员已【送回】洗衣订单，请查看！";
		pushMessage.setTitle("洗衣订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.clothesOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	
	
	@ApiOperation(value = "小哥完成订单", notes = "小哥完成订单",response=ReturnResult.class,position = 6)
	@RequestMapping(value="completeClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult completeClothesOrder(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		
		OrderPayStatusDto clothesOrder = clothesService.queryPriceInfoByOrderId(orderId);
		//查看用户余额、积分是否可支付订单（可以则代扣）
		String userId = clothesOrder.getUserId();
		MemberDto member = memberService.queryMemberInfo(userId);
		String message="尊敬的"+member.getUserName()+"您好，您的衣服已经送达，请确认!";
		//订单是否已支付
		if(!clothesOrder.getPayStatus().equals(PayStatus.PAID)){
			IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.payOut, IntegralSourceType.exchange);
			
			OrderPayStatusDto payInfo = clothesService.handleClothesOrderPayPrice(clothesOrder.getFinalPrice(),
					member.getBalance(), member.getScore(),
					clothesOrder.getCard(), integralRule.getRatio());
			//代扣成功
			if(payInfo.getPayStatus().equals(PayStatus.PAID)){
				payInfo.setId(orderId);
				clothesService.updateClothesOrderPayStatus(payInfo);
				message="<p>尊敬的"+member.getUserName()+"：</p><p style=\"text-indent: 2em;\">您好，您的衣服已经送达，请确认!"
						+ "</p><p style=\"text-indent: 2em;\">为了不影响您继续下单使用，系统已扣除订单号为："+clothesOrder.getOrderNumber()+" "
						+ "洗衣订单的未支付金额，代扣余额为："+payInfo.getBalance()+"，代扣积分为："+payInfo.getIntegralNum()+"。</p><p style=\"text-indent: 2em;\">如您有任何疑问，请拨打我们的客服电话：4008323325。</p><p style=\"text-indent: 2em;\">祝您生活愉快！</p>";
			}else{
				message = "尊敬的"+member.getUserName()+"您好:您的衣服已经送达，请确认!为了下次能给您提供更好的服务,请您及时付款。"; 
			}
		}
		//修改订单状态、修改洗衣状态、修改洗衣状态描述
		clothesService.updateClothesOrderStatus(orderId, ClothesOrderStatus.COMPLETE_CLOTHES_ORDER,employeeId);
		
		//保存到消息中心
		MessageInfoDto messageInfoDto=new MessageInfoDto();
		messageInfoDto.setTitle("订单号为："+clothesOrder.getOrderNumber()+"的乐E洗衣订单已完成");
		messageInfoDto.setPublisher("系统");
		messageInfoDto.setMessageType(MessageType.PERSONAL);
		MessageContentDto contentDto=new MessageContentDto();
		contentDto.setContent(message);
		contentDto.setContentType(FileType.text.ordinal());
		messageInfoDto.addMessageContents(contentDto);
		MessageReceiveInfoDto infoDto=new MessageReceiveInfoDto();
		infoDto.setMemberId(userId);
		messageInfoDto.addMessageReceiveInfo(infoDto);
		messageService.saveMessage(messageInfoDto);
		
		//推送给用户
		PushMessageDto pushMessage = new PushMessageDto();
		pushMessage.setType(member.getDeviceType());
		pushMessage.addDeviceToken(member.getDeviceToken());
		 
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		pushMessage.setTitle("洗衣订单");
		pushMessage.setText(message);
		pushMessage.setType(member.getDeviceType());
		pushMessage.putExtras("messageId", orderId);
		
		comsystemService.push(pushMessage);
		//数据统计 TODO
		QueueSender queueSender = new QueueSender(QueueManager.ORDER_STATISTICS_QUEUE, QueueManager.HOST);
		StatisticsOrderMessage statisticsOrderMessage = new StatisticsOrderMessage();
		statisticsOrderMessage.setOrderType(OrderType.clothesOrder.ordinal());
		statisticsOrderMessage.setOrderStatus(ClothesOrderStatus.COMPLETE_CLOTHES_ORDER.ordinal());
		statisticsOrderMessage.setOrderId(orderId);
		queueSender.send(statisticsOrderMessage);
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "查询预约订单列表", notes = "查询预约订单列表",response=ReturnResult.class,position = 2)
	@RequestMapping(value="queryBespeakOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryBespeakOrder(@RequestBody PageRequest request) throws Exception{
		List<QueryBespeakOrderResponse> result=null;
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<String> communityIds = employeeService.queryCommunityOfEmployee(employeeId);
		
		if(communityIds==null)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "你还没有负责的小区");
		List<String> communitides=new ArrayList<>();
		for(String communityId :communityIds){
			communitides.add(communityId);
			List<CommunityDto> communities = comsystemService.queryChirldCommunity(communityId);
			if(communities!=null){
				for(CommunityDto dto: communities){
					communitides.add(dto.getCommunityId());
				}
			}
		}
		List<ClothesOrdersDto> list = clothesService.queryBespeakOrder(communitides,request.getPage());
		if(list!=null){
			QueryBespeakOrderResponse response=null;
			result=new ArrayList<QueryBespeakOrderResponse>();
			for (ClothesOrdersDto clothesOrdersDto : list) {
				String addressId = clothesOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response=new QueryBespeakOrderResponse();
				String operator = clothesOrdersDto.getOperator();
				int receiveStatus =0;
				if(StringUtils.isNotEmpty(operator)){
					if(operator.equals(employeeId)){
						receiveStatus=1;
					}else{
						receiveStatus=2;
					}
				}
				response.setReceiveStatus(receiveStatus);
				response.setRemark(clothesOrdersDto.getRemark());
				response.setBespeakTime(clothesOrdersDto.getBespeakTime());
				response.setOrderId(clothesOrdersDto.getId());
				response.setOrderNumber(clothesOrdersDto.getOrderNumber());
				response.setOrderTime(clothesOrdersDto.getOrderTime());
				response.setPhone(addressInfo.getPhone());
				response.setUserName(addressInfo.getUserName());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				String finisher = clothesOrdersDto.getFinisher();
				if(StringUtils.isNotEmpty(finisher) && !finisher.equals("-1")){
					if(finisher.equals("0")){
						response.setProperty("已分派给物业人员");
					}else{
						EmployeeDto employee = employeeService.queryEmployeeById(finisher);
						response.setProperty("物业人员："+employee.getRealName()+"已领取订单");
					}
				}
				
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "物业人员查询预取衣订单列表", notes = "查询预约订单列表",response=ReturnResult.class,position = 2)
	@RequestMapping(value="propertyQueryTakeClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyQueryTakeClothesOrder(@RequestBody PageRequest request)throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		//查看物业人员负责的小区
		List<String> communities = employeeService.queryCommunityOfEmployee(employeeId);
		
		List<ClothesOrdersDto> list = clothesService.propertyQueryTakeClothesOrder(communities,request.getPage());
		List<PropertyQueryTakeClothesOrderResponse> result=null;
		if(list!=null){
			PropertyQueryTakeClothesOrderResponse response=null;
			result=new ArrayList<PropertyQueryTakeClothesOrderResponse>();
			for (ClothesOrdersDto clothesOrdersDto : list) {
				String finisher = clothesOrdersDto.getFinisher();
				int receiveStatus =0;
				if(StringUtils.isNotEmpty(finisher)){
					if(finisher.equals("0")){
						receiveStatus=0;
					}else if(finisher.equals(employeeId)){
						receiveStatus=1;
					}else{
						continue;
					}
				}
				String addressId = clothesOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response=new PropertyQueryTakeClothesOrderResponse();
				
				response.setReceiveStatus(receiveStatus);
				response.setBespeakTime(clothesOrdersDto.getBespeakTime());
				response.setOrderId(clothesOrdersDto.getId());
				response.setOrderNumber(clothesOrdersDto.getOrderNumber());
				response.setOrderTime(clothesOrdersDto.getOrderTime());
				response.setPhone(addressInfo.getPhone());
				response.setUserName(addressInfo.getUserName());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value = "查看用户的拼团卡列表", notes = "查看用户的拼团卡列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryUserCoupons",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryUserCoupons(@RequestBody OrderIdRequest request) throws Exception{
		//查询订单的用户id,下单社区
		ClothesOrderUserIdDto clothesOrder = clothesService.queryClothesOrderUserId(request.getOrderId());
		String userId = clothesOrder.getUserId();
		List<CouponDto> list = comsystemService.queryUserGroupCoupons(userId);
		return ReturnResult.SUCCESS(list);
	}
	
	@ApiOperation(value = "查看衣服价格列表", notes = "查看衣服价格列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryClothesPrice",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryClothesPrice(@RequestBody QueryClothesPriceListRequest request) throws Exception{
		//查询订单的用户id,下单社区
		ClothesOrderUserIdDto clothesOrder = clothesService.queryClothesOrderUserId(request.getOrderId());
		
		String userId = clothesOrder.getUserId();
		double discountRatioOne=1;
		double discountRatioTwo=1;
		MemberRankDto memberRank = memberService.queryMemberRankInfo(userId);
		
		discountRatioOne=memberRank.getPreferentialScale();
		discountRatioTwo=memberRank.getDiscount();
		String couponId = request.getCardId();
		boolean useCoupon=false;
		if(StringUtils.isNotEmpty(couponId) && !couponId.equals("-1")){
			 useCoupon=true;
			 CouponDto coupon = comsystemService.queryCoupon(couponId);
			
			 double one = coupon.getDiscountRatioOne();
			 double two = coupon.getDiscountRatioTwo();
			 if(one<discountRatioOne && two<discountRatioTwo){
				 discountRatioOne=one;
				 discountRatioTwo=two;
			 }
		}
		 
		String communityId = clothesOrder.getCommunityId();
		List<ClothesPriceOfCategoryDto> result = clothesService.queryAllClothesPrice(communityId,discountRatioOne,discountRatioTwo,useCoupon);
		return ReturnResult.SUCCESS(result);
	}
	
	
	@ApiOperation(value = "查看取衣订单列表", notes = "查看取衣订单列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryTakeClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryTakeClothesOrder(@RequestBody PageRequest request) throws Exception{
		List<QueryTakeClothesOrderResponse> result=null;
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		//显示取件人
		List<TakeClothesOrderDto> list = clothesService.queryTakeClothesOrder(employeeId,request.getPage());
		if(list!=null){
			QueryTakeClothesOrderResponse response=null;
			result=new ArrayList<QueryTakeClothesOrderResponse>(list.size());
			for (TakeClothesOrderDto clothesOrdersDto : list) {
				String addressId = clothesOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response=new QueryTakeClothesOrderResponse();
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setBespeakTime(clothesOrdersDto.getBespeakTime());
				response.setOrderId(clothesOrdersDto.getOrderId());
				response.setOrderNumber(clothesOrdersDto.getOrderNumber());
				response.setOrderTime(clothesOrdersDto.getOrderTime());
				response.setRemark(clothesOrdersDto.getRemark());
				response.setBarCode(clothesOrdersDto.getBarCode());
				response.setClothesNumber(clothesOrdersDto.getClothesNumber());
				/*if(StringUtils.isNotEmpty(propertyId)){
					EmployeeDto employee = employeeService.queryEmployeeById(employeeId);
					response.setPropertyId(propertyId);
					response.setPropertyName(employee.getRealName());
					response.setPropertyPhone(employee.getPhone());
				}*/
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看进行中订单列表", notes = "查看进行中订单列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryWashingClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryWashingClothesOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<ClothesOrdersDto> list = clothesService.queryWashingClothesOrder(employeeId, request.getPage());
		List<QueryWashingClothesOrderResponse> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryWashingClothesOrderResponse>(list.size());
			QueryWashingClothesOrderResponse response=null;
			for (ClothesOrdersDto clothesOrdersDto : list) {
				String addressId = clothesOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response=new QueryWashingClothesOrderResponse();
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				response.setAddress(addressInfo.getCommunityName());
				
				response.setOrderId(clothesOrdersDto.getId());
				response.setOrderNumber(clothesOrdersDto.getOrderNumber());
				response.setOrderTime(clothesOrdersDto.getOrderTime());
				response.setRemark(clothesOrdersDto.getRemark());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value = "查看送回订单列表", notes = "查看送回订单列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="querySendClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult querySendClothesOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<DeliveryClothesOrderDto> list = clothesService.queryDeliveryClothesOrder(employeeId,request.getPage());
		List<QuerySendClothesOrderResponse> result=null;
		if(list!=null){
			result=new ArrayList<QuerySendClothesOrderResponse>();
			QuerySendClothesOrderResponse response=null;
			for (DeliveryClothesOrderDto clothesOrder : list) {
				response=new QuerySendClothesOrderResponse();
				String addressId = clothesOrder.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setPhone(addressInfo.getPhone());
				response.setUserName(addressInfo.getUserName());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				
				response.setBackTime(clothesOrder.getBackTime());
				response.setOrderId(clothesOrder.getOrderId());
				response.setOrderNumber(clothesOrder.getOrderNumber());
				response.setOrderTime(clothesOrder.getOrderTime());
				response.setRemark(clothesOrder.getRemark());
				String propertyId = clothesOrder.getPropertyId();
				/*if(StringUtils.isNotEmpty(propertyId)){
					EmployeeDto employee = employeeService.queryEmployeeById(employeeId);
					response.setPropertyId(propertyId);
					response.setPropertyName(employee.getRealName());
					response.setPropertyPhone(employee.getPhone());
				}*/
				//TODO 暂定小哥送回 原为0
				int selfSend=0;
				if(StringUtils.isNotEmpty(propertyId)&&propertyId.equals(employeeId)){
					selfSend=1;
				}
				int deliveryStatus = clothesOrder.getDeliveryStatus();
				String finisher = clothesOrder.getFinisher();
				if(StringUtils.isNotEmpty(finisher) && !finisher.equals("-1")){
					if(finisher.endsWith("0")){
						selfSend=2;
						deliveryStatus=0;
					} else
					if(!finisher.equals(employeeId)){
						selfSend=2;
						deliveryStatus=1;
					}
				}	
				response.setSelfSend(selfSend);
				response.setPayStatus(clothesOrder.getPayStatus());
				response.setDeliveryStatus(deliveryStatus);
				response.setFactoryRemark(clothesOrder.getFactoryRemark());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "物业人员查看送回订单列表", notes = "物业人员查看送回订单列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="propertyQuerySendClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyQuerySendClothesOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<DeliveryClothesOrderDto> list = clothesService.propertyQueryDeliveryClothesOrder(employeeId,request.getPage());
		List<PropertyQuerySendClothesOrder> result=null;
		if(list!=null){
			result=new ArrayList<PropertyQuerySendClothesOrder>();
			PropertyQuerySendClothesOrder response=null;
			for (DeliveryClothesOrderDto clothesOrder : list) {
				response=new PropertyQuerySendClothesOrder();
				String addressId = clothesOrder.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setPhone(addressInfo.getPhone());
				response.setUserName(addressInfo.getUserName());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				
				response.setBackTime(clothesOrder.getBackTime());
				response.setOrderId(clothesOrder.getOrderId());
				response.setOrderNumber(clothesOrder.getOrderNumber());
				response.setOrderTime(clothesOrder.getOrderTime());
				response.setRemark(clothesOrder.getRemark());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看完成订单列表", notes = "查看完成订单列表",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryFinishClothesOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryFinishClothesOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<FinishClothesOrderDto> list = clothesService.queryFinishClothesOrder(employeeId,request.getPage());
		List<QueryFinishClothesOrderResponse> result=null;
		if(list!=null){
			result=new ArrayList<QueryFinishClothesOrderResponse>();
			QueryFinishClothesOrderResponse response=null;
			for(FinishClothesOrderDto clothesOrder:list){
				response=new QueryFinishClothesOrderResponse();
				response.setOrderTime(clothesOrder.getCreateTime());
				response.setOrderId(clothesOrder.getId());
				response.setOrderNumber(clothesOrder.getOrderNumber());
				response.setPayStatus(clothesOrder.getPayStatus());
				response.setPrice(clothesOrder.getPrice());
				
				String addressId = clothesOrder.getAddressId();
			  	AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
			  	String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setPhone(addressInfo.getPhone());
				response.setUserName(addressInfo.getUserName());
				 
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看洗衣订单的衣服", notes = "查看洗衣订单的衣服",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryClothes",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryClothes(@RequestBody OrderIdRequest request) throws Exception{
		List<ClothesDto> result = clothesService.queryWashClothes(request.getOrderId());
		return ReturnResult.SUCCESS(result); 
	}
	
	//XXX********************************值得买*****************************************/
	@ApiOperation(value = "查看值得买订单列表(刚下单)", notes = "查看值得买订单列表(刚下单)",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryGoodsOrder(@RequestBody PageRequest request) throws Exception{
		List<QueryWorthBuyOrderResponse> result=null;
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<String> communityIds = employeeService.queryCommunityOfEmployee(employeeId);
		
		if(communityIds==null)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "你还没有负责的小区");
		List<String> communitides=new ArrayList<>();
		for(String communityId :communityIds){
			communitides.add(communityId);
			List<CommunityDto> communities = comsystemService.queryChirldCommunity(communityId);
			if(communities!=null){
			for(CommunityDto dto: communities){
				communitides.add(dto.getCommunityId());
			}
			}
		}
		List<GoodsOrdersDto> list = mallService.queryWorthBuyOrder(communitides,request.getPage());
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryWorthBuyOrderResponse>(list.size());
			QueryWorthBuyOrderResponse response=null;
			for (GoodsOrdersDto goodsOrdersDto : list) {
				response=new QueryWorthBuyOrderResponse();
				String addressId = goodsOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setDeliveryTime(goodsOrdersDto.getDeliveryTime());
				response.setOrderId(goodsOrdersDto.getOrderId());
				response.setOrderNumber(goodsOrdersDto.getOrderNumber());
				response.setOrderTime(goodsOrdersDto.getOrderTime());
				String operator = goodsOrdersDto.getOperator();
				int receiveStatus =0;
				if(StringUtils.isNotEmpty(operator)){
					if(operator.equals(employeeId)){
						receiveStatus=1;
					}else{
						receiveStatus=2;
					}
				}
				response.setReceiveStatus(receiveStatus);
				response.setGoodsItems(goodsOrdersDto.getGoodsItems());
				response.setRemark(goodsOrdersDto.getRemark());
				String finisher = goodsOrdersDto.getFinisher();
				
				if(StringUtils.isNotEmpty(finisher) && finisher.equals("0")){
					finisher="已指派给物业人员";
				}
				response.setProperty(finisher);
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看值得买订单列表(派送中)", notes = "查看值得买订单列表(派送中)",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryDeliveryGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryDeliveryGoodsOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<DeliveryGoodsOrdersDto> list = mallService.queryDeliveryGoodsOrder(employeeId,request.getPage());
		List<QueryDeliveryGoodsOrderResponse> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryDeliveryGoodsOrderResponse>(list.size());
			QueryDeliveryGoodsOrderResponse response=null;
			for (DeliveryGoodsOrdersDto goodsOrdersDto : list) {
				response=new QueryDeliveryGoodsOrderResponse();
				String addressId = goodsOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setOrderId(goodsOrdersDto.getOrderId());
				response.setOrderNumber(goodsOrdersDto.getOrderNumber());
				response.setOrderTime(goodsOrdersDto.getOrderTime());
				response.setGoodsItems(goodsOrdersDto.getGoodsItems());
				response.setRemark(goodsOrdersDto.getRemark());
				response.setDeliveryTime(goodsOrdersDto.getDeliveryTime());
				String finisher = goodsOrdersDto.getFinisher();
				int deliveryStatus=0;
				if(StringUtils.isNotEmpty(finisher)){
					deliveryStatus=2;
				}
				response.setDeliveryStatus(deliveryStatus);
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "物业人员查看值得买订单列表(刚下单)", notes = "物业人员查看值得买订单列表(刚下单)",response=ReturnResult.class,position = 4)
	@RequestMapping(value="propertyQueryGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyQueryGoodsOrder(@RequestBody PageRequest request) throws Exception{
		List<QueryWorthBuyOrderResponse> result=null;
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<String> communityIds = employeeService.queryCommunityOfEmployee(employeeId);
		
		if(communityIds==null)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "你还没有负责的小区");
		 
		List<GoodsOrdersDto> list = mallService.prpertyQueryGoodsOrder(communityIds,request.getPage());
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryWorthBuyOrderResponse>(list.size());
			QueryWorthBuyOrderResponse response=null;
			for (GoodsOrdersDto goodsOrdersDto : list) {
				response=new QueryWorthBuyOrderResponse();
				String addressId = goodsOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setDeliveryTime(goodsOrdersDto.getDeliveryTime());
				response.setOrderId(goodsOrdersDto.getOrderId());
				response.setOrderNumber(goodsOrdersDto.getOrderNumber());
				response.setOrderTime(goodsOrdersDto.getOrderTime());
				String finisher = goodsOrdersDto.getFinisher();
				int receiveStatus =2;
				if(StringUtils.isNotEmpty(finisher)){
					if(finisher.equals("0")){
						receiveStatus=0;
					}else if(finisher.equals(employeeId)){
						receiveStatus=1;
					}
				}
				response.setReceiveStatus(receiveStatus);
				response.setGoodsItems(goodsOrdersDto.getGoodsItems());
				response.setRemark(goodsOrdersDto.getRemark());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "物业人员查看值得买订单列表(派送中)", notes = "物业人员查看值得买订单列表(派送中)",response=ReturnResult.class,position = 4)
	@RequestMapping(value="propertyQueryDeliveryGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyQueryDeliveryGoodsOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<DeliveryGoodsOrdersDto> list = mallService.propertyQueryDeliveryGoodsOrder(employeeId, request.getPage());
		List<QueryDeliveryGoodsOrderResponse> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryDeliveryGoodsOrderResponse>(list.size());
			QueryDeliveryGoodsOrderResponse response=null;
			for (DeliveryGoodsOrdersDto goodsOrdersDto : list) {
				response=new QueryDeliveryGoodsOrderResponse();
				String addressId = goodsOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setOrderId(goodsOrdersDto.getOrderId());
				response.setOrderNumber(goodsOrdersDto.getOrderNumber());
				response.setOrderTime(goodsOrdersDto.getOrderTime());
				response.setGoodsItems(goodsOrdersDto.getGoodsItems());
				response.setRemark(goodsOrdersDto.getRemark());
				response.setDeliveryTime(goodsOrdersDto.getDeliveryTime());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	
	
	
	
	@ApiOperation(value = "查看值得买订单列表(已完成)", notes = "查看值得买订单列表(已完成)",response=ReturnResult.class,position = 4)
	@RequestMapping(value="queryFinishGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryFinishGoodsOrder(@RequestBody PageRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		List<FinishGoodsOrderDto> list = mallService.queryFinishGoodsOrder(employeeId, request.getPage());
		List<QueryFinishGoodsOrderResponse> result=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryFinishGoodsOrderResponse>(list.size());
			QueryFinishGoodsOrderResponse response=null;
			for (FinishGoodsOrderDto goodsOrdersDto : list) {
				response=new QueryFinishGoodsOrderResponse();
				String addressId = goodsOrdersDto.getAddressId();
				AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
				response.setUserName(addressInfo.getUserName());
				response.setPhone(addressInfo.getPhone());
				String remarkAddress = addressInfo.getRemarkAddress();
				if(StringUtils.isBlank(remarkAddress)){
					response.setAddress(addressInfo.getCommunityName());
				}else{
					response.setAddress(remarkAddress);
				}
				response.setOrderId(goodsOrdersDto.getOrderId());
				response.setOrderNumber(goodsOrdersDto.getOrderNumber());
				response.setOrderTime(goodsOrdersDto.getOrderTime());
				response.setRemark(goodsOrdersDto.getRemark());
				response.setGoodsItems(goodsOrdersDto.getGoodsItems());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value = "小哥领取值得买订单", notes = "小哥领取值得买订单",response=ReturnResult.class,position = 4)
	@RequestMapping(value="receiveGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult receiveGoodsOrder(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		GoodsOrderSatatus orderStatus = GoodsOrderSatatus.readyOurDepot;
		
		String operator = mallService.queryDeliveryStatusOperator(orderId, orderStatus);
		if(StringUtils.isBlank(operator)){
			mallService.updateDeliveryStatusOperator(request.getOrderId(), employeeId,orderStatus);
			//领取订单后将订单走到'准备配送'状态
			mallService.addGoodsOrderDeliveryStatus(request.getOrderId(), employeeId,GoodsOrderSatatus.outDepot,GoodsOrderSatatus.outDepot.getValue());
			return ReturnResult.SUCCESS();
		}else{
			EmployeeDto dto = employeeService.queryEmployeeById(employeeId);
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY,"该洗衣订单已被[ "+dto.getRealName()+" ]领取");
		}
	}
	@ApiOperation(value = "确认值得买派送时间", notes = "确认值得买派送时间",response=ReturnResult.class,position = 6)
	@RequestMapping(value="confirmDeliveryTime",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult confirmDeliveryTime(@RequestBody ConfirmTimeRequest request) throws Exception{
		//记录送回时间
		String orderId = request.getOrderId();
		long time = request.getTime();
		mallService.updateGoodsOrderDeliveryTime(orderId, time,request.getRemark());
		String address = request.getTakeAddress();
		if(StringUtils.isNotEmpty(address)){
			String addressId = clothesService.queryClothesOrderAddressId(orderId);
			memberService.updateAddressRemark(addressId, request.getTakeAddress());
		}
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "小哥自主派送值得买订单", notes = "小哥自主派送值得买订单",response=ReturnResult.class,position = 6)
	@RequestMapping(value="deliveryGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deliveryGoodsOrder(@RequestBody OrderIdRequest request) throws Exception{
		//订单进入派送状态
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		long deliveryTime = mallService.queryGoodsOrderBackTime(orderId);
		if(deliveryTime==0)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "请先预约送回时间");
		EmployeeDto employee = employeeService.queryEmployeeById(employeeId);
		String description = String.format(GoodsOrderSatatus.delivery.getValue(), employee.getRealName(),employee.getPhone());
		mallService.addGoodsOrderDeliveryStatus(orderId, employeeId, GoodsOrderSatatus.delivery,description);
		return ReturnResult.SUCCESS();
	}
	 
	
	@ApiOperation(value = "小哥指派给物业去送回", notes = "小哥指派给物业去送回",response=ReturnResult.class,position = 4)
	@RequestMapping(value="assignGoodsOrderToProperty",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult assignGoodsOrderToProperty(@RequestBody OrderIdRequest request) throws Exception{
		String orderId = request.getOrderId();
		long deliveryTime = mallService.queryGoodsOrderBackTime(orderId);
		if(deliveryTime==0)
			return ReturnResult.FAILUER(ReturnResult.FAULURE_NEED_DISPLAY, "请先预约送回时间");
		//查询该楼盘的众包人员
	
		String communityId = mallService.queryCommunityIdByOrderId(orderId);
		mallService.updateDeliveryStatusFinisher(orderId, "0", GoodsOrderSatatus.outDepot);
		
		//推送给物业人员
		List<EmployeeDto> employees = employeeService.queryEmployeeByCommunityId(communityId,RedisAuthType.property.ordinal());
		PushMessageDto pushMessage = new PushMessageDto();
		for(EmployeeDto employee:employees){
			pushMessage.addDeviceToken(employee.getDeviceToken());
		}
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 有值得买订单，请查看！";
		pushMessage.setTitle("值得买订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.goodsOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "众包人员取人领取派送订单", notes = "众包人员取人领取派送订单",response=ReturnResult.class,position = 7)
	@RequestMapping(value="propertyReceiveGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyReceiveGoodsOrder(@RequestBody OrderIdRequest request) throws Exception{
		//订单绑定众包人员
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		EmployeeDto employee = employeeService.queryEmployeeById(employeeId);
		String description = String.format(GoodsOrderSatatus.delivery.getValue(), employee.getRealName(),employee.getPhone());
		mallService.updateDeliveryStatusFinisher(orderId, employeeId, GoodsOrderSatatus.outDepot);
		mallService.addGoodsOrderDeliveryStatus(orderId, employeeId, GoodsOrderSatatus.delivery,description);
		//推送给该订单的小哥
		PushMessageDto pushMessage = new PushMessageDto();
		String BEmployeeId = mallService.queryDeliveryStatusOperator(orderId, GoodsOrderSatatus.readyOurDepot);
		EmployeeDto dto = employeeService.queryEmployeeById(BEmployeeId);
		pushMessage.addDeviceToken(dto.getDeviceToken());
		 
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 物业人员已【接收】值得买派送订单，请查看！";
		pushMessage.setTitle("值得买订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.goodsOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "众包人员送达订单", notes = "众包人员送达订单,并点击完成",response=ReturnResult.class,position = 7)
	@RequestMapping(value="propertyFinishGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult propertyFinishGoodsOrder(@RequestBody OrderIdRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		mallService.updateDeliveryStatusFinisher(orderId, employeeId, GoodsOrderSatatus.delivery);
		//推送给该订单的小哥
		PushMessageDto pushMessage = new PushMessageDto();
		String BEmployeeId = mallService.queryDeliveryStatusOperator(orderId, GoodsOrderSatatus.readyOurDepot);
		EmployeeDto dto = employeeService.queryEmployeeById(BEmployeeId);
		pushMessage.addDeviceToken(dto.getDeviceToken());
		 
		pushMessage.setMessageId(UuidUtil.getUUIDString());
		String jsonStr = "你好, 物业人员【送达】收值得买派送订单，请查看！";
		pushMessage.setTitle("值得买订单");
		pushMessage.setText(jsonStr);
		pushMessage.setType(DeviceType.ASSISTANT);
		pushMessage.putExtras("type",String.valueOf(OrderType.goodsOrder.ordinal()));
		pushMessage.putExtras("orderId", orderId);
		
		comsystemService.push(pushMessage);
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "小哥确认订单已送达", notes = "小哥确认订单已送达",response=ReturnResult.class,position = 7)
	@RequestMapping(value="finishGoodsOrder",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult finsishGoodsOrder(@RequestBody OrderIdRequest request)throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String employeeId = userSession.getEmployeeId();
		String orderId = request.getOrderId();
		mallService.addGoodsOrderDeliveryStatus(orderId, employeeId, GoodsOrderSatatus.finish, GoodsOrderSatatus.finish.getValue());
		return ReturnResult.SUCCESS();
	}
	
	@ApiOperation(value = "小哥查看用户信息", notes = "小哥查看用户信息",response=ReturnResult.class,position = 7)
	@RequestMapping(value="queryUserInfo",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryUserInfo(@RequestBody QueryUserInfoRequest request) throws Exception{
		MemberInfoDto result = memberService.queryMemberAccountInfo(request.getPhone());
		
		
		return ReturnResult.SUCCESS(result);
	}
	 
}
