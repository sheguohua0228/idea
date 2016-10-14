package com.aplus.lk.clothes.mapper;

import java.util.List;

import com.aplus.lk.clothes.entity.WashStatus;

public interface WashStatusMapper {
	
	public List<WashStatus> queryWashStatusByOrderId(long orderId);
	
	public int save(WashStatus washStatus);
	
}
