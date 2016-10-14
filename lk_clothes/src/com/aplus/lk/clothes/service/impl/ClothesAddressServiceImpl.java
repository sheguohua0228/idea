package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.entity.ClothesAddress;
import com.aplus.lk.clothes.entity.ClothesOrder;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.mapper.ClothesAddressMapper;
import com.aplus.lk.clothes.mapper.ClothesOrderMapper;
import com.aplus.lk.clothes.mapper.EmployeeMapper;
import com.aplus.lk.clothes.service.ClothesAddressService;
import com.aplus.lk.clothes.utils.JPushUtils;

@Service
public class ClothesAddressServiceImpl implements ClothesAddressService{

	@Autowired
	private ClothesAddressMapper clothesAddressMapper;
	@Autowired
	private ClothesOrderMapper clothesOrderMapper;
	@Autowired
	private EmployeeMapper employeeMapper; 
	
	@Override
	public void save(ClothesAddress clothesAddress) {
		clothesAddressMapper.save(clothesAddress);
	}

	@Override
	public ClothesAddress queryClothesAddressByUserId(String userId) {
		return clothesAddressMapper.queryClothesAddressByUserId(userId);
	}

	@Override
	public void update(ClothesAddress clothesAddress, String employeeId, long clothesOrderId) {
		Employee employee = employeeMapper.queryById(employeeId);
		ClothesOrder clothesOrder = clothesOrderMapper.queryClothesOrderById(clothesOrderId);
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("orderNumber", clothesOrder.getOrderNumber());
		extras.put("type", "0");
		JPushUtils.sendPushToAssistant("乐E下", "你好:"+employee.getRealName()+",洗衣订单为:"+clothesOrder.getOrderNumber(), null, employee.getRegistrationID(),extras);
		clothesAddressMapper.update(clothesAddress);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("id", clothesOrderId);
		parameterMap.put("employeeId", employeeId);
		clothesOrderMapper.updateEmployeeIdById(parameterMap);
	}

	@Override
	public void updateAddress(ClothesAddress clothesAddress) {
		clothesAddressMapper.update(clothesAddress);
	}
	@Override
	public ClothesAddress queryClothesAddress(long clothesAddressId){
		return clothesAddressMapper.queryClothesAddressById(clothesAddressId);
	}
}
