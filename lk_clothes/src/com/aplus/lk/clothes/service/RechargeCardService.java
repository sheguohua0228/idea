package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.RechargeCard;

public interface RechargeCardService {

	public Pager query(Pager pager);
	
	public void createRechargeCard(RechargeCard rechargeCard);
}
