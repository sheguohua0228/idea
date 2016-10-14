package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.Map;

import jxl.read.biff.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.ClothesOrder;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.ManagerOrder;
import com.aplus.lk.clothes.entity.ReassignmentRecord;
import com.aplus.lk.clothes.mapper.EmployeeMapper;
import com.aplus.lk.clothes.mapper.ManagerOrderMapper;
import com.aplus.lk.clothes.service.ManagerOrderService;
import com.aplus.lk.clothes.utils.JPushUtils;
@Service
public class ManagerOrderServiceImpl implements ManagerOrderService{

	@Autowired
	private ManagerOrderMapper managerOrderMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	@Override
	public Pager queryPager(Pager pager,  String orderNumber,
			Integer status, Integer orderStatus, Integer payStatus,
			String phoneNumber) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		parameterMap.put("orderNumber", orderNumber);
		parameterMap.put("status", status);
		parameterMap.put("orderStatus", orderStatus);
		parameterMap.put("payStatus", payStatus);
		parameterMap.put("phoneNumber", phoneNumber);
		parameterMap.put("firstResult",
				(pager.getPageNo() - 1) * pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());

		pager.setDataList(managerOrderMapper.queryByLimit(parameterMap));
		pager.setTotalRecords(managerOrderMapper.queryCount(parameterMap));

		return pager;
	}
	
	@Override
	public ManagerOrder queryManagerOrderAndAddressById(long id) {
		ManagerOrder order = managerOrderMapper.queryManagerOrderById(id);
		
		return order;
	}

	@Override
	public Employee queryEmployeebyID(String  employeeId) {
		// TODO Auto-generated method stub
		Employee employee=managerOrderMapper.queryEmployessById(employeeId);
		return employee;
	}



	@Override
	public void updateStatusById(long id,long status) {
		// TODO Auto-generated method stub
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", id);
		parameterMap.put("status", status);
		managerOrderMapper.updateStatusById(parameterMap);
	}
	
	@Override
	public void changeOrderEmployee(long orderId, String employeeId) {
		System.out.println("x");
		
		Employee employee = employeeMapper.queryById(employeeId);
		

		ManagerOrder managerOrder = managerOrderMapper.queryManagerOrderById(orderId);
		
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("orderNumber", managerOrder.getOrderNumber());
		
		extras.put("type","3");
		JPushUtils.sendPushToAssistant("乐E下", "你好:"+employee.getRealName()+",洗衣订单为:"+managerOrder.getOrderNumber(), null, employee.getRegistrationID(),extras);

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", orderId);
		parameterMap.put("employeeId", employeeId);
		managerOrderMapper.updateEmployeeIdById(parameterMap);
	}

	@Override
	public void createRecord(ReassignmentRecord record) {
		// TODO Auto-generated method stub
		managerOrderMapper.createRecord(record);
	}
	
	

}
