package com.aplus.lk.clothes.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.RechargeCard;
import com.aplus.lk.clothes.mapper.RechargeCardMapper;
import com.aplus.lk.clothes.service.RechargeCardService;

@Service
public class RechargeCardServiceImpl implements RechargeCardService{

	@Autowired
	private RechargeCardMapper rechargeCardMapper;
	@Override
	public Pager query(Pager pager) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo()-1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		pager.setDataList(rechargeCardMapper.query(parameterMap));
		pager.setTotalRecords(rechargeCardMapper.queryCount());
		return pager;
	}
	@Override
	public void createRechargeCard(RechargeCard rechargeCard) {
		int num = rechargeCard.getNum();
		List<RechargeCard> list = new ArrayList<RechargeCard>(num);
		Date now = new Date();
		RechargeCard card = null;
		BigDecimal denomination = rechargeCard.getDenomination();
		BigDecimal price = rechargeCard.getPrice();
		BigDecimal usecondiction = rechargeCard.getUseCondition();
		BigDecimal discountprice = rechargeCard.getDiscountPrice();
		Date startTime = rechargeCard.getStartTime();
		Date invalidTime = rechargeCard.getInvalidTime();
		String remark = rechargeCard.getRemark();
		int rechargeType = rechargeCard.getRechargeType();
		if(rechargeType==2){
			denomination=discountprice;
		}
		int length=7;
		int time = rechargeCard.getTime();
		if(rechargeType==3||rechargeType==4){
			length=10;
			time=0;
		}
		 
		for(int i=0;i<num;i++){
			card=new RechargeCard(now, now, denomination, price,
					startTime,
					invalidTime, remark, rechargeType,time,usecondiction,discountprice);
			 String password = RandomStringUtils.randomNumeric(length);
			 card.setPassword(password);
			 card.setUseStatus(0);
			 card.setUseType(rechargeCard.getUseType());
			 card.setDarnNum(rechargeCard.getDarnNum());
			 card.setPassportNum(rechargeCard.getPassportNum());
			 card.setVisaPhotoNum(rechargeCard.getVisaPhotoNum());
			 list.add(card);
		}
		rechargeCardMapper.saveAll(list);
	}

}
