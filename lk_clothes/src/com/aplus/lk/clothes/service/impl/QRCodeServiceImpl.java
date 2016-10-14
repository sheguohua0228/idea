package com.aplus.lk.clothes.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplus.lk.clothes.entity.QRCode;
import com.aplus.lk.clothes.mapper.QRCodeMapper;
import com.aplus.lk.clothes.service.QRCodeService;

@Service
public class QRCodeServiceImpl implements QRCodeService{

	@Autowired
	private QRCodeMapper qRCodeMapper;
	
	@Override
	public List<QRCode> queryAll(Map<String, Object> params) {
		return qRCodeMapper.queryAll(params);
	}

	@Override
	public int queryCount(Map<String, Object> params) {
		return qRCodeMapper.queryCount(params);
	}

}
