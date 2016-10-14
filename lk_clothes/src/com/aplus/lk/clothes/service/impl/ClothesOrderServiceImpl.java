package com.aplus.lk.clothes.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aplus.lk.clothes.bean.ClothesPriceParams;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.WashClothesModle;
import com.aplus.lk.clothes.entity.ClothesAddress;
import com.aplus.lk.clothes.entity.ClothesOrder;
import com.aplus.lk.clothes.entity.ClothesOrder.OrderStatus;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.Member;
import com.aplus.lk.clothes.entity.OutFactoryClothes;
import com.aplus.lk.clothes.entity.ReassignmentRecord;
import com.aplus.lk.clothes.entity.WashClothes;
import com.aplus.lk.clothes.entity.WashStatus;
import com.aplus.lk.clothes.mapper.ClothesAddressMapper;
import com.aplus.lk.clothes.mapper.ClothesOrderMapper;
import com.aplus.lk.clothes.mapper.EmployeeMapper;
import com.aplus.lk.clothes.mapper.MemberMapper;
import com.aplus.lk.clothes.mapper.WashClothesMapper;
import com.aplus.lk.clothes.mapper.WashStatusMapper;
import com.aplus.lk.clothes.service.ClothesOrderService;
import com.aplus.lk.clothes.utils.FileUtil;
import com.aplus.lk.clothes.utils.JPushUtils;

@Service
public class ClothesOrderServiceImpl implements ClothesOrderService {
	
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ClothesOrderMapper clothesOrderMapper;
	@Autowired
	private ClothesAddressMapper clothesAddressMapper;
	@Autowired
	private WashClothesMapper washClothesMapper;
	@Autowired
	private WashStatusMapper washStatusMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Override
	public List<ClothesOrder> queryAll(String barCode, String orderNumber,
			Integer status, Integer orderStatus, Integer payStatus,
			int firstResult, int maxResult) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("barCode", barCode);
		parameterMap.put("orderNumber", orderNumber);
		parameterMap.put("status", status);
		parameterMap.put("orderStatus", orderStatus);
		parameterMap.put("payStatus", payStatus);
		parameterMap.put("firstResult", firstResult);
		parameterMap.put("maxResult", maxResult);
		return clothesOrderMapper.queryAll(parameterMap);
	}

	@Override
	public Pager queryPager(Pager pager, String barCode, String orderNumber,
			Integer status, Integer orderStatus, Integer payStatus,String phoneNumber) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("barCode", barCode);
		parameterMap.put("orderNumber", orderNumber);
		parameterMap.put("status", status);
		parameterMap.put("orderStatus", orderStatus);
		parameterMap.put("payStatus", payStatus);
		parameterMap.put("phoneNumber", phoneNumber);
		parameterMap.put("firstResult",
				(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());

		pager.setDataList(clothesOrderMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(clothesOrderMapper.queryCount(parameterMap));

		return pager;
	}

	@Override
	public ClothesOrder queryClothesOrderById(long id) {
		ClothesOrder order = clothesOrderMapper.queryClothesOrderById(id);
		return order;
	}

	@Override
	public ClothesOrder queryClothesOrderByBarCode(String barCode, HttpServletRequest request) {
		ClothesOrder order = clothesOrderMapper
				.queryClothesOrderByBarCode(barCode);
		if (order == null) {
			return null;
		}
		if (order != null) {
			//生成子条形码
			List<WashClothes> listWashClothes = washClothesMapper.queryWashClothesByOrderId(order.getId());
			ClothesAddress address = clothesAddressMapper.queryClothesAddressById(order.getClothesAddressId());
			Employee employee = employeeMapper.queryById(order.getEmployeeId());
			if(address != null){
				order.setClothesAddress(address);
			}
			if(employee != null){
				order.setEmployee(employee);
			}
			if(listWashClothes != null && listWashClothes.size() > 0){
				String contents = null;
				for(int i = 0 ; i < listWashClothes.size() ; i ++ ){
					if(StringUtils.isBlank(listWashClothes.get(i).getChildBarCode())){
						contents = makeChildCode(employee.getUsername(), listWashClothes.size(), i);
						listWashClothes.get(i).setChildBarCode(contents);
						washClothesMapper.update(listWashClothes.get(i));
					}
				}
			}
			//生成子条形码结束
			order.setWashClothesSet(listWashClothes);
			order.setWashStatusSet(washStatusMapper.queryWashStatusByOrderId(order.getId()));
		}
		return order;
	}

	@Transactional
	@Override
	public boolean collectToFactory(WashClothesModle washClothesModle ,MultipartFile imageUpload,
			long clothesOrderId, Boolean isProccessed, HttpServletRequest request) {
		try {
			
			List<WashClothes> washClothesList = washClothesModle.getWashClothesList();
			
			ClothesOrder clothesOrder = clothesOrderMapper.queryClothesOrderById(clothesOrderId);
			if(clothesOrder == null){
				request.setAttribute("status", "error");
				request.setAttribute("message", "订单不存在");
				return false;
			}
			if(clothesOrder.getOrderStatus() != OrderStatus.collect.ordinal()){
				request.setAttribute("status", "error");
				request.setAttribute("message", "该订单状态不能更新到工厂状态");
				return false;
			}
			
			WashStatus washStatus = new WashStatus();
			washStatus.setClothesOrderStatus(ClothesOrder.OrderStatus.factory.ordinal());
			washStatus.setClothesOrderId(clothesOrderId);
			washStatus.setClothesImageUrl(FileUtil.copyImage(imageUpload, request, "upload/status"));
			// 维护洗衣状态
			Date createTime = new Date();
			washStatus.setCreateTime(createTime);
			washStatus.setUpdateTime(createTime);
			if(isProccessed){
				washStatus.setDescription("您的衣服已经分拣完毕，正在洗涤中.您的留言已经收到，我们会认真对待处理.");
			}else{
				washStatus.setDescription("您的衣服已经分拣完毕，正在洗涤中.");
			}
			washStatusMapper.save(washStatus);

			// 更新衣服条形码
			for (WashClothes clothes : washClothesList) {
				clothes.setUpdateTime(createTime);
				washClothesMapper.update(clothes);
			}

			// 更新洗衣订单状态
			updateOrderStatus(clothesOrderId, OrderStatus.factory.ordinal(),isProccessed,null);
			request.setAttribute("status", "success");
			request.setAttribute("message", "更新成功");
			return true;
		} catch (Exception e) {
			logger.error("洗衣订单状态更新错误",e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int updateOrderStatus(long orderId, int newOrderStatus, Boolean isProcessOfRemark,String factoryRemark) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("updateTime", new Date());
		parameterMap.put("id", orderId);
		parameterMap.put("orderStatus", newOrderStatus);
		parameterMap.put("isProcessedOfRemark", isProcessOfRemark);
		parameterMap.put("factoryRemark", factoryRemark);
		return clothesOrderMapper.updateOrderStatus(parameterMap);
	}

	@Override
	public ClothesOrder queryClothesOrderAndClothesStatusAddressById(long id) {
		ClothesOrder order = clothesOrderMapper.queryClothesOrderById(id);
		order.setWashClothesSet(washClothesMapper.queryWashClothesByOrderId(id));
		order.setWashStatusSet(washStatusMapper.queryWashStatusByOrderId(id));
		order.setClothesAddress(clothesAddressMapper.queryClothesAddressById(order.getClothesAddressId()));
		order.setEmployee(employeeMapper.queryById(order.getEmployeeId()));
		return order;
	}
	@Override
	public boolean updateOrderStatus(long orderId, OrderStatus oldOrderStatus, 
				OrderStatus newOrderStatus,String description,
				MultipartFile imageUpload,String operator,
				HttpServletRequest request){
		try {
			ClothesOrder order = clothesOrderMapper.queryClothesOrderById(orderId);
			if(order == null){
				if(request!=null){
				request.setAttribute("status", "error");
				request.setAttribute("message", "订单不存在");
				}
				return false;
			}
			
			if(order.getOrderStatus() != oldOrderStatus.ordinal()){
				if(request!=null){
			 
				request.setAttribute("status", "error");
				request.setAttribute("message", "该订单状态不能更新到"+getStatusStr(newOrderStatus)+"状态");
				}
				return false;
			}
			
			WashStatus washStatus = new WashStatus();
			washStatus.setClothesOrderStatus(newOrderStatus.ordinal());
			washStatus.setClothesOrderId(orderId);
			washStatus.setOperator(operator);
			// 维护洗衣状态
			Date createTime = new Date();
			washStatus.setCreateTime(createTime);
			washStatus.setUpdateTime(createTime);
			washStatus.setDescription(description);
			if(imageUpload!=null) washStatus.setClothesImageUrl(FileUtil.copyImage(imageUpload, request, "upload/status"));
			washStatusMapper.save(washStatus);
			// 更新洗衣订单状态
			updateOrderStatus(orderId, newOrderStatus.ordinal(), null,null);
			//  如果为  已洗涤到派送中状态  则推送消息给小工
			if(newOrderStatus == OrderStatus.send){
				Employee employee = employeeMapper.queryById(order.getEmployeeId());
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("orderNumber", order.getOrderNumber());
				extras.put("clothesId", "");
				extras.put("type", "1");
				JPushUtils.sendPushToAssistant("", "订单["+order.getOrderNumber()+"]已经洗涤完毕，请及时处理", null, employee.getRegistrationID(), extras);
			}
			if(request!=null){
				request.setAttribute("status", "success");
				request.setAttribute("message", "操作成功");
			}
			
			return true;
		} catch (IllegalStateException e) {
			logger.error("洗衣订单状态更新错误", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("文件上传错误", e);
			e.printStackTrace();
		}
		return false;
	}
	
	public String getStatusStr(OrderStatus orderStatus){
		String statusStr = "";
		switch (orderStatus) {
		case appointments:
			statusStr = "预约";
			break;
		case collect:
			statusStr = "已收衣";
			break;
		case factory:
			statusStr = "已分拣";
			break;
		case washing:
			statusStr = "已洗涤";
			break;
		case send:
			statusStr = "派送中";
			break;
		case completed:
			statusStr = "已完成";
			break;
		default:
			break;
		}
		return statusStr;
	}

	@Override
	public void updateStatusById(long orderId, int newStatus) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", orderId);
		parameterMap.put("status", newStatus);
		clothesOrderMapper.updateStatusById(parameterMap);
	}

	@Override
	public void updateIsProcessOfRemarkById(long orderId,
			Boolean isProcessedOfRemark) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", orderId);
		parameterMap.put("isProcessedOfRemark", isProcessedOfRemark);
		clothesOrderMapper.updateIsProcessOfRemarkById(parameterMap);
	}

	@Override
	public ClothesOrder queryClothesOrderByChildBarCode(String childBarCode) {
		ClothesOrder order = clothesOrderMapper.queryClothesOrderByChildBarCode(childBarCode);
		if (order != null) {
			//生成子条形码
			List<WashClothes> listWashClothes = washClothesMapper.queryWashClothesByOrderId(order.getId());
//			if(listWashClothes != null && listWashClothes.size() > 0){
//				for(int i = 0 ; i < listWashClothes.size() ; i ++ ){
//					listWashClothes.get(i).setChildBarCode(makeChildCode(order.getBarCode(), listWashClothes.size(), i));
//				}
//			}
			//生成子条形码结束
			order.setWashClothesSet(listWashClothes);
			List<WashStatus> washStatus = washStatusMapper.queryWashStatusByOrderId(order.getId());
			order.setWashStatusSet(washStatus);
			
			if(order.getOrderStatus()==OrderStatus.washing.ordinal()){
				order.setEmployee(employeeMapper.queryById(order.getEmployeeId()));
				order.setClothesAddress(clothesAddressMapper.queryClothesAddressById(order.getClothesAddressId()));
			}
		 
		}
		return order;
	}
	
	@Override
	public ClothesOrder queryWashClothesByChildBarCode(String childBarCode) {
		ClothesOrder order = clothesOrderMapper.queryClothesOrderByChildBarCode(childBarCode);
		if (order != null) {
		//	HashSet<WashClothes> washClothes = washClothesMapper.queryByChildBarCode(childBarCode);
			List<WashClothes> washClothes = new LinkedList<WashClothes>();
			washClothes.add(washClothesMapper.queryByChildBarCode(childBarCode));
			order.setWashClothesSet(washClothes);
			order.setWashStatusSet(washStatusMapper.queryWashStatusByOrderId(order.getId()));
		}
		return order;
	}
	/**
	 * 条形码生成
	 * @param barCode
	 * @param size
	 * @param i
	 * @return
	 */
	private String makeChildCode(String employeeUsername, int size, int i) {
		return RandomStringUtils.randomNumeric(8);
	}

	@Override
	public boolean collectToFactory(WashClothesModle washClothesModle,
			String savePath, long clothesOrderId, boolean isProccessed,String factoryRemark,
			String operator,String outImageUrl,HttpServletRequest request) {
		try {
			
			List<WashClothes> washClothesList = washClothesModle.getWashClothesList();
			
			ClothesOrder clothesOrder = clothesOrderMapper.queryClothesOrderById(clothesOrderId);
			if(clothesOrder == null){
				request.setAttribute("status", "error");
				request.setAttribute("message", "订单不存在");
				return false;
			}
			if(clothesOrder.getOrderStatus() != OrderStatus.collect.ordinal()){
				request.setAttribute("status", "error");
				request.setAttribute("message", "该订单状态不能更新到工厂状态");
				return false;
			}
			
			WashStatus washStatus = new WashStatus();
			washStatus.setClothesOrderStatus(ClothesOrder.OrderStatus.factory.ordinal());
			washStatus.setClothesOrderId(clothesOrderId);
			washStatus.setClothesImageUrl(savePath);
			washStatus.setOperator(operator);
			washStatus.setOutImageUrl(outImageUrl);
			// 维护洗衣状态
			Date createTime = new Date();
			washStatus.setCreateTime(createTime);
			washStatus.setUpdateTime(createTime);
			
			if(clothesOrder.getIsProcessedOfRemark()){
				washStatus.setDescription("您的衣物已收到，正在分拣中..您的留言已经收到，我们会认真对待处理");
			}else{
				washStatus.setDescription("您的衣物已收到，正在分拣中");
			}
			washStatusMapper.save(washStatus);

			// 更新衣服条形码、品牌、颜色、瑕疵、说明
			for (WashClothes clothes : washClothesList) {
				clothes.setUpdateTime(createTime);
				if("on".equals(clothes.getIsOut())){
					clothes.setIsOut("1");
				}else{
					clothes.setIsOut("0");
				}
				washClothesMapper.update(clothes);
			}

			// 更新洗衣订单状态
			updateOrderStatus(clothesOrderId, OrderStatus.factory.ordinal(),isProccessed, factoryRemark);
			request.setAttribute("status", "success");
			request.setAttribute("message", "更新成功");
			return true;
		} catch (Exception e) {
			logger.error("洗衣订单状态更新错误",e);
			e.printStackTrace();
		}
		return false;
	}  
	@Override
	public void createTelOrder(ClothesOrder order) {
		if(order!=null){
			clothesOrderMapper.createTelOrder(order);
		}
	}

	@Override
	public void createRecord(ReassignmentRecord record) {
		clothesOrderMapper.createRecord(record);
	}

	@Override
	public void changeOrderEmployee(long orderId, String employeeId) {
		Employee employee = employeeMapper.queryById(employeeId);
		ClothesOrder clothesOrder = clothesOrderMapper.queryClothesOrderById(orderId);
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("orderNumber", clothesOrder.getOrderNumber());
		extras.put("type", "0");
		JPushUtils.sendPushToAssistant("乐E下", "你好:"+employee.getRealName()+",洗衣订单为:"+clothesOrder.getOrderNumber(), null, employee.getRegistrationID(),extras);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", orderId);
		parameterMap.put("employeeId", employeeId);
		clothesOrderMapper.updateEmployeeIdById(parameterMap);
	}

	@Override
	public Pager queryBackWashList(Pager pager, String phoneNumber) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("phoneNumber", phoneNumber);
		parameterMap.put("firstResult",
				(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());

		pager.setDataList(clothesOrderMapper.queryBackWashList(parameterMap));
		pager.setTotalRecords(clothesOrderMapper.queryBackWashListCount(parameterMap));
		return pager;
	}

	@Override
	public Pager queryHistoryOrder(Pager pager, Long orderId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("orderId", orderId);
		parameterMap.put("firstResult",(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		pager.setDataList(clothesOrderMapper.queryHistoryOrder(parameterMap));
		pager.setTotalRecords(clothesOrderMapper.queryHistoryOrderCount(parameterMap));
		return pager;
	}


	@Override
	public List<OutFactoryClothes> queryBarCode(String barCode) {
        return clothesOrderMapper.queryBarCode(barCode);
	}

	@Override
	public List<WashClothes> queryClothesByOrderId(Long orderId) {
		return washClothesMapper.queryWashClothesByOrderId(orderId);
	}





	@Override
	public void updateClothesPrice(ClothesPriceParams clothesPriceParams) {
		Long orderId = clothesPriceParams.getOrderId();
		if(orderId!=null){
		
			washClothesMapper.modifyClothesPrice(clothesPriceParams.getWashClothesList());
			double totalPrice = washClothesMapper.queryClothesTotalPrice(orderId);
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("orderId", orderId);
			param.put("totalPrice", totalPrice);
			clothesOrderMapper.modifyOrderPrice(param);
		}
		
	}

	@Override
	public void queryWaitFactoryList(HttpServletRequest request,String phoneNumber,String barCode) {
		// TODO Auto-generated method stub
		   Map<String,Object> param=new HashMap<String, Object>();
		   param.put("phoneNumber", phoneNumber);
		   param.put("barCode", barCode);
		   List<OutFactoryClothes> clothesOrder = clothesOrderMapper.queryWashList(param);
	       for(int i=0;i<clothesOrder.size();++i){
	    	   clothesOrder.get(i).setClothesList(clothesOrderMapper.queryClothesDetail(clothesOrder.get(i).getOrderNumber()));
	       }
	       request.setAttribute("clothes",clothesOrder);
	}

	@Override
	public boolean updateOrderPrintStatus(Long orderId) {
		// TODO Auto-generated method stub
		clothesOrderMapper.updateOrderPrintStatus(orderId);
		return true;
	}

	@Override
	public int queryOrderCountByPhoneNum(String phoneNumber) {
		return clothesOrderMapper.queryOrderCountByPhoneNum(phoneNumber);
	}
	
	@Override
	public int queryCollectCount(){
		List<Integer> orderIdList = clothesOrderMapper.queryCollectOrderCount();
		int count = 0;
		for(Integer orderId:orderIdList){
			count += clothesOrderMapper.queryClothesCount(orderId.intValue());
					
		}
		return count;
	}

	@Override
	public Map<String,Object> queryCommunityCount() {
		// TODO Auto-generated method stub
		List<Integer> orderIdList = clothesOrderMapper.queryCommunityOrderCount();
		int count = 0;
		int clothes = 0;
		int shoes = 0;
		int darning = 0;
		Map<String,Object> parameter = new HashMap<String, Object>();
		for(Integer orderId:orderIdList){
			parameter.put("orderId", orderId);
			count += clothesOrderMapper.queryClothesCount(orderId.intValue());
			parameter.put("clothes_name", "皮");
			clothes += 	clothesOrderMapper.querySortCount(parameter);
			parameter.put("clothes_name", "鞋");
			shoes += 	clothesOrderMapper.querySortCount(parameter);
			parameter.put("clothes_name", "织补");
			darning += 	clothesOrderMapper.querySortCount(parameter);
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("count",count);
		result.put("clothes",clothes);
		result.put("shoes",shoes);
		result.put("darning",darning);
		return result;
		
	}

	@Override
	public int updateOrderFactoryRemark(String factoryRemark,
			String orderNumber) {
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("factoryRemark", factoryRemark);
		parameter.put("orderNumber", orderNumber);
		return clothesOrderMapper.updateFactoryRemark(parameter);
	}

	@Override
	public int updateOrderFactoryMatter(String orderNumber,
			String childBarCode, String factoryMatter) {
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("childBarCode", childBarCode);
		parameter.put("orderNumber", orderNumber);
		parameter.put("factoryMatter", factoryMatter);
		return clothesOrderMapper.updateFactoryMatter(parameter);
	}
	
	@Override
	public boolean withhod(int method, ClothesOrder order,Member member) {
		try {
			if(order!=null){
				BigDecimal price = order.getPrice();
				if(method==1){//余额代扣
					BigDecimal deposit = member.getDeposit();
					if(deposit.compareTo(price)!=-1){
						member.setDeposit(deposit.subtract(price));
						order.setPayType("4");
						clothesOrderMapper.finishOrder(order);
						memberMapper.updateAccount(member);
						return true;
					}else{
						return false;
					}
				}else if(method==2){//积分代扣
					if(order.getCardId()!=null){
						return false;
					}
					Integer score = member.getScore();
					BigDecimal bigdecimalScore = new BigDecimal(score/10);
					if(bigdecimalScore.compareTo(price)!=-1 ){
						int after = price.multiply(BigDecimal.TEN).setScale(0,BigDecimal.ROUND_UP).intValue();
						member.setScore(score-after);
						order.setPayType("3");
						clothesOrderMapper.finishOrder(order);
						memberMapper.updateAccount(member);
						return true;
					}else{
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
