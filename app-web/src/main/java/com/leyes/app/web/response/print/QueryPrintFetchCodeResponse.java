package com.leyes.app.web.response.print;

import java.util.ArrayList;
import java.util.List;

import com.leyes.app.dto.print.QueryPrintFetchCodeDto;

public class QueryPrintFetchCodeResponse {

	private List<QueryPrintFetchCodeDto> list;

	public QueryPrintFetchCodeResponse(){
		if(list==null)
			list=new ArrayList<QueryPrintFetchCodeDto>();
	}
	public void addData(QueryPrintFetchCodeDto dto){
		list.add(dto);
	}
	public List<QueryPrintFetchCodeDto> getList() {
		return list;
	}
	
	
}
