package com.leyes.app.service;

import java.util.List;
import java.util.Map;

import com.leyes.app.dto.query.AccountFlowDto;
import com.leyes.app.message.IncomeOutDetailMessage;

public interface QuerySystemService {

	public void createAccountFlow(IncomeOutDetailMessage incomOutMessage) ;
	
	public Map<String,List<AccountFlowDto>> queryAccountFlow(String userId,int page)throws Exception;
}
