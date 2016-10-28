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

import com.leyes.app.annotation.Security;
import com.leyes.app.dto.comsystem.CommunityDto;
import com.leyes.app.dto.comsystem.IncomeOutFlowDto;
import com.leyes.app.dto.comsystem.IntegralRuleDto;
import com.leyes.app.dto.employee.EmployeeDto;
import com.leyes.app.dto.print.PhotoSizePriceDto;
import com.leyes.app.dto.print.PrintOrderBaseInfoDto;
import com.leyes.app.dto.print.QueryPrintFetchCodeDto;
import com.leyes.app.dto.print.QueryPrintOrderDetailDto;
import com.leyes.app.dto.print.VisaPhotoRequireInfoDto;
import com.leyes.app.enums.IntegralSourceType;
import com.leyes.app.enums.OrderType;
import com.leyes.app.enums.PayType;
import com.leyes.app.enums.RedisAuthType;
import com.leyes.app.enums.SMSTemplateEnum;
import com.leyes.app.enums.TradeType;
import com.leyes.app.message.IncomeOutDetailMessage;
import com.leyes.app.mq.QueueSender;
import com.leyes.app.queue.QueueManager;
import com.leyes.app.service.ComsystemService;
import com.leyes.app.service.EmployeeService;
import com.leyes.app.service.MemberService;
import com.leyes.app.service.PrintService;
import com.leyes.app.util.SpringContextUtils;
import com.leyes.app.web.pojo.UserSession;
import com.leyes.app.web.request.clothes.ApplyRefundRequest;
import com.leyes.app.web.request.clothes.AppraiseRequest;
import com.leyes.app.web.request.employee.OrderIdRequest;
import com.leyes.app.web.request.print.NotifyEmployeeRequest;
import com.leyes.app.web.request.print.PlacePrintOrderRequest;
import com.leyes.app.web.request.print.QueryOrderRequest;
import com.leyes.app.web.request.print.ReuploadPassportPhotoRequest;
import com.leyes.app.web.response.comsystem.AppraiseResponse;
import com.leyes.app.web.response.print.QueryFetchCodeResponse;
import com.leyes.app.web.utils.ReturnResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//@Controller
@RequestMapping("print")
@Api(value = "print-api", description = "打印模块接口API", position = 1)
public class PrintController  extends BaseController{
    
    private PrintService printService=SpringContextUtils.getBean("printService");
    private MemberService memberService=SpringContextUtils.getBean("memberService");
    private ComsystemService comsystemService=SpringContextUtils.getBean("comsystemService");
    private EmployeeService employeeService=SpringContextUtils.getBean("employeeService");
    
	/**
	 * @throws Exception  
	 * @Title: queryPrintSizePrice
	 * @Description: 查看打印尺寸及价格（6寸、8寸）
	 * @return
	 * @return QueryPrintSizePriceResponse
	 * @throws
	 */
	@ApiOperation(value = "查看打印尺寸及价格（6寸、8寸）", notes = "查看打印尺寸及价格（6寸、8寸）", response = ReturnResult.class, position = 1)
	@RequestMapping(value = "queryPrintSizePrice", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryPrintSizePrice() throws Exception {
		 List<PhotoSizePriceDto> result = printService.queryPhotoSizePrice();
		return ReturnResult.SUCCESS(result);
	}


	/**
	 * @throws Exception 
	 * @Title: queryVisaPhotoInfo
	 * @Description:获取护照打印要求列表
	 * @return
	 * @return QueryVisaPhotoRequireInfoResponse
	 * @throws
	 */
	@ApiOperation(value = "获取护照打印要求列表", notes = "获取护照打印要求列表", response = ReturnResult.class, position = 2)
	@RequestMapping(value = "queryVisaPhotoInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnResult queryVisaPhotoInfo() throws Exception {
		 List<VisaPhotoRequireInfoDto> result = printService.queryVisaPhotoInfo();
		return ReturnResult.SUCCESS(result);
	}

	/**
	 * @throws Exception 
	 * @Title: deletePrintOrder
	 * @Description: 删除打印订单
	 * @param request
	 * @return ReturnResult
	 * @throws
	 */
	@ApiOperation(value = "删除打印订单", notes = "删除打印订单", response = Result.class, position = 3)
	@RequestMapping(value = "deletePrintOrder", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult deletePrintOrder(@RequestBody OrderIdRequest request) throws Exception {
		printService.deletePrintOrder(request.getOrderId());
		return ReturnResult.SUCCESS();
	}

	/**
	 * @throws Exception 
	 * @Title: savePrintOrder
	 * @Description: 下打印订单
	 * @param request
	 * @return
	 * @return PrintOrderResponse
	 * @throws
	 */
	@ApiOperation(value = "下打印订单", notes = "下打印订单", response = Result.class, position = 4)
	@RequestMapping(value = "placePrintOrder", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult placePrintOrder(@RequestBody PlacePrintOrderRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		
		PrintOrderBaseInfoDto result = printService.placePrintOrder(userId, request.getPhotoInfos());
		
		//如订单为支付30分钟后删除该订单
		return ReturnResult.SUCCESS(result);
	}
	 
	@ApiOperation(value = "重传护照订单照片", notes = "当护照订单审核失败时，重传护照订单照片", response = Result.class, position = 4)
	@RequestMapping(value = "reuploadPassportPhoto", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult reuploadPassportPhoto(@RequestBody ReuploadPassportPhotoRequest request)throws Exception {
		printService.updatePrintOrderPhotoUrl(request.getOrderId(),request.getPhotoUrl());
		return ReturnResult.SUCCESS();
	}
	/**
	 * @throws Exception 
	 * @Title: savePrintOrder
	 * @Description: 查看打印订单列表
	 * @param request
	 * @return
	 * @return QueryPrintFetchCodeResponse
	 * @throws
	 */
	@ApiOperation(value = "查看打印订单列表", notes = "查看打印订单列表", response = Result.class, position = 5)
	@RequestMapping(value = "queryPrintOrder", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryPrintOrder(@RequestBody QueryOrderRequest request) throws Exception {
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		List<QueryPrintFetchCodeDto> result = printService.queryPrintOrder(userId,request.getPage(),request.getOrderType());
		return ReturnResult.SUCCESS(result);
	}
	/**
	 * @throws Exception 
	 * @Title: savePrintOrder
	 * @Description: 查看护照订单详情
	 * @param request
	 * @return queryPrintOrderDetail
	 * @throws
	 */
	@ApiOperation(value = "查看护照订单详情", notes = "查看护照订单详情", response = Result.class, position = 6)
	@RequestMapping(value = "queryPassportOrderDetail", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryPassportOrderDetail(@RequestBody OrderIdRequest request) throws Exception {
		QueryPrintOrderDetailDto result = printService.queryPrintOrderDetail(request.getOrderId());
		return ReturnResult.SUCCESS(result);
	}
	@ApiOperation(value = "查看提取码", notes = "根据订单id查询打印订单的提取码", response = Result.class, position = 6)
	@RequestMapping(value = "queryFetchCode", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult queryFetchCode(@RequestBody OrderIdRequest request) throws Exception {
		 String fetchCode = printService.queryFetchCode(request.getOrderId());
		 QueryFetchCodeResponse response=new QueryFetchCodeResponse();
		 response.setFetchCode(fetchCode);
		 return ReturnResult.SUCCESS(response);
	}
	
	/**
	 * @throws Exception 
	 * @Title: applyRefund
	 * @Description: 申请照片订单退款
	 * @param request
	 * @return
	 * @return ReturnResult 
	 * @throws
	 */
	@ApiOperation(value = "提交申请照片退款请求", notes = "提交申请照片退款请求", response = Result.class, position = 7)
	@RequestMapping(value = "applyRefund", method = RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult applyRefund(@RequestBody ApplyRefundRequest request) throws Exception {
		printService.applyRefund(request.getReason(), request.getOrderId());
		return ReturnResult.SUCCESS();
	}
	@ApiOperation(value="评价",notes="评价打印订单",response=ReturnResult.class,position=8)
	@RequestMapping(value="appraise",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult appraise(@RequestBody AppraiseRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		//评价
		String appraiseId = printService.appraise(userId,request.getOrderId(), request.getContent(), request.getServiceStar());
		
		IntegralRuleDto integralRule = comsystemService.queryIntegralRule(TradeType.income, IntegralSourceType.appraise);
		int integral = integralRule.getRatio().intValue();
		//送积分
		IncomeOutFlowDto dto = new IncomeOutFlowDto(userId, 0, integral, BigDecimal.ZERO, BigDecimal.ZERO );
		memberService.updateMemberAccount(dto);
		
		QueueSender queueSender = new QueueSender(QueueManager.ACCOUNT_INCOMEOUT_DETAIL_QUEUE, QueueManager.HOST);
		//TODO
		IncomeOutDetailMessage incomeMessage = new IncomeOutDetailMessage();
		incomeMessage.setUserId(userId);
		incomeMessage.setMoney("+"+String.valueOf(integral));
		incomeMessage.setCurrencyType(PayType.INTEGRAL);
		incomeMessage.setRemark(OrderType.clothesOrder.getValue() +"-评价");
		queueSender.send(incomeMessage);
		AppraiseResponse response=new AppraiseResponse();
		response.setAppraiseId(appraiseId);
		return ReturnResult.SUCCESS(response);
	}
	@ApiOperation(value="通知小哥",notes="用户打印护照后，通知小哥提供表格",response=ReturnResult.class,position=8)
	@RequestMapping(value="notifyEmployee",method=RequestMethod.POST)
	@ResponseBody
	@Security
	public ReturnResult notifyEmployee(@RequestBody NotifyEmployeeRequest request) throws Exception{
		UserSession userSession = sessionContextUtils.getContextCurrentUser();
		String userId = userSession.getUserId();
		String mobile = memberService.queryMemberMobileById(userId);
		//用户护照订单通知状态
		printService.updateVisaPhotoNoticeStatus(request.getOrderId());
		String communityId=request.getCommunityId();
		CommunityDto community = comsystemService.queryCommunityById(communityId);
		//找到对应小区的小哥
		List<EmployeeDto> employees = employeeService.queryEmployeeByCommunityId(communityId, RedisAuthType.employee.ordinal());
		String[] phones=new String[employees.size()];
		List<String[]> contents=new ArrayList<String[]>();
		if(employees!=null){
			for (int i=0;i<employees.size();i++) {
				phones[i]=employees.get(i).getPhone();
				String[] content={employees.get(i).getRealName(),community.getName(),mobile};
				contents.add(content);
			}
		}
		//给对应小哥发送短信
		comsystemService.sendSMS(phones, SMSTemplateEnum.T_NotifyEmployeeSMS,contents);
		return ReturnResult.SUCCESS();
	}
	
}
