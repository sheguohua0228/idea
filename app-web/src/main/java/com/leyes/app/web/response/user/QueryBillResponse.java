package com.leyes.app.web.response.user;

import java.io.Serializable;
import java.util.List;

import com.leyes.app.dto.query.AccountFlowDto;

public class QueryBillResponse implements Serializable{

	private String date;
	
	private List<AccountFlowDto> list;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<AccountFlowDto> getList() {
		return list;
	}

	public void setList(List<AccountFlowDto> list) {
		this.list = list;
	}
	
	
}
