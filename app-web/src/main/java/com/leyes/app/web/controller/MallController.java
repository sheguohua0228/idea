package com.leyes.app.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.leyes.app.annotation.Security;
import com.leyes.app.dto.comsystem.FileDto;
import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.comsystem.ServiceChargeDto;
import com.leyes.app.dto.member.AddressDto;
import com.leyes.app.dto.member.AddressInfoDto;
import com.leyes.app.dto.member.UserInfoDto;
import com.leyes.app.dto.shop.AppraiseDto;
import com.leyes.app.dto.shop.CategoryDto;
import com.leyes.app.dto.shop.GoodsDetailDto;
import com.leyes.app.dto.shop.GoodsDto;
import com.leyes.app.dto.shop.GoodsInfoDto;
import com.leyes.app.dto.shop.GoodsLimitTimeDto;
import com.leyes.app.dto.shop.GoodsNameDto;
import com.leyes.app.dto.shop.GoodsOrderBaseInfoDto;
import com.leyes.app.dto.shop.GoodsOrderDeliveryInfoDto;
import com.leyes.app.dto.shop.GoodsOrderDetailDto;
import com.leyes.app.dto.shop.GoodsOrderInfoDto;
import com.leyes.app.dto.shop.GoodsOrderItemDto;
import com.leyes.app.dto.shop.SecondCategoryDto;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.TradeType;
import com.leyes.app.message.IncomeOutDetailMessage;
import com.leyes.app.mq.QueueSender;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.MallService;
import com.leyes.app.service.MemberService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.clothes.ApplyRefundRequest;
import com.leyes.app.web.request.employee.OrderIdRequest;
import com.leyes.app.web.request.print.QueryOrderRequest;
import com.leyes.app.web.request.shop.GoodsOrderAppraiseRequest;
import com.leyes.app.web.request.shop.PlaceGoodsOrderRequest;
import com.leyes.app.web.request.shop.QueryAppraiseRequest;
import com.leyes.app.web.request.shop.QueryGoodsInfoRequest;
import com.leyes.app.web.request.shop.QueryGoodsRequest;
import com.leyes.app.web.request.shop.SearchGoodsRequest;
import com.leyes.app.web.response.comsystem.AppraiseResponse;
import com.leyes.app.web.response.shop.QueryAppraiseResponse;
import com.leyes.app.web.response.shop.QueryGoodsDesciptionResponse;
import com.leyes.app.web.response.shop.QueryGoodsInfoResponse;
import com.leyes.app.web.response.shop.QueryGoodsOrderDetailResponse;
import com.leyes.app.web.response.shop.QueryGoodsOrderResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("mall")
@Api(value = "mall-api", description = "值得买模块接口API", position = 1)
public class MallController extends BaseController{

	private MallService mallService=SpringContextUtils.getBean("mallService");
	
	private MemberService memberService=SpringContextUtils.getBean("memberService");
	
	private ComsystemService comsystemService=SpringContextUtils.getBean("comsystemService");
	
	@ApiOperation(value = "查看商品分类", notes = "查看商品分类", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryCategory", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryCategory() throws Exception {
		List<CategoryDto> result = comsystemService.queryCategoryByType(1);
		if(result!=null){
			for (CategoryDto categoryDto : result) {
				categoryDto.getCategories().add(0, new SecondCategoryDto("-1", "全部"));
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value = "查看商品列表", notes = "查看商品列表(按分类、排序规则)", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryGoods", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryGoods(@RequestBody QueryGoodsRequest request) throws Exception {
		String categoryId = request.getCategoryId();
		String c_categoryId = request.getC_categoryId();
		List<String> categoryIds=new ArrayList<String>();
		if(StringUtils.isNotEmpty(c_categoryId) && !c_categoryId.equals("-1")){
			categoryIds.add(c_categoryId);
		}else{
			categoryIds=comsystemService.queryChildCategoryId(categoryId);
			if(categoryIds.size()==0){
				categoryIds.add(categoryId);
			}
		}
		List<GoodsDto> result = mallService.queryGoods(categoryIds,request.getSortCode(), request.getSort(), request.getPage());
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看限时特惠商品列表", notes = "查看限时特惠商品列表", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryLimitTimeGoods", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryLimitTimeGoods() throws Exception {
		List<GoodsLimitTimeDto> result = mallService.queryLimitTimeGoods();
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看最新上架商品列表", notes = "查看最新上架商品列表", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryNewGoods", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryNewGoods() throws Exception {
		List<GoodsDto> result = mallService.queryNewGoods();
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看猜您喜欢商品列表", notes = "查看猜您喜欢商品列表", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryGuessGoods", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryGuessGoods() throws Exception {
		//TODO 目前只是随机取商品
		List<GoodsDto> result =mallService.queryFavoriteGoods();
		return ReturnResult.SUCCESS(result);
	}
	
	@ApiOperation(value = "查看商品详情", notes = "查看商品详情", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryGoodsInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryGoodsInfo(@RequestBody QueryGoodsInfoRequest request) throws Exception {
		QueryGoodsInfoResponse response = new QueryGoodsInfoResponse();
		String goodsId = request.getGoodsId();
		GoodsDetailDto goodsInfo = mallService.queryGoodsInfo(goodsId);
		List<FileDto> list = mallService.queryGoodsImages(goodsId);
		response.setGoodsId(goodsId);
		response.setImages(list);
		response.setIntroduce(goodsInfo.getIntroduce());
		response.setMarketPrice(goodsInfo.getMarketPrice());
		response.setPrice(goodsInfo.getPrice());
		response.setName(goodsInfo.getName());
		response.setSaleVolume(goodsInfo.getSaleVolume());
		response.setSpecification(goodsInfo.getSpecification());
		response.setDistribution(goodsInfo.getDistribution());
		ServiceChargeDto serviceCharge = comsystemService.queryServiceChargeByOrderType(OrderType.goodsOrder);
		if(serviceCharge!=null){
			BigDecimal price = new BigDecimal(goodsInfo.getPrice());
			BigDecimal condition = new BigDecimal(serviceCharge.getCondition());
			if(price.compareTo(condition)==-1){
				response.setExpress("订单未满"+condition+"元需增加"+serviceCharge.getExpense()+"元配送费");
			}else{
				response.setExpress("免运费");
			}
		}
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "查看商品描述详情", notes = "查看商品描述详情", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryGoodsDescription", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryGoodsDesciption(@RequestBody QueryGoodsInfoRequest request) throws Exception {
		String detail= mallService.queryGoodsDescription(request.getGoodsId());
		QueryGoodsDesciptionResponse response =new QueryGoodsDesciptionResponse();
		response.setDetail(detail);
		return ReturnResult.SUCCESS(response);
	}
	
	@ApiOperation(value = "查看商品评价", notes = "查看商品评价", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryAppraise", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryAppraise(@RequestBody QueryAppraiseRequest request) throws Exception {
		List<AppraiseDto> appraises = mallService.queryAppraise(request.getGoodsId(), request.getPage());
		List<QueryAppraiseResponse> result=null;
		QueryAppraiseResponse response=null;
		if(appraises!=null && appraises.size()>0){
			result=new ArrayList<QueryAppraiseResponse>();
			for(AppraiseDto appraise:appraises){
				UserInfoDto userInfo = memberService.queryUserInfo(appraise.getUserId());
				response=new QueryAppraiseResponse(userInfo.getUserName(),
						userInfo.getHeadImage(),
						appraise.getStar(),
						appraise.getContent());
				result.add(response);
			}
		}
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "下单", notes = "下单", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "placeOrder", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult placeOrder(@RequestBody PlaceGoodsOrderRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String addressId = request.getAddressId();
		
		String jsonStr = request.getGoodsInfos();
		List<GoodsInfoDto> goodsInfos=JSONObject.parseArray(jsonStr, GoodsInfoDto.class);
		ServiceChargeDto serviceCharge = comsystemService.queryServiceChargeByOrderType(OrderType.goodsOrder);
		//查看地址对应的小区
		AddressInfoDto addressInfo = memberService.queryAddressById(addressId);
		//创建订单
		GoodsOrderBaseInfoDto result = mallService.placeGoodsOrder(userId, addressId,addressInfo.getCommunityId(), goodsInfos,serviceCharge);
		
		//TODO 创建异步线程 30分钟不支付 订单取消 还原库存量
		
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看订单列表", notes = "查看订单列表", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryGoodsOrder", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryGoodsOrder(@RequestBody QueryOrderRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		List<GoodsOrderInfoDto> list = mallService.queryGoodsOrder(userId,request.getOrderType(), request.getPage());
		List<QueryGoodsOrderResponse> result=null;
		QueryGoodsOrderResponse response=null;
		if(list!=null && list.size()>0){
			result=new ArrayList<QueryGoodsOrderResponse>();
			for (GoodsOrderInfoDto goodsOrderInfoDto : list) {
				response=new QueryGoodsOrderResponse();
				String orderId = goodsOrderInfoDto.getOrderId();
				response.setPayStatus(goodsOrderInfoDto.getPayStatus());
				response.setOrderStatus(goodsOrderInfoDto.getOrderStatus());
				response.setOrderId(orderId);
				response.setOrderNumber(goodsOrderInfoDto.getOrderNumber());
				response.setFreight(goodsOrderInfoDto.getFreight());
				response.setPrice(goodsOrderInfoDto.getPrice());
				List<GoodsOrderItemDto> items = mallService.queryGoodsOrderItem(orderId);
				response.setGoodsList(items);
				if(items==null)
					response.setNumber(0);
				else
					response.setNumber(items.size());
				result.add(response);
			}
		} 
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看订单详情", notes = "查看订单详情", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryGoodsOrderDetail", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryGoodsOrderDetail(@RequestBody OrderIdRequest request) throws Exception {
		QueryGoodsOrderDetailResponse response=new QueryGoodsOrderDetailResponse();
		String orderId = request.getOrderId();
	    GoodsOrderDetailDto order = mallService.queryGoodsOrderDetail(orderId);
		AddressInfoDto address = memberService.queryAddressById(order.getAddressId());
		response.setBalance(order.getBalance());
		response.setIntegral(order.getIntegral());
		response.setOrderNumber(order.getOrderNumber());
		response.setPlaceOrderTime(order.getPlaceOrderTime());
		response.setPrice(order.getPrice());
		response.setPayStatus(order.getPayStatus());
		response.setOrderStatus(order.getOrderStatus());
		response.setFreight(order.getFreight());
		response.setAddress(new AddressDto(address.getUserName(), address.getPhone(), address.getCommunityName(), address.getAddressDetail()));
		List<GoodsOrderItemDto> items = mallService.queryGoodsOrderItem(orderId);
		response.setGoodsList(items);
		List<GoodsOrderDeliveryInfoDto> deliveries = mallService.queryGoodsOrderDeliveryInfo(orderId);
		response.setDeliveries(deliveries);
		return ReturnResult.SUCCESS(response);
	}
	
	@ApiOperation(value="评价",notes="评价商品订单",response=ReturnResult.class,position=8)
	@RequestMapping(value="appraise",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult appraise(@RequestBody GoodsOrderAppraiseRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		//评价
		String appraiseId=mallService.appraise(request.getOrderId(), request.getContent(), request.getServiceStar(), request.getGoodsId(), userId);
		
		IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.income, IntegralSourceType.appraise);
		int integral = integralRule.getRatio().intValue();
		//送积分
		IncomeOutFlowDto dto = new IncomeOutFlowDto(userId, 0, integral, BigDecimal.ZERO, BigDecimal.ZERO);
		memberService.updateMemberAccount(dto);
		QueueSender queueSender = new QueueSender(QueueManager.ACCOUNT_INCOMEOUT_DETAIL_QUEUE, QueueManager.HOST);
		 
		IncomeOutDetailMessage incomeMessage = new IncomeOutDetailMessage();
		incomeMessage.setUserId(userId);
		incomeMessage.setMoney(String.valueOf(integral));
		incomeMessage.setCurrencyType(PayType.INTEGRAL);
		incomeMessage.setRemark(OrderType.goodsOrder.getValue() +"-评价");
		queueSender.send(incomeMessage);
		AppraiseResponse response=new AppraiseResponse();
		response.setAppraiseId(appraiseId);
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value = "提交申请值得买退款请求", notes = "提交申请值得买退款请求", response = Result.class, position = 7)
	@RequestMapping(value = "applyRefund", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult applyRefund(@RequestBody ApplyRefundRequest request) throws Exception {
		 mallService.applyRefund(request.getReason(), request.getOrderId());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value = "模糊搜索商品", notes = "模糊搜索商品", response = Result.class, position = 7)
	@RequestMapping(value = "searchGoods", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult searchGoods(@RequestBody SearchGoodsRequest request) throws Exception {
		List<GoodsNameDto> result = mallService.queryGoodsLikeName(request.getKeyWord());
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "删除值得买订单", notes = "删除值得买订单", response = Result.class, position = 7)
	@RequestMapping(value = "deleteOrder", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deleteOrder(@RequestBody OrderIdRequest request) throws Exception {
		 mallService.deleteOrder(request.getOrderId());
		return ReturnResult.SUCCESS();
	}
	
}
