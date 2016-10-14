package com.aplus.lk.clothes.service;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.bean.MessageDto;
import com.aplus.lk.clothes.bean.MessageRequest;
import com.aplus.lk.clothes.bean.Pager;

public interface IMessageSerivce {

	void save(MessageRequest message);
	
	List<Map<String,Object>> queryAllPhone();
	
	Pager query(Pager pager,Integer msgType);
	
	MessageDto messageDetail(long messageId);
}
