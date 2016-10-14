package com.aplus.lk.clothes.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Member;
import com.aplus.lk.clothes.mapper.MemberMapper;
import com.aplus.lk.clothes.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Member queryById(String id) {
		return memberMapper.queryById(id);
	}

	@Override
	public Member queryByPhoneNo(String phoneNo) {
		return memberMapper.queryByPhoneNo(phoneNo);
	}
	@Override
	public Pager queryPrintOrder(Pager pager,String memberId){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("firstResult", (pager.getPageNo()-1)*pager.getPageSize());
		parameterMap.put("maxResult", pager.getPageSize());
		parameterMap.put("memberId", memberId);
		pager.setDataList(memberMapper.queryPrintOrder(parameterMap));
		pager.setTotalRecords(memberMapper.queryPrintOrderCount(memberId));
		return pager;
	}

	@Override
	public void createMember(Member member) {
		if(member!=null){
			memberMapper.createMember(member);
		}
	}
	
}
