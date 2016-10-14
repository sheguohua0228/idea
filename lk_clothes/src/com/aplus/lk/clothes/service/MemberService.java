package com.aplus.lk.clothes.service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Member;

public interface MemberService {
	
	public Member queryById(String id);
	
	public Member queryByPhoneNo(String phoneNo);
	
	public Pager queryPrintOrder(Pager pager,String memberId);
	
	public void createMember(Member member);
}
