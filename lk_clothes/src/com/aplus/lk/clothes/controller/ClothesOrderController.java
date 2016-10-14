package com.aplus.lk.clothes.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.ClothesPriceParams;
import com.aplus.lk.clothes.bean.MessageContentVo;
import com.aplus.lk.clothes.bean.MessageRequest;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.ReassignmentOrderRequest;
import com.aplus.lk.clothes.bean.TelOrderRequest;
import com.aplus.lk.clothes.bean.WashClothesModle;
import com.aplus.lk.clothes.entity.Admin;
import com.aplus.lk.clothes.entity.BrandWashMethod;
import com.aplus.lk.clothes.entity.ClothesAddress;
import com.aplus.lk.clothes.entity.ClothesOrder;
import com.aplus.lk.clothes.entity.ClothesOrder.OrderStatus;
import com.aplus.lk.clothes.entity.ClothesOrder.Status;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.Member;
import com.aplus.lk.clothes.entity.OutFactoryClothes;
import com.aplus.lk.clothes.entity.ReassignmentRecord;
import com.aplus.lk.clothes.entity.WashClothes;
import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.AdminService;
import com.aplus.lk.clothes.service.ClothesAddressService;
import com.aplus.lk.clothes.service.ClothesOrderService;
import com.aplus.lk.clothes.service.EmployeeService;
import com.aplus.lk.clothes.service.IBrandWashService;
import com.aplus.lk.clothes.service.IMessageSerivce;
import com.aplus.lk.clothes.service.MemberService;
import com.aplus.lk.clothes.service.WashClothesService;
import com.aplus.lk.clothes.utils.JPushUtils;
import com.aplus.lk.clothes.utils.OrderNumberUtil;
import com.aplus.lk.clothes.utils.StringUtils;
import com.aplus.lk.clothes.utils.UUIDUtils;

/**
 * 
 * @ClassName: ClothesOrderController
 * @Description: TODO 洗衣订单控制器
 * @author w.gang wgang1130@163.com
 * @date 2015-7-16 下午3:16:11
 *
 */
@Controller
@RequestMapping("clothesOrder")
public class ClothesOrderController {
	
	@Autowired
	private AddressCenterService addressCenterService;
	@Autowired
	private ClothesOrderService clothesOrderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private WashClothesService washClothesService;
	@Autowired
	private ClothesAddressService clothesAddressService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private IMessageSerivce messageSerivce;
	@Autowired
	private IBrandWashService brandWashService;
	
	private Logger logger = Logger.getLogger(getClass());
	
	public void queryList(Pager pager, String barCode, String orderNumber, 
			Integer status , Integer orderStatus, Integer payStatus,String phoneNumber
			,HttpServletRequest request){
		pager = clothesOrderService.queryPager(pager, barCode, orderNumber,status, orderStatus, payStatus, phoneNumber);
		request.setAttribute("pager", pager);
		request.setAttribute("barCode", barCode);
		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("status", status);
		request.setAttribute("orderStatus", orderStatus);
		request.setAttribute("payStatus", payStatus);
		request.setAttribute("phoneNumber", phoneNumber);
	}
	// 已取消订单
	@RequestMapping("cancel")
	public String queryCancelOrder(Pager pager, String phoneNumber, String orderNumber, 
			Integer orderStatus ,HttpServletRequest request){
		queryList(pager, null, orderNumber, Status.invalid.ordinal(), orderStatus, null, phoneNumber, request);
		return "clothes_order_cancel_list"; 
	}
	// 预约订单
	@RequestMapping("appointment")
	public String queryAppointmentsOrder(Pager pager, String phoneNumber, String orderNumber, 
			Integer payStatus ,HttpServletRequest request){
		queryList(pager, null, orderNumber, null, OrderStatus.appointments.ordinal(), payStatus, phoneNumber, request);
		return "clothes_order_appointments_list";
	}
	
	// 收衣订单
	@RequestMapping("collect")
	public String queryCollectOrder(Pager pager, String barCode, String orderNumber, 
			Integer payStatus,HttpServletRequest request){
		queryList(pager, barCode, orderNumber, Status.processed.ordinal(), OrderStatus.collect.ordinal(), payStatus, null,request);
		Map<String, Object> count = clothesOrderService.queryCommunityCount();
		request.setAttribute("count", count.get("count"));
		request.setAttribute("clothes", count.get("clothes"));
		request.setAttribute("shoes", count.get("shoes"));
		request.setAttribute("darning", count.get("darning"));
		return "clothes_order_collect_list";
	}
	
	// 工厂订单
	@RequestMapping("factory")
	public String queryFactoryOrder(Pager pager, String barCode, String orderNumber, 
			Integer payStatus,HttpServletRequest request){
		queryList(pager, barCode, orderNumber,  Status.processed.ordinal(), OrderStatus.factory.ordinal(), payStatus, null, request);
		int count = clothesOrderService.queryCollectCount();
		request.setAttribute("count", count);
		return "clothes_order_factory_list";
	}
	
	@RequestMapping("sorting")
	public String querySortingClothes(Pager pager,Long[] ids,String barCode,String phoneNumber, String userName, 
			String[] washType,HttpServletRequest request){
		if(pager.getPageSize()==10){
			pager.setPageSize(19);
		}
		pager=washClothesService.querySortingClothes(pager,ids, barCode, userName, phoneNumber, washType);
		request.setAttribute("pager", pager);
		request.setAttribute("barCode", barCode);
		request.setAttribute("phoneNumber", phoneNumber);
		request.setAttribute("userName", userName);
		request.setAttribute("washType", washType);
		if(ids!=null){
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<ids.length;i++){
				sb.append("ids=" +ids[i]+"&");
			}
			if(sb.length()>0){
				sb.insert(0, "?");
			}
			request.setAttribute("orderIds",  sb.substring(0, sb.length()-1));
		}
		
		return "clothes_order_sorting_list";
	}
	
	// 洗涤订单
	@RequestMapping("wash")
	public String queryWashOrder(Pager pager, String barCode, String orderNumber, 
			Integer payStatus,HttpServletRequest request){
		queryList(pager, barCode, orderNumber,  Status.processed.ordinal(), OrderStatus.washing.ordinal(), payStatus, null, request);
		return "clothes_order_wash_list";
	}
	
	//应出厂订单
	@RequestMapping("waitfactorylist")
	public String queryOutFactoryList(HttpServletRequest request,String barCode,String phoneNumber){
		 clothesOrderService.queryWaitFactoryList(request,phoneNumber,barCode);
		return "clothes_order_waitfactory_list";
	}
	
	//出厂衣物
	@RequestMapping("outfactory") 
	public String queryOutFactory(){
		
		return "clothes_order_outfactory_list";
	}
	// 派送中订单
	@RequestMapping("sending")
	public String queryBackOrder(Pager pager, String barCode, String orderNumber, 
			Integer payStatus,HttpServletRequest request){
		queryList(pager, barCode, orderNumber,  Status.processed.ordinal(), OrderStatus.send.ordinal(), payStatus, null, request);
		return "clothes_order_back_list";
	}
	
	// 已完成订单
	@RequestMapping("completed")
	public String queryCompleteOrder(Pager pager, String barCode, String orderNumber, 
			Integer payStatus,String phoneNumber,HttpServletRequest request){
		queryList(pager, barCode, orderNumber, Status.completed.ordinal(),
				OrderStatus.completed.ordinal(), payStatus, phoneNumber, request);
		return "clothes_order_completed_list";
	}
	//返洗订单
	@RequestMapping("backWashing")
	public String queryBackWashing(Pager pager,String phoneNumber,HttpServletRequest request){
		pager=clothesOrderService.queryBackWashList(pager, phoneNumber);
		request.setAttribute("pager", pager);
		request.setAttribute("phoneNumber", phoneNumber);
		return "clothes_order_backwash_list";
	}
	@RequestMapping(value="view",method = RequestMethod.GET)
	public String queryEmployeeById(long id,Integer type, HttpServletRequest request){
		ClothesOrder order = clothesOrderService.queryClothesOrderAndClothesStatusAddressById(id);
		request.setAttribute("clothesOrder", order);
		if(type!=null&&type==1){
			Pager pager=new Pager();
			pager.setPageSize(200);
			pager=clothesOrderService.queryHistoryOrder(pager, id);
			request.setAttribute("pager", pager);
		}
		request.setAttribute("checkViewType", type);
		return "clothes_order_view";
	}
	
	@RequestMapping("queryBarCode")
	@ResponseBody
	public List<OutFactoryClothes> queryBarCode(HttpServletRequest request,String barCode){
		List<OutFactoryClothes> clothes=clothesOrderService.queryBarCode(barCode);

		return clothes;
		
	}

	// 状态维护 订单详细信息
	/**
	 * 
	* @Title: queryByBarCode 
	* @Description: TODO
	* @param barCode 子条形码
	* @param orderStatus
	* @param request
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping("details")
	public String queryByBarCode(String barCode, Integer orderStatus,HttpServletRequest request){
		ClothesOrder order = null;
		if(orderStatus == OrderStatus.factory.ordinal() || orderStatus == OrderStatus.washing.ordinal() || orderStatus == OrderStatus.send.ordinal()){
			order = clothesOrderService.queryClothesOrderByChildBarCode(barCode);
			
			//order = clothesOrderService.queryWashClothesByChildBarCode(barCode);
		}else{
			order = clothesOrderService.queryClothesOrderByBarCode(barCode, request);
		}
		if(order == null || order.getOrderStatus() != orderStatus){
			request.setAttribute("status", "warn");
			request.setAttribute("message", "处于该状态的订单不存在");
			return "frame_message";
		}
		request.setAttribute("clothesOrder", order);
		return "clothes_order_details";
	}
	
	@RequestMapping("details_new")
	public String queryByBarCodeNew(String barCode, Integer orderStatus,String phoneNum,HttpServletRequest request){
		ClothesOrder order = null;
		if(orderStatus == OrderStatus.factory.ordinal() || orderStatus == OrderStatus.washing.ordinal() || orderStatus == OrderStatus.send.ordinal()){
			order = clothesOrderService.queryClothesOrderByChildBarCode(barCode);
		}else{
			order = clothesOrderService.queryClothesOrderByBarCode(barCode, request);
		}
		if(order == null || order.getOrderStatus() != orderStatus){
			request.setAttribute("status", "warn");
			request.setAttribute("message", "处于该状态的订单不存在");
			return "frame_message";
		}
		request.setAttribute("clothesOrder", order);
		List<BrandWashMethod> brands = brandWashService.queryAllBrand(null);
		 
		request.setAttribute("brands", brands);
		
		//查询该订单的用户是否是新用户
		int count = clothesOrderService.queryOrderCountByPhoneNum(order.getPhoneNumber());
		if(count <= 1){
			request.setAttribute("isNew", "1");
		}
		
		return "clothes_order_details_new";
	}

	@RequestMapping("collectToFactory")
	public String collectToFactory(WashClothesModle clothesModle,MultipartFile imageUpload,
			final long clothesOrderId, boolean isProccessed,String factoryRemark, final HttpServletRequest request){
		String pic_base_64_data = request.getParameter("picData");
		BASE64Decoder decode=new BASE64Decoder();
		String savePath= request.getSession().getServletContext().getRealPath("upload/status");
		String uuid = UUID.randomUUID().toString();
		try {
			byte[] datas = decode.decodeBuffer(pic_base_64_data);
			File file = new File(savePath + "/" + uuid + ".jpg");
			OutputStream fos = new FileOutputStream(file);
			fos.write(datas);
			fos.close();
			Admin loginAdmin = adminService.getLoginAdmin();
			String outImageUrl = request.getParameter("outImageUrl");
			String outImgUrl = "";
			if(!"".equals(outImageUrl)){
				outImgUrl = "/upload/status/"+outImageUrl.substring(outImageUrl.lastIndexOf("/")+1);
			}
			clothesOrderService.collectToFactory(clothesModle,
					"/upload/status/" + uuid + ".jpg", clothesOrderId,
					isProccessed,factoryRemark,loginAdmin.getName(),outImgUrl, request);
			ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
			exe.schedule(new Runnable() {
				
				@Override
				public void run() {
					factoryToWash(null,clothesOrderId,null);
					
				}
			}, 30, TimeUnit.MINUTES);
			exe.shutdown();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "frame_message";
	}
	
	@RequestMapping("factoryToWash")
	public String factoryToWash(MultipartFile imageUpload,long clothesOrderId, 
			HttpServletRequest request){
		String description = "您的衣物已经分拣完毕，正在洗涤中";
		
		clothesOrderService.updateOrderStatus(clothesOrderId, OrderStatus.factory,
				OrderStatus.washing, description, imageUpload,null,request);
		//将订单的衣服全部从未洗涤变成洗涤中
		washClothesService.updateClothesStatus(clothesOrderId, 0, 1);
		return "frame_message";
	}
	/**
	 * 
	* @Title: directFactoryTowash 
	* @Description: 直接进行到洗涤的一步
	* @param clothesOrderId
	* @param request
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping("singleDirectFactoryTowash")
	public String directFactoryTowash(long clothesOrderId, 
			HttpServletRequest request){
		String description = "您的衣物已经分拣完毕，正在洗涤中";
		Admin loginAdmin = adminService.getLoginAdmin();
		clothesOrderService.updateOrderStatus(clothesOrderId, OrderStatus.factory,
				OrderStatus.washing, description, null,loginAdmin.getName(),request);
		//将订单的衣服全部从未洗涤变成洗涤中
		washClothesService.updateClothesStatus(clothesOrderId, 0, 1);
		return "redirect:wash";
	}
	//打印标记
	@RequestMapping("directFactoryTowash")
	@ResponseBody
	public AjaxResult manyDirectFactoryTowash(Long[] ids,HttpServletRequest request){
		int length = ids.length;
		Set<Long> list=new HashSet<Long>();	
		for(int i=0;i<length;i++){
			Long id = ids[i];
			list.add(id);
		}
		Long[] array = list.toArray(new Long[list.size()]);
		AjaxResult result = new AjaxResult();
		
		/*String description = "您的衣物已经分拣完毕，正在洗涤中";*/
		boolean isUpdate=false;
		if(array!=null){
			for(long clothesOrderId:array){
				isUpdate = clothesOrderService.updateOrderPrintStatus(clothesOrderId);
				/*washClothesService.updateClothesStatus(clothesOrderId, 0, 1);*/
			}
		}
		if(isUpdate){
			result.setStatus(AjaxResult.Status.success);
			result.setMessage("已打印完毕");
		}else{
			result.setStatus(AjaxResult.Status.error);
			result.setMessage("打印失败");
		}
		return result;
	}
	
	
	@RequestMapping("washSingleClothes")
	@ResponseBody
	public AjaxResult washSingleClothes(Long clothesOrderId,String childBarCode, HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		boolean isUpdate = washClothesService.updateWashStatus(clothesOrderId, childBarCode,3);
		if(isUpdate){
			long washedNum = washClothesService.queryWashedClothes(clothesOrderId,3);
			long clothesNum = washClothesService.queryWashedClothesCount(clothesOrderId);
			result.setStatus(AjaxResult.Status.success);
			result.setMessage("条码为："+childBarCode+"的衣服已标记为已洗涤");
			//如果全部已洗涤，那么将已洗涤状态的改为派送中
			if(washedNum==clothesNum){
				result.setData(true);
				/*washClothesService.updateClothesStatus(clothesOrderId, 3, 4);直接把所有子订单已洗涤变成派送中*/
			}
		}else{
			result.setStatus(AjaxResult.Status.error);
			result.setMessage("标记条码为："+childBarCode+"的衣服失败");
		}
		return result;
	}
	@RequestMapping("washToSending")
	public String washToSending(MultipartFile imageUpload,long clothesOrderId, HttpServletRequest request){
		String description = "您的衣物已打包，正在派送中";
		Admin loginAdmin = adminService.getLoginAdmin();
		clothesOrderService.updateOrderStatus(clothesOrderId, OrderStatus.washing,
				OrderStatus.send, description, imageUpload,loginAdmin.getName(), request);
		return "frame_message";
	}
	/*
	 * 衣物出厂
	 * */
	@RequestMapping("directToOutFactory")
	@ResponseBody
	public AjaxResult manyDirectToOutFactory(String[] barcode,Long[] orderId,HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		/*String description = "您的衣物已打包，正在派送中.";
		Admin loginAdmin = adminService.getLoginAdmin();*/
		boolean isUpdate = false;
		String description = "您的衣物已打包，正在派送中";
		Admin loginAdmin = adminService.getLoginAdmin();
		for(int i=0;i<barcode.length;++i){
			
			
			isUpdate = washClothesService.updateWashStatus(orderId[i], barcode[i],4);
		if(isUpdate){
				
				long allSend = washClothesService.queryWashedClothes(orderId[i],4);
				long clothesNum = washClothesService.queryWashedClothesCount(orderId[i]);
				
				if(allSend==clothesNum){
					result.setData(true);//订单进入下一步  当所有衣服全部进入派送中 订单改变状态
					clothesOrderService.updateOrderStatus(orderId[i], OrderStatus.washing,
							OrderStatus.send, description, null,loginAdmin.getName(), request);
				}else{
					ClothesOrder clothes = clothesOrderService.queryClothesOrderById(orderId[i]);
					String registId = employeeService.queryRegistIdByOrderId(orderId[i]);
					WashClothes washclothes = washClothesService.queryByChildBarCode(barcode[i]);
					Map<String, String> extras = new HashMap<String, String>();
					extras.put("orderNumber", clothes.getOrderNumber());
					extras.put("clothesId", String.valueOf(washclothes.getId()));
					extras.put("type", "2");
					JPushUtils.sendPushToAssistant("", "订单为["+clothes.getOrderNumber()+"]中的["+washclothes.getClothesName()+"]已经洗涤完毕，请及时处理", null, registId, extras);
				}
			}else{
				result.setStatus(AjaxResult.Status.error);
				result.setMessage("标记条码为："+barcode[i]+"的衣服失败");
			    return result;
			}
		}
		result.setStatus(AjaxResult.Status.success);
		result.setMessage("所有衣物已出厂完毕");
		return result;
	}
	/**
	 * 
	* @Title: sendSingleClothes 
	* @Description: 单件衣服到派送状态
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping("sendSingleClothes")
	@ResponseBody
	public AjaxResult sendSingleClothes(Long clothesOrderId,String childBarCode, HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		boolean isUpdate = washClothesService.updateWashStatus(clothesOrderId, childBarCode,4);
		if(isUpdate){
			
			long allSend = washClothesService.queryWashedClothes(clothesOrderId,4);
			long clothesNum = washClothesService.queryWashedClothesCount(clothesOrderId);
			result.setStatus(AjaxResult.Status.success);
			result.setMessage("条码为："+childBarCode+"的衣服已标记为已派送");
			if(allSend==clothesNum){
				result.setData(true);
			}else{
				ClothesOrder clothes = clothesOrderService.queryClothesOrderById(clothesOrderId);
				String registId = employeeService.queryRegistIdByOrderId(clothesOrderId);
				WashClothes washclothes = washClothesService.queryByChildBarCode(childBarCode);
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("orderNumber", clothes.getOrderNumber());
				extras.put("clothesId", String.valueOf(washclothes.getId()));
				extras.put("type", "2");
				JPushUtils.sendPushToAssistant("", "订单为["+clothes.getOrderNumber()+"]中的["+washclothes.getClothesName()+"]已经洗涤完毕，请及时处理", null, registId, extras);
			}
		}else{
			result.setStatus(AjaxResult.Status.error);
			result.setMessage("标记条码为："+childBarCode+"的衣服失败");
		}
		return result;
	}
	/**
	 * 
	* @Title: finishSingleClothes 
	* @Description: 单件衣服到完成状态
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping("finishSingleClothes")
	@ResponseBody
	public AjaxResult finishSingleClothes(Long clothesOrderId,String childBarCode, HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		boolean isUpdate = washClothesService.updateWashStatus(clothesOrderId, childBarCode,5);
		if(isUpdate){
			long allFinished = washClothesService.queryWashedClothes(clothesOrderId,5);
			long clothesNum = washClothesService.queryWashedClothesCount(clothesOrderId);
			result.setStatus(AjaxResult.Status.success);
			result.setMessage("条码为："+childBarCode+"的衣服已标记为已完成");
			if(allFinished==clothesNum){
				result.setData(true);
			}
		}else{
			result.setStatus(AjaxResult.Status.error);
			result.setMessage("标记条码为："+childBarCode+"的衣服失败");
		}
		return result;
	}
	
	@RequestMapping("invalid")
	@ResponseBody
	public AjaxResult invalid(long id){
		AjaxResult result = new AjaxResult(com.aplus.lk.clothes.bean.AjaxResult.Status.error, "删除失败", null);
		ClothesOrder order = clothesOrderService.queryClothesOrderById(id);
		if(order == null || order.getOrderStatus() != OrderStatus.appointments.ordinal()){
			result.setMessage("该订单不处于预约状态，取消失败");
		}else{
			clothesOrderService.updateStatusById(order.getId(), Status.invalid.ordinal());
			result.setMessage("取消成功");
			result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		}
		return result;
	}
	
	@RequestMapping("processRemark")
	@ResponseBody
	public AjaxResult processRemark(long orderId,String orderNumber,String phone,
			String memberId, String pushContent,String imageUrl){
		AjaxResult result = new AjaxResult(com.aplus.lk.clothes.bean.AjaxResult.Status.error,"回复失败", null);
		if(StringUtils.isEmpty(pushContent)){
			result.setMessage("请输入回复内容");
			return result;
		}
		Member member = memberService.queryById(memberId);
		String registrationID = member.getRegistrationID();
		if(StringUtils.isEmpty(registrationID)){
			result.setMessage("用户推送注册ID不存在");
			return result;
		}
		try {
			MessageRequest message=new MessageRequest();
			message.setMessageType("1");
			message.setTitle("您的洗衣订单留言回复");
			Admin admin = adminService.getLoginAdmin();
			message.setPublisher(admin.getName());
			MessageContentVo[] contents=new MessageContentVo[2];
			
			if(StringUtils.isNotBlank(imageUrl)){
				contents[0]=new MessageContentVo(imageUrl, 1, 1);
				contents[1]=new MessageContentVo(pushContent, 0, 2);
			}else{
				contents[0]=new MessageContentVo(pushContent, 0, 1);
			}
			message.setContents(contents);
			message.setMobile(phone);
			message.setSendObject("3");
			messageSerivce.save(message);
			clothesOrderService.updateIsProcessOfRemarkById(orderId, true);
			result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		} catch (Exception e) {
			result.setMessage("回复失败");
		}
		
		/*Map<String, String> extras = new HashMap<String, String>();
		extras.put("orderId", String.valueOf(orderId));
		
		PushResult pushResult = JPushUtils.sendPushToClient("乐E下", pushContent, null, registrationID, extras);
		if(pushResult.getStatus() == PushResult.SUCCESS){
			//clothesOrderService.updateIsProcessOfRemarkById(orderId, true);
			result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		}else{
			
		}*/
		return result;
	}
	 
	
	/**
	 * 
	 * @Title: checkChildBarCode 
	 * @Description: 检查字条形码是否存在
	 * @return boolean
	 * @throws
	 */
	@RequestMapping("checkChildBarCode")
	@ResponseBody
	public boolean checkChildBarCode(String childBarCode){
		return washClothesService.queryByChildBarCode(childBarCode) == null;
	}
	/**
	 * 
	* @Title: queryLatelyOrder 
	* @Description: 查询用户订单
	* @param pager
	* @param phoneNumber
	* @param request
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping("queryClothesOrder")
	public String queryClothesOrder(Pager pager,String phoneNumber,HttpServletRequest request){
		queryList(pager, null, null, null, null, null, phoneNumber, request);
		return "clothes_order_list";
	}
	  /**
	   * 
	  * @Title: telOrderView 
	  * @Description: 跳转到电话下单录入页面
	  * @return
	  * @return String    
	  * @throws
	   */
	@RequestMapping("telOrderView")
	public String telOrderView(HttpServletRequest request){
		request.setAttribute("addressCenterList", addressCenterService.queryAll());
		return "clothes_order_input_tel";
	}
	/**
	 * 
	* @Title: telOrder 
	* @Description: 电话下单
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping(value="telOrder",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult telOrder(TelOrderRequest telOrderRequest){
		try{
			String memberId = getMemberId(telOrderRequest.getPhone(), telOrderRequest.getName());
			long addressId = createClothesAddress(telOrderRequest, memberId);
			/*创建订单*/
			ClothesOrder order = makeClothesOrderParams(addressId, telOrderRequest.getEmployeeId(), memberId, telOrderRequest.getPhone());
			clothesOrderService.createTelOrder(order);
			/*查询小工，推送消息*/
			Employee employee = employeeService.queryById(telOrderRequest.getEmployeeId());
			Map<String, String> extras = new HashMap<String, String>();
			extras.put("orderNumber", order.getOrderNumber());
			extras.put("type", "0");
			JPushUtils.sendPushToAssistant("乐E下", "你好:"+employee.getRealName()+",洗衣订单为:"+order.getOrderNumber(), null, employee.getRegistrationID(),extras);
		}catch(Exception e){
			logger.error("电话下单失败",e);
			return new AjaxResult(AjaxResult.Status.error, "下单失败");
		}
		return new AjaxResult(AjaxResult.Status.success, "下单成功");
	}
	
	@RequestMapping("changeOrderEmployeeView")
	public String changeOrderEmployeeView(String clothesOrderId,String employeeId,
			String address,String changeType,long clothesAddressId,HttpServletRequest request){
		request.setAttribute("orderId", clothesOrderId);
		request.setAttribute("employeeId", employeeId);
		request.setAttribute("address", address);
		request.setAttribute("changeType", changeType);
		request.setAttribute("clothesAddressId", clothesAddressId);
		request.setAttribute("addressCenterList", addressCenterService.queryAll());
		return "clothes_order_reassignment_input";
	}
	/**
	 * 
	* @Title: changeOrderEmployee
	* @Description: 改派
	* @param orderId
	* @param request
	* @return
	* @return String    
	* @throws
	 */
	@RequestMapping("changeOrderEmployee")
	@ResponseBody
	public AjaxResult changeOrderEmployee(ReassignmentOrderRequest orderRequest,HttpServletRequest request){
		try {
		clothesOrderService.changeOrderEmployee(Long.valueOf(orderRequest.getOrderId()), orderRequest.getToEmployeeId());
		ClothesAddress clothesAddress = clothesAddressService.queryClothesAddress(orderRequest.getClothesAddressId());
		String address = orderRequest.getDeliveryAddress();
		if(orderRequest.getChangeType()==0){
			clothesAddress.setDeliveryAddress(address);
		}else if(orderRequest.getChangeType()==1){
			clothesAddress.setSendAddress(address);
		}
		clothesAddress.setLatitude(orderRequest.getLatitude());
		clothesAddress.setLongitude(orderRequest.getLongitude());
		clothesAddressService.updateAddress(clothesAddress);
		
		ReassignmentRecord record = new ReassignmentRecord();
		record.setFromEmployeeId(orderRequest.getFromEmployeeId());
		Admin admin = adminService.getLoginAdmin();
		record.setOperator(admin.getName());
		record.setOrderId(orderRequest.getOrderId());
		record.setToEmployeeId(orderRequest.getToEmployeeId());
		record.setChangeType(orderRequest.getChangeType());
		clothesOrderService.createRecord(record);
		} catch (Exception e) {
			logger.error("订单改派失败",e);
			return new AjaxResult(AjaxResult.Status.error, "订单改派失败");
		}
		return new AjaxResult(AjaxResult.Status.success, "订单改派成功");
	}
	
	@RequestMapping("/brand/washHistory")
	@ResponseBody
	public AjaxResult queryBrandWashHistory(String phone,String clothesName,String brand){
		AjaxResult result=new AjaxResult();
		 try {
			 clothesName=new String(clothesName.getBytes("iso8859-1"),"utf-8");
			 brand=new String(brand.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		result.setData(washClothesService.queryClothesByPhoneBrand(phone, clothesName, brand));
		result.setMessage("");
		result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		return result;
	}
	
	@RequestMapping("/clothesPrice")
	@ResponseBody
	public AjaxResult queryOrderPrice(Long orderId,HttpServletRequest request){
		AjaxResult result=new AjaxResult();
		result.setData(clothesOrderService.queryClothesByOrderId(orderId));
		result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		return result;
	}
	@RequestMapping("/modifyPrice")
	@ResponseBody
	public AjaxResult modifyClothesPrice(ClothesPriceParams clothesPriceParams,HttpServletRequest request){
		AjaxResult result=new AjaxResult();
		clothesOrderService.updateClothesPrice(clothesPriceParams);
		result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		return result;
	}
	
	@RequestMapping("/queryMemberAccount")
	@ResponseBody
	public AjaxResult queryMemberAccount(Long orderId){
		AjaxResult result=new AjaxResult();
		ClothesOrder order = clothesOrderService.queryClothesOrderById(orderId);
		Member member = memberService.queryById(order.getUserId());
		result.setData(member);
		result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		return result;
	}
	@RequestMapping(value="/withhold",method=RequestMethod.POST)
	public AjaxResult withhod(int method,Long orderId){
		AjaxResult result=new AjaxResult();
		ClothesOrder order = clothesOrderService.queryClothesOrderById(orderId);
		boolean msg=false;
		Member member =null;
		if(order!=null){
			String userId = order.getUserId();
			member = memberService.queryById(userId);
			msg = clothesOrderService.withhod(method, order,member);
		}
		final Member fmember=member;
		if(msg){
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					MessageRequest message = new MessageRequest();
					message.setPublisher("系统管理员");
					message.setMessageType("1");
					message.setTitle("洗衣订单支付通知");
					message.setSendObject("2");
					ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("registrationID",fmember.getRegistrationID());
					map.put("id", fmember.getId());
					list.add(map);
					message.setMemberIdAndRegisterId(list);
					MessageContentVo[] contents=new MessageContentVo[1];
					contents[0]=new MessageContentVo("<p>尊敬的用户:</p><p style=\"text-indent: 2em;\">您好，为了不影响您的正常下单，未支付订单金额已从您的余额/积分中扣除。</p><p style=\"text-indent: 2em;\">如您有任何疑问，请拨打我们的客服电话：4008323325。</p><p style=\"text-indent: 2em;\">祝您生活愉快！</p>", 0, 0);
					message.setContents(contents);
					messageSerivce.save(message);
				}
			});
			executor.shutdown();
			result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		}else{
			result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.error);
		}
		
		return result;
	}
	
	/**
	 * 组装洗衣订单
	 * @param addressUserInfo
	 * @param feeEmployee
	 */
	private ClothesOrder makeClothesOrderParams(Long addressUserInfoId,
			String feeEmployeeId, String userId, String phoneNumber) {
		ClothesOrder clothesOrderPO = new ClothesOrder();
		clothesOrderPO.setClothesAddressId(addressUserInfoId);
		clothesOrderPO.setEmployeeId(feeEmployeeId);
		clothesOrderPO.setUserId(userId);
		clothesOrderPO.setPhoneNumber(phoneNumber);
		clothesOrderPO.setPayStatus(0); //未支付
		clothesOrderPO.setStatus(1);    //未处理
		clothesOrderPO.setOrderStatus(0);//该订单收衣小哥未处理
		clothesOrderPO.setOrderNumber(OrderNumberUtil.getOrderNo());
		clothesOrderPO.setPrice(new BigDecimal(0));
		
		return clothesOrderPO;
	}
	/**
	 * 
	* @Title: getMemberId 
	* @Description: 获取用户id,如果不存在则创建
	* @param phone
	* @param name
	* @return
	* @return String    
	* @throws
	 */
	private String getMemberId(String phone,String name){
		/*判断用户是否为app用户*/
		Member member = memberService.queryByPhoneNo(phone);
		String memberId = null;
		if(member==null){
			memberId=UUIDUtils.getUUID();
			member=new Member();
			member.setId(memberId);
			member.setUsername(phone);// 用户名(默认)
			member.setMobile(phone);
			member.setScore(50); //积分
			member.setIsAccountEnabled(true); //是否启用
			member.setIsAccountLocked(false);//是否锁定
			member.setLoginFailureCount(0); //登录失败次数
			member.setRegisterIp("120.26.116.84"); //注册IP
			member.setLoginDate(new Date());//最后登录日期
			member.setCreateDate(new Date());
			member.setModifyDate(new Date());
			member.setDeposit(new BigDecimal("0.00"));
			memberService.createMember(member);
		}else{
			memberId=member.getId();
		}
		return memberId;
	}

	private long createClothesAddress(TelOrderRequest telOrderRequest,String memberId){
		/*创建洗衣地址*/
		ClothesAddress address = new ClothesAddress();
		address.setDeliveryAddress(telOrderRequest.getDeliveryAddress());
		address.setUserId(memberId);
		address.setSendAddress(telOrderRequest.getDeliveryAddress());
		address.setPhoneNumber(telOrderRequest.getPhone());
		address.setLongitude(telOrderRequest.getLongitude());
		address.setLatitude(telOrderRequest.getLatitude());
		String name = telOrderRequest.getName();
		name=StringUtils.isBlank(name)?"匿名":name;
		address.setUsername(name);
		address.setAddressCenterId(Long.parseLong(telOrderRequest.getAddressCenterId()));
		address.setIsAccord(true);
		clothesAddressService.save(address);
		return address.getId();	
	}
	
	@RequestMapping("/modifyFactoryRemark")
	@ResponseBody
	public AjaxResult modifyFactoryRemark(String factoryRemark,String orderNumber){
		AjaxResult result=new AjaxResult();
		if(clothesOrderService.updateOrderFactoryRemark(factoryRemark, orderNumber)==1)
		result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		else result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.error);
		return result;
	}
	
	@RequestMapping("/modifyFactoryMatter")
	@ResponseBody
	public AjaxResult modifyFactoryMatter(String orderNumber,String childBarCode,String factoryMatter){
		AjaxResult result=new AjaxResult();
		if(clothesOrderService.updateOrderFactoryMatter(orderNumber, childBarCode,factoryMatter)==1)
		result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		else result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.error);
		return result;
	}
}
