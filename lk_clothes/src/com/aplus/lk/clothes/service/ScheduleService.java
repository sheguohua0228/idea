package com.aplus.lk.clothes.service;

import javax.servlet.http.HttpServletRequest;
import com.aplus.lk.clothes.bean.Pager;

public interface ScheduleService {

	public Pager queryPager(Pager pager, String name , Integer status, Integer type);

	public void delete(long[] ids, HttpServletRequest request);
	
}
