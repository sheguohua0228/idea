package com.aplus.lk.clothes.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aplus.lk.clothes.bean.AjaxResult;
import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.bean.ReassignmentOrderRequest;

import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.ManagerOrder;

import com.aplus.lk.clothes.service.AddressCenterService;
import com.aplus.lk.clothes.service.AdminService;
import com.aplus.lk.clothes.service.ManagerOrderService;

@Controller
@RequestMapping("managerOrder")
public class ManagerController {
   
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private ManagerOrderService managerOrderService;
	@Autowired
	private AddressCenterService addressCenterService;
	
	
	private Logger logger = Logger.getLogger(getClass());
	public void queryList(Pager pager, String orderNumber, 
			Integer status , Integer orderStatus, Integer payStatus,String phoneNumber
			,HttpServletRequest request){
		pager = managerOrderService.queryPager(pager, orderNumber,status, orderStatus, payStatus, phoneNumber);
		
		request.setAttribute("pager", pager);
		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("status", status);
		request.setAttribute("orderStatus", orderStatus);
		request.setAttribute("payStatus", payStatus);
		request.setAttribute("phoneNumber", phoneNumber);
		
	}
	//预约订单
	@RequestMapping("appointment")
	public String queryAppointmentOrder(Pager pager, String phoneNumber, String orderNumber, 
			Integer payStatus ,HttpServletRequest request){
		queryList(pager,orderNumber, null, 0, payStatus, phoneNumber, request);
		
		return "manager_order_appointments_list";
	}
   //进行中订单
	@RequestMapping("processing")
    public String queryProcessingOrder(Pager pager, String phoneNumber, String orderNumber, 
			Integer payStatus ,HttpServletRequest request){
		queryList(pager,orderNumber, null, 1, payStatus, phoneNumber, request);
	    return "manager_order_processing_list";
	}
	//待支付订单
		@RequestMapping("waitPay")
	    public String queryWaitPayOrder(Pager pager, String phoneNumber, String orderNumber, 
				Integer payStatus ,HttpServletRequest request){
	    	queryList(pager,orderNumber, null, 2, payStatus, phoneNumber, request);
	    	return "manager_order_waitpay_list";
	    }
	//完成订单
	@RequestMapping("finish")
    public String queryFinishOrder(Pager pager, String phoneNumber, String orderNumber, 
			Integer payStatus ,HttpServletRequest request){
    	queryList(pager,orderNumber, null, 3, payStatus, phoneNumber, request);
    	return "manager_order_finish_list";
    }
	//已取消订单
    @RequestMapping("cancel")
    public String queryCancelOrder(Pager pager, String phoneNumber, String orderNumber, 
			Integer payStatus ,HttpServletRequest request){
    	queryList(pager,orderNumber, null, 9, payStatus, phoneNumber, request);
    	return "manager_order_cancel_list";
    }
	@RequestMapping(value="view",method = RequestMethod.GET)
	public String queryDetailById(long id, HttpServletRequest request){
		ManagerOrder order = managerOrderService.queryManagerOrderAndAddressById(id);
		Employee employee=managerOrderService.queryEmployeebyID(order.getEmployeeId());
		request.setAttribute("managerOrder", order);
        request.setAttribute("employee", employee);
		System.out.println(order.getPayType());
        
		return "manager_order_view";
	}
	
	
	
	@RequestMapping("invalid")
	@ResponseBody
	public AjaxResult invalid(long id){
		
		AjaxResult result = new AjaxResult(com.aplus.lk.clothes.bean.AjaxResult.Status.error, "删除失败", null);
		ManagerOrder order =managerOrderService.queryManagerOrderAndAddressById(id);
		if(order == null || order.getOrderStatus() != 0){
			result.setMessage("该订单不处于预约状态，取消失败");
		}else{
			
			managerOrderService.updateStatusById(id, 9);
			result.setMessage("取消成功");
			result.setStatus(com.aplus.lk.clothes.bean.AjaxResult.Status.success);
		}
		return result;
	}
	
	
	@RequestMapping("changeOrderEmployee")
	@ResponseBody
	public AjaxResult changeOrderEmployee(ReassignmentOrderRequest orderRequest,HttpServletRequest request){
		try {
			
		managerOrderService.changeOrderEmployee(Long.valueOf(orderRequest.getOrderId()), orderRequest.getToEmployeeId());
		/*ClothesAddress clothesAddress = clothesAddressService.queryClothesAddress(orderRequest.getClothesAddressId());
		String address = orderRequest.getDeliveryAddress();
		if(orderRequest.getChangeType()==0){
			clothesAddress.setDeliveryAddress(address);
		}else if(orderRequest.getChangeType()==1){
			clothesAddress.setSendAddress(address);
		}
		clothesAddress.setLatitude(orderRequest.getLatitude());
		clothesAddress.setLongitude(orderRequest.getLongitude());
		clothesAddressService.updateAddress(clothesAddress);*/
		
		/*ReassignmentRecord record = new ReassignmentRecord();
		record.setFromEmployeeId(orderRequest.getFromEmployeeId());
		Admin admin = adminService.getLoginAdmin();
		record.setOperator(admin.getName());
		record.setOrderId(orderRequest.getOrderId());
		record.setToEmployeeId(orderRequest.getToEmployeeId());
		record.setChangeType(orderRequest.getChangeType());
		managerOrderService.createRecord(record);*/
		} catch (Exception e) {
			logger.error("订单改派失败",e);
			return new AjaxResult(AjaxResult.Status.error, "订单改派失败");
		}
		return new AjaxResult(AjaxResult.Status.success, "订单改派成功");
	}
	
	@RequestMapping("changeOrderEmployeeView")
	public String changeOrderEmployeeView(long OrderId,HttpServletRequest request  ){
		ManagerOrder managerOrder=managerOrderService.queryManagerOrderAndAddressById(OrderId);
		request.setAttribute("order", managerOrder);
		
		request.setAttribute("addressCenterList", addressCenterService.queryAll());
		return "manager_order_reassignment_input";
		
	}
	
}
