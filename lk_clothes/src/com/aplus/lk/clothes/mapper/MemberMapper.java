package com.aplus.lk.clothes.mapper;

import java.util.List;
import java.util.Map;

import com.aplus.lk.clothes.entity.Member;
import com.aplus.lk.clothes.entity.PrintOrder;

public interface MemberMapper {
	
	public Member queryById(String id);
	
	public Member queryByPhoneNo(String phoneNo);
	
	public List<PrintOrder> queryPrintOrder(Map<String, Object> parameterMap);
	
	public int queryPrintOrderCount(String memberId);
	
	public void createMember(Member member);
	
	public int updateAccount(Member member);
}
