package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.Pager;

public interface IAppraiseSerivce {

	public Pager query(Pager pager,String phoneNumber,String orderNumber);
}
